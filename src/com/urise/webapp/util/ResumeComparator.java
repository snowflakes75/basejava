package com.urise.webapp.util;

import com.urise.webapp.model.Resume;

import java.util.Comparator;

public class ResumeComparator {
    public static Comparator<Resume> resumeComparator = Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);
}
