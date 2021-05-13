package ru.lsz.safefile;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

public class WriteToCSV {
    public static void ToCSV(Map<String, String> networkDevices) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("Devices.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        builder.append("IP,NetName" + "\n");

        for (Map.Entry<String, String> pair : networkDevices.entrySet()) {
            builder
                    .append(pair.getKey())
                    .append(",")
                    .append(pair.getValue())
                    .append("\n");
        }

        if (pw != null) {
            pw.write(builder.toString());
            pw.close();
        }
    }
}

