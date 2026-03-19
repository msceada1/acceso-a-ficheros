package ejercicio_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio1 {

    public static void main(String[] args) {
        File f = new File("./src/ejercicio_1/leeme.txt");

        try (FileReader fr = new FileReader(f);
             BufferedReader br = new BufferedReader(fr)) {
            /*int contador = 0;

            while (br.readLine() != null) {
                contador++;
            }

            System.out.printf("El fichero leeme.txt tiene %d lineas", contador);
             */

            Path fichero = Path.of("./src/ejercicio_1/leeme.txt");
            System.out.printf("El fichero leeme.txt tiene %d lineas", Files.lines(fichero).count());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
