package openWeather.file;

import openWeather.weather.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class cityNameScanner {
    private static Scanner scanner;

    private static String getCityNameFromConsole(){
        System.out.println("City name will be read from console...");
        System.out.println("Enter city name: ");
        scanner = new Scanner(System.in);
        String cityNameFromConsole = scanner.nextLine();
        return cityNameFromConsole;
    }

    private static String getCityNameFromFile(){
        System.out.println("City name will be read from file...");
        try {
            Scanner fileIn = new Scanner(new File("C:/Users/User/IdeaProjects/AutoTestimine/input.txt"));
            String cityname = fileIn.nextLine();
            return cityname;
        }catch(FileNotFoundException e){
            return ("Input file not found: " + e);
        }
    }

    public static String getCityNameFromInput(){
        System.out.println("Insert 'R' to read city from file. \nInsert 'C' to insert city from console. \n");
        scanner = new Scanner(System.in);
        String userDecision = scanner.nextLine();

        if(userDecision.equalsIgnoreCase("R")){
            return getCityNameFromFile();
        } else if(userDecision.equalsIgnoreCase("C")){
            return getCityNameFromConsole();
        }else{
            System.out.println("Wrong input, city name will be read from file...");
            return getCityNameFromFile();
        }
    }

    public static void main(String [] args) {
        City city = new City();
        String cityName = getCityNameFromInput();
        city.setName(cityName);
        System.out.println(city.toString());
    }

}
