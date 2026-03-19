import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio1 {

    public static void main(String[] args) {
        File f = new File("./src/leeme.txt");

        try (FileReader fr = new FileReader(f);
             BufferedReader br = new BufferedReader(fr)) {
            int contador = 0;

            while (br.readLine() != null) {
                contador++;
            }

            System.out.printf("El fichero leeme.txt tiene %d lineas", contador);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
