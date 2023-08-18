package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.ObjectStreamStorage;

public class PathStorageOOSTest extends AbstractStorageTest{
    public PathStorageOOSTest() {
        super(new PathStorage(STORAGE_DIR,  new ObjectStreamStorage()));
    }
}
