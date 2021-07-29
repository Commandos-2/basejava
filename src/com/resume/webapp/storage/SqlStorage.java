package com.resume.webapp.storage;

import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.*;
import com.resume.webapp.storage.sql.SqlHelper;
import com.resume.webapp.util.JsonParser;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import static com.resume.webapp.model.SectionType.EDUCATION;
import static com.resume.webapp.model.SectionType.EXPERIENCE;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.sqlHelp("DELETE FROM resume", (ps) -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name=? WHERE uuid=?;");) {
                        String uuid = r.getUuid();
                        ps.setString(1, r.getFullName());
                        ps.setString(2, uuid);
                        if (ps.executeUpdate() == 0) {
                            throw new NotExistStorageException(uuid);
                        }
                    }
                    delete(conn, r, "contact");
                    delete(conn, r, "section");
                    delete(conn, r, "organization_section");
                    saveContacts(conn, r);
                    saveSections(conn, r);
                    saveOrganizationSections(conn, r);
                    return null;
                }
        );
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    saveContacts(conn, r);
                    saveSections(conn, r);
                    saveOrganizationSections(conn, r);
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE uuid=? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact WHERE resume_uuid=? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(rs, resume);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT *  FROM section WHERE resume_uuid=?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, resume);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT *  FROM organization_section WHERE resume_uuid=?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addOrganizationSection(rs, resume, conn);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.sqlHelp("DELETE FROM resume WHERE uuid=?", (ps) -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> map = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid ")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    map.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume resume = map.get(rs.getString("resume_uuid"));
                    addContact(rs, resume);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume resume = map.get(rs.getString("resume_uuid"));
                    addSection(rs, resume);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT *  FROM organization_section ")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume resume = map.get(rs.getString("resume_uuid"));
                    addOrganizationSection(rs, resume, conn);
                }
            }
            return new ArrayList<>(map.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.sqlHelp("SELECT count(*) FROM resume", (ps) -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private static void saveContacts(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactsType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            ContactsType type = ContactsType.valueOf(rs.getString("type"));
            resume.addContact(type, value);
        }
    }

    private static void addSection(ResultSet rs, Resume resume) throws SQLException {
        String text = rs.getString("value");
        if (text != null) {
            SectionType sectionType = SectionType.valueOf(rs.getString("type"));
            resume.addSection(sectionType, JsonParser.read(text, AbstractSection.class));
        }
    }

    private void addOrganizationSection(ResultSet rs, Resume resume, Connection conn) throws SQLException {
        String name = rs.getString("name");
        if (name != null) {
            List<Organization.Position> list = new ArrayList<>();
            try (PreparedStatement ps = conn.prepareStatement("SELECT *  FROM position WHERE id_organization_section=?")) {
                ps.setString(1, rs.getString("id"));
                ResultSet rs2 = ps.executeQuery();
                while (rs2.next()) {
                    list.add(new Organization.Position(rs2.getDate("initial_date").toLocalDate(), rs2.getDate("end_date").toLocalDate(), rs2.getString("heading"), rs2.getString("value")));
                }
            }
            Organization organization = new Organization(name, rs.getString("url"), list);
            SectionType sectionType = SectionType.valueOf(rs.getString("type"));
            OrganizationSection organizationSection = (resume.getSections().containsKey(sectionType)) ? (OrganizationSection) resume.getSections(sectionType) : new OrganizationSection(new ArrayList<Organization>());
            organizationSection.getOrganizations().add(organization);
            resume.addSection(sectionType, organizationSection);
        }
    }

    private void saveSections(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> e : r.getSections().entrySet()) {
                SectionType sectionType = e.getKey();
                if (sectionType == EXPERIENCE || sectionType == EDUCATION) {
                    continue;
                }
                ps.setString(1, r.getUuid());
                ps.setString(2, sectionType.name());
                ps.setString(3, JsonParser.write(e.getValue(), AbstractSection.class));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void saveOrganizationSections(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO organization_section (resume_uuid, type, name, url, id) VALUES (?,?,?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> e : r.getSections().entrySet()) {
                SectionType sectionType = e.getKey();
                if (sectionType == EXPERIENCE || sectionType == EDUCATION) {
                    for (Organization organization : ((OrganizationSection) e.getValue()).getOrganizations()) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, sectionType.name());
                        ps.setString(3, organization.getHomePage().getName());
                        ps.setString(4, organization.getHomePage().getUrl());
                        String id = UUID.randomUUID().toString();
                        ps.setString(5, id);
                        ps.addBatch();
                        ps.executeBatch();
                        try (PreparedStatement ps2 = conn.prepareStatement("INSERT INTO position (id_organization_section, heading, value,initial_date,end_date) VALUES (?,?,?,?,?)")) {
                            for (Organization.Position position : organization.getPositions()) {
                                ps2.setString(1, id);
                                ps2.setString(2, position.getHeading());
                                ps2.setString(3, position.getText());
                                ps2.setDate(4, Date.valueOf(position.getInitialDate()));
                                ps2.setDate(5, Date.valueOf(position.getEndDate()));
                                ps2.addBatch();
                            }
                            ps2.executeBatch();
                        }
                    }
                }
            }
        }
    }

    private void delete(Connection conn, Resume r, String target) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM " + target + " WHERE resume_uuid=?")) {
            ps.setString(1, r.getUuid());
            ps.execute();
        }
    }
}