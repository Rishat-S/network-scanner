package ru.lsz;

import java.io.IOException;
import java.net.UnknownHostException;

public class App {
    public static void main(String[] args) throws UnknownHostException, IOException {
        for(int i = 1; i < 25; i++) {
            new Thread(new RunSearch("192.168.0", i*10, (i+1)*10)).start();;
        }
    }
}
