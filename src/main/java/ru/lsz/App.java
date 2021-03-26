package ru.lsz;

public class App {

    public static final String HOSTS = "192.168.0";
    public static final int THREAD_COUNT = 25;

    public static void main(String[] args) {
        for(int i = 1; i < THREAD_COUNT; i++) {
            final int range = 250/THREAD_COUNT;
            new Thread(new RunSearch(HOSTS, i* range, (i+1)* range)).start();;
        }
    }
}
