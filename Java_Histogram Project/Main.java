package com.company;
import javax.swing.*;

public class Main {
public static void main(String[] args) {
        HistogramLetters pan = new HistogramLetters();
        JFrame f = new JFrame("Pie Chart");
        f.add(pan);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000,1000);
        f.setVisible(true);
        }
}