/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classify;

/**
 *
 * @author Babycat
 */
public class Word_pro {
    private String word;
    private double pro;

    public Word_pro() {
    }

    public Word_pro(String word, double pro) {
        this.word = word;
        this.pro = pro;
    }

    public String getWord() {
        return word;
    }

    public double getPro() {
        return pro;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setPro(double pro) {
        this.pro = pro;
    }
    
}
