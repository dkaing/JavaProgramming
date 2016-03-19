
/**
 * This program analyzes export data from different countries. The data contains 
 * the following variables: Country, Exports, and Value (dollars). 
 * 
 * by Davin Kaing
 * 
 * This assignment is part of Coursera's course, Java Programming: Solving Problems with 
 * Software, offered by Duke University.
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class csvdataCountryExport {
    
    // Get specific row of data when provided the country name.
    
    public void countryinfo(CSVParser parser, String country){
        for (CSVRecord record: parser){
            if(record.get("Country").contains(country)) {
                System.out.print(record.get("Country")+": ");
                System.out.print(record.get("Exports")+": ");
                System.out.println(record.get("Value (dollars)"));
        }
    }
    }
    
    // Find the list of exporters when given two exported items.
    
    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2){
        for (CSVRecord record: parser){
        String export = record.get("Exports");
        if(export.contains(exportitem1) && export.contains(exportitem2)){
            System.out.println(record.get("Country"));
        }
    }
    }
    
    // Find the number of exporters when given an exported item.
    
    public void numberOfExporters(CSVParser parser, String exportitem) {
        int country = 0;
        for (CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportitem)){
                country +=1;
            }
        }
        System.out.println("Number of Countries with "+exportitem+": "+country);
        
    }
    
    // Find exporters with value greater than a certain amount.
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record: parser){
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()){
                System.out.print(record.get("Country")+": ");
                System.out.println(record.get("Value (dollars)"));
            }
        }
    }
    
    // Test the above methods on real data.
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr. getCSVParser();
        bigExporters(parser, "$999,999,999,999");
        
    }
}


