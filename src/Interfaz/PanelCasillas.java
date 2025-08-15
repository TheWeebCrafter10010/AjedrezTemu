
package Interfaz;

import Logica.*;
import Recursos.ImagePieza;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PanelCasillas extends JPanel{
    
    public static Casilla[][] casillas = new Casilla[8][8];
    ArrayList<Pieza> piezasEnTablero = new ArrayList<>();
    
    //public static ImageIcon[] imagenes;
    
    public static Map<Integer, ImagePieza> imagenesPiezas = new HashMap<>();
    
    private boolean clicado;
    
    private Casilla casillaClicada;
    private Casilla casillaPrimera;

    public PanelCasillas() {
        setBounds(54, 58, 602, 602);
        setVisible(true);
        setLayout(null);
        generarCasillas();
        Border borde = BorderFactory.createLineBorder(Color.BLACK, 1);
        setBorder(borde);
        eventoRaton();
        repaint();
    }
    
    private void generarCasillas(){
        int x = 0;
        int y = 0;
        Color[] colores = {Color.WHITE,Color.BLACK};
        int indiceColor = -1;
        for(int i =1; 601>i;i+=75){
            indiceColor++;
            for(int j =1; 601>j;j+=75){
                casillas[y][x] = new Casilla(colores[indiceColor%2], j, i,new int[]{x,y});
                add(casillas[y][x]);
                x++;
                indiceColor++;
            }
            x = 0;
            y++;
        }
       
    }   
    
    public void pintarCasillas(ArrayList<Casilla> casillas){
        for(Casilla c: casillas){
            c.pintarme();
        }
    }
    
    public void agregarPieza(){
        generarPiezas(true);
        generarPiezas(false);
        generarMovimietosPiezas();
    }

private void generarPiezas(boolean blancas){
    int filaPeones = blancas ? 6 : 1;
    int filaMayores = blancas ? 7 : 0;

    // Generar peones
    for(int i = 0; i < 8; i++){
        piezasEnTablero.add(new Peon(casillas[filaPeones][i], blancas));
    }

    // Generar piezas mayores
    piezasEnTablero.add(new Torre(casillas[filaMayores][0], blancas));
    piezasEnTablero.add(new Torre(casillas[filaMayores][7], blancas));
    piezasEnTablero.add(new Caballo(casillas[filaMayores][1], blancas));
    piezasEnTablero.add(new Caballo(casillas[filaMayores][6], blancas));
    piezasEnTablero.add(new Alfil(casillas[filaMayores][2], blancas));
    piezasEnTablero.add(new Alfil(casillas[filaMayores][5], blancas));
    piezasEnTablero.add(new Reina(casillas[filaMayores][3], blancas));
    piezasEnTablero.add(new Rey(casillas[filaMayores][4], blancas));
}


    private void eventoRaton(){
        this.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(MouseEvent e){
                logicaMovimientoEvento(e);
            }
        }
        );
    }

    private void logicaMovimientoEvento(MouseEvent e){
            casillaClicada = (Casilla)getComponentAt(e.getPoint());

                // Ignora clics en casillas vacías si no hay pieza seleccionada
                if(!(clicado || casillaClicada.obtenerPieza() != null)){
                    //ESTO ES PARA COMPROBAR SI CLICEASTE UNA PIEZA Y ES LA PRIMERA VEZ QUE LO HACES
                    //PARA QUE PASE A LO SIGUIENTE DEBE SER: -La segunda vez que cliceaste o si cliceaste a una pieza
                    //Si clicas una casilla vacia no pasa nada
                    return;
                }
                
                if(clicado){
                    // Si se clicó la misma casilla: deseleccionar
                    if(casillaClicada == casillaPrimera){
                        casillaPrimera.pintarme();
                        pintarCasillas(casillaPrimera.obtenerPieza().getMovimientosValidos());
                        casillaPrimera.obtenerPieza().setBeenClicked(false);
                        clicado=false;
                        casillaPrimera = null;
                        return;
                    }
                    
                    if(casillaPrimera.obtenerPieza().getMovimientosValidos().contains(casillaClicada)){
                        casillaPrimera.pintarme();
                        pintarCasillas(casillaPrimera.obtenerPieza().getMovimientosValidos());
                        casillaPrimera.obtenerPieza().setBeenClicked(false);
                        casillaPrimera.obtenerPieza().moverA(casillaClicada);
                        this.repaint();
                        casillaPrimera = null;
                        clicado = false;
                        generarMovimietosPiezas();
                        return;
                    }
                    return;
                }
                
                if(casillaClicada.obtenerPieza() != null){
                    casillaClicada.pintarme(Color.YELLOW);
                    Pieza p = casillaClicada.obtenerPieza();
                    pintarCasillas(p.getMovimientosValidos());
                    casillaPrimera = casillaClicada;
                    clicado = true;
                    return;
                }
        
        
    }

    private void generarMovimietosPiezas(){
        for(Pieza p: piezasEnTablero){
            p.resetMovimientosValidos();
            p.obtenerMovimientos();
        }
    }
    
    private void deseleccionar(){
        
    }
}
