package ru.lsz;

public class App {

    public static final String HOSTS = "192.168.70";
    public static final int THREAD_COUNT = 25;

    public static void main(String[] args) {
        final int range = 250/THREAD_COUNT;
        for(int i = 1; i < THREAD_COUNT; i++) {
            new Thread(new RunSearch(HOSTS, i* range, (i+1)* range)).start();;
        }
    }
}