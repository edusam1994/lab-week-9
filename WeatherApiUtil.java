package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.JarURLConnection;
import java.net.URL;
import org.json.JSONObject;


public class WeatherApiUtil {
    public static String fetchDataFromAPI(String apiUrl) throws IOException{
        URL url = new URL(apiUrl);
        HttpURLConnection connection =(HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setReadTimeout(5000);

        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) !=null){
                response.append(line);
            }
            reader.close();
            return response.toString();
        }else {
            throw new IOException("Failed to fetch data. HTTP error code:"+responseCode);
        }
    }
    public  static WeatherData parseJson(String json){
        JSONObject jsonObject = new JSONObject(json);
        double  temperature = jsonObject.getJSONObject("main").getDouble("temp");
        String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
        return  new WeatherData(temperature , description);
    }
}
