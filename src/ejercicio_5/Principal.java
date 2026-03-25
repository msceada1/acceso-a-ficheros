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
        Path directorio = Path.of("./src/ejercicio_5", nombre);

        // Eliminamos el BufferedReader del try-with-resources
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            Files.createFile(directorio);
            System.out.println("Escribe algo bonito (pulsa Enter vacío para terminar): ");

            String txt;
            // Leemos mientras no sea nulo ni esté vacío
            while ((txt = br.readLine()) != null && !txt.isEmpty()) {
                Files.writeString(directorio, txt + System.lineSeparator(), StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            System.err.println("Error al manejar el fichero: " + e.getMessage());
        }
        // NOTA: No cerramos 'br' para no matar System.in
    }

    /**
     * Borra un fichero de texto lanzando una excepcion si no lo encuentra
     */
    private static void borrarFicheroDeTexto() {
        String nombre = MiEntradaSalida.leerCadena("Indica el nombre del fichero que deseas borrar");
        Path directorio = Path.of("./src/ejercicio_5", nombre); //ruta donde se debe encontrar el fichero de texto a borrar.
        try {
            Files.deleteIfExists(directorio); //si el fichero existia se borra. En caso contrario se lanzara una excepcion
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Muestra los ficheros de texto que tiene un directorio
     */
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

    /*
    public static void mostrarFicheros(){
        Path ruta =  Path.of("src", "ejercicio5", "salidasEjercicio5");

        try(Stream<Path> lista = Files.list(ruta)){
            System.out.println("Archivos en "+ ruta.getFileName());
            lista.forEach(p -> System.out.println(p.getFileName()));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
   }
     */

    /*
    Gemini ha dicho
El problema principal es que estás utilizando un try-with-resources para gestionar el BufferedReader que envuelve a System.in.

¿Por qué ocurre el error?
Cuando el bloque try termina, Java cierra automáticamente el recurso definido entre los paréntesis.
Al cerrar el BufferedReader, también se cierra el flujo subyacente: System.in.

Una vez que System.in se cierra, no se puede volver a abrir mientras el programa esté en ejecución.
La próxima vez que intentes leer algo (ya sea en este metodo o en otro usando MiEntradaSalida),
el Scanner o Reader lanzará un NoSuchElementException porque el "grifo" de la entrada estándar está bloqueado.

La Solución
No debes cerrar los flujos que envuelven a System.in a menos que estés seguro de que no vas a pedir más datos en el programa.
     */
}
