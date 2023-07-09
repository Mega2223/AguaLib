package net.mega2223.utils.tests;

import net.mega2223.utils.objects.GraphBuilder;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphBuilderTest {
    public static void main(String[] args) {

        List<List<Double[]>> lines = new ArrayList<>();
        lines.add(new ArrayList<>());
        lines.add(new ArrayList<>());
        lines.add(new ArrayList<>());
        lines.add(new ArrayList<>());

        //lines.get(2).add(new Double[]{-1.,-.25});
        //lines.get(2).add(new Double[]{0.,0.});
        //lines.get(2).add(new Double[]{1.,4.});
        //lines.get(2).add(new Double[]{2.,16.});
        //lines.get(2).add(new Double[]{3.,32.});

        for (double i = - Math.PI; i <= Math.PI; i+= Math.PI/100) {
            lines.get(0).add(new Double[]{i,Math.sin(i)});
            lines.get(1).add(new Double[]{i,Math.cos(i)});
            lines.get(2).add(new Double[]{i,Math.pow(i,2)/5.0});
            lines.get(3).add(new Double[]{i,Math.sqrt(Math.abs(i))});
        }
        List<String> subs = Arrays.asList("Sin","Cos","Sq","Sqrt(abs)");

        double[][][] data = GraphBuilder.toDataFormat(lines);

        ArrayList<Color> colors = new ArrayList<Color>();
        colors.add(Color.blue);
        colors.add(Color.red);
        colors.add(Color.magenta);
        colors.add(Color.green);

        BufferedImage graph = GraphBuilder.buildPureGraph(data,colors,500,500);
        BufferedImage aux = GraphBuilder.buildAuxiliarLines(data,Color.gray,new double[]{3.14/3,3.0/6},500,500);
        BufferedImage transpose = GraphBuilder.transpose(graph, aux);
        GraphBuilder.buildCorners(transpose);
        BufferedImage sub = GraphBuilder.buildSubs(transpose,subs,colors,.25,GraphBuilder.DIRECTION_RIGHT);
        GraphBuilder.buildCorners(graph);
        TestWindow testWindow = new TestWindow(sub);
        testWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testWindow.pack();

    }

    static class TestWindow extends JFrame{
        TestWindow(BufferedImage img){
            setSize(200,200);
            setVisible(true);
            add(new JLabel(new ImageIcon(img)));
        }
    }
}
