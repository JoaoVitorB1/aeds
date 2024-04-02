import java.lang.reflect.Method;

public class teste {

    public static void main(String[] args) {
        String x;
        boolean fim = false;

        while (!fim) {
            x = MyIO.readLine();

            // verifica se a entrada é igual a 0
            if (x.length() == 1 && x.charAt(0) == '0') {
                fim = true;
            } else {
                // VERIFICA SE A EXPRESSAO TEM 2 OU 3 VARIAVEIS
                // E FAZ AS SUBSTITUICOES DAS LETRAS POR NUMEROS

                boolean a, b, c;
                a = x.charAt(2) != '0';
                b = x.charAt(4) != '0';
                c = x.charAt(6) != '0';

                // montagem da expressao booleana
                String expressao;
                if (x.charAt(0) == '2') {
                    expressao = x.substring(3);
                    chamarFuncao(expressao, a, b);
                } else if (x.charAt(0) == '3') {
                    expressao = x.substring(4);
                    chamarFuncao(expressao, a, b, c);
                }
            }
        }
    }

    public static void chamarFuncao(String nomeFuncao, boolean... args) {
        try {
            // Obter o método com base no nome passado e na quantidade de argumentos
            Method metodo = TP1Q05.class.getMethod(nomeFuncao, boolean[].class);
            // Invocar o método passando os argumentos
            System.out.println(metodo.invoke(null, (Object) args));
        } catch (Exception e) {
            // Lidar com erros de forma adequada
            e.printStackTrace();
            System.out.println("Erro ao chamar a função: " + nomeFuncao);
        }
    }

    public static boolean and(boolean... args) {
        for (boolean arg : args) {
            if (!arg) return false;
        }
        return true;
    }

    public static boolean or(boolean... args) {
        for (boolean arg : args) {
            if (arg) return true;
        }
        return false;
    }

    public static boolean not(boolean arg) {
        return !arg;
    }
}
