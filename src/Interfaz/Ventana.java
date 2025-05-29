package Interfaz;

import javax.swing.JFrame;


public class Ventana extends JFrame{
    PanelCasillas panelCasillas = new PanelCasillas();
    
    String[] rutasImagenes;
    public Ventana(){
        super("Ajedrez de Temu");
        setBounds(300, 20, 720, 720);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        this.add(panelCasillas);
        crearRutas();
        //panelCasillas.crearImagenes(75, 75, rutasImagenes);
        this.repaint();
        
    }
    
    private void crearRutas(){
        rutasImagenes = new String[1];
        // Ejemplo de ruta: /Imagenes/bonie.png
        rutasImagenes[0] = "/Imagenes/Torre.png";
    }
    
    public void iniciar(){
        panelCasillas.agregarPieza();
        repaint();
    }
 
}
