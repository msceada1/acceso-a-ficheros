package ejercicio_12;

import utils.MiEntradaSalida;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Principal {

    public static void crearDirectorio() {
        String nombreDirectorio = MiEntradaSalida.leerCadena("Indica el nombre del directorio:\n");
        Path ruta = Path.of("src", "ejercicio_12", nombreDirectorio);
        try {
            Files.createDirectory(ruta);
            System.out.println("El directorio " + nombreDirectorio + " se ha creado con éxito");
        } catch (IOException e) {
            System.err.println("El directorio " + nombreDirectorio + " ya existe");
        }
    }

    public static void crearFicheroDeTexto() {
        String nombreFichero = MiEntradaSalida.leerCadena("Indica el nombre del fichero:\n");
        Path ruta = Path.of("src", "ejercicio_12", nombreFichero);
        try {
            Files.createFile(ruta);
            String texto = MiEntradaSalida.leerCadena("Siéntete libre:\n");
            Files.writeString(ruta, texto);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void borrarFicheroDeTexto() {
        Path ruta = Path.of("src", "ejercicio_12");
        System.out.println("Ficheros de textos: ");
        try {
            Files.list(ruta).filter(p -> p.toString().endsWith(".txt")).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String nombre = MiEntradaSalida.leerCadena("Que fichero deseas borrar?");
        Path archivo = Path.of("src", "ejercicio_12", nombre);
        try {
            Files.deleteIfExists(archivo);
            System.out.println("Fichero " + nombre + " borrado con éxito");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void mostrarFicherosCarpeta() {
        Path ruta = Path.of("src", "ejercicio_12");
        System.out.println("Directorios: ");
        try {
            Files.list(ruta).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String nombreDirectorio = MiEntradaSalida.leerCadena("Indica el nombre del directorio: ");
        Path directorio = Path.of("src", "ejercicio_12", nombreDirectorio);
        if (!Files.isDirectory(directorio)) {
            System.out.println("Error, no has seleccionado un directorio");
        } else {
            try {
                Files.list(directorio).forEach(System.out::println);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
