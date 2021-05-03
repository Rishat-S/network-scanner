package ru.lsz;

import ru.lsz.bing.BingApiResponse;
import ru.lsz.netsearch.RunSearch;
import ru.lsz.safefile.SafeFile;
import ru.lsz.safefile.WriteToCSV;

import java.io.IOException;
import java.lang.instrument.UnmodifiableClassException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class App {

    public static final Map<String, String> COMPUTERS = new ConcurrentHashMap<>();
    public static final String[] HOSTS = new String[]{"192.168.70", "192.168.170"};
    public static final int THREAD_COUNT = 50;
    public static final int TIMEOUT = 35;

    public static void main(String[] args) throws InterruptedException, UnmodifiableClassException {

        int index;
        final int range = 250 / THREAD_COUNT;

        for (String host : HOSTS) {
            for (index = 0; index < THREAD_COUNT; index++) {
                new Thread(new RunSearch(host, index * range, (index + 1) * range)).start();
            }
        }

        TimeUnit.SECONDS.sleep(TIMEOUT);

        WriteToCSV.ToCSV(COMPUTERS);

        String imageURL = BingApiResponse.getImageURL();
//
//        COMPUTERS.values()
////                .stream().filter(s -> s.equals("lsz0055.dominantsugar.ru"))
//                .forEach(compName -> SafeFile.saveImageToFile(imageURL, compName));

        COMPUTERS.values()
                .stream()
                .filter(x -> !x.equals("lsz0055.dominantsugar.ru"))
                .forEach(compName -> {
                    try {
                        SafeFile.copyFileUsingStream(compName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

    }
}