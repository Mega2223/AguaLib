package net.mega2223.utils.objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class GraphBuilder {

    public static BufferedImage buildAuxiliarLines(double[][][] data, Color auxColor, double[] intervals, int dX, int dY){
        return buildAuxiliarLines(new BufferedImage(dX, dY,BufferedImage.TYPE_4BYTE_ABGR),data,auxColor,intervals);
    }
    public static BufferedImage buildAuxiliarLines(BufferedImage image, double[][][] data, Color auxColor, double[] intervals){
        Graphics2D graphics2D = image.createGraphics();
        int dX = image.getWidth(), dY = image.getHeight();
        double[] boundaries = getBoundaries(data);
        double[] ranges = {boundaries[MAX_X] - boundaries[MIN_X], boundaries[MAX_Y] - boundaries[MIN_Y]};

        graphics2D.setColor(auxColor);

        for(double x = boundaries[MIN_X]; x < boundaries[MAX_X]; x+= intervals[0]){
            double tr = (x-boundaries[MIN_X])/(boundaries[MAX_X])*(dX/2.0);
            graphics2D.drawLine((int) tr,0, (int) tr,dY);
        }

        for(double y = boundaries[MIN_Y]; y < boundaries[MAX_Y]; y+= intervals[1]){
            double tr = dY - ((y-boundaries[MIN_Y])/(boundaries[MAX_Y])*(dY/2.0));
            graphics2D.drawLine(0, (int) tr,dY, (int) tr);
        }

        return image;
    }

    public static BufferedImage buildPureGraph(double[][][] data, List<Color> colors, int dX, int dY){
        return buildPureGraph(new BufferedImage(dX,dY,BufferedImage.TYPE_4BYTE_ABGR),data,colors);
    }
    public static BufferedImage buildPureGraph(BufferedImage image, double[][][] data, List<Color> colors){
        Graphics2D graphics2D = image.createGraphics();
        int dX = image.getWidth(), dY = image.getHeight();
        double[] boundaries = getBoundaries(data);
        double[] ranges = {boundaries[MAX_X] - boundaries[MIN_X], boundaries[MAX_Y] - boundaries[MIN_Y]};

        for (int i = 0; i < data.length; i++) {
            double[][] line = data[i];
            double[][] translated = new double[line.length][2];
            for (int l = 0; l < line.length; l++) {
                translated[l][0] = (data[i][l][0]-boundaries[MIN_X])/(boundaries[MAX_X])*(dX/2.0);
                translated[l][1] = (dY-(data[i][l][1]-boundaries[MIN_Y])/(boundaries[MAX_Y])*(dY/2.0));
                System.out.println(translated[l][1]);
            }
            graphics2D.setColor(colors.get(i));
            for (int l = 1; l < translated.length; l++) {
                graphics2D.drawLine((int) translated[l-1][0], (int) translated[l-1][1], (int) translated[l][0], (int) translated[l][1]);
            }
        }

        return image;
    }
    protected static final int MIN_X = 0, MIN_Y = 1, MAX_X = 2, MAX_Y = 3;
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
    //ik BufferedImage already can do that but i did it for the fun of it
    public static BufferedImage transpose(BufferedImage bot, BufferedImage top){
        for (int x = 0; x < bot.getWidth(); x++) {
            for (int y = 0; y < bot.getHeight(); y++) {
                int rgbaB = bot.getRGB(x,y);//8
                int rB = 0XFF & rgbaB;
                int gB = (0XFF00 & rgbaB) >> 8;
                int bB = (0XFF0000 & rgbaB) >> 16;
                int aB = (0XFF000000 & rgbaB) >> 24;
                int rgbaT = top.getRGB(x,y);
                int rT = 0XFF & rgbaT;
                int gT = (0XFF00 & rgbaT) >> 8;
                int bT = (0XFF0000 & rgbaT) >> 16;
                int aT = (0XFF000000 & rgbaT) >> 24;
                rB += rT;
                gB += gT;
                bB += bT;
                aB += aT;
                rgbaB = (Math.min(aB,255) << 24) | (Math.min(bB,255) << 16) | (Math.min(gB,255) << 8) | Math.min(rB,255);
                bot.setRGB(x,y,rgbaB);
            }
        }
        return bot;
    }

}
