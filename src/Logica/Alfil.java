
package Logica;

import Interfaz.Casilla;
import Interfaz.PanelCasillas;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Alfil extends Pieza{

    public Alfil(Casilla casilla, boolean esBlanca) {
        super(casilla, esBlanca);
    }

    @Override
    public ImageIcon ponerImagen(boolean esBlanca) {
        
        if(esBlanca){
            return PanelCasillas.imagenesPiezas.get(4);
        }
        return PanelCasillas.imagenesPiezas.get(-4);
    }

    @Override
    public ArrayList<Casilla> obtenerMovimientos() {
        
        ArrayList<Casilla> casillas = getListaMovimientos();
        int x = getCoordX();
        int y = getCoordY();
        
        while(x+1<8&&y+1<8){
            x +=1;
            y +=1;
            int check = detectarPiezaCasilla(PanelCasillas.casillas[y][x]);
            boolean detengo = agregarCasilla(check, PanelCasillas.casillas[y][x], casillas);
            
            if(detengo){
                break;
            }
        }
        x = getCoordX();
        y = getCoordY();
        while(x-1>=0&&y-1>=0){
            x -=1;
            y -=1;
            int check = detectarPiezaCasilla(PanelCasillas.casillas[y][x]);
            boolean detengo = agregarCasilla(check, PanelCasillas.casillas[y][x], casillas);
            
            if(detengo){
                break;
            }
        }
        x = getCoordX();
        y = getCoordY();
        while(x+1<8&&y-1>=0){
            x +=1;
            y -=1;
            int check = detectarPiezaCasilla(PanelCasillas.casillas[y][x]);
            boolean detengo = agregarCasilla(check, PanelCasillas.casillas[y][x], casillas);
            
            if(detengo){
                break;
            }
        }
        x = getCoordX();
        y = getCoordY();
        while(x-1>=0&&y+1<8){
            x -=1;
            y +=1;
            int check = detectarPiezaCasilla(PanelCasillas.casillas[y][x]);
            boolean detengo = agregarCasilla(check, PanelCasillas.casillas[y][x], casillas);
            
            if(detengo){
                break;
            }
        }
        return casillas;
        
    }
    
    public static void movimientosAlfil(Pieza p){
        ArrayList<Casilla> casillas = p.getListaMovimientos();
        int x = p.getCoordX();
        int y = p.getCoordY();
        
        while(x+1<8&&y+1<8){
            x +=1;
            y +=1;
            int check = p.detectarPiezaCasilla(PanelCasillas.casillas[y][x]);
            boolean detengo = p.agregarCasilla(check, PanelCasillas.casillas[y][x], casillas);
            
            if(detengo){
                break;
            }
        }
        x = p.getCoordX();
        y = p.getCoordY();
        while(x-1>=0&&y-1>=0){
            x -=1;
            y -=1;
            int check = p.detectarPiezaCasilla(PanelCasillas.casillas[y][x]);
            boolean detengo = p.agregarCasilla(check, PanelCasillas.casillas[y][x], casillas);
            
            if(detengo){
                break;
            }
        }
        x = p.getCoordX();
        y = p.getCoordY();
        while(x+1<8&&y-1>=0){
            x +=1;
            y -=1;
            int check = p.detectarPiezaCasilla(PanelCasillas.casillas[y][x]);
            boolean detengo = p.agregarCasilla(check, PanelCasillas.casillas[y][x], casillas);
            
            if(detengo){
                break;
            }
        }
        x = p.getCoordX();
        y = p.getCoordY();
        while(x-1>=0&&y+1<8){
            x -=1;
            y +=1;
            int check = p.detectarPiezaCasilla(PanelCasillas.casillas[y][x]);
            boolean detengo = p.agregarCasilla(check, PanelCasillas.casillas[y][x], casillas);
            
            if(detengo){
                break;
            }
        }
    }

    @Override
    public String tipoPieza() {
        return "Alfil";
    }
    
    
}
