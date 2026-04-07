package ejercicio_11;

import java.nio.file.Path;

public class Principal {

    public static void main(String[] args) {

        Path fichero = Path.of("src", "ejercicio_11", "nombres.txt");
        String patron = "^[A-ZÁÉÍÓÚÑ]+\\s[A-ZÁÉÍÓÚÑ]+\\s[A-ZÁÉÍÓÚÑ]+\\s[1-4][A-Z]$";
    }
}
