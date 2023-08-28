package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class TextSection extends Section<String> {
    private static final long serialVersionUID = 1L;
    private String text;

    public TextSection() {
    }

    public TextSection(String text) {
        this.text = text;
    }

    @Override
    public void addContent(String content) {
        this.text = content;
    }

    @Override
    public String getContent(int i) {
        return text;
    }

    @Override
    public int size() {
        return 1;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return text;
    }

}
