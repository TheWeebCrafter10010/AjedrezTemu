package Logica.Juego;

import Interfaz.Casilla;
import Logica.Piezas.Pieza;

import java.util.ArrayList;

public interface ITablero {

    void iniciarTablero();
    void pintarCasillas(Pieza piezaOrigen);
    void moverPieza(Casilla origen, Casilla destino);
    ArrayList<Pieza> getPiezasEnTablero();
    void setClickListener(IClickListener clickListener);
}
