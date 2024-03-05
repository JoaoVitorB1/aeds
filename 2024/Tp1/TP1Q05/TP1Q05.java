class TP1Q05 {

    public static void main(String[] args) {
        String x;
        boolean fim = false;
        while (fim == false) {
            x = MyIO.readLine();

            // verifica se a entrada é igual a 0
            if (x.length() == 1 && x.charAt(0) == '0') {
                fim = true;
            } else {

                // VERIFICA SE A EXPRESSAO TEM 2 OU 3 VARIAVEIS
                // E FAZ AS SUBSTITUICOES DAS LETRAS POR NUMEROS

                boolean a, b;
                if (x.charAt(2) == '0') {
                    x = x.replaceAll("A", "false");
                } else {
                    x = x.replaceAll("A", "true");
                }
                if (x.charAt(4) == '0') {
                    x = x.replaceAll("B", "false");
                } else {
                    x = x.replaceAll("B", "true");
                }
                
                if (x.charAt(0) == '3') {
                    boolean c;

                    if (x.charAt(3) == '0') {
                        x = x.replaceAll("C", "false");
                    } else {
                        x = x.replaceAll("C", "true");
                    }
                }

                // montagem da expressao booleana
                String expressao;
                x = x.replaceAll(" ", "");
                if (x.charAt(0) == '2') {
                    char y[] = new char[x.length() - 3];
                    for (int i = 3, j = 0; i < x.length(); i++, j++) {
                        y[j] = x.charAt(i);
                    }
                    expressao = String.valueOf(y);
                    System.out.println(expressao);

                } else if (x.charAt(0) == '3') {
                    char y[] = new char[x.length() - 4];
                    for (int i = 4, j = 0; i < x.length(); i++, j++) {
                        y[j] = x.charAt(i);
                    }
                    expressao = String.valueOf(y);
                    System.out.println(expressao);

                }
            }
        }
    }

    // minha parte
    public static boolean and(boolean a, boolean b) {
        return a && b;
    }

    public static boolean and(boolean a, boolean b, boolean c) {
        return a && b && c;
    }

    public static boolean or(boolean a, boolean b) {
        return a || b;
    }

    public static boolean or(boolean a, boolean b, boolean c) {
        return a || b || c;
    }

    public static boolean not(boolean a) {
        return !a;
    }

}
