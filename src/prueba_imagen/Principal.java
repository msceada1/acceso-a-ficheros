package prueba_imagen;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Principal {

    public static void main(String[] args) {

        Path imagen = Path.of("src", "prueba_imagen", "pradera.bmp");
        Path imagenInversa = Path.of("src", "prueba_imagen", "inversa.bmp");

        try (FileChannel canal = FileChannel.open(imagen, StandardOpenOption.READ);
             FileChannel canalNuevo = FileChannel.open(imagenInversa, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {

            ByteBuffer byteBuffer = ByteBuffer.allocate(54);
            canal.read(byteBuffer);
            byteBuffer.flip();

            canalNuevo.write(byteBuffer);

            ByteBuffer byteBuffer2 = ByteBuffer.allocate(3);

            while (canal.read(byteBuffer2) > 0) {
                byteBuffer2.flip();
                int color = byteBuffer2.get() & 0xFF;
                int invertido = 255 - color;
                canalNuevo.write(byteBuffer2);
                byteBuffer2.clear();
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
