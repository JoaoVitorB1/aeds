//pronto
class TP1Q03 {

    public static void main(String[] args) {
        String x;
        boolean fim = false;
        MyIO.setCharset("UTF-8");
        // mesma coisa �
        char caractereInvalido = '\uFFFD';

        while (fim == false) {
            x = MyIO.readLine();
            // verifica se a entrada eh FIM
            if (x.length() == 3 &&x.charAt(0) == 'F' &&x.charAt(1) == 'I' &&x.charAt(2) == 'M') {
                fim = true;
            } else {
                char[] y = new char[x.length()];
                // troca letra por letra da palavra
                for (int i = 0; i < x.length(); i++) {
                    if (x.charAt(i) != caractereInvalido) {
                        y[i] = (char) (x.charAt(i) + 3);
                    } else {
                        y[i] = caractereInvalido;
                    }
                }
                System.out.println(y);
            }
        }
    }
}
