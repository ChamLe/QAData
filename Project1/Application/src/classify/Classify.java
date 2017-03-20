/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classify;

import edu.stanford.nlp.ling.WordTag;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.hus.nlp.sd.SentenceDetector;
import vn.hus.nlp.tagger.VietnameseMaxentTagger;
import textclassifyv1.pkg1.Convert;
//import vnStopWord.Tach_tu;

/**
 *
 * @author Babycat
 */
public class Classify {

    private static ArrayList<String> stopword = new ArrayList<String>();
    //private static ArrayList<ArrayList<Word_pro>> w = new ArrayList<ArrayList<Word_pro>>();
    private static double pro[] = new double[8];
//    private static int[] total = new int[8];

    private static boolean check_tag(String i) {

        if (i.equals("Np") || i.equals("Nc") || i.equals("Nu")
                || i.equals("P") || i.equals("R") || i.equals("L")
                || i.equals("M") || i.equals("E") || i.equals("C")
                || i.equals("CC") || i.equals("I") || i.equals("T")) {
            return false;
        }

        return true;
    }

    private static boolean check_word(String i) {
        for (String s : stopword) {
            if (s.equalsIgnoreCase(i)) {
                return false;
            }
        }
        if (i.length() <= 2) {
            return false;
        }
        return !i.matches("^(\\W|\\d)+$");
    }

    private static HashMap prePro(String input) throws IOException {
        File in = new File(input);
        HashMap hm = new HashMap();
        vn.hus.nlp.sd.SentenceDetector st = new SentenceDetector("models\\sentDetection\\VietnameseSD.bin.gz");
        String s[] = st.detectSentences(input);
        VietnameseMaxentTagger tagger = new VietnameseMaxentTagger();
        for (String t : s) {

            if (isConsortium(in)) {
                List<WordTag> li = tagger.tagText2(Convert.convertFromConsortiumToNormal(t));
            }
            List<WordTag> li = tagger.tagText2(t);
            for (WordTag l : li) {
                String word = l.word().replaceAll(" ", "_");
                String tag = l.tag();
                if (check_word(word) && check_tag(tag)) {
                    if (hm.containsKey(word)) {
                        int number = (int) hm.get(word);
                        hm.put(word, number + 1);
                    } else {
                        hm.put(word, 1);
                    }
                }
            }

        }

        return hm;
    }

    private static HashMap prePro_string(String input) throws IOException {

        HashMap hm = new HashMap();
        vn.hus.nlp.sd.SentenceDetector st = new SentenceDetector("models\\sentDetection\\VietnameseSD.bin.gz");
        String s[] = st.detectSentences(input);
        VietnameseMaxentTagger tagger = new VietnameseMaxentTagger();
        for (String t : s) {

            List<WordTag> li = tagger.tagText2(t);
            for (WordTag l : li) {
                String word = l.word().replaceAll(" ", "_");
                String tag = l.tag();
                if (check_word(word) && check_tag(tag)) {
                    if (hm.containsKey(word)) {
                        int number = (int) hm.get(word);
                        hm.put(word, number + 1);
                    } else {
                        hm.put(word, 1);
                    }
                }
            }

        }

        return hm;
    }

    private static int min(double a[]) {
        double m = a[0];
        int pos = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > m) {
                m = a[i];
                pos = i;
            }
        }
        return pos;
    }

    public static String classify(ArrayList<ArrayList<Word_pro>> w, int total[], String input) throws FileNotFoundException, IOException {
        File st = new File("vnStopWord.txt");
        Scanner scan = new Scanner(st);
        while (scan.hasNext()) {
            stopword.add(scan.next());
        }

        //pre_processing input
        HashMap hm = prePro(input);

        String a[] = {"Cultural", "Economy", "Education", "Entertainment", "Medical", "Politic", "Science", "Sport"};

//        File in = new File("Dictionary\\toltal_word.txt");
//        Scanner y = new Scanner(in);
//        int d = 0;
//        while (y.hasNext()) {
//            total[d] = y.nextInt();
//            d++;
//        }

        //khởi tạo mảng lưu xác suất của nhãn
        for (int t = 0; t < 8; t++) {
            pro[t] = 0;
        }

        Iterator it = hm.entrySet().iterator();
        while (it.hasNext()) {
            //read word by word in hashmap
            Map.Entry pair = (Map.Entry) it.next();
            String word = pair.getKey().toString();
            int so = (int) pair.getValue();

            // calculate probability a word with each tag 
            for (int k = 0; k < 8; k++) {
                boolean kt = false;
                for (Word_pro wp : w.get(k)) {
                    if (word.equalsIgnoreCase(wp.getWord())) {
                        kt = true;
                        pro[k] += Math.log((double) wp.getPro()) * so;
                        break;
                    } else {
                        kt = false;

                    }

                }
                if (kt == false) {
                    pro[k] += so * Math.log((double) ((double) 1 / (double) (w.get(k).size() + total[k])));
                }
            }
        }

        return a[min(pro)];
    }

    public static String classify_string(ArrayList<ArrayList<Word_pro>> w, int total[], String input) {
        String s1 = null;

        return s1;
    }

    public static boolean isConsortium(File file) {
        Character thanhs[] = new Character[]{
            769, 768, 777, 771, 803
        };

        try {
            Scanner inp = new Scanner(file);

            while (inp.hasNext()) {
                String s = inp.nextLine();

                for (Character thanh : thanhs) {
                    if (s.contains(thanh.toString())) {
                        inp.close();
                        return true;
                    }
                }
            }

            inp.close();
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(Tach_tu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
