package Logica.Juego;

import Interfaz.Casilla;
import Logica.Piezas.Pieza;

import java.awt.event.MouseEvent;

public class JuegoController implements IClickListener {
    private int turnos;
    private int jugadorActual;
    private MovimientoController movController;
    private ITablero tablero;

    public JuegoController(ITablero tablero,MovimientoController movimientoController) {
        this.movController = movimientoController;
        this.tablero = tablero;
    }

    @Override
    public void onClick(Casilla casillaClicada) {
        ejecutarAccion(casillaClicada);
    }

    public void iniciarJuego() {
        tablero.iniciarTablero();
        generarTodosLosMovimientos();
        turnos = 0;
        jugadorActual = 1; // El jugador blanco comienza
    }

    public void cambiarTurno() {
        turnos++;
    }
    private void ejecutarAccion(Casilla casillaClicada) {
        AccionMouse accion = movController.clicarCasilla(casillaClicada);
        switch (accion) {
            case PRIMERA_SELECCION:
                Pieza piezaSeleccionada = casillaClicada.getPieza();
                tablero.pintarCasillas(piezaSeleccionada);
                break;
            case SELECCION_IGUAL:
                tablero.pintarCasillas(casillaClicada.getPieza());
                break;
            case INTENTO_MOVIMIENTO:
                Casilla casillaAnterior = movController.getCasillaAnteriorClicada();
                Pieza piezaAMover = casillaAnterior.getPieza();
                if(piezaAMover.getMovimientosValidos().contains(casillaClicada)) {
                    tablero.pintarCasillas(piezaAMover);
                    tablero.moverPieza(casillaAnterior, casillaClicada);
                    generarTodosLosMovimientos();
                    cambiarTurno();
                    movController.resetearCasillaAnterior();
                }else{
                    tablero.pintarCasillas(piezaAMover);
                    movController.setEstadoActual(AccionMouse.NINGUNA_ACCION);
                    movController.resetearCasillaAnterior();
                }

                break;
            case NINGUNA_ACCION:

                break;
        }
    }

    private void generarTodosLosMovimientos(){
        for(Pieza p: tablero.getPiezasEnTablero()){
            p.resetMovimientosValidos();
            p.obtenerMovimientos();
        }
    }


}
