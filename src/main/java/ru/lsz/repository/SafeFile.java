package ru.lsz.repository;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SafeFile {

    public static final String SAVER_JPG = "DominantScreenSaver.jpg";
    private static final String SCREEN_SAVER_JPG = "\\C$\\screensaver\\DominantScreenSaver.jpg";

    private static String getPathToPastImage(String compName) {
        String[] name = compName.split("\\.");
        return "\\\\" + name[0] + SCREEN_SAVER_JPG;
    }

    public static void saveImageToFile(String url) {
        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, Paths.get(SAVER_JPG), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFileUsingStream(String compName) {

        String pathToPastImage = getPathToPastImage(compName);

        try (InputStream is = Files.newInputStream(Paths.get(SAVER_JPG));
             OutputStream os = Files.newOutputStream(Paths.get(pathToPastImage));) {

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
