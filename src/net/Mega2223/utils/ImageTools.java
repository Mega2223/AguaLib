package net.Mega2223.utils;

import net.Mega2223.utils.objects.GraphRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class ImageTools {
    public static BufferedImage createFlipped(BufferedImage image) {
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(1, -1));
        at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    public static Image createFlipped(Image image){
        return createFlipped(image);
    }

    public static Image getScaledGraph(Dimension legal, double grid, GraphRenderer renderer){
        double larger = 0;
        if (legal.getHeight() > legal.getWidth()){
            larger = legal.getHeight();
        } else {larger = legal.getWidth();}

        renderer.dimension = new Dimension((int)larger,(int)larger); //why
        final Image readj = renderer.renderWithGrid(new ArrayList<>(),grid).getScaledInstance((int)legal.getWidth(),(int)legal.getHeight(), BufferedImage.SCALE_AREA_AVERAGING);
        return readj;
    }

	public static Image getImageByURL(String url) throws IOException {
        return getImageByURL(new URL(url));
    }

    public static Image getImageByURL(URL url) throws IOException {
        System.setProperty("http.agent", "Chrome");
        //sério isso?
        //isso é prova definitiva que HTTP é coisa do demônio
        //mano eu to mt puto vai se fuder era so fingir que era a porra do Chrome
        return ImageIO.read(url);
    }
	
}
