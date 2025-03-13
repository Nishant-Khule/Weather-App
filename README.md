# Weather-App
Java Programming Micro Project

Here's a detailed line-by-line explanation of your `WeatherAppGUI` Java program, which you can use to create a **README** file.  

---

## 📌 **Overview**  
This Java program fetches real-time weather data from the **OpenWeatherMap API** for a user-specified city. It retrieves **temperature, humidity, and pressure** and displays them in the console.

---

## 🚀 **How It Works**  

### **1️⃣ Import Required Libraries**  
```java
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
```
🔹 `org.json.simple.*` → Parses the JSON response from the API.  
🔹 `java.io.*` → Handles input/output operations (reading API response & user input).  
🔹 `java.net.*` → Manages HTTP requests to fetch data from OpenWeatherMap API.  

---

### **2️⃣ Declare the API Key**  
```java
public static final String API_KEY = "7caabb97aaf507a4cfc2b26f8043ec0f";
```
🔹 Stores the **API key** (Replace with your own).  
🔹 This is required to access OpenWeatherMap's API.  

---

### **3️⃣ `getWeather()` Method - Fetches Weather Data**  
```java
public double[] getWeather() {
```
🔹 This method **fetches weather data** from OpenWeatherMap API and returns a **double array** containing temperature, humidity, and pressure.

---

#### **📌 Step 1: Ask User for City Name**
```java
BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
System.out.println("Enter City name: ");
String city = input.readLine();
```
🔹 Creates a `BufferedReader` to **take input from the user**.  
🔹 Asks the user to **enter the city name**.  

---

#### **📌 Step 2: Construct the API URL**
```java
String apiurl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + 
                "&appid=" + API_KEY + "&units=metric&lang=en";
```
🔹 Builds the **API request URL** dynamically using:  
   - **`city`** → User-input city name.  
   - **`API_KEY`** → Required for authentication.  
   - **`units=metric`** → Ensures temperature is in Celsius.  
   - **`lang=en`** → Sets response language to English.  

---

#### **📌 Step 3: Establish Connection to API**
```java
URL url = new URL(apiurl);
HttpURLConnection connect = (HttpURLConnection) url.openConnection();
connect.setRequestMethod("GET");
```
🔹 Creates a **URL object** from the API string.  
🔹 Opens an HTTP connection using `HttpURLConnection`.  
🔹 Sets the request method to `"GET"` (fetch data).  

---

#### **📌 Step 4: Check API Response Code**
```java
int responceCode = connect.getResponseCode();
if (responceCode == 200) {
```
🔹 Calls `getResponseCode()` to check if the request was successful.  
🔹 **200 OK** → API call was successful.  

---

#### **📌 Step 5: Read the API Response**
```java
BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
StringBuffer responce = new StringBuffer();
String line;
while ((line = in.readLine()) != null) {
    responce.append(line);
}
in.close();
```
🔹 **Reads the API response** using `BufferedReader`.  
🔹 Stores the response line by line into `StringBuffer response`.  

---

#### **📌 Step 6: Parse JSON Data**
```java
JSONParser parser = new JSONParser();
JSONObject jsonObject = (JSONObject) parser.parse(responce.toString());
JSONObject main = (JSONObject) jsonObject.get("main");
```
🔹 Uses **JSONParser** to parse the API response.  
🔹 Extracts the `"main"` JSON object, which contains temperature, humidity, and pressure.  

---

#### **📌 Step 7: Extract Weather Data**
```java
double temperature = ((Number) main.get("temp")).doubleValue();
double humidity = ((Number) main.get("humidity")).doubleValue();
double pressure = ((Number) main.get("pressure")).doubleValue();
```
🔹 Retrieves **temperature, humidity, and pressure** values.  
🔹 Uses **`Number` type** to handle both `Long` and `Double` values safely.  

---

#### **📌 Step 8: Return Data as Array**
```java
return new double[]{temperature, humidity, pressure};
```
🔹 Returns the extracted weather data as a **double array**.  

---

#### **📌 Step 9: Handle Errors**
```java
} else {
    System.out.println("Error: Fetching data from server!");
}
```
🔹 If the **API response is not 200**, print an error message.  

---

#### **📌 Step 10: Return Default Values (If Error Occurs)**
```java
} catch (Exception e) {
    System.out.println("Error: "+e.getMessage());
}
return new double[]{0,0,0};
```
🔹 If an exception occurs, return `[0, 0, 0]` as default values.  

---

### **4️⃣ `show()` Method - Display Weather Data**
```java
public void show(double[] weatherData) {
    System.out.println("Temperature: " + weatherData[0] + "°C");
    System.out.println("Humidity: " + weatherData[1] + "%");
    System.out.println("Pressure: " + weatherData[2] + " hPa");
}
```
🔹 Takes a **double array as input** and prints the weather details.  

---

### **5️⃣ `main()` Method - Program Execution**
```java
public static void main(String[] args) {
    WeatherAppGUI w = new WeatherAppGUI();
    double[] weatherData = w.getWeather();  // ✅ Ask for city name only once
    w.show(weatherData);  // ✅ Use fetched data
}
```
🔹 Creates an instance of `WeatherAppGUI`.  
🔹 Calls `getWeather()` **once** to fetch weather data.  
🔹 Calls `show(weatherData)` to display the weather details.  

---

## 🎯 **Expected Output**
```plaintext
Enter City name:
Mumbai
Temperature: 29.5°C
Humidity: 78.0%
Pressure: 1015.0 hPa
```

---

## 🔥 **Key Features**
✅ **Real-time weather data** from OpenWeatherMap API.  
✅ **Handles errors gracefully** (returns default values if API fails).  
✅ **Asks for city name only once** to avoid redundant inputs.  

---

## 📌 **Improvements & Future Enhancements**
1️⃣ **Add a GUI** using `Swing` or `JavaFX` instead of console output.  
2️⃣ **Error handling**: Show user-friendly messages if the API key is invalid.  
3️⃣ **More data**: Fetch wind speed, weather description, etc.  

---

## 🚀 **How to Run the Program**
1. **Install Java** on your system.  
2. **Get an API Key** from [OpenWeatherMap](https://home.openweathermap.org/api_keys).  
3. **Replace `API_KEY` in the code** with your actual API key.  
4. **Run the program** in any Java-supported IDE or terminal.  

---

## 🔗 **References**
- [OpenWeatherMap API Documentation](https://openweathermap.org/current)  
- [Java Networking (HttpURLConnection)](https://docs.oracle.com/javase/8/docs/api/java/net/HttpURLConnection.html)  

---

This README provides a clear **explanation of your code**, how it works, and how to use it. 🚀 Let me know if you need any modifications! 😊
