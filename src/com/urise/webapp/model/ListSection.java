package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends Section<String> {
    private static final long serialVersionUID = 1L;
    private List<String> textList = new ArrayList<>();

    public ListSection() {
    }

    @Override
    public void addContent(String content) {
        textList.add(content);
    }

    @Override
    public String getContent(int i) {
        return textList.get(i);
    }

    @Override
    public int size() {
        return textList.size();
    }

    @Override
    public String toString() {
        return textList.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return textList.equals(that.textList);

    }

    @Override
    public int hashCode() {
        return textList.hashCode();
    }
}
