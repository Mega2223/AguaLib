package net.Mega2223.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class imageTools {
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
	
	public static Image getImageByURL(String url) throws IOException {
        return getImageByURL(new URL(url));
    }

    public static Image getImageByURL(URL url) throws IOException {
        System.setProperty("http.agent", "Chrome");
        //sério isso?
        // isso é prova definitiva que HTTP é coisa do demônio
        //mano eu to mt puto vai se fuder era so fingir que era a porra do Chrome
        return ImageIO.read(url);
    }
	
}
