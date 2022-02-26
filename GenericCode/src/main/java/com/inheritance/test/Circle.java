package com.inheritance.test;

public class Circle extends Shape {

    private class PersistProcessor implements Processor {

        @Override
        public void process() {
            System.out.println("Process method of circle executed");
        }
    }

    @Override
    protected Processor getPersistProcessor() {
        return new PersistProcessor();
    }

    @Override
    public void setUri() {
        this.uri = "CircleUri";
    }
}
