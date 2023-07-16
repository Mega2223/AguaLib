package net.mega2223.utils.objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class GraphBuilder {

    public static final int DIRECTION_UP = 0, DIRECTION_DOWN = 1, DIRECTION_LEFT = 2, DIRECTION_RIGHT = 3;

    public static BufferedImage buildSubs(BufferedImage renderedGraph, List<String> subs, List<Color> colors, double additionalProportion, int direction){
        int dX = renderedGraph.getWidth(),dY = renderedGraph.getHeight();
        int sparePixels;
        if(direction == DIRECTION_UP || direction == DIRECTION_DOWN){
            sparePixels = (int) (dY * additionalProportion);
            dY += sparePixels;
        }
        else if(direction == DIRECTION_LEFT || direction == DIRECTION_RIGHT){
            sparePixels = (int) (dX * additionalProportion);
            dX += sparePixels;
        } else {return null;}

        BufferedImage ret = new BufferedImage(dX,dY,renderedGraph.getType());
        double[] trans = genTransSub(direction);
        trans[0]*=sparePixels;
        trans[1]*=sparePixels;
        transpose(ret,renderedGraph, (int) trans[0], (int) trans[1]);

        int[] corner = {0,0,0,0};
        if(direction == DIRECTION_LEFT){ corner[2] = sparePixels; corner[3] = dY;}
        else if(direction == DIRECTION_RIGHT){corner[0]=dX-sparePixels; corner[2] = dX; corner[3] = dY;}
        else if(direction == DIRECTION_DOWN){corner[1]=dY-sparePixels; corner[2] = dY-sparePixels; corner[3] = sparePixels;}
        else {corner[2] = dX; corner[3] = sparePixels;}

        Graphics2D g2d = ret.createGraphics();

        Font ac = new Font("arial", Font.BOLD,15);
        g2d.setFont(ac);

        double[] estimatedTextSize = getEstimatives(ac,subs);
        double[] writeStartingPoint = {
                corner[0] + ((float) corner[2] - corner[0])/2 - (estimatedTextSize[0]/2),
                corner[1] +((float) corner[3] - corner[1])/2 - (estimatedTextSize[1]/2)
        };
        System.out.println();
        for (int i = 0; i < subs.size(); i++) {
            g2d.setColor(colors.get(i%colors.size()));
            g2d.drawString(subs.get(i), (int) (writeStartingPoint[0])
                    ,(int) (writeStartingPoint[1]+(estimatedTextSize[1]*i)));
        }

        return ret;
    }

    private static double[] getEstimatives(Font font, List<String> data){
        //todo more accurate estumatives
        double estimatedYSpace = font.getSize() * data.size();
        double estimatedXSpace = 0;
        for(String ac : data){estimatedXSpace = Math.max(estimatedXSpace,ac.length()*font.getSize());}
        return new double[]{estimatedXSpace/2,estimatedYSpace/2};
    }

    private static double[] genTransSub(int dir){
        switch (dir){
            case DIRECTION_RIGHT:
                return new double[]{0,0};
            case DIRECTION_UP:
                return new double[]{0,1};
            case DIRECTION_LEFT:
                return new double[]{1,0};
            case DIRECTION_DOWN:
                return new double[]{0,0};
        }
        return new double[]{0,0};
    }

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
            double tr = (x - boundaries[MIN_X])/(ranges[0])*dX;
            graphics2D.drawLine((int) tr,0, (int) tr,dY);
        }
        for(double y = boundaries[MIN_Y]; y < boundaries[MAX_Y]; y+= intervals[1]) {
            double tr = dX - (y - boundaries[MIN_Y]) / (ranges[1]) * dY;
            graphics2D.drawLine(0, (int) tr, dY, (int) tr);
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
                translated[l][0] = (data[i][l][0] - boundaries[MIN_X])/(ranges[0])*dX;
                translated[l][1] = (1-(data[i][l][1] - boundaries[MIN_Y])/(ranges[1]))*dY;
            }
            graphics2D.setColor(colors.get(i% colors.size()));
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
    public static BufferedImage transpose(BufferedImage bot, BufferedImage top, int cX, int cY){
        for (int x = 0; x < top.getWidth(); x++) {
            int rX = x + cX;
            if(rX >= bot.getWidth()){continue;}
            for (int y = 0; y < top.getHeight(); y++) {
                int rY = y + cY;
                if(rY >= bot.getHeight()){continue;}
                int rgbaB = bot.getRGB(rX,rY);//8
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
                rgbaB = (aB << 24) | (Math.min(bB,255) << 16) | (Math.min(gB,255) << 8) | Math.min(rB,255);
                bot.setRGB(rX,rY,rgbaB);
            }
        }
        return bot;
    }

    public static BufferedImage transpose(BufferedImage bot, BufferedImage top){
        return transpose(bot,top,0,0);
    }

    public static void buildCorners(BufferedImage img){
        Graphics2D g = img.createGraphics();
        g.setColor(Color.black);
        int h = img.getHeight() - 1;
        int w = img.getWidth() - 1;
        g.drawLine(0,0,0, h);
        g.drawLine(0,0,w, 0);
        g.drawLine(w,h,0, h);
        g.drawLine(w,h,w, 0);

    }

}