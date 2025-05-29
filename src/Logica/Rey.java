
package Logica;

import Interfaz.Casilla;
import Interfaz.PanelCasillas;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Rey extends Pieza{

    public Rey(Casilla casilla, boolean esBlancha) {
        super(casilla, esBlancha);
    }

    @Override
    public ImageIcon ponerImagen(boolean esBlanca) {
        
        if(esBlanca){
            return PanelCasillas.imagenesPiezas.get(5);
        }
        return PanelCasillas.imagenesPiezas.get(-5);
    }

    @Override
    public ArrayList<Casilla> obtenerMovimientos() {
        
        ArrayList<Casilla> casillas = this.getListaMovimientos();
        
        int x = getCoordX();
        int y = getCoordY();
        //x = 5
        for(int i = y-1;y+2>i;i++){
            
            if(i>=0&&7>=i){
                for(int j = x-1;x+2>j;j++){
                    if(j>=0&&7>=j){
                        int check =detectarPiezaCasilla(PanelCasillas.casillas[i][j]);
                        
                        if(check == -1||check ==0){
                            casillas.add(PanelCasillas.casillas[i][j]);
                        }
                        
                    }
                }
            }
            
        }
        casillas.remove(PanelCasillas.casillas[y][x]);
        return casillas;
        
    }

    @Override
    public String tipoPieza() {
        return "Rey";
    }
    
    
}
