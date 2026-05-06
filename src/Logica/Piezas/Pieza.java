
package Logica.Piezas;

import Interfaz.Casilla;
import Recursos.ImagePieza;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public abstract class Pieza extends JLabel{
    
    private boolean canMove;
    private boolean beenClicked;
    private Casilla casillaActual;
    private int coordX;
    private int coordY;
    private ArrayList<Casilla> movimientosValidos= new ArrayList<>();
    private boolean primerMovimiento = true;
    
    private int jugador;


    /*
     * Constructor de la clase Pieza.
     * @param casilla La casilla en la que se coloca la pieza.
     * @param esBlancha Indica si la pieza es blanca (true) o negra (false).
     */

    public Pieza(Casilla casilla, boolean esBlancha){
        this.setBounds(6, 6, 60, 60);
        ImagePieza i = (ImagePieza)ponerImagen(esBlancha);
        setIcon(i);
        setJugador(i.getJugador());
        actualizarDatos(casilla);
        beenClicked = false;
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        casilla.add(this,BorderLayout.CENTER);
        casilla.revalidate();
        casilla.repaint();
        this.setVisible(true);
    }
    
    public void actualizarDatos(Casilla casilla){
        casillaActual = casilla;
        coordX = casilla.getCoordX();
        coordY = casilla.getCoordY(); 
    }
    
    public abstract ImageIcon ponerImagen(boolean esBlanca);
    
    public abstract ArrayList<Casilla> obtenerMovimientos();//AQUI SE DEBE USAR EL ARRAYLIST CREADO EN LA PIEZA
    
    public ArrayList<Casilla> getMovimientosValidos(){
        return movimientosValidos;
    }
    public void resetMovimientosValidos(){
        movimientosValidos.clear();
    }
    
    public abstract String tipoPieza();
    
    public void datosPieza(){
        System.out.println("Hola soy la pieza: "+tipoPieza());
        System.out.println("Mis coordenadas son: ( "+coordX+" ; "+coordY+" )");
    
    }
    
    public boolean esAmigo(Pieza p){
        return this.getJugador() == p.getJugador();
    }

    public int detectarPiezaCasilla(Casilla c){
        //Este metodo detecta si hay una pieza en la casilla y devuelve un entero
        //1 si es pieza amiga, -1 si es enemiga y 0 si no hay nada en la casilla

        if(c.tengoPieza()){
            Pieza p = c.getPieza();
            if(this.esAmigo(p)){
                return 1;
            }
            return -1;
        }
        return 0;
    }
    
    public boolean agregarCasilla(int check, Casilla c,ArrayList<Casilla> listaCasillas){

        if(check ==1){
            return true;
        }
        if(check ==-1){
            c.actualizarAmenaza(this);
            listaCasillas.add(c);
            return true;
        }
        c.actualizarAmenaza(this);
        listaCasillas.add(c);
        return false;
        
    }
    
    public boolean CanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean hasBeenClicked() {
        return beenClicked;
    }

    public void setBeenClicked(boolean beenClicked) {
        this.beenClicked = beenClicked;
    }

    public Casilla getCasillaActual() {
        return casillaActual;
    }

    public void setCasillaActual(Casilla casillaActual) {
        this.casillaActual = casillaActual;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public boolean getPrimerMovimiento() {
        return primerMovimiento;
    }
    public void setPrimerMovimiento(boolean primerMovimiento) {
        this.primerMovimiento = primerMovimiento;
    }

    public void moverA(Casilla destino) {
        if(destino.getPieza() != null){
            // Es captura
            destino.remove(destino.getPieza());
        }
        this.setPrimerMovimiento(false);
        Casilla origen = casillaActual;
        origen.remove(this);
        destino.add(this);
        this.actualizarDatos(destino);
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
    public int getJugador() {
        return jugador;
    }
    private void setJugador(int jugador){
        this.jugador = jugador;
    }
    public ArrayList<Casilla> getListaMovimientos(){
        return movimientosValidos;
    }
}
