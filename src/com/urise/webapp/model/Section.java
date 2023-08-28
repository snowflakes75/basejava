package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Section<T> implements Serializable {
    public abstract void addContent(T content);

    public abstract T getContent(int i);

    public abstract int size();

}
