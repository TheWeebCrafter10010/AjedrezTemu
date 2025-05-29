
package Logica;

import Interfaz.Casilla;
import Interfaz.PanelCasillas;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Peon extends Pieza{

    public Peon(Casilla casilla, boolean esBlancha) {
        super(casilla, esBlancha);
    }

    @Override
    public ImageIcon ponerImagen(boolean esBlanca) {
        if(esBlanca){
            return PanelCasillas.imagenesPiezas.get(1);
        }
        return PanelCasillas.imagenesPiezas.get(-1);
    }

    @Override
    public ArrayList<Casilla> obtenerMovimientos() {
        ArrayList<Casilla> casillas = getListaMovimientos();
        
        if(getJugador() == 1){
            int y = getCoordY()-1;
            System.out.println("la coordenada siguiente es:" +y);
            if (y >=0){
                System.out.println("ola");
                int x1= getCoordX()+1;
                int x2= getCoordX()-1;

                if(x1<8){
                    int check = detectarPiezaCasilla(PanelCasillas.casillas[y][x1]);
                    
                    if(check == -1){
                        casillas.add(PanelCasillas.casillas[y][x1]);
                        PanelCasillas.casillas[y][x1].actualizarAmenaza(this);
                    }
                    if(check ==0){
                        PanelCasillas.casillas[y][x1].actualizarAmenaza(this);
                    }

                }
                if(x2>=0){
                    int check = detectarPiezaCasilla(PanelCasillas.casillas[y][x2]);
                    if(check == -1){
                        casillas.add(PanelCasillas.casillas[y][x2]);
                        PanelCasillas.casillas[y][x2].actualizarAmenaza(this);
                    }
                    if(check ==0){
                        PanelCasillas.casillas[y][x2].actualizarAmenaza(this);
                    }

                }
                if(!PanelCasillas.casillas[y][getCoordX()].tengoPieza()){
                    casillas.add(PanelCasillas.casillas[y][getCoordX()]);
                }
                return casillas;
            }
            System.out.println("llegue hasta aqui, no hay movimientos");
            return casillas;
        }
        
        if(getJugador() == 2){
            int y = getCoordY()+1;
            System.out.println("la coordenada siguiente es:" +y);
            if (8 >y){

                int x1= getCoordX()+1;
                int x2= getCoordX()-1;

                if(x1<8){
                    int check = detectarPiezaCasilla(PanelCasillas.casillas[y][x1]);
                    
                    if(check == -1){
                        casillas.add(PanelCasillas.casillas[y][x1]);
                        PanelCasillas.casillas[y][x1].actualizarAmenaza(this);
                    }
                    if(check ==0){
                        PanelCasillas.casillas[y][x1].actualizarAmenaza(this);
                    }

                }
                if(x2>=0){
                    int check = detectarPiezaCasilla(PanelCasillas.casillas[y][x2]);
                    if(check == -1){
                        casillas.add(PanelCasillas.casillas[y][x2]);
                        PanelCasillas.casillas[y][x2].actualizarAmenaza(this);
                    }
                    if(check ==0){
                        PanelCasillas.casillas[y][x2].actualizarAmenaza(this);
                    }
                }
                if(!PanelCasillas.casillas[y][getCoordX()].tengoPieza()){
                    casillas.add(PanelCasillas.casillas[y][getCoordX()]);
                }
                return casillas;
            }
            System.out.println("llegue hasta aqui, no hay movimientos");

            return casillas;
        }
        
        return null;
    }

    @Override
    public String tipoPieza() {
        return "Peon";
    }
    
    
    
}
