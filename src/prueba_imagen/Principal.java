package prueba_imagen;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Principal {

    public static void main(String[] args) {

        Path imagenOrigen = Path.of("src", "prueba_imagen", "pradera.bmp"); //ruta de entrada
        Path imagenInversa = Path.of("src", "prueba_imagen", "inversa.bmp"); //ruta de salida

        //Se utiliza un FileChannel de lectura y otro de escritura para los ByteBuffer
        try (FileChannel canal = FileChannel.open(imagenOrigen, StandardOpenOption.READ);
             FileChannel canalNuevo = FileChannel.open(imagenInversa, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {

            ByteBuffer byteBuffer = ByteBuffer.allocate(54); //crea una caja de memoria (buffer) de 54 bytes
            canal.read(byteBuffer);//lee los 54 primeros bytes apuntando a la posicion 54
            byteBuffer.flip();//se mueve el puntero a la posicon 0 y deja la final en el 54

            canalNuevo.write(byteBuffer);//se copia en el canal nuevo los primeros 54 bytes

            ByteBuffer byteBuffer2 = ByteBuffer.allocate(3); //se crea un buffer de 3 bytes (rojo, azul, verde)

            while (canal.read(byteBuffer2) > 0) { //mientras sigo leyendo bytes
                byteBuffer2.flip(); //se mueve el puntero a la posicion 0

                //se recorre hasta el limite obteniendo el color de origen, se saca el invertido y se actualiza
                for (int i = 0; i < byteBuffer2.limit(); i++) {
                    int colorOrigen = byteBuffer2.get(i) & 0xFF;
                    int colorInvertido = 255 - colorOrigen;
                    byteBuffer2.put(i, (byte) colorInvertido);
                }

                //se escribe en el archivo de salida
                canalNuevo.write(byteBuffer2);
                byteBuffer2.clear();// evita el ciclo infinito
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
