package com.training.domains;

import java.util.List;

@FunctionalInterface
public interface MyConsumer<T> {

    public void show(List<T> list);

}
