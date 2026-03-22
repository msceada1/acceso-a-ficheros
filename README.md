# Ficheros y Almacenamiento Permanente en Java

Este repositorio contiene la base teórica y ejemplos prácticos sobre el manejo de información persistente en Java, basado en los contenidos de **Programación 1º DAM**.

---

## 1. Introducción
Las aplicaciones requieren manejar información que no se pierda al cerrar el programa. Esta información se guarda en **ficheros**. Incluso las bases de datos utilizan ficheros internamente para almacenar sus datos. En Java, existen múltiples formas de leer, escribir y modificar esta información.

## 2. Tipos de Ficheros
*   **Ficheros de texto:** Almacenan caracteres alfanuméricos (ASCII, UNICODE, UTF-8).
*   **Ficheros binarios:** Almacenan secuencias de dígitos binarios (imágenes, audios, etc.).

## 3. Flujos de Datos (Streams)
Un flujo es una abstracción de un canal de datos que conecta una fuente con un destino. 
*   **Independencia:** Son independientes del dispositivo (disco, red, sensores).
*   **Tipos:**
    *   **Flujos de caracteres (16 bits):** Clases `Reader` y `Writer`.
    *   **Flujos de bytes (8 bits):** Clases `InputStream` y `OutputStream`.

### Jerarquía recomendada para texto:
| Operación | Flujo (Básico) | Filtro (Buffer) |
| :--- | :--- | :--- |
| **Lectura** | `FileReader` | `BufferedReader` |
| **Escritura** | `FileWriter` | `PrintWriter` |

## 4. La Clase File
Representa una **ruta** (path) en el sistema de archivos, no el contenido del fichero. Permite manipular metadatos y la estructura de directorios.

**Métodos principales:**
*   `exists()`: Verifica si existe el archivo/directorio.
*   `isFile()` / `isDirectory()`: Comprueba el tipo.
*   `getName()` / `getAbsolutePath()`: Obtiene nombres y rutas.
*   `createNewFile()` / `mkdir()`: Crea archivos o carpetas.
*   `delete()`: Borra el elemento.
*   `list()` / `listFiles()`: Lista el contenido de un directorio.

## 5. Operaciones Básicas
El ciclo de vida estándar es: **Apertura → Operación → Cierre**.

### Try-with-resources (Java 9+)
Permite declarar los recursos fuera y gestionarlos automáticamente:
```java
File archivo = new File("ejemplo.txt");
FileReader fr = new FileReader(archivo);
BufferedReader br = new BufferedReader(fr);

try (fr; br) {
    String linea;
    while ((linea = br.readLine()) != null) {
        System.out.println(linea);
    }
} catch (IOException e) {
    System.out.println("Error de E/S: " + e.getMessage());
}
