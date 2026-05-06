
package ajedreztemu;
import Logica.Juego.JuegoController;
import Logica.Juego.MovimientoController;
import Recursos.Prueba;
import Interfaz.*;

import javax.swing.*;
import java.net.URISyntaxException;

public class AjedrezTemu {

    public static void main(String[] args) throws URISyntaxException {
        Prueba p = new Prueba();
        p.pruebaUrl();
        Ventana ventana = new Ventana();


        PanelCasillas tablero = new PanelCasillas();

        JuegoController juegoController = new JuegoController(tablero, new MovimientoController());
        tablero.setClickListener(juegoController);

        juegoController.iniciarJuego();

//        ventana.setPanelCasillas(tablero);

        PanelDatos panelDatos = new PanelDatos();
        ventana.add(tablero);
        ventana.add(panelDatos);
        ventana.revalidate();
        ventana.repaint();
        ventana.setVisible(true);
    }
    
}
