package com.inheritance.test;

public abstract class Shape {

    protected String uri;

    protected Processor persistProcessor = getPersistProcessor();

    protected abstract Processor getPersistProcessor();

    public abstract void setUri();

    public String getUri() {
        return this.uri;
    }
}
