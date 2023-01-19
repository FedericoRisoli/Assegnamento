package com.example.assegnamento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class CarrelloTest {

    //test non davvero molto utili
    private Carrello carrello;
    private ObservableList<Vini> lista;
    private ObservableList<Vini> nondisp;
    private ObservableList<String> ordine;
    private ObservableList<String> ordinenondisp;
    @BeforeEach
    void setUp() throws Exception {
        carrello = Carrello.getIstance();
        lista = FXCollections.observableArrayList();
        nondisp = FXCollections.observableArrayList();
        ordine = FXCollections.observableArrayList();
        ordinenondisp = FXCollections.observableArrayList();
    }
    @Test
    void testGetCarrello() {
        carrello.setCarrello(lista);
        assertEquals(lista, carrello.getCarrello());
    }
    @Test
    void testGetOrdine() {
        carrello.addOrdine("test");
        ordine.add("test");
        assertEquals(ordine, carrello.getOrdine());
    }
    @Test
    void testClearOrdine() {
        carrello.addOrdine("test");
        carrello.clearOrdine();
        assertEquals(ordine, carrello.getOrdine());
    }
    @Test
    void testGetNondisp() {
        carrello.setNondisp(nondisp);
        assertEquals(nondisp, carrello.getNondisp());
    }
    @Test
    void testGetOrdineNonDisp() {
        carrello.addOrdineNonDisp("test");
        ordinenondisp.add("test");
        assertEquals(ordinenondisp, carrello.getOrdineNonDisp());
    }
    @Test
    void testClearOrdineNonDisp() {
        carrello.addOrdineNonDisp("test");
        carrello.clearOrdineNonDisp();
        assertEquals(ordinenondisp, carrello.getOrdineNonDisp());
    }
}