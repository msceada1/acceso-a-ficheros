package ejercicio_7;

import utils.MiEntradaSalida;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Principal {

    public static void listarDirectorioQueEmpiezaPor() {
        String nombre = MiEntradaSalida.leerCadena("Indica el nombre del directorio: ");
        Path directorio = Path.of("./src/ejercicio_7", nombre);
        String cadena = MiEntradaSalida.leerCadena("¿Por que palabra o letra empieza?");

        if (Files.isDirectory(directorio)) {
            try (Stream<Path> ficheros = Files.list(directorio)) {
                ficheros.filter(p -> p.getFileName().toString().startsWith(cadena))
                        .forEach(p -> {
                            if (Files.isDirectory(p)) {
                                System.out.printf("%s - directorio %n", p.getFileName());
                            } else {
                                try {
                                    System.out.printf("%s %.2f kb %n", p.getFileName(), Files.size(p) / 1024.0);
                                } catch (IOException e) {
                                    System.err.println(e.getMessage());
                                }
                            }
                        });
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void buscarArchivosPorExtension() {
        String nombreDirectorio = MiEntradaSalida.leerCadena("Indica el nombre del directorio:\n");
        Path directorio = Path.of("src", "ejercicio_7", nombreDirectorio);
        String extension = MiEntradaSalida.leerCadena("Indica la extension:\n");

        if (Files.isDirectory(directorio)) {
            try (Stream<Path> ficheros = Files.list(directorio)) {
                ficheros.filter(p -> p.getFileName().toString().endsWith("." + extension))
                        .forEach(p -> {
                            if (Files.isDirectory(p)) {
                                System.out.printf("%s - directorio %n", p.getFileName());
                            } else {
                                try {
                                    System.out.printf("%s %.2f kb %n", p.getFileName(), Files.size(p) / 1024.0);
                                } catch (IOException e) {
                                    System.err.println(e.getMessage());
                                }
                            }
                        });
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void buscarArchivoEnDirectorio() {
        String nombreDirectorio = MiEntradaSalida.leerCadena("Indica el nombre del directorio:\n");
        Path directorio = Path.of("src", nombreDirectorio);
        String nombreFichero = MiEntradaSalida.leerCadena("Indica el nombre del fichero");

        if (Files.isDirectory(directorio)) {
            try (Stream<Path> ficheros = Files.list(directorio)) {
                ficheros.filter(p -> p.getFileName().toString().equalsIgnoreCase(nombreFichero))
                        .forEach(p -> {
                            if (!Files.isDirectory(p)) {
                                try {
                                    System.out.println("Informacion fichero " + p.getFileName().toString() + "" +
                                            "ruta: " + p.toAbsolutePath() + "\npeso en kb: " + Files.size(p));
                                } catch (IOException e) {
                                    System.err.println(e.getMessage());
                                }
                            }
                        });
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void buscarArchivoEnDirectorioRecursiva() {
        String nombreDirectorio = MiEntradaSalida.leerCadena("Indica el nombre del directorio:\n ");
        Path directorio = Path.of("src", nombreDirectorio);
        String nombreFichero = MiEntradaSalida.leerCadena("Indica el nombre del fichero:\n");

        if (Files.isDirectory(directorio)) {

        }
    }
}
