
package Logica;

import Interfaz.Casilla;
import Interfaz.PanelCasillas;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Torre extends Pieza{

    public Torre(Casilla casilla, boolean esBlanca) {
        super(casilla,esBlanca);
    }

    @Override
    public ImageIcon ponerImagen(boolean esBlanca) {
        
        if(esBlanca){
            return PanelCasillas.imagenesPiezas.get(2);
        }
        return PanelCasillas.imagenesPiezas.get(-2);
        
    }

    @Override
    public ArrayList<Casilla> obtenerMovimientos() {
        ArrayList<Casilla> casillas = getListaMovimientos();
        int x = getCoordX();
        int y = getCoordY();
        
        for(int i = x+1; 8>i; i++){
            int check = detectarPiezaCasilla(PanelCasillas.casillas[y][i]);
            
            boolean detengo =agregarCasilla(check, PanelCasillas.casillas[y][i], casillas);
            
            if(detengo){
                break;
            }
            
        }
        for(int i = x-1; i>-1; i--){
            
            int check = detectarPiezaCasilla(PanelCasillas.casillas[y][i]);
            
            boolean detengo =agregarCasilla(check, PanelCasillas.casillas[y][i], casillas);
            
            if(detengo){
                break;
            }
            
        }
        for(int i = y+1; 8>i; i++){
            int check = detectarPiezaCasilla(PanelCasillas.casillas[i][x]);
            
            boolean detengo =agregarCasilla(check, PanelCasillas.casillas[i][x], casillas);
            
            if(detengo){
                break;
            }
        }
        for(int i = y-1; i>-1; i--){
            int check = detectarPiezaCasilla(PanelCasillas.casillas[i][x]);
            boolean detengo =agregarCasilla(check, PanelCasillas.casillas[i][x], casillas);
            
            if(detengo){
                break;
            }
        }
        return casillas; 
    }

    public static void movimientosTorre(Pieza p){
        ArrayList<Casilla> casillas = p.getListaMovimientos();
        int x = p.getCoordX();
        int y = p.getCoordY();
        
        for(int i = x+1; 8>i; i++){
            int check = p.detectarPiezaCasilla(PanelCasillas.casillas[y][i]);
            
            boolean detengo = p.agregarCasilla(check, PanelCasillas.casillas[y][i], casillas);
            
            if(detengo){
                break;
            }
            
        }
        for(int i = x-1; i>-1; i--){
            
            int check = p.detectarPiezaCasilla(PanelCasillas.casillas[y][i]);
            
            boolean detengo = p.agregarCasilla(check, PanelCasillas.casillas[y][i], casillas);
            
            if(detengo){
                break;
            }
            
        }
        for(int i = y+1; 8>i; i++){
            int check = p.detectarPiezaCasilla(PanelCasillas.casillas[i][x]);
            boolean detengo = p.agregarCasilla(check, PanelCasillas.casillas[i][x], casillas);
            
            if(detengo){
                break;
            }
        }
        for(int i = y-1; i>-1; i--){
            int check = p.detectarPiezaCasilla(PanelCasillas.casillas[i][x]);
            boolean detengo = p.agregarCasilla(check, PanelCasillas.casillas[i][x], casillas);
            
            if(detengo){
                break;
            }
        }
    }
    @Override
    public String tipoPieza() { 
        return "Torre";
    }
    
}
