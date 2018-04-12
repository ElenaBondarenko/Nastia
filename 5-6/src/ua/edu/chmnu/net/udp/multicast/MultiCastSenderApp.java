package ua.edu.chmnu.net.udp.multicast;

import java.io.IOException;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static ua.edu.chmnu.net.common.CmdLineParser.extractValue;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;

public class MultiCastSenderApp {

    public static void main(String[] args) throws SocketException, IOException {
        ExecutorService service = Executors.newCachedThreadPool();

        String group = "224.0.0.3";
        int port = 5559;
        for (int i = 0; i < args.length; ++i) {
            String value = extractValue(args[i], "-g:");
            if (value != null) {
                group = value;
                continue;
            }

            value = extractValue(args[i], "-p:");
            if (value != null) {
                port = Integer.parseInt(value);
            }

        }

        MultiCastSender sender =  new MultiCastSender(group, port).setAction(() -> {
            String apiData;
            String toSend;
            try {
                apiData = WeatherGetter.sendGet();
                Weather weather = JSON.parseObject(apiData, Weather.class);
                
                Object timezone = JSONPath.eval(weather, "$.timezone");
                Object summary = JSONPath.eval(weather, "$.currently.summary");
                Object temperature = JSONPath.eval(weather, "$.currently.temperature");
                Object dailySummary = JSONPath.eval(weather, "$.daily.summary");
                
                toSend = timezone + " - " + summary + ", temperature: " + temperature + "\n" + dailySummary;
           
                return toSend.getBytes();
            } catch (Exception ex) {
                Logger.getLogger(MultiCastSenderApp.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        service.submit(sender);

        try (Scanner in = new Scanner(System.in)) {
            System.out.println("To stop press Q");
            String line;
            do {
                line = in.nextLine();
            } while (line != null && !line.equalsIgnoreCase("Q"));
        }
        sender.setActive(false);
        service.shutdown();
    }
}
