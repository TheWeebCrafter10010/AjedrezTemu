package Logica.Juego;

import Interfaz.Casilla;
import Logica.Piezas.Pieza;

import java.awt.*;

public class MovimientoController {
    private Casilla casillaAnteriorClicada;//Guardar la casilla anterior para poder comparar con la nueva y asi ejecutar el movimiento
    private AccionMouse estadoActual= AccionMouse.NINGUNA_ACCION;

    public MovimientoController(){
        casillaAnteriorClicada = null;
    }

    public AccionMouse clicarCasilla(Casilla casillaActualClicada){

        if(casillaAnteriorClicada == null&& casillaActualClicada.tengoPieza()){
            casillaAnteriorClicada = casillaActualClicada;
            estadoActual = AccionMouse.PRIMERA_SELECCION;
            return estadoActual;
        }

        if(estadoActual!=AccionMouse.PRIMERA_SELECCION) return AccionMouse.NINGUNA_ACCION;

        if(casillaActualClicada == casillaAnteriorClicada){
            casillaAnteriorClicada = null;
            estadoActual = AccionMouse.NINGUNA_ACCION;
            return AccionMouse.SELECCION_IGUAL;
        }

        estadoActual = AccionMouse.INTENTO_MOVIMIENTO;
        return estadoActual;
    }

    public Casilla getCasillaAnteriorClicada() {
        return casillaAnteriorClicada;
    }
    public void resetearCasillaAnterior(){
        casillaAnteriorClicada = null;
    }
    public void setEstadoActual(AccionMouse estadoActual){
        this.estadoActual = estadoActual;
    }
}
