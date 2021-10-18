package net.Mega2223.utils.tests;

import net.Mega2223.utils.ImageTools;
import net.Mega2223.utils.objects.GraphRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class graphicTest2 {
    static ScheduledThreadPoolExecutor executor;
    static JFrame jFrame;
    static JLabel jLabel;
    static Runnable aiQueAbusoFazMeRir;
    static Color[] colors = {Color.cyan, Color.BLUE, Color.RED};
    static List<List<double[]>> values;

    public static void main(String[] args) {

        jFrame = new JFrame();
        jLabel = new JLabel();
        executor = new ScheduledThreadPoolExecutor(1);
        aiQueAbusoFazMeRir = new oMundoÉPodre();
        values = new ArrayList<>();
        List<double[]> value1 = new ArrayList<>();
        value1.add(new double[]{-1,-1});
        value1.add(new double[]{1,1});
        value1.add(new double[]{1,-1});
        value1.add(new double[]{-1,-1});

        values.add(value1);

        jFrame.setVisible(true);
        jFrame.setSize(180,180);
        jFrame.setLocation(514,279);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new FlowLayout());
        jFrame.add(jLabel);

        executor.scheduleAtFixedRate(aiQueAbusoFazMeRir,0,20, TimeUnit.MILLISECONDS);
    }

    public static class oMundoÉPodre implements Runnable {
        @Override
        public void run() {
            //System.out.println(jFrame.getLocation());
            GraphRenderer renderer = new GraphRenderer(values,new Dimension(jFrame.getWidth()-60, jFrame.getHeight()-60),colors);
            Image rendered;
            //rendered = ImageTools.getScaledGraph(new Dimension(jFrame.getWidth()-60, jFrame.getHeight()-60),1,renderer);
            rendered = renderer.renderWithGrid(new ArrayList<>(),new double[]{0.2,0.2});
            jLabel.setIcon(new ImageIcon(rendered));
        }
    }

}
