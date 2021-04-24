package ru.lsz;

public class App {

    public static final String[] HOSTS = new String[]{"192.168.70", "192.168.170"};
    public static final int THREAD_COUNT = 25;

    public static void main(String[] args) {
        final int range = 250/THREAD_COUNT;
        for (int j = 0; j < HOSTS.length; j++) {
            for(int i = 1; i < THREAD_COUNT; i++) {
                new Thread(new RunSearch(HOSTS[j], i* range, (i+1)* range)).start();;
            }
        }
    }
}