
package Logica;

import Interfaz.Casilla;
import Interfaz.PanelCasillas;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Caballo extends Pieza{

    public Caballo(Casilla casilla, boolean esBlancha) {
        super(casilla, esBlancha);
    }

    @Override
    public ImageIcon ponerImagen(boolean esBlanca) {
        
        if(esBlanca){
            return PanelCasillas.imagenesPiezas.get(3);
        }
        return PanelCasillas.imagenesPiezas.get(-3);
    }

    @Override
    public ArrayList<Casilla> obtenerMovimientos() {
        ArrayList<Casilla> casillas = getListaMovimientos();
        int x = getCoordX();
        int y = getCoordY();
        
        for(int i = 1;i>=-1;i-=2){
                    for(int j = 2;j>=-2;j-=4) {
                        boolean checkX = x+i<8 && x+i>=0;
                        boolean checkY = y+j<8 && y+j>=0;
                        if(checkX && checkY){
                             int check = detectarPiezaCasilla(PanelCasillas.casillas[y+j][x+i]);
                             if(check ==0||check==-1){
                                 casillas.add(PanelCasillas.casillas[y+j][x+i]);
                                 PanelCasillas.casillas[y+j][x+i].actualizarAmenaza(this);
                             }
                        }
                    }
                }
                for(int i = 2;i>=-2;i-=4){
                    for(int j = 1;j>=-1;j-=2) {
                        boolean checkX = x+i<8 && x+i>=0;
                        boolean checkY = y+j<8 && y+j>=0;
                        if(checkX && checkY){
                            int check = detectarPiezaCasilla(PanelCasillas.casillas[y+j][x+i]);
                             if(check ==0||check==-1){
                                 casillas.add(PanelCasillas.casillas[y+j][x+i]);
                                 PanelCasillas.casillas[y+j][x+i].actualizarAmenaza(this);
                             }
                        }
                    }
                }
                return casillas;
        
    }
    

    @Override
    public String tipoPieza() {
        return "Caballo";
    }
    
    
}
