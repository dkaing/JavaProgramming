
/**
 * This program analyzes weather data that contains the following variable:
 * TimeEST, Temperature, Dew Point, Humidity, Sea Level Pressure, Visibility, 
 * Wind Direction, Wind Speed, Gust Speed, Precipitation, Conditions, Wind Degree, 
 * and DateUTC. This program uses TimeEST, Temperature, Dew Point, Humidity, and 
 * DateUTC.
 * 
 * by Davin Kaing 
 * 
 * March 18, 2016
 * 
 * This assignment is part of Coursera's course, Java Programming: Solving Problems with 
 * Software, offered by Duke University.
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class csvweather {
    
    // Find coldest hour in file.
    
    public CSVRecord coldestHourinFile(CSVParser parser){
        CSVRecord coldest = null;
        for (CSVRecord current:parser){
        if(!current.get("TemperatureF").contains("N/A")){
            if(coldest == null){
                coldest = current;
            }
            else {
                double currentT = Double.parseDouble(current.get("TemperatureF"));
                double coldestT = Double.parseDouble(coldest.get("TemperatureF"));
                if(currentT< coldestT){
                    coldest = current;
                }
                
            }
        }
        }
        return coldest;
    }
    
    // Find file in a list of files that has the coldest temperature.
    
    public void fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldest = null;
        String coldestfile = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourinFile(fr.getCSVParser());
            String filename = f.getName();
            if(coldest == null){
                coldest = current;
            }
            else {
                double coldestT = Double.parseDouble(coldest.get("TemperatureF"));
                double currentT = Double.parseDouble(current.get("TemperatureF"));
                if(currentT<coldestT){
                    
                    coldest = current;
                    coldestfile = f.getName();
                }
            }
        }
        System.out.println("File with the Coldest Temperature of Selected is: " + coldestfile +" with Temperature at " + coldest.get("TemperatureF"));
    }
    
    // Find file with the lowest humidity.
    
    public void fileWithLowestHumidity(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowest = null;
        String lowesthumidityfile = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            String filename = f.getName();
            if(lowest == null){
                lowest = current;
            }
            else {
                double lowestH = Double.parseDouble(lowest.get("Humidity"));
                double currentH = Double.parseDouble(current.get("Humidity"));
                
                if(currentH<lowestH){
                    
                    lowest = current;
                    lowesthumidityfile = f.getName();
                }
            }
        }
        System.out.println("File with the Lowest Humidity of Selected is: " + lowesthumidityfile +" with Humidity at " + lowest.get("Humidity")+" at the following date and time " +lowest.get("DateUTC"));
    
    }
    
    // Find the lowest humidity in a file.
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowesthumidity = null;
        for(CSVRecord current: parser){
            if(!current.get("Humidity").contains("N/A")){
                
            if(lowesthumidity == null){
                lowesthumidity = current;
            }
            else{
                
                double lowestH = Double.parseDouble(lowesthumidity.get("Humidity"));
                double currentH = Double.parseDouble(current.get("Humidity"));
                if(currentH<lowestH){
                    lowesthumidity = current;
                }
            }
        }
        }
        return lowesthumidity;
    }
    
    // Find the average temperature in a file.
    
    public double averageTemperatureInFile(CSVParser parser){
        CSVRecord data = null;
        double d = 0;
        double u = 0;
        for(CSVRecord current: parser){
            double c = Double.parseDouble(current.get("TemperatureF"));
            d +=c;
            u +=1;
        }
        return d/u;
        
    }
    
    // Find the average temperature with the higest humidity in the file.
    
    public void averageTemperatureWithHighHumidityInFile(CSVParser parser, double value){
        CSVRecord highesthumidity = null;
        CSVRecord data = null;
        double d = 0;
        double u = 0;
        for(CSVRecord current: parser){
            if( highesthumidity == null){
                highesthumidity = current;
                d = Double.parseDouble(highesthumidity.get("TemperatureF"));
                u = 1;
            }
            else {
                double highhum = Double.parseDouble(highesthumidity.get("Humidity"));
                double currenthum = Double.parseDouble(current.get("Humidity"));

                if(currenthum>= value){
                    double hightemp = Double.parseDouble(current.get("TemperatureF"));
                   d+=hightemp;
                   u+=1;
                }
                
                if(currenthum>highhum){
                    System.out.println("Current Another Sec "+ currenthum);
                    highesthumidity = current;
                }  
            }
        }
        
        double highesthum = Double.parseDouble(highesthumidity.get("Humidity"));
        double avg =  d/u;
        if(highesthum>value){
            System.out.println("Average Temp when high Humidity is " + avg);
        } else {
            System.out.println("No temperatures with that humidity");
        }
    }
        
    // Test average temperature with high humidity.
    
    public void testavgtempwithhighhumidity() {
        FileResource fr = new FileResource();
        averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
    }
    
    // Test average temperature.
    
    public void testavetemp(){
        FileResource fr = new FileResource();
        double avg = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average Temperature is " +avg);
    }
    
    // Test coldest hour in file method.
        
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourinFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEDT") );
    }
    
    // Test lowest humidity method.
    
    public void testLowestHumidity(){
        FileResource fr = new FileResource();
        CSVRecord lowesthum = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was "+ lowesthum.get("Humidity")+ " at " + lowesthum.get("DateUTC"));

    }
}
