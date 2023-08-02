package com.urise.webapp.model;

import java.util.Objects;

public class TextSection extends Section<String> {
    private String text;

    public TextSection(String text) {
        this.text = text;
    }

    @Override
    public void addContent(String content) {
        this.text = content;
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
