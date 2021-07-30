package ru.lsz;

import ru.lsz.bingAPI.GetURL;
import ru.lsz.netsearch.RunSearch;
import ru.lsz.repository.SafeFile;
import ru.lsz.repository.WriteToCSV;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class App {

    public static final Map<String, String> DEVICES = new ConcurrentHashMap<>();
    private static final String[] HOSTS = new String[]{"192.168.70", "192.168.170"};
    private static final int THREAD_COUNT = 50;

    public static void main(String[] args) throws Exception {

        saveImageFromBing();

        for (String host : HOSTS) {
            searchComputers(host);
        }

        safeToCSV();

//        copyFileToComputers();

    }

    private static void saveImageFromBing() throws Exception {
        SafeFile.saveImageToFile(GetURL.getURL());
    }

    private static void safeToCSV() {
        WriteToCSV.ToCSV(DEVICES);
    }

    private static void searchComputers(String host) throws InterruptedException {
        int index;
        final int range = 250 / THREAD_COUNT;
        Thread[] threads = new Thread[THREAD_COUNT];

        for (index = 0; index < threads.length; index++) {
            threads[index] = new Thread(new RunSearch(host, index * range, (index + 1) * range));
            threads[index].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

    }

    private static void copyFileToComputers() {
        for (Map.Entry<String, String> entry : DEVICES.entrySet()) {
            if ((entry.getValue().startsWith("lsz")
                    || entry.getValue().startsWith("LSZ"))
                    && !entry.getValue().startsWith("lsz-")) {
                SafeFile.copyFileUsingStream(entry.getValue());
            }
        }
    }
}
