package net.mega2223.utils.tests;

import net.mega2223.utils.objects.GraphBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GraphBuilderTest2 {
    //P0: [1.54349964E12\4.18853881E8]
    // P1: [1.57505724E12\1.6826195E9]
    // P2: [1.60661484E12\2.358640101E9]
    // P3: [1.63817244E12\2.967484822E9]
    // P4: [1.66973004E12\3.574679079E9]

    public static void main(String[] args) {
        List<List<Double[]>> lines = new ArrayList<>();
        lines.add(new ArrayList<>());
        lines.get(0).add(new Double[]{1.54349964E12,4.18853881E8});
        lines.get(0).add(new Double[]{1.57505724E12,1.6826195E9});
        lines.get(0).add(new Double[]{1.60661484E12,2.358640101E9});
        lines.get(0).add(new Double[]{1.63817244E12,2.967484822E9});
        lines.get(0).add(new Double[]{1.66973004E12,3.574679079E9});

        ArrayList<Color> colors = new ArrayList<Color>();
        colors.add(Color.blue);
        Font font = Font.decode("consolas");
        font = new Font(font.getFontName(),Font.PLAIN,12);
        double[][][] data = GraphBuilder.toDataFormat(lines);

        List<String> sX = new ArrayList<>();
        List<String> sY = new ArrayList<>();
        sX.add("0"); sY.add("0");

        double[] interval = {(1.66973004E12-1.54349964E12)/10,(3.574679079E9-4.18853881E8)/10};

        BufferedImage num = GraphBuilder.generateGraphAndSubs(data,colors,200,400,interval,font,GraphBuilder.DIRECTION_LEFT,GraphBuilder.DIRECTION_UP,sX,sY,40,Color.gray,Color.black);
        GraphBuilderTest.TestWindow testWindow = new GraphBuilderTest.TestWindow(num);
        testWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testWindow.pack();
    }
}
