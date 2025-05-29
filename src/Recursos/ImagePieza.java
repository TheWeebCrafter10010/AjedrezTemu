
package Recursos;

import Interfaz.PanelCasillas;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class ImagePieza extends ImageIcon{
    private int jugador;
    private static int indicePiezas=0;
    private int indice;

    public ImagePieza(String ruta) {
        hallarJugador(ruta);
        ruta = "/Imagenes/"+ruta;
        URL url = getClass().getResource(ruta);
        ImageIcon icono = new ImageIcon(url);
        Image imagenEscalada = icono.getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH);
        this.setImage(imagenEscalada);
        
        //PARA HALLAR EL INDICE Y PONERLO EN UN HASHMAP
        if(jugador == 2){
            indicePiezas--;
            indice = indicePiezas;
            PanelCasillas.imagenesPiezas.put(indice, this);
            return;
        }
        if(0>indicePiezas){
            indicePiezas = 0;
        }
        indicePiezas++;
        indice = indicePiezas;
        PanelCasillas.imagenesPiezas.put(indice, this);
    }
    
    private void hallarJugador(String ruta){
        char a = ruta.charAt(0);
        
        if(a == 'b'){
            jugador = 2;
            return;
        }
        jugador = 1;
    
    }

    public int getJugador() {
        return jugador;
    }

    public int getIndice() {
        return indice;
    }
    
    
    
    
}
