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
public class Weather {
    
    private String              timezone;
    private WeatherCurrently    currently;
    private WeatherDaily        daily;
    
    public String getTimezone() {
        return timezone;
    }
    
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    
    public WeatherCurrently getCurrently() {
        return currently;
    }
    
    public void setCurrently(WeatherCurrently currently) {
        this.currently = currently;
    }
    
    public WeatherDaily getDaily() {
        return daily;
    }
    
    public void setDaily(WeatherDaily daily) {
        this.daily = daily;
    }
    
}
