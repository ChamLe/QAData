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
public class Phuluc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        String source = "D:\\C.H.A.M CHAM CHI\\TTNT\\Project3\\new 2.txt";
        String des = "D:\\C.H.A.M CHAM CHI\\TTNT\\Project3\\2.xml";
        File in = new File(source);
        Scanner sc = new Scanner(in);
        PrintWriter out = new PrintWriter(des);
        while (sc.hasNext()) {
            
            
            out.write("<soHieuNguyHiem>"+sc.nextLine()+"</soHieuNguyHiem>\r\n");
      
        
      }
        out.close();
    }
    
}
