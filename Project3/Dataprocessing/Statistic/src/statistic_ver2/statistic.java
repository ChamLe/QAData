/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic_ver2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import statistic.DBConnection;
import statistic_ver2.sentence;

/**
 * 0
 *
 * @author Thien
 * @description: huong dan su dung thu vien xmlpull
 */
public class statistic {

    private ArrayList<HashMap> ar = new ArrayList();
    private XmlPullParserFactory factory;
    private XmlPullParser xpp;

    public void tag(String filePath) throws FileNotFoundException {
        FileInputStream inp;
        try {
            //tạo input từ file đầu vào
            inp = new FileInputStream(filePath);
            //khởi tạo phần xử lý với xml
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            xpp = factory.newPullParser();
            xpp.setInput(inp, "UTF-8");

            String text = null;//biến lưu trữ text bên trong thẻ
            //cái event type giúp mình biết là đang ở vị trí thế nào hay là hết văn bản hay chưa
            int eventType = xpp.getEventType();
            int kt = 0;
            sentence sen = new sentence();
            HashMap m = sen.getSen();
            while (eventType != XmlPullParser.END_DOCUMENT) {//END_DOCUMENT là hết văn bản
                String tagName = xpp.getName();//lấy tên thẻ

                switch (eventType) {
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG: {//nếu là thẻ mớ đầu, bên trong đoạn này c có thể xử lý nhiều loại thẻ khác nhau

                        if (tagName.equals("answer") || tagName.equals("test") || tagName.equals("base") || tagName.equals("root")) {
                            kt = 0;
                            break;
                        } else if (tagName.equals("question")) {
                            kt = 1;
                            //m = new HashMap();
                            sen = new sentence();
                            m = sen.getSen();
                            break;
                        } else {
                            eventType = xpp.next();
                            if (XmlPullParser.TEXT == 4 && kt == 1) {

                                text = xpp.getText();
                                text = text.trim();
                                //System.out.println(tagName+"  "+text+"\n");
                                if (m.containsKey(tagName)) {
                                    m.put(tagName, m.get(tagName).toString() + " " + text);
                                    // System.out.println(tagName + " " + text);
                                } else {

                                    m.put(tagName, text);
                                }
                            }
                            break;
                        }//tương tự với các thẻ khác

                    }
                    case XmlPullParser.END_TAG: {//nếu là thẻ cuối của thẻ nào đó thì làm gì đó
                        if (tagName.equals("question")) {
                            //sen.setSen(m);
                            ar.add(m);
                        }
                        break;
                    }
                }

                eventType = xpp.next();
            }
            inp.close();
        } catch (XmlPullParserException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeresult() {
        //System.out.println(filePath);
        System.out.println("a  " + "ac  " + "ano   " + "dl   " + "if1   " + "if2   " + "if3    " + "if4    " + "qt    " + "l    " + "sp    " + "ti    " + "tl    " + "tv    " + "tp    " + "v   ");
        for (int j = 0; j < ar.size(); j++) {
            HashMap hm = ar.get(j);
            Set set = hm.entrySet();
            // Lay mot iterator
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                System.out.print(me.getValue() + "   ");
            }
            System.out.println("\n");
        }

    }

    public void saveDB() {
        // new DBConnection(hm).writeDB();
        DBConnection con = new DBConnection();
        for (int i = 0; i < ar.size(); i++) {
            con.saveSentence(ar.get(i));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        statistic st = new statistic();
        File folder = new File("D:\\NCKH\\Project3\\Dataprocessing\\Statistic\\data");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                st.tag("D:\\NCKH\\Project3\\Dataprocessing\\Statistic\\data\\" + file.getName().toString());
                // st.tag("D:\\NCKH\\Project3\\Dataprocessing\\Statistic\\data\\DrivingLicence.xml");
            }
            System.out.println(file.getName() + "\n");
        }
       // st.writeresult();
        st.saveDB();
    }
}
