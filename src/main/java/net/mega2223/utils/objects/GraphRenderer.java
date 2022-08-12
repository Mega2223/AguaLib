package net.mega2223.utils.objects;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;

import static net.mega2223.utils.ImageTools.createFlipped;

public class GraphRenderer {

    protected static final int GLOBAL_CONVERTION_RATE_DIFFERENCE = 100;
    public List<List<double[]>> values;
    public Dimension dimension;
    public Color[] colours; //britanico pra não dar ambiguidade, genial dmite

    public GraphRenderer(List<List<double[]>> val, Dimension dims, Color[] colors) {
        values = val;
        dimension = dims;
        colours = colors;
    }

    public static BufferedImage getImage(List<List<double[]>> dobros, Dimension dim, Color[] colors) {
        return
                getImage(dobros, new ArrayList<List<double[]>>(), dim, colors);
    }

    public static BufferedImage getImage(List<List<double[]>> dobros, List<List<double[]>> linhasNEssenciais, Dimension dim, Color[] colors) {
        BufferedImage ret = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_4BYTE_ABGR);

        List<double[]> gr = dobros.get(0);

        double minX = gr.get(0)[0];
        double maxX = gr.get(0)[0];
        double minY = gr.get(0)[1];
        double maxY = gr.get(0)[1];

        for (List<double[]> act : dobros) {
            for (double[] e : act) {
                if (e[0] > maxX) {
                    maxX = e[0];
                }
                if (e[1] > maxY) {
                    maxY = e[1];
                }
                if (e[0] < minX) {
                    minX = e[0];
                }
                if (e[1] < minY) {
                    minY = e[1];
                }
            }
        }

        double multIndexY = (dim.getHeight() / (maxY - minY));
        double multIndexX = (dim.getWidth() / (maxX - minX));

        multIndexY = multIndexY - (multIndexY / GLOBAL_CONVERTION_RATE_DIFFERENCE);
        multIndexX = multIndexX - (multIndexX / GLOBAL_CONVERTION_RATE_DIFFERENCE);

        List<List<double[]>> translated = translateGr(dobros);

        Graphics graphics = ret.getGraphics();

        double loc0X = (-minX) * multIndexX;
        double loc0Y = (-minY) * multIndexY;


        //x1 y1 x2 y2

        //-3,3 (L100)
        //0,6
        //3 pra frente

        //-3,3
        //3
        //

        graphics.setColor(Color.blue);

        List<List<double[]>> notExTR = new ArrayList<>();

        for (List<double[]> act : linhasNEssenciais) {
            List<double[]> go = new ArrayList<>();
            for (double[] inte : act) {
                double[] vaiag = {inte[0] - minX, inte[1] - minY};
                go.add(vaiag);
            }
            notExTR.add(go);
        }

        graphics.setColor(Color.lightGray);
        double[] ant = null;
        for (List<double[]> act : notExTR) {
            for (double[] inte : act) {
                if (ant == null) {
                    ant = inte;
                    continue;
                }
                int TXA = (int) (ant[0] * multIndexX);
                int TYA = (int) (ant[1] * multIndexY);
                int ACX = (int) (inte[0] * multIndexX);
                int ACY = (int) (inte[1] * multIndexY);
                graphics.drawLine(TXA, TYA, ACX, ACY);
                //System.out.println( "(" + (int)ant[0] + "\\"+ (int)ant[1] +"\\"+ (int)inte[0] + "\\"+ (int)inte[1] +"):   " + TXA + "\\" + TYA + "\\" + ACX + "\\" +ACY);
                ant = inte;
            }

            ant = null;
        }

        //((Graphics2D)graphics).drawString("eba",40,40);

        ant = null;
        int ind = 0;
        int colorIndex = 0;
        for (List<double[]> act : translated) {
            try {
                graphics.setColor(colors[ind]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                colorIndex = 0;
            }
            colorIndex++;
            for (double[] inte : act) {
                if (ant == null) {
                    ant = inte;
                    continue;
                }
                int TXA = (int) (ant[0] * multIndexX);
                int TYA = (int) (ant[1] * multIndexY);
                int ACX = (int) (inte[0] * multIndexX);
                int ACY = (int) (inte[1] * multIndexY);
                graphics.drawLine(TXA, TYA, ACX, ACY);
                //System.out.println( "(" + (int)ant[0] + "\\"+ (int)ant[1] +"\\"+ (int)inte[0] + "\\"+ (int)inte[1] +"):   " + TXA + "\\" + TYA + "\\" + ACX + "\\" +ACY);
                ant = inte;
            }
            graphics.setColor(Color.red);
            ind++;
            ant = null;
        }

        graphics.setColor(Color.gray);
        graphics.drawLine((int) loc0X, 0, (int) loc0X, ret.getHeight() - (ret.getHeight() / GLOBAL_CONVERTION_RATE_DIFFERENCE));
        graphics.drawLine(0, (int) loc0Y, ret.getWidth() - (ret.getWidth() / GLOBAL_CONVERTION_RATE_DIFFERENCE), (int) loc0Y);

        graphics.dispose();


        //System.out.println("==================================");
        return createFlipped(ret);
    }

    public static BufferedImage renderWithGrid(List<List<double[]>> dobros, List<List<double[]>> linhasNEssenciais, Dimension dim, Color[] colors, double frequency) {
        return renderWithGrid(dobros, linhasNEssenciais, dim, colors, new double[]{frequency, frequency});
    }

    public static BufferedImage renderWithGrid(List<List<double[]>> dobros, List<List<double[]>> linhasNEssenciais, Dimension dim, Color[] colors, double frequency[]) {

        List<double[]> first = dobros.get(0);
        if (first.size() == 0) {
            int analise = 1;
            while (dobros.get(analise).size() != 0) {
                first = dobros.get(analise);
                analise++;
            }
        }

        if (first.size() == 0) {
            return null;
        }

        List<List<double[]>> linhasNEssenciaisB = new ArrayList<>();
        linhasNEssenciaisB.addAll(linhasNEssenciais);

        double minX = first.get(0)[0];
        double maxX = first.get(0)[0];
        double minY = first.get(0)[1];
        double maxY = first.get(0)[1];

        for (List<double[]> act : dobros) {
            for (double[] e : act) {
                if (e[0] > maxX) {
                    maxX = e[0];
                }
                if (e[1] > maxY) {
                    maxY = e[1];
                }
                if (e[0] < minX) {
                    minX = e[0];
                }
                if (e[1] < minY) {
                    minY = e[1];
                }
            }
        }

        for (double n = minY; n <= maxY; n += frequency[1]) {
            List<double[]> eb = new ArrayList<>();
            eb.add(new double[]{maxX, n});
            eb.add(new double[]{minX, n});
            linhasNEssenciaisB.add(eb);
        }

        for (double n = minX; n <= maxX; n += frequency[0]) {
            List<double[]> eb = new ArrayList<>();
            eb.add(new double[]{n, minY});
            eb.add(new double[]{n, maxY});
            linhasNEssenciaisB.add(eb);
        }

        return getImage(dobros, linhasNEssenciaisB, dim, colors);
    }

    public static BufferedImage renderWithGridAndNumberNotation(List<List<double[]>> dobros, List<List<double[]>> linhasNEssenciais, Dimension dim, Color[] colors, double frequency[]) {
        BufferedImage getImage = renderWithGrid(dobros, linhasNEssenciais, dim, colors, frequency);

        int width = getImage.getWidth();
        int height = getImage.getHeight();
        BufferedImage ret = new BufferedImage(width + (width / 10), height + (height / 10), getImage.getType());
        Graphics graphics = ret.getGraphics();
        Graphics2D graphics2D = ret.createGraphics();

        List<double[]> first = dobros.get(0);
        if (first.size() == 0){
            int analise = 1;
            while (dobros.get(analise).size() != 0){
                first = dobros.get(analise);
                analise++;
            }
        }

        if (first.size() == 0){return null;}

        double minX = first.get(0)[0];
        double maxX = first.get(0)[0];
        double minY = first.get(0)[1];
        double maxY = first.get(0)[1];

        for (List<double[]> act : dobros) {
            for (double[] e : act) {
                if (e[0] > maxX) {
                    maxX = e[0];
                }
                if (e[1] > maxY) {
                    maxY = e[1];
                }
                if (e[0] < minX) {
                    minX = e[0];
                }
                if (e[1] < minY) {
                    minY = e[1];
                }
            }
        }

        double multIndexY = (dim.getHeight() / (maxY - minY));
        double multIndexX = (dim.getWidth() / (maxX - minX));

        multIndexY = multIndexY - (multIndexY / GLOBAL_CONVERTION_RATE_DIFFERENCE);
        multIndexX = multIndexX - (multIndexX / GLOBAL_CONVERTION_RATE_DIFFERENCE);

        graphics.drawImage(getImage,ret.getWidth()-getImage.getWidth(),0,null);
        graphics2D.setColor(Color.black);

        for (double n = minY; n <= maxY; n += frequency[1]) {
            double n2 = maxY - n + (frequency[1]/2);
            double[] rendercoords = {0, n2*multIndexY};
            graphics2D.drawString((int) n + "", (int) rendercoords[0], (int) rendercoords[1]);
        }


        graphics.setColor(Color.red);
        for (double n = minX; n <= maxX; n += frequency[0]) {
            double n2 = maxX - n;
            double[] rendercoords = {ret.getWidth()-(n2*multIndexX),getImage.getHeight() + 13};
            graphics2D.drawString((int) n + "", (int) rendercoords[0], (int) rendercoords[1]);
            //graphics2D.drawOval((int) rendercoords[0], (int) rendercoords[1],30,30);
        }
        graphics.setColor(Color.red);
        //graphics.drawRect(0,0,ret.getWidth()-1,ret.getHeight()-1);

        return ret;
    }

    protected static List<List<double[]>> translateGr(List<List<double[]>> gr) {
        List<List<double[]>> ret = new ArrayList();

        List<double[]> first = gr.get(0);
        double minX = first.get(0)[0];
        double maxX = first.get(0)[0];
        double minY = first.get(0)[1];
        double maxY = first.get(0)[1];

        for (List<double[]> act : gr) {
            for (double[] e : act) {
                if (e[0] > maxX) {
                    maxX = e[0];
                }
                if (e[1] > maxY) {
                    maxY = e[1];
                }
                if (e[0] < minX) {
                    minX = e[0];
                }
                if (e[1] < minY) {
                    minY = e[1];
                }
            }
        }
        //System.out.println(maxX +","+minX +","+maxY +","+minY +",");

        for (List<double[]> act : gr) {
            List<double[]> fin = new ArrayList<>();
            for (double[] e : act) {
                fin.add(new double[]{e[0] - minX, e[1] - minY});
            }
            ret.add(fin);
        }

        return ret;
    }

    /*
    public static BufferedImage renderWithGrid(List<List<double[]>> dobros,List<List<double[]>> linhasNEssenciais, Dimension dim, Color[] colors,double frequency){

     */

    public BufferedImage renderWithGrid(List<List<double[]>> extraLanes, double gridIndex) {
        return renderWithGrid(this.values, extraLanes, this.dimension, this.colours, new double[]{gridIndex, gridIndex});
    }

    public BufferedImage renderWithGrid(List<List<double[]>> extraLanes, double gridIndex[]) {
        return renderWithGrid(this.values, extraLanes, this.dimension, this.colours, gridIndex);
    }

    public BufferedImage renderWithGridAndNumberNotation(List<List<double[]>> linhasNEssenciais, double gridIndex[]) {
        return renderWithGridAndNumberNotation(this.values,linhasNEssenciais,this.dimension,this.colours,gridIndex);

    }

        public BufferedImage renderImage() {
        return getImage(values, dimension, colours);

    }
}
