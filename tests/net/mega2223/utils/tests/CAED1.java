package net.mega2223.utils.tests;

import net.mega2223.utils.objects.GraphRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CAED1
{

    public static final int legal = 3;
    public static void main(String[] args) {
        List<double[]> vals = new ArrayList<>();

        for (double r = 0; r <= 24; r = r + 0.1){
            //−x²+26x−120,
            double legal = -(r*r) + (26*r) - 120;
            vals.add(new double[]{r,legal});
            System.out.println(legal);
        }

        List<List<double[]>> vvv = new ArrayList<>();
        vvv.add(vals);

        GraphRenderer renderer = new GraphRenderer(vvv, new Dimension(100,100),new Color[]{Color.red});

        JFrame frame = new JFrame();
        frame.setSize(100,100);
        frame.setLayout(new FlowLayout());
        JLabel lab = new JLabel();
        frame.add(lab);
        lab.setIcon(new ImageIcon(renderer.renderWithGrid(new ArrayList<>(),new double[]{1,1})));
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
