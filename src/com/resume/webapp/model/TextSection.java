package com.resume.webapp.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private String content;

    public TextSection() {
    }

    public TextSection(String content) {
        Objects.requireNonNull(content, "content must not be null");
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public String toHtml() {
        return content;
        //  return "<dd><textarea name=\"${typeSections.name()}\" cols=\"50\"rows=\"3\">"+content+"</textarea></dd>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
