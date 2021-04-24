package ru.lsz;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class App {

    public static final Map<String, String> COMPUTERS = new ConcurrentHashMap<>();
    public static final String[] HOSTS = new String[]{"192.168.70", "192.168.170"};
    public static final int THREAD_COUNT = 50;
    public static final int TIMEOUT = 35;

    public static void main(String[] args) throws InterruptedException {

        int index;
        final int range = 250 / THREAD_COUNT;

        for (String host : HOSTS) {
            for (index = 0; index < THREAD_COUNT; index++) {
                new Thread(new RunSearch(host, index * range, (index + 1) * range)).start();
            }
        }

        TimeUnit.SECONDS.sleep(TIMEOUT);

        COMPUTERS.values().stream()
                .sorted()
                .forEach(System.out::println);


    }
}