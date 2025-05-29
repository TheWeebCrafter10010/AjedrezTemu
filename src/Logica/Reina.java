/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Interfaz.Casilla;
import Interfaz.PanelCasillas;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Usuario
 */
public class Reina extends Pieza{

    public Reina(Casilla casilla, boolean esBlancha) {
        super(casilla, esBlancha);
    }

    @Override
    public ImageIcon ponerImagen(boolean esBlanca) {
        if(esBlanca){
            return PanelCasillas.imagenesPiezas.get(6);
        }
        return PanelCasillas.imagenesPiezas.get(-6);
    }

    @Override
    public ArrayList<Casilla> obtenerMovimientos() {
        
        Torre.movimientosTorre(this);
        Alfil.movimientosAlfil(this);
        return getListaMovimientos();
    }

    @Override
    public String tipoPieza() {
        return "Reina";
    }
    
    
}
