package com.resume.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractSection implements Serializable {
    protected Object information;

    public AbstractSection(Object information) {
        Objects.requireNonNull(information,"information not be null");
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractSection that = (AbstractSection) o;
        return information.equals(that.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(information);
    }

    public abstract Object getInformation();

    public abstract void setInformation(Object setInformation);




}
