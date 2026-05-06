package Interfaz;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PanelDatos extends JPanel {
    private final int origenX = 676;
    private final int origenY = 58;


    public PanelDatos() {
        setBounds(origenX, origenY, 275, 602);
        Border borde = BorderFactory.createLineBorder(Color.BLACK, 1);
        setBorder(borde);
        crearCampos();

    }

    private void crearCampos(){
        Font fuenteGrande = new Font("Arial", Font.BOLD, 20);
        JTextField nombre = new JTextField(20);

            nombre.setEditable(false);
            nombre.setText("Pruebaaaaaaa");
            nombre.setFont(fuenteGrande);
            this.add(nombre);

    }


}
