package com.example.assegnamento;

import org.junit.jupiter.api.*;

import static com.example.assegnamento.PersonnelpageController.isValidDate;
import static com.example.assegnamento.PersonnelpageController.roundToTwoDecimalPlaces;
import static org.junit.jupiter.api.Assertions.*;

class PersonnelpageControllerTest {
    @Test
    void testRoundToTwoDecimalPlaces() {
        double num = 15.245;
        double expected = 15.25;
        double actual = roundToTwoDecimalPlaces(num);
        assertEquals(expected, actual);
    }
    @Test
    void testRoundToTwoDecimalPlaces2() {
        double num = 0.000000001;
        double expected = 0;
        double actual = roundToTwoDecimalPlaces(num);
        assertEquals(expected, actual);
    }
    @Test
    void testRoundToTwoDecimalPlaces3() {
        double num = 15.244;
        double expected = 15.24;
        double actual = roundToTwoDecimalPlaces(num);
        assertEquals(expected, actual);
    }
    @Test
    void testIsValidDate() {
        String dateStr = "20-10-2020";
        boolean expected = true;
        boolean actual = isValidDate(dateStr);
        assertEquals(expected, actual);
    }
    @Test
    void testIsValidDate4() {
        String dateStr = "20/10/2020";
        boolean expected = false;
        boolean actual = isValidDate(dateStr);
        assertEquals(expected, actual);
    }

    @Test
    void testIsValidDate2() {
        String dateStr = "200/10/2020";
        boolean expected = false;
        boolean actual = isValidDate(dateStr);
        assertEquals(expected, actual);
    }
    @Test
    void testIsValidDate3() {
        String dateStr = "a";
        boolean expected = false;
        boolean actual = isValidDate(dateStr);
        assertEquals(expected, actual);
    }
}

