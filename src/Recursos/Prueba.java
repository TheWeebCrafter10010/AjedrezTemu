
package Recursos;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Prueba {
    
    public void pruebaUrl() throws URISyntaxException{
    
        URL url = darUrl();
        File carpeta = new File(url.toURI());
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    // Filtrar solo imágenes
                    if (archivo.isFile()) {
                        new ImagePieza(archivo.getName());
                    }
                }
            }
        } else {
            System.out.println("No se encontró la carpeta de imágenes.");
        }
        
    }
    
    public URL darUrl(){
        URL url = getClass().getResource("/Imagenes");
        return url;
    } 
}
