package com.resume.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageSearchKeyUuidTest.class,
        MapStorageSearchKeyResumeTest.class,
        FileStorageTest.class,
        PathStorageTest.class
})
public class AllStorageTest {
}
