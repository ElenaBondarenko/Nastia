/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.net.udp.multicast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author bastila
 */
public class WeatherGetter {

    private final static String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

	WeatherGetter http = new WeatherGetter();
	http.sendGet();
    }
        
    public static String sendGet() throws Exception {

	String url = "https://api.darksky.net/forecast/8076c7e3e4a09883789087aa2731b0c1/37.8267,-122.4233";
		
	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	// optional default is GET
	con.setRequestMethod("GET");

	//add request header
	con.setRequestProperty("User-Agent", USER_AGENT);

	int responseCode = con.getResponseCode();

	BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        //System.out.println(response.toString());
        return response.toString();
    } 
}
