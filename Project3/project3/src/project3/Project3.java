/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Babycat
 */
public class Project3 {

    /**
     * @param args the command line arguments
     */
    static void convertXml(File a,String output) throws FileNotFoundException{
      
        Scanner sc = new Scanner(a);
        PrintWriter out = new PrintWriter(output);
        int d = 0;
        while(sc.hasNext()){
            
            System.out.println(sc.nextLine());
            String qu =  sc.nextLine();
            String base = sc.nextLine();
            String an = sc.nextLine();
            out.write("<test>\r\n");
            out.write("     <question>"+qu+"</question>\r\n");
            out.write("     <base>"+base+"</base>\r\n");
            out.write("     <answer>"+an+"</answer>\r\n");
             out.write("</test>\r\n");
             
         
        }
        out.close();
    }
    public static void main(String[] args) throws FileNotFoundException {
        String source ="D:\\C.H.A.M CHAM CHI\\TTNT\\Project3\\convertedfile\\loi";
        String des = "D:\\C.H.A.M CHAM CHI\\TTNT\\Project3\\convertedfile\\mistakexml\\";
        File input = new File(source);
        int i = 0;
        for(File a : input.listFiles()){
            System.out.println(a.getPath());
            convertXml(a,des+a.getName()+".xml");
            System.out.println(i);
            i++;
        }
    }
    
}
