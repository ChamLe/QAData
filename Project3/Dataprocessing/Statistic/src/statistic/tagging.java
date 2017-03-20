/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * 0
 *
 * @author Thien
 * @description: huong dan su dung thu vien xmlpull
 */
public class tagging {

    private HashMap hm = new HashMap();
    private XmlPullParserFactory factory;
    private XmlPullParser xpp;

    private void addTag(String tag, String content) {
        if (hm.containsKey(tag)) {
            HashMap tmp = (HashMap) hm.get(tag);
            if (tmp.containsKey(content)) {
                int i = (int) tmp.get(content) + 1;
                tmp.put(content, i);
            } else {
                tmp.put(content, 1);
            }
            hm.put(tag, tmp);
        } else {
            HashMap tmp = new HashMap();
            tmp.put(content, 1);
            hm.put(tag, tmp);
        }
    }

    
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

            while (eventType != XmlPullParser.END_DOCUMENT) {//END_DOCUMENT là hết văn bản
                String tagName = xpp.getName();//lấy tên thẻ

                switch (eventType) {
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG: {//nếu là thẻ mớ đầu, bên trong đoạn này c có thể xử lý nhiều loại thẻ khác nhau
                        if (tagName.equals("question") || tagName.equals("answer") || tagName.equals("test")||tagName.equals("base")||tagName.equals("root")) {
                            break;
                        } else {
                            if (XmlPullParser.TEXT == 4) {
                                eventType = xpp.next();
                                text = xpp.getText();
                                text = text.trim();
                                //System.out.println(tagName+"  "+text+"\n");
                                addTag(tagName, text);
                            }
                            break;
                        }//tương tự với các thẻ khác

                    }
                    case XmlPullParser.END_TAG: {//nếu là thẻ cuối của thẻ nào đó thì làm gì đó
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
    public void writeresult(){
         //System.out.println(filePath);
         Set set = hm.entrySet();
        // Lay mot iterator
        Iterator i = set.iterator();
         while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
             System.out.println(me.getKey() + ": ");
            HashMap temp = (HashMap) me.getValue();
            Set st = temp.entrySet();
            Iterator istm = st.iterator();
            while (istm.hasNext()) {
                Map.Entry m = (Map.Entry) istm.next();
                System.out.println(m.getKey() + "  " + m.getValue() + "\n");
            }
        }
    }
    public void saveDB(){
        new DBConnection(hm).writeDB();
    }
    public static void main(String[] args) throws FileNotFoundException {
        tagging tag = new tagging();
         File folder = new File("D:\\NCKH\\Project3\\Dataprocessing\\Statistic\\data");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
           
            if (file.isFile()) {
                tag.tag("D:\\NCKH\\Project3\\Dataprocessing\\Statistic\\data\\"+file.getName().toString());
            }
            System.out.println(file.getName()+"\n");
        }
       // tag.writeresult();
       tag.saveDB();
    }
}

