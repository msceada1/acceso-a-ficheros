package ejercicio_7;

import utils.MiEntradaSalida;

import java.nio.file.Path;

public class Principal {

    private static void listarDirectorio() {
        String nombre = MiEntradaSalida.leerCadena("Indica el nombre del directorio: ");
        Path directorio = Path.of("./src/ejercicio_7", nombre);


    }
}
