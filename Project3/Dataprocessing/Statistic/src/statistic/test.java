/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author thinkpad
 */
public class test {

    private Connection con;
    // private HashMap hm;

    public test() {
        //this.hm = hm;
        String url = "jdbc:mysql://127.0.0.1:3306/QADatabase";
        String s = "com.mysql.jdbc.Driver";
        try {
            Class.forName(s);
            con = DriverManager.getConnection(url, "adminFf1Pbuj", "c5h8CW-Kb-HV");
            System.out.println("successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void addRow(tagST tagst){
        String sql = "insert into tbl_statistic(tag_name,tag_content,tag_counter) values(?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, tagst.getTag());
            stm.setString(2,tagst.getTag_content());
            stm.setInt(3, tagst.getCount());
            stm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    public static void main(String[] args) throws FileNotFoundException {
        new test().addRow(new tagST("a","so luong",1));
    }
}
