package net.Mega2223.utils.tests;

import net.Mega2223.utils.ImageTools;
import net.Mega2223.utils.objects.GraphRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.Mega2223.utils.objects.GraphRenderer.getImage;
import static net.Mega2223.utils.objects.GraphRenderer.renderWithGrid;

public class graphicTest1 {

    public static void main(String[] args) throws InterruptedException, IOException {


        Image red;

        window Window = new window();
        List<double[]> eba = new ArrayList<>();
        List<double[]> eba2 = new ArrayList<>();
        List<double[]> eba3 = new ArrayList<>();
        List<double[]> ebay = new ArrayList<>();
        List<List<double[]>> vai = new ArrayList<>();
        List<List<double[]>> fui = new ArrayList<>();

        vai.add(eba);
        vai.add(eba3);
        vai.add(eba2);

        fui.add(ebay);

        //eba3.add(new double[]{0,0});

        Color[] colors = {Color.green, Color.yellow, Color.blue};


        Random rand = new Random();
        double n = 2;
        double nA = n;
        double pRate = 100.001;
        boolean cool = true;
        boolean bad = false;
        for (double g = 0; g <= 1000000; g += .25) {

            int gint = (int)g;
            eba.add(new double[]{g,n*10});

            System.out.println(n);

            if(cool){
                cool = n < 100;
                if(n > 0.1){n = (n* 1.01) ;  bad = n > -100 && !cool;
                } else {if(!cool){return;}n = n-(n/100)+.01;cool = true; bad = n > -100 && !cool; }

            }
            if(bad){
                if(n > -100){
                    //cool = true;
                    n--;

                } else {
                    bad = false;
                    cool = true;
                }
            }



            //todo debugger classe
            GraphRenderer rend = new GraphRenderer(vai, new Dimension(400, 400), colors);

            Dimension dim = new Dimension(Window.getWidth()-30,  + Window.getHeight()-80);

            red = ImageTools.getScaledGraph(dim, 25, rend);
            Window.setLabel(red);
            Window.secLabel.setText((int) g +"");//+ " : " + (int)constant);


            Thread.sleep(10);
            for (List<double[]> accc : vai) {
                if (accc.size() > 2000) {
                    accc.remove(0);
                }
            }



        }

    }

    static class window extends JFrame {
        public JLabel label;
        public JLabel secLabel;

        public window() throws IOException {
            setVisible(true);
            setSize(480, 450);
            setAlwaysOnTop(true);
            label = new JLabel();
            add(label);
            secLabel = new JLabel();
            add(secLabel);
            setLayout(new FlowLayout());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setIconImage(ImageTools.getImageByURL("https://avatars.githubusercontent.com/u/59067466?s=400&u=9d154cbed85befb100018e3c9e4708875b51b141&v=4"));
            setTitle("Teste de gráficos");
        }

        public void setLabel(Image img) {
            label.setIcon(new ImageIcon(img));
        }
    }

}
