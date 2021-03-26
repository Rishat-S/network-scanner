package ru.lsz;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class RunSearch implements Runnable {
    String hosts;
    int begin;
    int count;

    public RunSearch(String hosts, int begin, int count) {
        this.hosts = hosts;
        this.begin = begin;
        this.count = count;
    }

    public void checkHosts(String subnet) throws UnknownHostException, IOException{
        int timeout=1000;

        for (int i = begin; i < count ; i++){
            String host = subnet + "." + i;
            if (InetAddress.getByName(host).isReachable(timeout)){
                System.out.printf("%-15s is reachable\n", host);
            } else {
                System.err.printf("%-15s is free\n", host);
            }
        }
    }

    @Override
    public void run() {
        try {
            checkHosts(hosts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
