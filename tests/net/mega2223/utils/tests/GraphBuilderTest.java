package net.mega2223.utils.tests;

import net.mega2223.utils.objects.GraphBuilder;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GraphBuilderTest {
    public static void main(String[] args) {

        List<List<Double[]>> lines = new ArrayList<>();
        lines.add(new ArrayList<>());
        lines.add(new ArrayList<>());

        for (double i = - Math.PI; i < Math.PI; i+= Math.PI/1000) {
            lines.get(0).add(new Double[]{i,Math.sin(i)});
            lines.get(1).add(new Double[]{i,Math.cos(i)});
        }

        double[][][] data = GraphBuilder.toDataFormat(lines);

        ArrayList<Color> colors = new ArrayList<Color>();
        colors.add(Color.blue);
        colors.add(Color.red);
        BufferedImage graph = GraphBuilder.buildPureGraph(data,colors,300,300);
        BufferedImage aux = GraphBuilder.buildAuxiliarLines(data,Color.gray,new double[]{3.14/2,0.5},300,300);
        new TestWindow(GraphBuilder.transpose(graph,aux)).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    static class TestWindow extends JFrame{
        TestWindow(BufferedImage img){
            setSize(200,200);
            setVisible(true);
            add(new JLabel(new ImageIcon(img)));
        }
    }
}
