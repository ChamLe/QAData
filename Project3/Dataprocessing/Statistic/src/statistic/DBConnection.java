/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import statistic_ver2.sentence;

/**
 *
 * @author thinkpad
 */
public class DBConnection {

    private Connection con;
    private HashMap hm;

    public DBConnection(HashMap hm) {
        this.hm = hm;
        String url = "jdbc:mysql://127.0.0.1:3306/QADatabase?useUnicode=true&characterEncoding=utf-8";
        String s = "com.mysql.jdbc.Driver";
        try {
            Class.forName(s);
            con = DriverManager.getConnection(url, "adminFf1Pbuj", "c5h8CW-Kb-HV");
            System.out.println("successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DBConnection() {
        String url = "jdbc:mysql://127.0.0.1:3306/QADatabase?useUnicode=true&characterEncoding=utf-8";
        String s = "com.mysql.jdbc.Driver";
        try {
            Class.forName(s);
            con = DriverManager.getConnection(url, "adminFf1Pbuj", "c5h8CW-Kb-HV");
            System.out.println("successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void addRow(tagST tagst) {
        String sql = "insert into tbl_tag(tag_name,tag_content,tag_counter) values(?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, tagst.getTag());
            stm.setString(2, tagst.getTag_content());
            stm.setInt(3, tagst.getCount());
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("\n"+tagst.getTag() + " " + tagst.getTag_content() + " ");
            e.printStackTrace();
        }
    }

    public void writeDB() {
        Set set = hm.entrySet();
        // Lay mot iterator
        Iterator i = set.iterator();
        String tag = "";
        String tag_content = "";
        int tag_counted;
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            tag = me.getKey().toString();
            System.out.println(me.getKey() + ": ");

            HashMap temp = (HashMap) me.getValue();
            Set st = temp.entrySet();
            Iterator istm = st.iterator();
            while (istm.hasNext()) {
                Map.Entry m = (Map.Entry) istm.next();
                System.out.println(m.getKey() + "  " + m.getValue() + "\n");
                tag_content = m.getKey().toString();
                tag_counted = Integer.parseInt(m.getValue().toString());
                addRow(new tagST(tag, tag_content, tag_counted));
            }
        }
    }

    public void saveSentence(HashMap hm) {
        String sql = "insert into tbl_statistic_sen(a,ac,ano,dl,if1,if2,if3,if4,qt,l,sp,ti,tl,tv,tp,v) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            //HashMap hm = sen.getSen();
            Set set = hm.entrySet();
            // Lay mot iterator
            Iterator i = set.iterator();
            int j = 1;
            while (i.hasNext() && j<=16) {
                Map.Entry me = (Map.Entry) i.next();
                switch(me.getKey().toString()){
                    case "a":{
                        stm.setString(1,me.getValue().toString());
                        break;
                    }
                    case "ac":{
                        stm.setString(2,me.getValue().toString());
                        break;
                    }
                    case "ano":{
                        stm.setString(3,me.getValue().toString());
                        break;
                    }
                    case "dl":{
                        stm.setString(4,me.getValue().toString());
                        break;
                    }
                    case "if1":{
                        stm.setString(5,me.getValue().toString());
                        break;
                    }
                    case "if2":{
                        stm.setString(6,me.getValue().toString());
                        break;
                    }
                    case "if3":{
                        stm.setString(7,me.getValue().toString());
                        break;
                    }
                    case "if4":{
                        stm.setString(8,me.getValue().toString());
                        break;
                    }
                    case "qt":{
                        stm.setString(9,me.getValue().toString());
                        break;
                    }
                    case "l":{
                        stm.setString(10,me.getValue().toString());
                        break;
                    }
                    case "sp":{
                        stm.setString(11,me.getValue().toString());
                        break;
                    }
                    case "ti":{
                        stm.setString(12,me.getValue().toString());
                        break;
                    }
                    case "tl":{
                        stm.setString(13,me.getValue().toString());
                        break;
                    }
                    case "tv":{
                        stm.setString(14,me.getValue().toString());
                        break;
                    }
                    case "tp":{
                        stm.setString(15,me.getValue().toString());
                        break;
                    }
                    case "v":{
                        stm.setString(16,me.getValue().toString());
                        break;
                    }
                    
                }
//                System.out.println(me.getKey() + ": ");
             //   stm.setString(j, me.getValue().toString());
               // j++;
                
            }//System.out.println("j = "+j);
            stm.executeUpdate();
        } catch (Exception e) {
           // System.out.println(tagst.getTag() + " " + tagst.getTag_content() + " ");
            e.printStackTrace();
        }
    }
}
