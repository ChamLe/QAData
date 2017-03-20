/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic_ver2;

import java.util.HashMap;

/**
 *
 * @author thinkpad
 */
public class sentence {

    private HashMap sen;

    public sentence() {
        sen = new HashMap();
        String s[] = {"a", "ac", "ano", "dl", "if1", "if2", "if3", "if4", "qt", "l", "sp", "ti", "tl", "tv", "tp", "v"};
        for (int i = 0; i < s.length; i++) {
            sen.put(s[i],"");
        }
    }

    public HashMap getSen() {
        return sen;
    }

    public void setSen(HashMap sen) {
        this.sen = sen;
    }
    

}
