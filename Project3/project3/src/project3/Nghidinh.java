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
public class Nghidinh {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        String source = "D:\\C.H.A.M CHAM CHI\\TTNT\\Project3\\new 1.txt";
        String des = "D:\\C.H.A.M CHAM CHI\\TTNT\\Project3\\2.xml";
        File in = new File(source);
        Scanner sc = new Scanner(in);
        PrintWriter out = new PrintWriter(des);
        boolean c = true, d = true, k = true, m = true;
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.charAt(0) == 'C') {
                if (!k) {
                    out.write("           </khoan>\r\n");
                    k = true;
                }
                if (!d) {

                    out.write("    </dieu>\r\n");
                    d = true;
                }
                if (!m) {
                    out.write("    </muc>\r\n");
                    m = true;
                }
                if (c) {
                    out.write("<chuong>\r\n");
                    out.write("    <tenChuong>" + s + "</tenChuong>\r\n");
                    c = false;
                } else {
                    out.write("</chuong>\r\n");
                    out.write("<chuong>\r\n");
                    out.write("    <tenChuong>" + s + "</tenChuong>\r\n");
                    c = false;
                }
            } else if (s.charAt(0) == 'M') {
                if (m) {
                    out.write("    <muc>\r\n");
                    out.write("        <tenMuc>" + s + "<tenMuc>\r\n");
                    m = false;
                } else {
                    out.write("    </muc>\r\n");
                    out.write("    <muc>\r\n");
                    out.write("         <tenMuc>" + s + "<tenMuc>\r\n");
                    m = false;
                }
            } else if (s.charAt(0) == 'Đ') {
                if (!k) {
                    out.write("          </khoan>\r\n");
                    k = true;
                }
                if (d) {
                    d = false;
                    out.write("        <dieu>\r\n");
                    out.write("            <tenDieu>" + s + "</tenDieu>\r\n");
                } else {
                    out.write("        </dieu>\r\n");
                    out.write("        <dieu>\r\n");
                    out.write("            <tenDieu>" + s + "</tenDieu>\r\n");
                }
            } else if (s.charAt(0) < 'a' && s.charAt(0)> '0') {
                if (k) {
                    out.write("            <khoan>\r\n");
                    out.write("                 <tenKhoan>" + s + "</tenKhoan>\r\n");
                    k = false;
                } else {
                    out.write("            </khoan>\r\n");
                    out.write("            <khoan>\r\n");
                    out.write("                  <tenKhoan>" + s + "</tenKhoan>\r\n");
                    k = false;
                }

            } else {
                    out.write("                  <diem>" + s + "</diem>\r\n");
            }

        }
        if (!k) {
                    out.write("            </khoan>\r\n");
        }
        if (!d) {
                    out.write("        </dieu>\r\n");
        }
        if (!m) {
            out.write("    </muc>");
        }
        if (!c) {
            out.write("</chuong>\r\n");
        }
        out.close();
    }

}
