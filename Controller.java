package com.example.demo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField cityTextField;
    @FXML
    private Button getWeatherButton;
    @FXML
    private Label temperatureLabel;
    @FXML
    private Label descriptionLabel;

    @FXML
    private void getWeather(){
        String cityName=cityTextField.getText().trim();
        if (!cityName.isEmpty()){
            String apiKey ="2119811dbcb5ab2d8a0c409b4b8b1c9a";
            String apiUrL="http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid="+apiKey;
             try {
                 String jsonResponse = WeatherApiUtil.fetchDataFromAPI(apiUrL);

                 WeatherData weatherData= WeatherApiUtil.parseJson(jsonResponse);
                 temperatureLabel.setText("TEMPERATURE:"+weatherData.getTemperature()+"°C");
                 descriptionLabel.setText("DESCRIPTION:"+weatherData.getDescription());

             }catch (Exception e){
                 e.printStackTrace();
                 temperatureLabel.setText("Error fetching weather data");
                 descriptionLabel.setText("");

             }

        }
    }
}
