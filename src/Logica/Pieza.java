
package Logica;

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
    
    private int jugador;


    
    public Pieza(Casilla casilla, boolean esBlancha){
        
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
        
        if(beenClicked){
            return movimientosValidos;
        }
        movimientosValidos.clear();
        movimientosValidos = obtenerMovimientos();
        beenClicked = true;
        return movimientosValidos;
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
        if(c.tengoPieza()){
            Pieza p = c.obtenerPieza();
            if(this.esAmigo(p)){
                return 1;
            }
            return -1;
        }
        return 0;
        //Devuelve 1 si es pieza amiga, -1 si es enemiga y 0 si no hay nada en la casilla
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
    
    /*public boolean addCasillaToLista(ArrayList<Casilla> casillas, Casilla c){
        //ESTE METODO DEVUELVE TRUE SI LA CASILLA QUE ENTRA TIENE UNA PIEZA
        //ESTE METODO TAMBIEN AÑADE A LA LISTA DE CASILLAS SI LA PIEZA ES ENEMIGA
        
        //DEVUELVE FALSE CASO CONTRARIO
        //METODO DE MRDA XDDDD, NO FUNCIONARA CON REY Y PEON
        
        //ARREGLARE ESTE METODO CAGADA 20/05/25
        if(c.tengoPieza()){
            Pieza p = c.obtenerPieza();
            if(this.esAmigo(p)){
                return this.esAmigo(p);
            }
            casillas.add(c);
            return true;
        }
        casillas.add(c);
        return false;
    }*/
    
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
