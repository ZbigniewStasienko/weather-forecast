# Weather Forecast Spring Boot Application

## Application Description

This Spring Boot application provides a simple REST API that returns a weather forecast for the next 3 days for the five largest cities in Poland. It utilizes the WeatherAPI service to fetch weather data.

## Usage

After the application starts correctly, the API provides the following endpoints to retrieve weather information and API documentation:

1. **Get Weather Forecast for Largest Polish Cities**
    - **URL:** [http://localhost:8080/api/weather/biggest_polish_cities](http://localhost:8080/api/weather/biggest_polish_cities)
    - **Method:** `GET`
    - **Description:** Retrieves a 3-day weather forecast for the five largest cities in Poland.

2. **API Documentation Endpoints:**
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

### 3. Run the application

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





