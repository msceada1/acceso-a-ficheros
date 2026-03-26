package ejercicio_7;

import utils.MiEntradaSalida;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class Principal {

    static Scanner scanner = new Scanner(System.in);

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
        System.out.println("Introduce el directorio: \n");
        Path p = Path.of(scanner.nextLine());

        System.out.println("Introduce el nombre del fichero que quieres buscar");
        String ficheroABuscar = scanner.nextLine();

        if (Files.isDirectory(p)) {

            try (Stream<Path> ficheros = Files.find(p, 1, ((path, atr) -> {
                return path.getFileName().toString().equals(ficheroABuscar);
            }))) {
                ficheros.forEach(path -> {
                    if (Files.isDirectory(path)) {
                        System.out.printf("%s - directorio %n", path.getFileName());
                    } else {
                        try {
                            System.out.printf("%s %.2f kb %n", path.getFileName(), Files.size(path) / 1024.0);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static void buscarArchivoEnDirectorioRecursiva() {
        System.out.println("Introduce el directorio \n");
        Path p = Path.of(scanner.nextLine());

        System.out.println("Introduce el nombre del archivo a buscar");
        String archivoABuscar = scanner.nextLine();

        if (Files.isDirectory(p)) {
            try (Stream<Path> ficheros = Files.walk(p)) {
                ficheros.filter(path -> path.getFileName().toString().startsWith(archivoABuscar))
                        .forEach(path -> {
                            if (Files.isDirectory(path)) {
                                System.out.printf("%s - directorio %n", path.getFileName());
                            } else {
                                try {
                                    System.out.printf("%s %.2f kb %n", path.getFileName(), Files.size(path) / 1024.0);
                                } catch (IOException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        });
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
