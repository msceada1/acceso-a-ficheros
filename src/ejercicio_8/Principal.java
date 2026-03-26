package ejercicio_8;

import utils.MiEntradaSalida;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Principal {

    public static void main(String[] args) {

        String nombreFichero = MiEntradaSalida.leerCadena("introduce el nombre del fichero");
        Path directorio = Path.of("src", nombreFichero);

        if (Files.exists(directorio) || !Files.isRegularFile(directorio)) {
            System.out.println("el fichero no existe o no es valido");
            return;
        }

        try (Stream<String> lineas = Files.lines(directorio)){
            boolean cumple = lineas.allMatch(l -> l.matches(""));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
