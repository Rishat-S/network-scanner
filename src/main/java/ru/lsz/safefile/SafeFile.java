package ru.lsz.safefile;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SafeFile {

//    private static final String SAVER_JPG = "\\\\lsz0055\\C$\\screensaver\\DominantScreenSaver.jpg";
    private static final String SAVER_JPG = "C:\\1C\\DominantScreenSaver.jpg";
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

    public static void copyFileUsingStream(String compName) throws IOException {
        String pathToPastImage = getPathToPastImage(compName);

        try (InputStream is = new FileInputStream(SAVER_JPG);
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
