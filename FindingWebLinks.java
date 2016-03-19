
/**
 * Write a description of url here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.net.*;
import java.io.*;
import edu.duke.*;


public class url {
    public void findURLs(String url){
        URLResource page = new URLResource(url);
        String source = page.asString();
        int d = 0;
        int f = 0;
        int start = 0;
        int https = 0;
        int http = 0;
        int com = 0;
        int dots = 0;
        int comdash = 0;
        int comdashandcom = 0;
        int comend = 0;
        
        while(true){
            int index = source.indexOf("href=", start);
            if (index == -1) {
                break;
            }
            if(source.indexOf("href")>=0){
                d +=1;
            }
            int firstQuote = index +6;
            int endQuote = source.indexOf("\"", firstQuote);
            String sub = source.substring(firstQuote, endQuote);
            //System.out.println(sub);
            if(sub.startsWith("http")){
                
                http = http +1;
            }
            if(sub.startsWith("https")){
                
                https = https +1;
            }
            if (sub.indexOf(".com")>=0){
            
            com +=1;
            }
            int loc = sub.indexOf(".");
            
            while(loc!=-1){
                loc = sub.indexOf(".", loc+1);
                if (loc >=0){
                    dots+=1;
                }
            
            }
            if (sub.endsWith(".com/" )||sub.endsWith(".com")){
                comdashandcom +=1;
            }
            
            if (sub.endsWith(".com/" )){
                comdash +=1;
            }
            if (sub.endsWith(".com" )){
                comend +=1;
            }
            
            f+=1;
            
            start = endQuote +1;
        }
        System.out.println("URLs = "+http);
        System.out.println("https = " + https);
        System.out.println("com = " + com);
        System.out.println("ends with com/ = " + comdash);
        System.out.println("com/com = " + comdashandcom);
        System.out.println("ends with com = " + comend);
        System.out.println("dots = " + dots);
    }
 
    
    public void testURL() {
        findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
    }
}
