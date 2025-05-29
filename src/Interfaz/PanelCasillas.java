
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
        
        piezasEnTablero.add(new Torre(casillas[0][0], true));
        piezasEnTablero.add(new Alfil(casillas[4][4], false));
        piezasEnTablero.add(new Caballo(casillas[7][7], true));
        piezasEnTablero.add(new Reina(casillas[5][5], false));
        piezasEnTablero.add(new Peon(casillas[2][7], false));
        piezasEnTablero.add(new Peon(casillas[4][7], true));
        piezasEnTablero.add(new Rey(casillas[1][2], true)); 
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
    
    private void moverPieza(Casilla origen, Casilla destino){
        
        if(destino.obtenerPieza() != null){
            //Es captura
            destino.remove(destino.obtenerPieza());
        }
        
        Pieza p = origen.obtenerPieza();
        origen.remove(p);
        destino.add(p);
        p.actualizarDatos(destino);
        this.repaint();
    }
    
    private void logicaMovimientoEvento(MouseEvent e){
            casillaClicada = (Casilla)getComponentAt(e.getPoint());
                //System.out.println("Coordenadas: ( "+casillaClicada.getCoordX()+";"+casillaClicada.getCoordY()+" )");   
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
                        moverPieza(casillaPrimera, casillaClicada);
                        casillaPrimera = null;
                        clicado = false;
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
    
    private void deseleccionar(){
        
    }
}
