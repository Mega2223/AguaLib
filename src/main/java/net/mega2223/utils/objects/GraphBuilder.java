package net.mega2223.utils.objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class GraphBuilder {

    public static BufferedImage buildPureGraph(double[][][] data, List<Color> colors, int dX, int dY){
        BufferedImage image = new BufferedImage(dX,dY,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics2D = image.createGraphics();

        double[] boundaries = getBoundaries(data);
        double[] ranges = {boundaries[MAX_X] - boundaries[MIN_X], boundaries[MAX_Y] - boundaries[MIN_Y]};


        for (int i = 0; i < data.length; i++) {
            double[][] line = data[i];
            double[][] translated = new double[line.length][2];
            for (int l = 0; l < line.length; l++) {
                translated[l][0] = (data[i][l][0] / ranges[0] + 1)/2.0 * dX;
                translated[l][1] = (1-(data[i][l][1] / ranges[1] + 1)/2.0) * dY;
            }
            graphics2D.setColor(colors.get(i));
            for (int l = 1; l < translated.length; l++) {
                graphics2D.drawLine((int) translated[l-1][0], (int) translated[l-1][1], (int) translated[l][0], (int) translated[l][1]);
            }
        }

        return image;
    }
    protected static final int MIN_X = 0, MIN_Y = 1, MAX_X = 2, MAX_Y = 2;
    static double[] getBoundaries (double[][][] data){
        double minX = 0, minY = 0, maxX = 0, maxY = 0;
        for (int i = 0; i < data.length; i++) {
            for (int l = 0; l < data[i].length; l++) {
                minX = Math.min(minX,data[i][l][0]);
                minY = Math.min(minY,data[i][l][1]);
                maxX = Math.max(maxX,data[i][l][0]);
                maxY = Math.max(maxY,data[i][l][1]);
            }
        }
        return new double[] {minX,minY,maxX,maxY};
    }

    public static double[][][] toDataFormat(List<List<Double[]>> ogData){
        double[][][] data = new double[ogData.size()][][];
        for (int i = 0; i < data.length; i++) {
            List<Double[]> lineOg = ogData.get(i);
            double[][] line = new double[lineOg.size()][2];
            for (int j = 0; j < line.length; j++) {
                for (int k = 0; k < 2; k++) {
                    line[j][k] = lineOg.get(j)[k];
                }
            }
            data[i] = line;
        }
        return data;
    }


}
