package net.Mega2223.utils;

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
}
