package ejercicio_5;

import utils.MiEntradaSalida;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Principal {

    /**
     * Crea un directorio siempre y cuando este no exista. Si existe, se lanzara una excepción.
     */
    private static void crearDirectorio() {
        String nombre = MiEntradaSalida.leerCadena("Indica el nombre del directorio: ");

        Path directorio = Path.of("src", "ejercicio_5", nombre); //ruta donde se creara el directorio

        try {
            Files.createDirectory(directorio); //el metodo createDirectory de la clase Files verifica si existía o no dicho directorio
        } catch (IOException e) {
            System.err.println("No se puedo crear el directorio " + e.getMessage() + " porque ya existe");
        }
    }
}
