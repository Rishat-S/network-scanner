package ru.lsz.safefile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

public class WriteToCSV {
    public static void ToCSV(Map<String, String> networkDevices) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("Comp.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        builder.append("IP,Comp" + "\n");

        for (Map.Entry<String, String> pair : networkDevices.entrySet()) {
//            if (pair.getValue()) {
            builder
                    .append(pair.getKey())
                    .append(",")
                    .append(pair.getValue())
                    .append("\n");
//            }
        }

        if (pw != null) {
            pw.write(builder.toString());
            pw.close();
        }
    }
}

