package net.Mega2223.utils.tests;

import net.Mega2223.utils.imageTools;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.Mega2223.utils.objects.graphRenderer.getImage;
import static net.Mega2223.utils.objects.graphRenderer.renderWithGrid;

public class graphicTest1 {

    public static void main(String[] args) throws InterruptedException, IOException {


        BufferedImage red;

        window Window = new window();
        List<double[]> eba = new ArrayList<>();
        List<double[]> eba2 = new ArrayList<>();
        List<double[]> eba3 = new ArrayList<>();
        List<double[]> ebay = new ArrayList<>();
        List<List<double[]>> vai = new ArrayList<>();
        List<List<double[]>> fui = new ArrayList<>();

        vai.add(eba);
        vai.add(eba2);
        vai.add(eba3);
        fui.add(ebay);

        Color[] colors = {Color.red, Color.green, Color.cyan};

        for (double g = -4; g != -6; g = g + .1) {
            //h(t)=20t-10t²

            double constant = Math.sin(g);

            eba.add(new double[]{g,constant});
            eba2.add(new double[]{g,constant/2});



            red = renderWithGrid(vai,fui, new Dimension(400, 400), colors,5);
            Window.setLabel(red);
            Window.secLabel.setText((int) g + " : " + (int)constant);

            Thread.sleep(20);
            for (List<double[]> accc : vai) {
                if (accc.size() > 3000) {
                    accc.remove(0);
                }
            }



        }

    }

    private static class window extends JFrame {
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
            setIconImage(imageTools.getImageByURL("https://avatars.githubusercontent.com/u/59067466?s=400&u=9d154cbed85befb100018e3c9e4708875b51b141&v=4"));
            setTitle("Teste de gráficos");
        }

        public void setLabel(BufferedImage img) {
            label.setIcon(new ImageIcon(img));
        }
    }

}
