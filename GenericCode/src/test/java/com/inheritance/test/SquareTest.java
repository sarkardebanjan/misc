package com.inheritance.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SquareTest {

    @InjectMocks
    Square square;

    @Test
    public void getPersistProcessor_test() {
        square.getPersistProcessor();
    }

    @Test
    public void setUri_test() {
        square.setUri();
        Assertions.assertEquals("SquareUri", square.getUri());
    }

    @Test
    public void process_test() {
        square.getPersistProcessor().process();
    }
}
