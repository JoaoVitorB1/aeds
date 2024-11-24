//pronto
import java.io.*;
import java.net.*;

public class TP1Q07 {

    //funcao para analisar o codigo da pagina
    public static void getHtml(String nome, String endereco) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;
        int x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0, x6 = 0, x7 = 0, x8 = 0, x9 = 0, x10 = 0, x11 = 0, x12 = 0, x13 = 0,
                x14 = 0, x15 = 0, x16 = 0, x17 = 0, x18 = 0, x19 = 0, x20 = 0, x21 = 0, x22 = 0, x23 = 0, x24 = 0,
                x25 = 0;

        try {
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            //analisa todas as linhas da pagina
            while ((line = br.readLine()) != null) {                
                String[] palavras = line.split("\\s+");

                //analisa palavra por palavra da pagina
                for (String palavra : palavras) {
                    if (palavra.contains("<br>")) {
                        x24 += 1;
                    }
                    if (palavra.contains("<table>")) {
                        x25 += 1;

                    }
                    if (palavra.contains("<table>")) {
                        
                    }else{
                        //verifica as vogais
                        for (int i = 0; i < palavra.length(); i++) {

                            if (palavra.charAt(i) == 'a') {
                                x1 += 1;
                            }
                            if (palavra.charAt(i) == 'e') {
                                x2 += 1;
                            }
                            if (palavra.charAt(i) == 'i') {
                                x3 += 1;
                            }
                            if (palavra.charAt(i) == 'o') {
                                x4 += 1;
                            }
                            if (palavra.charAt(i) == 'u') {
                                x5 += 1;
                            }
                            if (palavra.charAt(i) == '\u00e1') {
                                x6 += 1;
                            }
                            if (palavra.charAt(i) == '\u00e9') {
                                x7 += 1;
                            }
                            if (palavra.charAt(i) == '\u00ed') {
                                x8 += 1;
                            }
                            if (palavra.charAt(i) == '\u00f3') {
                                x9 += 1;
                            }
                            if (palavra.charAt(i) == '\u00fa') {
                                x10 += 1;
                            }
                            if (palavra.charAt(i) == '\u00e0') {
                                x11 += 1;
                            }
                            if (palavra.charAt(i) == '\u00e8') {
                                x12 += 1;
                            }
                            if (palavra.charAt(i) == '\u00ec') {
                                x13 += 1;
                            }
                            if (palavra.charAt(i) == '\u00f2') {
                                x14 += 1;
                            }
                            if (palavra.charAt(i) == '\u00f9') {
                                x15 += 1;
                            }
                            if (palavra.charAt(i) == '\u00e3') {
                                x16 += 1;
                            }
                            if (palavra.charAt(i) == '\u00f5') {
                                x17 += 1;
                            }
                            if (palavra.charAt(i) == '\u00e2') {
                                x18 += 1;
                            }
                            if (palavra.charAt(i) == '\u00ea') {
                                x19 += 1;
                            }
                            if (palavra.charAt(i) == '\u00ee') {
                                x20 += 1;
                            }
                            if (palavra.charAt(i) == '\u00f4') {
                                x21 += 1;
                            }
                            if (palavra.charAt(i) == '\u00fb') {
                                x22 += 1;
                            }

                            //verifica as consoantes
                            if (palavra.charAt(i) == 'm' ||
                                    palavra.charAt(i) == 'n' ||
                                    palavra.charAt(i) == 'b' ||
                                    palavra.charAt(i) == 'v' ||
                                    palavra.charAt(i) == 'c' ||
                                    palavra.charAt(i) == 'x' ||
                                    palavra.charAt(i) == 'z' ||
                                    palavra.charAt(i) == 's' ||
                                    palavra.charAt(i) == 'd' ||
                                    palavra.charAt(i) == 'f' ||
                                    palavra.charAt(i) == 'g' ||
                                    palavra.charAt(i) == 'h' ||
                                    palavra.charAt(i) == 'j' ||
                                    palavra.charAt(i) == 'k' ||
                                    palavra.charAt(i) == 'l' ||
                                    palavra.charAt(i) == 'p' ||
                                    palavra.charAt(i) == 'y' ||
                                    palavra.charAt(i) == 't' ||
                                    palavra.charAt(i) == 'r' ||
                                    palavra.charAt(i) == 'w' ||
                                    palavra.charAt(i) == 'q') {
                                x23 += 1;
                            }

                        }
                    }

                }

            }

        } catch (

        MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here

        }

        //print dos resultados
        MyIO.print("a(" + x1 + ") e(" + x2 + ") i(" + x3 + ") o(" + x4 + ") u(" + x5 + ") \u00e1(" + x6 + ") \u00e9(" + x7
                + ") \u00ed(" + x8 + ") \u00f3(" + x9 + ") \u00fa(" + x10 + ") \u00e0(" + x11 + ") \u00e8(" + x12 + ") \u00ec(" + x13 + ") \u00f2(" + x14
                + ") \u00f9(" + x15 + ") \u00e3(" + x16 + ") \u00f5(" + x17 + ") \u00e2(" + x18 + ") \u00ea(" + x19 + ") \u00ee(" + x20 + ") \u00f4(" + x21
                + ") \u00fb(" + x22 + ") consoante(" + x23 + ") <br>(" + x24 + ") <table>(" + x25 + ") " + nome+"\n");
    }

    public static void main(String[] args) {
        String nome, endereco;
        boolean fim = false;
        MyIO.setCharset("UTF-8");                
        while (fim == false) {
            nome = MyIO.readLine();
            if (nome.length() == 3 && nome.charAt(0) == 'F' && nome.charAt(1) == 'I' && nome.charAt(2) == 'M') {
                fim = true;
            } else {
                endereco = MyIO.readLine();
                getHtml(nome, endereco);
            }
        }
    }
}
