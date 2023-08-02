package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section<String> {
    List<String> textList = new ArrayList<>();

    @Override
    public void addContent(String content) {
        textList.add(content);
    }

    @Override
    public String toString() {
        return textList.toString();
    }
}
