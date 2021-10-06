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

        Color[] colors = {Color.orange, Color.green, Color.cyan};

        for (double g = -40; g <= 40; g = g + 1) {
            double constant = g;
            eba.add(new double[]{constant,constant*constant});



            red = renderWithGrid(vai,fui, new Dimension(400, 400), colors,10);
            Window.setLabel(red);
            Window.secLabel.setText((int) g + "");

            Thread.sleep(20);
            for (List<double[]> accc : vai) {
                if (accc.size() > 600) {
                    accc.remove(0);
                }
            }


            List<double[]> first = vai.get(0);

            double minX = first.get(0)[0];
            double maxX = first.get(0)[0];
            double minY = first.get(0)[1];
            double maxY = first.get(0)[1];



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
