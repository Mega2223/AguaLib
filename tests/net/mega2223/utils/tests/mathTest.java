package net.mega2223.utils.tests;

import net.mega2223.utils.objects.GraphRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class mathTest {

    public static void main(String[] args) {

        double temp = 1;
        double volt = 100;

        List<List<double[]>> lists = new ArrayList<>();
        List<double[]> act = new ArrayList<>();

        for(double rounds = 1; rounds < 10000; rounds++){
            temp = temp + (volt/rounds);
            System.out.println(temp);
            act.add(new double[]{rounds, temp});
        }

        lists.add(act);

        Color[] color = new Color[]{Color.BLUE};
        Dimension dim = new Dimension(260,260);//todo só uma lista
        GraphRenderer renderer = new GraphRenderer(lists,dim,color);

        JFrame fram = new JFrame();


        fram.setLayout(new FlowLayout());

        fram.add(new JLabel(new ImageIcon(renderer.renderWithGrid(new ArrayList<>(),new double[]{1000,100}))));
        fram.setVisible(true);
        fram.setSize(300,300);
        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
