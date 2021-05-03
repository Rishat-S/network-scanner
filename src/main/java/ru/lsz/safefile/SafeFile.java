package ru.lsz.safefile;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SafeFile {
    public static void saveImageToFile(String url, String compName) {
        String[] name = compName.split("\\.");
        String screensaverPath = "\\C$\\screensaver\\DominantScreenSaver.jpg";
        String pathToPastImage = "\\\\" + name[0] + screensaverPath;

        try (InputStream in = new URL(url).openStream()) {

            Files.copy(in, Paths.get(pathToPastImage), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFileUsingStream(String compName) throws IOException {
        String[] name = compName.split("\\.");
        String screensaverPath = "\\C$\\screensaver\\DominantScreenSaver.jpg";
        String pathToPastImage = "\\\\" + name[0] + screensaverPath;

        try (InputStream is = new FileInputStream("\\\\lsz0055\\C$\\screensaver\\DominantScreenSaver.jpg");
             OutputStream os = new FileOutputStream(pathToPastImage);) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
