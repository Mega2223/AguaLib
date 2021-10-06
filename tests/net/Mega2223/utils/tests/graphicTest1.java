package net.Mega2223.utils.tests;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static net.Mega2223.utils.objects.graphRenderer.getImage;

public class graphicTest1 {

    public static void main(String[] args) throws InterruptedException {


        BufferedImage red;

        window Window = new window();
        List<double[]> eba = new ArrayList<>();
        List<double[]> eba2 = new ArrayList<>();
        List<double[]> eba3 = new ArrayList<>();
        List<List<double[]>> vai = new ArrayList<>();
        vai.add(eba);
        vai.add(eba2);
        vai.add(eba3);
        Color[] colors = {Color.orange, Color.green, Color.cyan};

        for (double g = -400; g <= 400; g = g + 0.04) {
            double constant = g;
            eba2.add(new double[]{constant, Math.sin(constant)});
            eba3.add(new double[]{constant, Math.cos(constant - (constant / 2)) * 2});
            eba.add(new double[]{constant, Math.sin((constant * constant) / 60)});

            red = getImage(vai, new Dimension(400, 400), colors);
            Window.setLabel(red);
            Window.secLabel.setText((int) g + "");// + " : " + (int)constant);

            Thread.sleep(2);
            for (List<double[]> accc : vai) {
                if (accc.size() > 350) {
                    accc.remove(0);
                }
            }

        }

    }

    public static class window extends JFrame {
        public JLabel label;
        public JLabel secLabel;

        public window() {
            setVisible(true);
            setSize(280, 250);
            setAlwaysOnTop(true);
            label = new JLabel();
            add(label);
            secLabel = new JLabel();
            add(secLabel);
            setLayout(new FlowLayout());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void setLabel(BufferedImage img) {
            label.setIcon(new ImageIcon(img));
        }
    }

}
