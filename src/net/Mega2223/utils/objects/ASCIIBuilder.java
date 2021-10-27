package net.Mega2223.utils.objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ASCIIBuilder {/*
    public BufferedImage toConvert;
    int AlturaDoASCII = 100;
    int LarguraDoASCII = 100;//todo

    public ASCIIBuilder(BufferedImage toConvert){
        this.toConvert = toConvert;
    }

    public String convert(){
        return convert(toConvert);
    }

    public static String convert(BufferedImage what){

    }

    public String ASCIIeer() throws IOException {return ASCIIeer(toConvert);}
    public static String ASCIIeer(BufferedImage img) throws IOException {
        String suamãepelada = null;



        //como eu não consigo escalar diretamente, eu vou dividir os pixels por (altura), crio um for no qual
        //a int tem que ser < que a imagem, e sempre acrescento a quantidade TamanhoDaImagem/(largura) em cada loop
        //depois disso eu arredondo a double atual e peço pra ele pegar a coordenada de pixel
        //e colocar o caractere equivalente,  5Head

        double alto = img.getHeight() / AlturaDoASCII;


        String ASCII = "";
        int PixLarg = 0;
        int PixAlt = 0;
        while (1 > 0) {
//				System.out.println(LarguraDoASCII);
//				System.out.println(AlturaDoASCII);
            double largo = img.getWidth() / LarguraDoASCII;
            int FileiraAtual = (int) alto;
            if (FileiraAtual >= img.getHeight()) {
                System.out.println("break");
                break;
            }
            while (1 > 0) {
                int LargAtual = (int) largo;
                PixLarg++;
                if (LargAtual >= img.getWidth()) {
                    ASCII = ASCII + System.getProperty("line.separator");
                    break;
                }

                //System.out.println("analisando pixel " + LargAtual + "(largura) e " + FileiraAtual +"altura");
                ASCII = ASCII + ASCII(img.getRGB(LargAtual, FileiraAtual), Valor1, Valor2, Valor3, Valor4, Valor5);

                largo = largo + img.getWidth() / LarguraDoASCII;


            }
            PixAlt++;
            //System.out.println("já renderizei " + PixLarg + " pixeis  e " + PixAlt + " fileiras");
            alto = alto + img.getHeight() / AlturaDoASCII;
        }
        System.out.println(ASCII);

        //JFrame freim =  new ASCIIViewer(ASCII);
        //freim.setVisible(true);
        Ascii = ASCII;
        return ASCII;
    }

    /**Pega um valor RGB e converte ele pra um caractere especificado no ASCII*//*
    public static String ASCII(int RGBVALUE, int PERF1, int PERF2, int PERF3, int PERF4, int PERF5) {
        //padrões:		25			100		150			175			200   (o que sobra é o @)
        //TO/DO resizeable ASCII (feito)
        Color c = new Color(RGBVALUE);
        int alpha = c.getAlpha();

        int R = c.getRed();
        int G = c.getGreen();
        int B = c.getBlue();


        //basicamente eu vou calcular uma média entre os valores RGB e vou criar uns retornos a partir disso
        double MEDIA = R + G + B;
        MEDIA = MEDIA / 3;
        if (!TemaClaro) {


            //.-[|-
            if (MEDIA >= 0 && MEDIA < PERF1) {
                return "..";
            } // P1
            if (MEDIA >= PERF1 && MEDIA < PERF2) {
                return "--";
            } // P2
            else if (MEDIA >= PERF2 && MEDIA < PERF3) {
                return "[]";
            } // P3
            else if (MEDIA >= PERF3 && MEDIA < PERF4) {
                return "||";
            } //P4
            else if (MEDIA >= PERF4 && MEDIA < PERF5) {
                return "##";
            } //P5
            else {
                return "@@";
            }
        } else {
            if (MEDIA >= 0 && MEDIA < PERF1) {
                return "@@";
            } // P1
            if (MEDIA >= PERF1 && MEDIA < PERF2) {
                return "##";
            } // P2
            else if (MEDIA >= PERF2 && MEDIA < PERF3) {
                return "||";
            } // P3
            else if (MEDIA >= PERF3 && MEDIA < PERF4) {
                return "[]";
            } //P4
            else if (MEDIA >= PERF4 && MEDIA < PERF5) {
                return "--";
            } //P5
            else {
                return "..";
            }
        }//P5
    }*/
}
