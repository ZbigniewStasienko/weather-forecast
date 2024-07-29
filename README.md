# Weather Forecast Spring Boot Application

## Application Description

This Spring Boot application provides a simple REST API that returns a weather forecast for the next 3 days (starting from today) for the five largest cities in Poland. It utilizes the [WeatherAPI](https://www.weatherapi.com/) service to fetch weather data.

## Usage

After the application starts correctly, the API provides the following endpoints to retrieve weather information and API documentation:

1. **Get weather forecast for largest Polish cities**
    - **URL:** [http://localhost:8080/api/weather/biggest_polish_cities](http://localhost:8080/api/weather/biggest_polish_cities)
    - **Method:** `GET`
    - **Description:** Retrieves a 3-day weather forecast for the five largest cities in Poland.

2. **API documentation endpoints:**
    - **Swagger JSON:** [http://localhost:8080/docs/swagger](http://localhost:8080/docs/swagger)
    - **Swagger YAML:** [http://localhost:8080/docs/swagger.yaml](http://localhost:8080/docs/swagger.yaml)
    - **Swagger UI:** [http://localhost:8080/swagger-ui-weather-forecast.html](http://localhost:8080/swagger-ui-weather-forecast.html)


## Prerequisites

### Check if Java 17 is installed

Open a terminal and type:
```bash
java -version
```

Output should be similar to:
```bash
java version "17.0.11" 2024-04-16 LTS
Java(TM) SE Runtime Environment (build 17.0.11+7-LTS-207)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.11+7-LTS-207, mixed mode, sharing)
```

If Java 17 is not installed, please download and install it from the: 
[oracle-website](https://www.oracle.com/pl/java/technologies/downloads/#java17)

## How to Run

### 1. Download the project:
Use the git command:
```bash
git clone https://github.com/ZbigniewStasienko/weather-forecast.git
```
Alternatively you can download the ZIP folder

### 2. Navigate to the project directory:

To navigate between directories, you can use the cd command on both Linux and Windows. To ensure we are in the correct directory for running our application
you can use the **ls command on Linux** or the **dir command on Windows** to check the contents of the directory.
For Linux, the command and its output might look like this:

```bash
~/weather-forecast$ ls
mvnw  mvnw.cmd  pom.xml  src  target
```
On Windows, you would use dir to achieve a similar result. 

The **mvnw** file, listed in the output, is crucial as it is used to run our application.

### 3. Generate your API key

To get an API key from WeatherAPI.com and set the proper fields in the "API Response Fields" directory, follow these steps:

1. Sign Up: Go to [WeatherAPI](https://www.weatherapi.com/) and sign up for an free account.
2. Generate API Key: Once logged in, navigate to the dashboard to generate your API key.
3. Select API Response Fields:

![Screenshot from 2024-07-29 14-18-12](https://github.com/user-attachments/assets/585c57a3-ade6-4f3e-b527-ada2a04546b7)

Ensure the rest of the fields are unticked and save changes.

4. Paste your key in application.yaml file.
```bash
weatherapi:
  key: 98ba5f398dcb4336af5120041242907
```
Change 98ba5f398dcb4336af5120041242907 to your key.

### 4. Run the application

After ensuring we are in the correct directory, we can run the following command on **Linux**:

```bash
./mvnw spring-boot:run
```

On **Windows**, the command would look quite similar, but we have to change the slash to a backslash:
```bash
.\mvnw spring-boot:run
```

If the application starts correctly, you will see multiple info logs in the terminal, ending with entries similar to:

```bash
INFO 11979 --- [main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
INFO 11979 --- [main] c.s.w.WeatherForecastApplication         : Started WeatherForecastApplication in 1.253 seconds (process running for 1.423)
```





