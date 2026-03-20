package ejercicio_3;

import utils.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Ejercicio3 {

    public static void main(String[] args) {

        Path fichero = Path.of("./src/ejercicio_3/salidaEj3.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String linea;

            while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase("fin")) {
                Files.writeString(fichero, linea, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }

    }
}
