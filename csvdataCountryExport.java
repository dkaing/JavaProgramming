
/**
 * Write a description of csvdata here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class csvdataCountryExport {
    public void countryinfo(CSVParser parser, String country){
        for (CSVRecord record: parser){
            if(record.get("Country").contains(country)) {
                System.out.print(record.get("Country")+": ");
                System.out.print(record.get("Exports")+": ");
                System.out.println(record.get("Value (dollars)"));
        }
    }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2){
        for (CSVRecord record: parser){
        String export = record.get("Exports");
        if(export.contains(exportitem1) && export.contains(exportitem2)){
            System.out.println(record.get("Country"));
        }
    }
    }
    
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
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record: parser){
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()){
                System.out.print(record.get("Country")+": ");
                System.out.println(record.get("Value (dollars)"));
            }
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr. getCSVParser();
        //listExportersTwoProducts(parser, "cotton", "flowers");
        bigExporters(parser, "$999,999,999,999");
        //numberOfExporters(parser, "cocoa");
        
        
    }
}


