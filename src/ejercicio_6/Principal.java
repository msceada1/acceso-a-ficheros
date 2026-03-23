package ejercicio_6;

import utils.MiEntradaSalida;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Principal {

    private static void mostrarPesoEnKB() {
        String nombre = MiEntradaSalida.leerCadena("Indica el nombre del directorio");
        Path directorio = Path.of("./src/ejercicio_6", nombre);

        if (Files.exists(directorio)) {
            try {
                System.out.println(Arrays.toString(Files.readAllBytes(directorio)));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        mostrarPesoEnKB();
    }

    /*
    import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Obtener los bytes de todos los archivos .txt en un directorio
try (Stream<Path> paths = Files.walk(Paths.get("ruta/directorio"))) {
    paths.filter(Files::isRegularFile)
         .forEach(path -> {
             try {
                 byte[] fileContent = Files.readAllBytes(path); // Lee cada archivo
                 // Procesar los bytes aquí
             } catch (Exception e) { e.printStackTrace(); }
         });
} catch (Exception e) { e.printStackTrace(); }
     */
}
