package ru.lsz.safefile;

import java.io.InputStream;
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
}
