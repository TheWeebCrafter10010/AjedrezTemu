
package Logica.Piezas;

import Interfaz.Casilla;
import Interfaz.PanelCasillas;
import Recursos.Prueba;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Peon extends Pieza{
    private boolean capturaEnPaso = false;

    private ArrayList<Casilla> casillasAlPaso = new ArrayList<>();
    private int direccion;
    public Peon(Casilla casilla, boolean esBlancha) {
        super(casilla, esBlancha);
        direccion = (getJugador() == 1) ? -1 : (getJugador() == 2) ? 1 : 0;
    }

    @Override
    public ImageIcon ponerImagen(boolean esBlanca) {
        if(esBlanca){
            return Prueba.imagenesPiezas.get(1);
        }
        return Prueba.imagenesPiezas.get(-1);
    }

    @Override
    public ArrayList<Casilla> obtenerMovimientos() {
        ArrayList<Casilla> casillas = getListaMovimientos();
        casillas.clear();
        if (direccion == 0) return casillas;

        if(isCapturaEnPaso()) {
            System.out.println("Captura en paso activada");
            for(Casilla c: casillasAlPaso) {
                if(!c.tengoPieza()){
                    System.out.println("Agregando casilla de captura en paso: ");
                    casillas.add(c);
                }

            }// Reseteamos la captura en paso después de agregar las casillas
        }


        if(getPrimerMovimiento()) {
            // Si es el primer movimiento, puede avanzar dos casillas
            int y = getCoordY() + 2 * direccion;
            if (y >= 0 && y < 8) {
                if (!PanelCasillas.casillas[y][getCoordX()].tengoPieza()) {
                    casillas.add(PanelCasillas.casillas[y][getCoordX()]);
                }
            }
        }

        int y = getCoordY() + direccion;
        if (y < 0 || y >= 8) {
            System.out.println("llegue hasta aqui, no hay movimientos");
            return casillas;
        }

        int x = getCoordX();
        int[] dx = {1, -1};

        for (int d : dx) {
            int nx = x + d;
            if (nx >= 0 && nx < 8) {
                int check = detectarPiezaCasilla(PanelCasillas.casillas[y][nx]);
                if (check == -1) {
                    casillas.add(PanelCasillas.casillas[y][nx]);
                }
                if (check <= 0) {
                    PanelCasillas.casillas[y][nx].actualizarAmenaza(this);
                }
            }
        }

        if (!PanelCasillas.casillas[y][x].tengoPieza()) {
            casillas.add(PanelCasillas.casillas[y][x]);
        }

        return casillas;
    }

    @Override
    public void moverA(Casilla destino) {
        int y = getCoordY();
        boolean primerMovimiento = getPrimerMovimiento();
        if(capturaEnPaso){
            for(Casilla c : casillasAlPaso) {
                if (c.equals(destino)) {
                    // Captura en paso
                    int destinoY = destino.getCoordY()+(direccion*-1);
                    Casilla casillaCapturada = PanelCasillas.casillas[destinoY][destino.getCoordX()];
                    casillaCapturada.remove(casillaCapturada.getPieza());
                    casillasAlPaso.clear();
                    break;
                }
            }
        }
        super.moverA(destino);


        if(primerMovimiento){
            int cantidadCasillasAvanzadas = Math.abs(destino.getCoordY() - y);
            avisarAlDun(cantidadCasillasAvanzadas);
        }
        capturaEnPaso = false;// Reseteamos la captura en paso después de mover


    }

    @Override
    public void resetMovimientosValidos() {
        super.resetMovimientosValidos();
        casillasAlPaso.clear();
        capturaEnPaso = false;
    }

    private void avisarAlDun(int casillasAvanzadas) {

        if (casillasAvanzadas == 2) {

            int[] dx = {1, -1};
            int x = getCoordX();

            for (int d : dx) {
                int nx = x + d;
                if (nx >= 0 && nx < 8) {
                    Casilla casillaAmenazada = PanelCasillas.casillas[getCoordY()][nx];
                    if (casillaAmenazada.tengoPieza()) {
                        Pieza piezaDeAlado = casillaAmenazada.getPieza();
                        if (piezaDeAlado.getJugador() != getJugador()&& piezaDeAlado instanceof Peon) {
                            Peon p = (Peon) piezaDeAlado;
                            System.out.println("Captura en paso activada por: " + p.tipoPieza());
                            p.setCapturaEnPaso(true);
                            p.getCasillasAlPaso().add(PanelCasillas.casillas[p.getCoordY()+p.getDireccion()][p.getCoordX()+(d*-1)]);

                        }
                    }
                }
            }

        }
    }

    public ArrayList<Casilla> getCasillasAlPaso() {
        return casillasAlPaso;
    }


    public boolean isCapturaEnPaso() {
        return capturaEnPaso;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setCapturaEnPaso(boolean capturaEnPaso) {
        this.capturaEnPaso = capturaEnPaso;
    }

    @Override
    public String tipoPieza() {
        return "Peon";
    }
    
    
    
}
