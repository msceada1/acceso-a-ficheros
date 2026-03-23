package ejercicio_5;

import utils.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        ejecutarPrograma();
    }

    /**
     * Crea un directorio siempre y cuando este no exista. Si existe, se lanzara una excepción.
     */
    private static void crearDirectorio() {
        String nombre = MiEntradaSalida.leerCadena("Indica el nombre del directorio: ");
        Path directorio = Path.of("src", "ejercicio_5", nombre); //ruta donde se creara el directorio
        try {
            Files.createDirectory(directorio); //el metodo createDirectory de la clase Files verifica si existía o no dicho directorio
        } catch (IOException e) {
            System.err.println("No se puedo crear el directorio " + e.getMessage() + " porque ya existe");
        }
    }

    /**
     * Crea un fichero de texto en una ruta especificada, siempre y cuando el fichero no exista. Por lo contrario
     * lanzara una excepcion. Una vez verificado que no exista el fichero, leera por consola el texto
     * que el usuario desee introducir y lo añadira a dicho fichero
     */
    private static void crearFicheroDeTexto() {
        String nombre = MiEntradaSalida.leerCadena("Indica el nombre del fichero de texto: ");

        Path directorio = Path.of("./src/ejercicio_5", nombre); //ruta donde se creara el fichero de texto

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Files.createFile(directorio); //el metodo ceateFile de la clase Files verifica si existia o no el fichero de texto
            System.out.println("Escribe algo bonito: ");
            String txt;
            while ((txt = br.readLine()) != null && !txt.isEmpty()) {
                Files.writeString(directorio, txt + System.lineSeparator(), StandardOpenOption.APPEND);
                //System.lineSeparator() respeta los espaciados y saltos de linea
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void borrarFicheroDeTexto() {
        String nombre = MiEntradaSalida.leerCadena("Indica el nombre del fichero que deseas borrar");
        Path directorio = Path.of("./src/ejercicio_5", nombre); //ruta donde se debe encontrar el fichero de texto a borrar.
        try {
            Files.deleteIfExists(directorio); //si el fichero existia se borra. En caso contrario se lanzara una excepcion
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void mostrarLosFicherosDeUnaCarpeta() {
        String nombre = MiEntradaSalida.leerCadena("Indica el nombre de la carpeta: ");
        File carpeta = new File("./src/ejercicio_5", nombre);

        System.out.println(Arrays.toString(carpeta.listFiles(f -> f.isFile() && f.getName().endsWith(".txt"))));
    }

    public static void ejecutarPrograma() {
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- MENÚ DE GESTIÓN DE FICHEROS ---");
            System.out.println("1. Crear directorio");
            System.out.println("2. Crear fichero de texto");
            System.out.println("3. Borrar fichero de texto");
            System.out.println("4. Mostrar los ficheros que contiene una carpeta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1:
                        crearDirectorio();
                        break;
                    case 2:
                        crearFicheroDeTexto();
                        break;
                    case 3:
                        borrarFicheroDeTexto();
                        break;
                    case 4:
                        mostrarLosFicherosDeUnaCarpeta();
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduzca un número válido.");
            }
        } while (opcion != 5);
    }
}
