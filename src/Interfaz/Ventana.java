package Interfaz;

import javax.swing.JFrame;


public class Ventana extends JFrame{
    PanelCasillas panelCasillas = new PanelCasillas();

    public Ventana(){
        super("Ajedrez de Temu");
        setBounds(300, 20, 720, 720);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        this.add(panelCasillas);
        //panelCasillas.crearImagenes(75, 75, rutasImagenes);
        this.repaint();
        
    }
    
    public void iniciar(){
        panelCasillas.agregarPieza();
        repaint();
    }
 
}
