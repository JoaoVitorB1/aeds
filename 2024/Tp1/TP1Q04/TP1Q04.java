//pronto
import java.util.Random;

public class TP1Q04 {
    public static void main(String[] args) {
        Random gerador = new Random();
        gerador.setSeed(4);
        String x;
        boolean fim = false;
        MyIO.setCharset("UTF-8");
        // mesma coisa �
        char caractereInvalido = '\uFFFD';
        while (fim == false) {
            x = MyIO.readLine();
            if (x.length() == 3 && x.charAt(0) == 'F' && x.charAt(1) == 'I' && x.charAt(2) == 'M') {
                fim = true;
            } else {
                char a = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
                char b = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

                char[] y = x.toCharArray();
                for (int i = 0; i < x.length(); i++) {
                    if (y[i] == caractereInvalido) {
                        y[i] = caractereInvalido;
                    } else {
                        if (y[i] == a) {
                            y[i] = b;
                        }
                    }
                }
                System.out.println(y);
            }
        }
    }

}
