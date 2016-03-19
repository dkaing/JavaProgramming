
/**
 * Write a description of BabyNamesProject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.io.File;

public class BabyNamesProject {
    public void totalUniqueNames(FileResource fr){
        int totalnames = 0;
        int boynames = 0;
        int girlnames = 0;
        String old = null;
        for(CSVRecord rec: fr.getCSVParser(false)){
            String newname = rec.get(0);
            if(old == null){
                old = newname;
                totalnames = 1;
                if(rec.get(1).contains("F")){
                    girlnames = 1;
                }else{
                    boynames = 1;
                }
            }
            else{
                totalnames +=1;
                if(rec.get(1).contains("F")){
                    girlnames +=1;
                }
                else{
                    boynames +=1;
                }
                
            }
            
        }
        System.out.println("Total Names = "+totalnames);
        System.out.println("Total Unique Girl Names = "+girlnames);
        System.out.println("Total Unique Boy Names = "+boynames);
    }
    
    public int getRank(String year, String name, String gender) {
        int next = 0;
        int rank = 0;
        int value = 0;
        int notthere = 0;
        
        String File = "/Users/davinkaing/Downloads/us_babynames/us_babynames_by_year/yob"+year+".csv";
        
        FileResource fr = new FileResource(File);
        for(CSVRecord rec: fr.getCSVParser(false)){
            
            if(rec.get(1).equals(gender)){
                int current = Integer.parseInt(rec.get(2));
                
                
                if(next == 0){
                    next = current;
                    rank = 1;
                    //System.out.print("Rank = " +rank);
                    //System.out.println(" " +rec.get(0) + " Births = " + rec.get(2) );
                }
                
                else{
                    rank +=1;
                    next = current;
                }
                
                if(rec.get(0).equals(name)&& rec.get(1).equals(gender)){
                    notthere =1;
                    value = rank;
                    //System.out.print("Rank = " +rank);
                    //System.out.println(" Name = " +rec.get(0) + " Gender = "+ rec.get(1)+ " Births = " + rec.get(2) );
                }
                else{
                    notthere = 0;
                }

            }
            
        }
        if(notthere==0) {
                    rank = 50000;
                }
        return value;
    }
    

    public String getName(String year, int Rank, String gender) {
        int next = 0;
        int rank = 0;
        String Name = null;
        int there = 0;

        String File = "/Users/davinkaing/Downloads/us_babynames/us_babynames_by_year/yob"+year+".csv";
        
        FileResource fr = new FileResource(File);
        for(CSVRecord rec: fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                int current = Integer.parseInt(rec.get(2));
                if(next == 0){
                    next = current;
                    rank = 1;
                    //System.out.print("Rank = " +rank);
                    //System.out.println(" " +rec.get(0) + " Births = " + rec.get(2) );
                }
                
                else{
                    rank +=1;
                    next = current;
                }
                if(rank == Rank && rec.get(1).equals(gender)){
                    there +=1;
                    Name = rec.get(0);
                    //System.out.print("Rank = " +rank);
                    //System.out.println(" Name = " +rec.get(0) + " Gender = "+ rec.get(1)+ " Births = " + rec.get(2) );
                }
            }
        }
        if(there==0) {
                 Name = "NO NAME";
                }
        return Name;
    }
    
    
    public void whatIsNameInYear(String name, String year, String newYear, String gender){
        
        int rank = getRank(year, name, gender);
        String newname = getName(newYear, rank, gender);
        System.out.println(name + " born in " +year+ " would be " + newname + " if she was born in " + newYear);
        
            
        
    }
    
    
    public void yearOfHighestRank(String name, String gender){
        
        int year = 1880;
        int highrank = 0;
        String yearstring = null;
        int yearOfHighestRank = 0;
        while (year<=2014){
            yearstring = Integer.toString(year);
            int rank = getRank(yearstring, name, gender);
            if(highrank == 0){
                highrank = rank;
            }
            else{
                if(rank<highrank){
                    highrank = rank;
                    yearOfHighestRank = year;
                    
                }
            }
            year +=1;
            
        }
        
        System.out.println("The highest ranked year for " + name + " is " + yearOfHighestRank);
    }
    
    public void getAverageRank(String name, String gender){
        
        double highrank = 0;
        int year = 1880;
        double n = 0;
        double ave = 0;
        String yearstring = null;
        
        while(year <= 2014){
            yearstring = Integer.toString(year);
            double rank = getRank(yearstring, name, gender);
            
            highrank+=rank;
            n+=1;
            ave = highrank/n;
            year +=1;
        }
       
        System.out.println("The average rank for " + name+ " is " + ave);
    }
    
    public int getBirths(String year, int Rank, String gender) {
        int next = 0;
        int rank = 0;
        int value = 0;
        int notthere = 0;
        
        String File = "/Users/davinkaing/Downloads/us_babynames/us_babynames_by_year/yob"+year+".csv";
       
        FileResource fr = new FileResource(File);
        for(CSVRecord rec: fr.getCSVParser(false)){
            
            if(rec.get(1).equals(gender)){
                int current = Integer.parseInt(rec.get(2));
                
                
                if(next == 0){
                    next = current;
                    rank = 1;
                    //System.out.print("Rank = " +rank);
                    //System.out.println(" " +rec.get(0) + " Births = " + rec.get(2) );
                }
                
                else{
                    rank +=1;
                    next = current;
                }
                
                if(rank == Rank && rec.get(1).equals(gender)){
                    notthere =1;
                    value = Integer.parseInt(rec.get(2));
                    //System.out.print("Rank = " +rank);
                    //System.out.println(" Name = " +rec.get(0) + " Gender = "+ rec.get(1)+ " Births = " + rec.get(2) );
                }
                else{
                    notthere = 0;
                }
                
                //System.out.print("Rank = " +rank);
                //System.out.println(" " +rec.get(0) + " Births = " + rec.get(2) );
                
            }
            
        }
        if(notthere==0) {
                    rank = -1;
                }
        return value;
    }
    
    
    public void getTotalBirthsRankedHigher(String year, String name, String gender){
        int rank = getRank(year, name, gender);
        int birth = 0;
        while(rank!=1){
            rank-=1;
            int c = getBirths(year, rank, gender);
            birth+=c;
        }
        System.out.println(" Total Births Ranked Higher for " + name + " with gender " + gender + " in Year = " + year + " is "+birth);
        
    }
    
    public void testnames(){
        //FileResource fr = new FileResource();
        //totalUniqueNames(fr);
        int B = getRank("1971", "Frank", "M");
        System.out.println("Question 4 = " + B);
        String C = getName("1980", 350, "F");
        System.out.println("Question 5 = " + C);
        String D = getName("1982", 450, "M");
        System.out.println("Question 6 = " + D);
        whatIsNameInYear("Susan", "1972", "2014", "F");
        whatIsNameInYear("Owen", "1974", "2014", "M");
        yearOfHighestRank("Genevieve", "F");
        yearOfHighestRank("Mich", "M");
        getAverageRank("Susan", "F");
        getAverageRank("Robert", "M");
        getTotalBirthsRankedHigher("1990", "Emily", "F");
        getTotalBirthsRankedHigher("1990", "Drew", "M");

        
    }

}
