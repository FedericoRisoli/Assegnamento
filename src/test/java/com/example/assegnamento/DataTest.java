package com.example.assegnamento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestData {
    @Test
    public void testGetInstance() {
        Data data = Data.getInstance();
        assertNotNull(data);
    }
    @Test
    public void testSetId() {
        Data data = Data.getInstance();
        data.Setid(1);
        assertEquals(data.GetId(), 1);
    }
    @Test
    public void testSetRole() {
        Data data = Data.getInstance();
        data.SetRole("admin");
        assertEquals(data.Getrole(), "admin");
    }
    @Test
    public void testSetUsername() {
        Data data = Data.getInstance();
        data.Setusername("Paolo");
        assertEquals(data.Getusername(), "Paolo");
    }
    @Test
    public void testSetPromo() {
        Data data = Data.getInstance();
        data.SetPromo(true);
        assertTrue(data.GetPromo());
    }
    @Test
    public void testReset() {
        Data data = Data.getInstance();
        data.Setid(1);
        data.SetRole("admin");
        data.Setusername("Paolo");
        data.SetPromo(true);
        data.reset();
        assertEquals(data.GetId(), -100);
        assertEquals(data.Getrole(), "");
        assertEquals(data.Getusername(), "");
        assertFalse(data.GetPromo());
    }
}
