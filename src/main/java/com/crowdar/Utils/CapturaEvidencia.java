package com.crowdar.Utils;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class CapturaEvidencia {

    public static void capturaPantallaEnDocumento(WebDriver driver, String nombreDocumento, String tituloEvidencias) throws Exception {
        // Capturar la pantalla en un ByteArrayOutputStream (en memoria)
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = ImageIO.read(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);

        // Abre o crea el documento de Word
        try (XWPFDocument doc = openOrCreateDocument(nombreDocumento)) {
            // Crear un nuevo párrafo y agregar el título de la evidencia
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(tituloEvidencias);
            run.setFontSize(13);

            // Insertar la imagen en el documento desde el ByteArrayInputStream
            run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "screenshot.png", Units.toEMU(500), Units.toEMU(350));

            // Guardar el documento
            try (FileOutputStream out = new FileOutputStream(nombreDocumento)) {
                doc.write(out);
                System.out.println("Documento guardado correctamente: " + nombreDocumento);
            }
        }

        // Pausa opcional (ajustable o reemplazable por una espera explícita)
        TimeUnit.SECONDS.sleep(1);
    }

    // Método para abrir o crear un documento de Word
    private static XWPFDocument openOrCreateDocument(String nombreDocumento) throws IOException {
        Path path = Paths.get(nombreDocumento);
        if (Files.exists(path)) {
            try {
                return new XWPFDocument(Files.newInputStream(path));
            } catch (IOException e) {
                System.err.println("Error abriendo el documento existente: " + e.getMessage());
            }
        }
        // Si el documento no existe o hubo un error al abrirlo, crear uno nuevo
        System.out.println("Creando un nuevo documento: " + nombreDocumento);
        return new XWPFDocument();
    }
}
