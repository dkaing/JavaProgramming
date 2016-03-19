
/**
 * Write a description of findgene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class findgene {
    
    public int findStopIndex(String dna, int index){
        int stop1 = dna.indexOf("tga", index);
        if (stop1 == -1 || (stop1-index)%3 !=0){
            stop1 = dna.length();
        }
        int stop2 = dna.indexOf("taa", index);
        if (stop2 == -1 || (stop2 - index) %3 !=0){
            stop2 = dna.length();
        }
        int stop3 = dna.indexOf("tag", index);
        if (stop3 == -1 || (stop3-index) %3 != 0){
            stop3 = dna.length();
        }
        return Math.min(stop1, Math.min(stop2, stop3));
    }
   
    public int c(String dna) {
        int clocation = 0;
        int c = 0;
        while(true){
            int cloc = dna.indexOf("c", clocation);
            if(cloc == -1){
                break;
            }
            if(cloc>=0){
                c = c+1;
            }
            clocation = cloc +1;
           
        }
        return c;
    }
    public int ctg(String dna) {
        int ctglocation = 0;
        int ctg = 0;
        while(true){
            int ctgloc = dna.indexOf("ctg", ctglocation);
            if(ctgloc == -1){
                break;
            }
            if(ctgloc>=0){
                ctg = ctg+1;
            }
            ctglocation = ctgloc +3;
           
        }
        return ctg;
    }
    public int g(String dna) {
        int glocation = 0;
        int g = 0;
        while(true){
            int gloc = dna.indexOf("g", glocation);
            if(gloc == -1){
                break;
            }
            if(gloc>=0){
                g = g+1;
            }
            glocation = gloc +1;
           
        }
        return g;
    }
    
    public StorageResource storegenes(String dna){
        dna = dna.toLowerCase();
        StorageResource store = new StorageResource();
        int start = 0;
        int cg = 0;
        int length = 0;
        int gene = 0;
        int startctg = 0;
        int ctg = 0;
        while(true){
            int loc = dna.indexOf("atg", start);
            if (loc == -1){
                break;
            }

            int end = findStopIndex(dna, loc+3);
            if (end!= dna.length()){
                String DNA = dna.substring(loc, end+3);

                gene = gene +1;
                if(DNA.length()>60){
                    length +=1;
                }
                float c = c(DNA);
                float g = g(DNA);
                float cgratio = (c+g)/DNA.length();
                if (cgratio>0.35){
                    cg=cg+1;
                }
                store.add(DNA);
                start = end +3;
                //startctg = ctgloc+3;
            }
            else {
                start = loc +3;
            }
            
        }
        System.out.println("Number of Genes = " +gene);
        System.out.println("cgratio = " +cg);
        System.out.println("Length over 60 = " +length);
        
        return store;
    }
    
    
    
    
    public void findgenes(String dna){
        dna = dna.toLowerCase();
        int start = 0;
        int stop = 0;
        int gene = 0;
        int clocation = 0;
        int glocation = 0;
        int c = 0;
        int g = 0;
        int cggene = 0;
        
        while(true){
            int loc = dna.indexOf("atg", start);
            int stoploc = findStopIndex(dna, start);
            
            if (loc == -1) {
                break;
            }
            if (stoploc == -1){
                break;
            }
            if ((stoploc - loc)%3 ==0){
                gene = gene+1;
                
                //String genestring = dna.substring(loc, stoploc+3);
                //System.out.println(genestring);
                //float cingene = c(genestring);
                //System.out.println(cingene);
                //float gingene = g(genestring);
                //System.out.println(gingene);
                //float cgratio = cingene/gingene;
                //System.out.println(cgratio);
                
                
            }

           //System.out.println("starts at "+loc);
           //System.out.println("stops at " + stoploc);
           //clocation = cloc +1;
           //System.out.println("c at = " + cloc);
           start = loc + 3;
           stop = stoploc +3;
        }
        System.out.println("genes = "+ gene);
        //System.out.println("cgenes = " + cggene);
        
        //System.out.println("c = " +c);
    }
    
    public void findAllGenes(String dna){
        dna = dna.toLowerCase();
        int start = 0;
        int stop = 0;
        int gene = 0;
        int clocation = 0;
        int c = 0;
        while(true){
            int loc = dna.indexOf("atg", start);
            if (loc == -1) {
                break;
            }
            int cloc = dna.indexOf("c", clocation);
            if (cloc == -1){
                break;
            }
            if(cloc>=0){
                c = c+1;
            }
            
            int stoploc = findStopIndex(dna, stop);

            if ((stoploc - loc)%3 ==0){
                gene = gene + 1;
            }

           //System.out.println("starts at "+loc);
           //System.out.println("stops at " + stoploc);
           clocation = cloc +1;
           System.out.println("c at = " + cloc);
           start = loc + 3;
           stop = stoploc +3;
        }
        System.out.println("genes = "+ gene);
        
    }
    
    public void testdna() {
        findgenes("atgctgcccctggggtaaatgcccgggtagatgxxxyyytgaatgxxxyyytaaatgxxtaa");

        
        
    }
    public int printMax(StorageResource store){
        int max = 0;
        for(String gene: store.data()){
            int length = gene.length();
            if(length> max){
                max = length;
            }
            else {
                max = max;
            }
        }
        return max;
    }
    public int findCTG(StorageResource store){
        int index = 0; 
        int ctg = 0;
        for(String gene: store.data()){
            int start = gene.indexOf("ctg", index);
            if((gene.length()-start)%3==0){
                ctg+=1;
            }
            index = start + 3;
        }
        return ctg;
    }
    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length());
            storegenes(s);
            System.out.println("Max = "+printMax(storegenes(s)));
            System.out.println("CTG = " +findCTG(storegenes(s)));
            
            
        }
    }
            

}
