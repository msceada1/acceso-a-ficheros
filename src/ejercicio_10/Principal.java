package ejercicio_10;

import utils.MiEntradaSalida;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Principal {

    public static void main(String[] args) {
        leerFichero();
    }

    public static void leerFichero() {
        String nombreFichero = MiEntradaSalida.leerCadena("Indica el nombre del fichero:\n");
        Path fichero = Path.of("src", "ejercicio_10", nombreFichero);

        if (Files.isRegularFile(fichero) && Files.isReadable(fichero)) {

            Pattern p = Pattern.compile("^F\\s((?:\\w:\\/)?(?:\\w+\\/)*[\\d\\p{L}]{3,}\\.\\p{L}{3})$");
            try (Stream<String> lineas = Files.lines(fichero)) {
                lineas.map(p::matcher)
                        .filter(Matcher::find)
                        .map(m -> m.group(1))
                        .map(Path::of)
                        .forEach(s -> {
                            try {
                                Files.createFile(s);
                            } catch (IOException e) {
                                System.err.println(e.getMessage());
                            }
                        });
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("fichero no encontrado");
        }
    }
}
