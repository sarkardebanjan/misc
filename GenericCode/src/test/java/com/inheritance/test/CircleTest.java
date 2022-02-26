package com.inheritance.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CircleTest {

    @InjectMocks
    Circle circle;

    @Test
    public void getPersistProcessor_test() {
        circle.getPersistProcessor();
    }

    @Test
    public void setUri_test() {
        circle.setUri();
        Assertions.assertEquals("CircleUri", circle.getUri());
    }

    @Test
    public void process_test() {
        circle.getPersistProcessor().process();
    }
}
