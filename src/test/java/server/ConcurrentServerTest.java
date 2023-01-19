package server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ConcurrentServerTest {
    @Test
    public void testAddOrdVendita() {
        ConcurrentServer server = ConcurrentServer.getInstance();
        server.addOrdVendita(1);
        int expectedValue = 1;
        assertEquals(expectedValue, server.getFirstOrdVendita());
    }
    @Test
    public void testGetFirstOrdVendita() {
        ConcurrentServer server = ConcurrentServer.getInstance();
        server.addOrdVendita(2);
        int expectedValue = 2;
        assertEquals(expectedValue, server.getFirstOrdVendita());
    }
    @Test
    public void testRemoveOrdVendita() {
        ConcurrentServer server = ConcurrentServer.getInstance();
        server.addOrdVendita(3);
        server.removeOrdVendita(3);
        assertTrue(server.ordVendita.isEmpty());
    }
    @Test
    public void testAddImpiegato() {
        ConcurrentServer server = ConcurrentServer.getInstance();
        server.addImpiegato(4);
        int expectedValue = 4;
        assertEquals(expectedValue, server.impiegatiOnline.get(0));
    }
    @Test
    public void testRemoveImpiegato() {
        ConcurrentServer server = ConcurrentServer.getInstance();
        server.addImpiegato(5);
        server.removeImpiegato(5);
        assertTrue(server.impiegatiOnline.isEmpty());
    }
}