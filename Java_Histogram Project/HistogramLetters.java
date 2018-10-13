package com.company;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;


public class HistogramLetters extends JPanel{
    //assigning color to each slice 
    Color Color() {
        Random g = new Random(); return new Color(g.nextInt(256), g.nextInt(256), g.nextInt(256));
    }

    //Number of frequency of letters you want to see, descending order of frequency
    int n = Integer.parseInt(JOptionPane.showInputDialog("Enter a number n : "));

    //paintComponent
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Graphics2D g1= (Graphics2D) g;

        Font f = new Font("SansSerif",Font.BOLD,20);
        g1.setFont(f);

        //Scanning the text
        Scanner scan = null;           //set scan to null
        try {
            scan = new Scanner(new File("Emma.txt"));
        } catch (FileNotFoundException e3) {

            e3.printStackTrace();         //if there is no file found
        }

        //set character array
        char[] L = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int[] c = new int[26];   //array c of size 26
        int sum = 0;

        while(scan.hasNextLine()) {
            String readline = scan.nextLine();
            //System.out.println("Line read: " + line);
            char[] D = readline.toCharArray();

            for(int i = 0; i < D.length; i++) {
                for(int z = 0; z < 26; z++) {
                    if(D[i] == L[z])
                    {
                        c[z]++;
                        sum++;
                        break;
                    }
                }
            }
        }
        double []frequency= new double[26];
        for(int i=0;i<26;i++){
            frequency[i]=(c[i] /  (double) sum);          //the count of letters divided by frequency
        }

        int num[] = new int[frequency.length];           //new array of size frequency
        for (int i = 0; i < frequency.length; i++) {
            num[i] = i+97;    	                        //ASCII code
        }
        //sorting the alphabets and frequencies
        for (int i = 0; i < frequency.length; i++) {
            int m = i;
            for (int k = i; k < frequency.length; k++) {
                if (frequency[k] > frequency[m]) {
                    m = k;
                }
            }
            //sort frequency using a temp variable
            double tmp;
            tmp = frequency[i];
            frequency[i] = frequency[m];
            frequency[m] = tmp;
            //sort the letters with a temp variable
            int temp;
            temp = num[i];
            num[i] = num[m];
            num[m] = temp;
        }
        //create an array to multiply the frequency by 360 to get the slice degree
        double [] arr = new double[26];
        for (int j=0;j<26;j++){
            arr[j] =  frequency[j]*360;
        }
        //Create an array to hold the starting angle position
        double[] b = new double[26];
        double total = 0;
        for(int i = 0; i<26;i++){
            total+=arr[i];
            b[i] = total;
        }
        //drawing and displaying the pie chart
        //All other events
        double pchart = ( ( (Math.round(b[n-1]*10000d)/10000d))/360 );
        g1.setColor(Color());
        g1.fill(new Arc2D.Double(100, 100, 300, 300,b[n-1],400-(arr[n-1]),Arc2D.PIE));
        g1.drawString("All Other Events, "+ Math.round((1- pchart)*10000d)/10000d, 200, 500);
        g1.fillRect(175, 490, 10, 10);

        //Events of input n displayed
        for(int i = 1; i<n+1;i++){
            g1.setColor(Color());
            g1.fill(new Arc2D.Double(100, 100, 300, 300, b[i-1], arr[i-1],Arc2D.PIE));
            g1.drawString(""+(char) num[i-1]+"  , "+ Math.round(frequency[i-1]*10000d)/10000d, 500, 50+((i-1)*20));
            g1.fillRect(475, ((i)*20)+20, 10, 10);
        }
    }
}
