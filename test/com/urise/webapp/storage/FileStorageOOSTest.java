package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.ObjectStreamStorage;

import java.io.File;

public class FileStorageOOSTest extends AbstractStorageTest{
    public FileStorageOOSTest() {
        super(new FileStorage(new File(STORAGE_DIR),  new ObjectStreamStorage()));
    }
}
