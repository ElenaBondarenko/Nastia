/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.net.udp.multicast;

/**
 *
 * @author bastila
 */
public class WeatherCurrently {
    
    private long temperature;
    private String summary;
    
    public long getTemperature() {
        return temperature;
    }
    
    public void setTemperature(long temperature) {
        this.temperature = temperature;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
}
