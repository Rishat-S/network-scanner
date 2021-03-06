package ru.lsz.netsearch;

import java.io.IOException;
import java.net.InetAddress;

import static ru.lsz.App.DEVICES;

public class RunSearch implements Runnable {
    String hosts;
    int begin;
    int count;

    public RunSearch(String hosts, int begin, int count) {
        this.hosts = hosts;
        this.begin = begin;
        this.count = count;
    }

    private void putToMapComputers(String host, String hostName) {
        DEVICES.put(host, hostName);
        if (hostName.equals("free")) {
            System.err.println(host + ":" + hostName);
        } else {
            System.out.println(host + ":" + hostName);
        }
    }

    public void checkHosts(String subnet) throws IOException {
        String host;
        String hostName;
        InetAddress address;
        int timeout = 1000;

        for (int i = begin; i < count; i++) {
            host = subnet + "." + i;
            address = InetAddress.getByName(host);
            hostName = address.getHostName();
            if (address.isReachable(timeout)) {
                putToMapComputers(host, hostName);
            } else {
                putToMapComputers(host, "free");
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
