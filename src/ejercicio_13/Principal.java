package ejercicio_13;

import utils.MiEntradaSalida;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Principal {

    public static void mostrarPalabras() {
        Path ruta = Path.of("src", "ejercicio_13");
        System.out.println("Ficheros de texto: ");

        try {
            Files.list(ruta).filter(p -> p.toString().endsWith(".txt")).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String nombre = MiEntradaSalida.leerCadena("Indica el nombre del fichero:\n");
        Path fichero = Path.of("src", "ejercicio_13", nombre);

        if (!Files.isRegularFile(fichero) || !Files.isReadable(fichero) || Files.notExists(fichero)) {
            try {
                throw new IOException("Error al acceder al fichero");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            Files.readString(fichero).split("\\s+");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void buscarPalabra() {
        Path ruta = Path.of("src", "ejercicio_13");
        System.out.println("Ficheros de texto: ");

        try {
            Files.list(ruta).filter(p -> p.toString().endsWith(".txt")).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String nombre = MiEntradaSalida.leerCadena("Indica el nombre del fichero:\n");
        Path fichero = Path.of("src", "ejercicio_13", nombre);

        if (!Files.isRegularFile(fichero) || !Files.isReadable(fichero) || Files.notExists(fichero)) {
            try {
                throw new IOException("Error al acceder al fichero");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        String palabra = MiEntradaSalida.leerCadena("Indica una palabra del fichero " + fichero);
        try (Stream<String> linea = Files.lines(fichero)){
            boolean contiene = linea.anyMatch(l -> l.contains(palabra));
            if (!contiene) {
                throw new IOException("No se ha encontrado la palabra " + palabra + " en el fichero " + fichero);
            } else {
                System.out.println("Creando fichero nuevo...");

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
