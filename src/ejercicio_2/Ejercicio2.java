package ejercicio_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Ejercicio2 {

    public static void main(String[] args) {
        Path fichero = Path.of("./src/ejercicio_1/leeme.txt");

        try {
            System.out.println(Files.lines(fichero).collect(Collectors.joining(" ")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
