package com.example.Testing.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalclateTest {


    @Test
    void testAddition_PositiveNumbers() {
        Calculate calc = new Calculate();
        int result = calc.addition(10, 5);
        assertEquals(15, result);
    }


    @Test
    void testAddition_Negumbers() {
        Calculate calc = new Calculate();
        int result = calc.addition(-10, 5);
        assertEquals(-5, result);
    }

    @Test
    void testAddition_Negumbers2() {
        Calculate calc = new Calculate();
        int result = calc.addition(-10, 5);
        assertNotEquals(5, result);
    }


    @Test
    void substartionctest()
    {
        Calculate cc = new Calculate();
        int calc = cc.sub(10,5);
        assertEquals(5 , calc);
    }


    @Test

    void subneg()
    {

        Calculate cn = new Calculate();
        int res = cn.sub(-10,5);

        assertNotEquals(5,res);

    }

}
