package ru.lsz.safefile;

import java.io.*;

public class SafeFile {

    private static String getPathToPastImage(String compName) {
        String[] name = compName.split("\\.");
        String screensaverPath = "\\C$\\screensaver\\DominantScreenSaver.jpg";
        return "\\\\" + name[0] + screensaverPath;
    }

    public static void copyFileUsingStream(String compName) throws IOException {
        String pathToPastImage = getPathToPastImage(compName);

        final String pathFromCopyImage = "\\\\lsz0055\\C$\\screensaver\\DominantScreenSaver.jpg";
        try (InputStream is = new FileInputStream(pathFromCopyImage);
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
