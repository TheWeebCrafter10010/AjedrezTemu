
package Interfaz;

import Logica.Juego.IClickListener;
import Logica.Juego.ITablero;
import Logica.Piezas.*;
import Recursos.ImagePieza;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PanelCasillas extends JPanel implements ITablero {
    
    public static Casilla[][] casillas = new Casilla[8][8];
    ArrayList<Pieza> piezasEnTablero = new ArrayList<>();
    private IClickListener clickListener;

    public PanelCasillas() {

    }

    @Override
    public void iniciarTablero() {
        setBounds(54, 58, 602, 602);
        setVisible(true);
        setLayout(null);
        generarCasillas();
        Border borde = BorderFactory.createLineBorder(Color.BLACK, 1);
        setBorder(borde);

        System.out.println("dibujando piezas");
        generarPiezas(true);
        generarPiezas(false);
        System.out.println("piezas dibujadas");
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                clickListener.onClick((Casilla)getComponentAt(e.getPoint()));
            }
        });
        repaint();
    }

    @Override
    public void moverPieza(Casilla origen, Casilla destino) {
        origen.getPieza().moverA(destino);
        this.repaint();
    }
    @Override
    public ArrayList<Pieza> getPiezasEnTablero() {
        return piezasEnTablero;
    }

    @Override
    public void pintarCasillas(Pieza piezaOrigen){
        ArrayList<Casilla> casillas = piezaOrigen.getMovimientosValidos();
        piezaOrigen.getCasillaActual().pintarme(Color.YELLOW);
         for(Casilla c: casillas){
            c.pintarme();
        }
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


    @Override
    public void setClickListener(IClickListener clickListener) {
        this.clickListener = clickListener;
    }
}

