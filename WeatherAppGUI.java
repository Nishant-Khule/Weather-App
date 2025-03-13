import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAppGUI {
    public static final String API_KEY = "7caabb97aaf507a4cfc2b26f8043ec0f";
    public double[] getWeather(){
        try{
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter City name: ");
            String city = input.readLine();
            String apiurl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric&lang=en";
            URL url = new URL(apiurl);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");

            int responceCode = connect.getResponseCode();
            if (responceCode == 200){
                BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                StringBuffer responce = new StringBuffer();
                String line;
                while ((line = in.readLine()) != null){
                    responce.append(line);
                }
                in.close();

                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(responce.toString());
                JSONObject main = (JSONObject) jsonObject.get("main");
                double temperature = ((Number) main.get("temp")).doubleValue();
                double humidity = ((Number) main.get("humidity")).doubleValue();
                double pressure = ((Number) main.get("pressure")).doubleValue();
                return new double[]{temperature,humidity,pressure};
            }else {
                System.out.println("Error: Fetching data from server!");
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    return new double[]{0,0,0};
    }

    public void show(double[] weatherData) {
        System.out.println("Temperature: " + weatherData[0] + "°C");
        System.out.println("Humidity: " + weatherData[1] + "%");
        System.out.println("Pressure: " + weatherData[2] + " hPa");
    }

    public static void main(String[] args) {
        WeatherAppGUI w = new WeatherAppGUI();
        double[] weatherData = w.getWeather();  // ✅ Ask for city name only once
        w.show(weatherData);  // ✅ Use fetched data
    }

}