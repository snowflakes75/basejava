package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class
        , SortedArrayStorageTest.class
        , ListStorageTest.class
        , MapUuidStorageTest.class
        , MapResumeStorageTest.class
        , FileStorageOOSTest.class
        , PathStorageOOSTest.class
        , JsonPathStorageTest.class
        , XmlPathStorageTest.class
        , DataPathStorageTest.class
})
public class AllStorageTest {
}
