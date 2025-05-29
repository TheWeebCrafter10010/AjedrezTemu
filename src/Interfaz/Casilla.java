
package Interfaz;

import Logica.Pieza;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Casilla extends JPanel{
    
    private int coordX;
    private int coordY;
    
    private boolean amenazado;
    private ArrayList<Pieza> piezasQueAmenazan = new ArrayList<>();
    
    private boolean beenClicked;
    
    private Color colorOriginal;
    private Color colorActual;
    
    public Casilla(Color color,int originX, int originY, int[] coords){
        
        setCoordX(coords[0]);
        setCoordY(coords[1]);
        
        setBackground(color);
        setBounds(originX, originY, 75, 75);
        setVisible(true);
        
        colorOriginal = color;
        colorActual = color;
        setLayout(new BorderLayout());
    }
    
    private PanelCasillas obtenerPadre(){
        if(this.getParent() instanceof PanelCasillas padre){
            return padre;
        }
        return null;
    }
    
    public Pieza obtenerPieza(){
        for(Component c: this.getComponents()){
            if(c instanceof Pieza p){
                return p;
            }
            
        }
        return null;
    }
    
    public boolean tengoPieza(){
        
        return (obtenerPieza() instanceof Pieza);

    }
    
    public void pintarme(){
        
        if(obtenerPieza()!= null && colorOriginal == colorActual){
            setBackground(Color.red);
            colorActual = Color.red;
            return;
        }
        if(colorOriginal!=colorActual){
            setBackground(colorOriginal);
            colorActual = colorOriginal;
            return;
        }
        
        setBackground(Color.green);
        colorActual = Color.green;
    }
    
    
    public void pintarme(Color color){
        setBackground(color);
        colorActual = color;
    }
    
    public void actualizarAmenaza(Pieza p){
        piezasQueAmenazan.add(p);
        amenazado = true;
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
    public boolean isAmenazado() {
        return amenazado;
    }

    public void setAmenazado(boolean amenazado) {
        this.amenazado = amenazado;
    }
    
    
    
}
