package ru.lsz;

import ru.lsz.bingAPI.GetURL;
import ru.lsz.netsearch.RunSearch;
import ru.lsz.safefile.SafeFile;
import ru.lsz.safefile.WriteToCSV;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class App {

    public static final Map<String, String> COMPUTERS = new ConcurrentHashMap<>();
    private static final String[] HOSTS = new String[]{"192.168.70", "192.168.170"};
    private static final int THREAD_COUNT = 50;
    private static final int TIMEOUT = 35;

    public static void main(String[] args) throws Exception {

        saveImageFromBing();

        searchComputers();

        safeToCSV();

        copyFileToComputers();

    }

    private static void saveImageFromBing() throws Exception {
        SafeFile.saveImageToFile(GetURL.getURL());
    }

    private static void safeToCSV() {
        WriteToCSV.ToCSV(COMPUTERS);
    }

    private static void searchComputers() throws InterruptedException {
        int index;
        final int range = 250 / THREAD_COUNT;

        for (String host : HOSTS) {
            for (index = 0; index < THREAD_COUNT; index++) {
                new Thread(new RunSearch(host, index * range, (index + 1) * range)).start();
            }
        }

        TimeUnit.SECONDS.sleep(TIMEOUT);

    }

    private static void copyFileToComputers() throws IOException {
        for (Map.Entry<String, String> entry : COMPUTERS.entrySet()) {
            SafeFile.copyFileUsingStream(entry.getValue());
        }
    }
}
