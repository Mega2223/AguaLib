package net.Mega2223.utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class genericTools {

    public static String ConvertToHTML(String[] eee) {
        String end = "";
        for (int ever = 0; ever < eee.length; ever++) {
            //pra deixar claro eu copiei isso de um projeto antigo

            if (ever == 0) {
                end = "<html>" + eee[ever];
            } else if (ever == 1) {
                end = end + "<br/>" + eee[ever].replace("-mega", "");
            } else {
                end = end + "<br/>" + eee[ever];
            }

        }
        return end;
    }
    public static List<String> tweetifyText(String textToSplitify, int maxLength) {
        List<String> finalTweets = new ArrayList<String>();
        String[] words = textToSplitify.split(" ");
        String actualTweet = "";

        for(int q = 0; q < words.length; q++){
            if((actualTweet + words[q]).length() > maxLength){ //280
                finalTweets.add(actualTweet);
                actualTweet = words[q] + " ";
            } else {actualTweet = actualTweet + words[q] + " ";}

        }


        //System.out.println("\n\n\n" + finalTweets.get(q));
        // eu poderia fazer um sistema de CopiaCola mas ia dar muito trabalho
        // e no final o objetivo de fazer isso é pra evitar trabalho

        return finalTweets;
    }
}