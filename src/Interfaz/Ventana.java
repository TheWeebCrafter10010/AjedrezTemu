package Interfaz;

import javax.swing.JFrame;


public class Ventana extends JFrame{

    public Ventana(){
        super("Ajedrez de Temu");
        setBounds(150, 20, 1000, 720);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
    }



    public void setPanelCasillas(PanelCasillas panelCasillas){
        this.add(panelCasillas);
        this.repaint();
    }
}
