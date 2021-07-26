package com.resume.webapp.util;

import com.resume.webapp.model.AbstractSection;
import com.resume.webapp.model.Resume;
import com.resume.webapp.model.TextSection;
import org.junit.Assert;
import org.junit.Test;

import static com.resume.webapp.storage.AbstractStorageTest.resume1;


public class JsonParserTest {
    @Test
    public void testResume() {
        String json=JsonParser.write(resume1,Resume.class);
        System.out.println(json);
        Resume resume=JsonParser.read(json,Resume.class);
        Assert.assertEquals(resume1,resume);
    }

    @Test
    public void write(){
        AbstractSection abstractSection=new TextSection("Commandos-2");
        String json = JsonParser.write(abstractSection,AbstractSection.class);
        System.out.println(json);
        AbstractSection abstractSection1=JsonParser.read(json,AbstractSection.class);
        Assert.assertEquals(abstractSection,abstractSection1);
    }
}
