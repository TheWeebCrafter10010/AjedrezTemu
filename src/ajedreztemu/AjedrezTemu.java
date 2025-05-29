
package ajedreztemu;
import Recursos.Prueba;
import Interfaz.*;
import java.net.URISyntaxException;

public class AjedrezTemu {

    public static void main(String[] args) throws URISyntaxException {
        Prueba p = new Prueba();
        p.pruebaUrl();
        Ventana interfaz = new Ventana();
        interfaz.iniciar();
        
    }
    
}
