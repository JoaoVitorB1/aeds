class TP1Q14 {
    static boolean isFim(String x) {
        if (x.length() == 1 && x.charAt(0) == '0') {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String x;
        boolean fim = false;

        while (fim == false) {
            x = MyIO.readLine();

            // verifica se a entrada é igual a 0
            if (isFim(x) == true) {
                fim = true;
            } else {

                // VERIFICA SE A EXPRESSAO TEM 2 OU 3 VARIAVEIS
                // E FAZ AS SUBSTITUICOES DAS LETRAS POR NUMEROS

                String expressao;
                boolean a, b, c = false;

                if (x.charAt(0) == '2') {

                    if (x.charAt(2) == '0') {
                        x = x.replaceAll("A", "0");
                        a = false;
                    } else {
                        x = x.replaceAll("A", "1");
                        a = true;
                    }
                    if (x.charAt(4) == '0') {
                        x = x.replaceAll("B", "0");
                        b = false;
                    } else {
                        x = x.replaceAll("B", "1");
                        b = true;
                    }

                    x = x.replaceAll(" ", "");
                    char y[] = new char[x.length() - 3];
                    for (int i = 3, j = 0; i < x.length(); i++, j++) {
                        y[j] = x.charAt(i);
                    }
                    expressao = String.valueOf(y);
                    String resultado = testeDuasVariaveis(expressao, a, b, expressao.length() - 1);
                    System.out.println(resultado);
                }
                if (x.charAt(0) == '3') {

                    if (x.charAt(2) == '0') {
                        x = x.replaceAll("A", "0");
                        a = false;
                    } else {
                        x = x.replaceAll("A", "1");
                        a = true;
                    }
                    if (x.charAt(4) == '0') {
                        x = x.replaceAll("B", "0");
                        b = false;
                    } else {
                        x = x.replaceAll("B", "1");
                        b = true;
                    }

                    if (x.charAt(6) == '0') {
                        x = x.replaceAll("C", "0");
                        c = false;
                    } else {
                        x = x.replaceAll("C", "1");
                        c = true;
                    }
                    x = x.replaceAll(" ", "");
                    char y[] = new char[x.length() - 4];
                    for (int i = 4, j = 0; i < x.length(); i++, j++) {
                        y[j] = x.charAt(i);
                    }
                    expressao = String.valueOf(y);
                    String resultado = testeTresVariaveis(expressao, a, b, c, expressao.length() - 1);
                    System.out.println(resultado);

                }

            }

        }
    }

    // verifica de tras pra frente expressoes de 2 variaveis
    // quando um parentese abre, percorre ate encontrar o parentese fechar
    // se outro parentese abre, ignora o parentese anterior
    // quando fecha ele calcula se eh verdadeiro ou falso
    public static String testeDuasVariaveis(String expressao, boolean a, boolean b, int i) {
        boolean resultado;
        // se i>0 faz a recursao
        if (i > 0) {

            char[] temp = expressao.toCharArray();
            // verifica se o parentese fecha para fazer a operacao
            // and
            if (expressao.charAt(i) == '(' && expressao.charAt(i - 1) == 'd') {
                boolean variavela, variavelb;
                if (temp[i + 1] == '0') {
                    variavela = false;
                } else {
                    variavela = true;
                }
                if (temp[i + 3] == '0') {
                    variavelb = false;
                } else {
                    variavelb = true;
                }
                resultado = and2(variavela, variavelb);
                if (resultado == true) {
                    temp[i - 3] = '1';
                } else {
                    temp[i - 3] = '0';
                }
                temp[i - 2] = ' ';
                temp[i - 1] = ' ';
                temp[i] = ' ';
                temp[i + 1] = ' ';
                temp[i + 2] = ' ';
                temp[i + 3] = ' ';
                temp[i + 4] = ' ';
                String novaString = new String(temp);
                novaString = novaString.replaceAll(" ", "");
                return testeDuasVariaveis(novaString, a, b, novaString.length() - 1);
            }
            // or
            else if (expressao.charAt(i) == '(' && expressao.charAt(i - 1) == 'r') {
                boolean variavela, variavelb;
                if (temp[i + 1] == '0') {
                    variavela = false;
                } else {
                    variavela = true;
                }
                if (temp[i + 3] == '0') {
                    variavelb = false;
                } else {
                    variavelb = true;
                }
                resultado = or2(variavela, variavelb);
                if (resultado == true) {
                    temp[i - 2] = '1';
                } else {
                    temp[i - 2] = '0';
                }
                temp[i - 1] = ' ';
                temp[i] = ' ';
                temp[i + 1] = ' ';
                temp[i + 2] = ' ';
                temp[i + 3] = ' ';
                temp[i + 4] = ' ';
                String novaString = new String(temp);
                novaString = novaString.replaceAll(" ", "");
                return testeDuasVariaveis(novaString, a, b, novaString.length() - 1);

            }
            // not
            else if (expressao.charAt(i) == '(' && expressao.charAt(i - 1) == 't') {
                boolean variavel;
                if (temp[i + 1] == '0') {
                    variavel = false;
                } else {
                    variavel = true;
                }
                resultado = not(variavel);
                if (resultado == true) {
                    temp[i - 3] = '1';
                } else {
                    temp[i - 3] = '0';
                }
                temp[i - 2] = ' ';
                temp[i - 1] = ' ';
                temp[i] = ' ';
                temp[i + 1] = ' ';
                temp[i + 2] = ' ';
                String novaString = new String(temp);
                novaString = novaString.replaceAll(" ", "");
                return testeDuasVariaveis(novaString, a, b, novaString.length() - 1);
            }
            // se nenhum parentese fecha, chama a funcao com i--
            else {
                return testeDuasVariaveis(expressao, a, b, i - 1);
            }
        } else {
            return expressao;
        }

    }

    // verifica de tras pra frente expressoes de 3 variaveis
    // quando um parentese abre, percorre ate encontrar o parentese fechar
    // se outro parentese abre, ignora o parentese anterior
    // quando fecha ele calcula se eh verdadeiro ou falso
    public static String testeTresVariaveis(String expressao, boolean a, boolean b, boolean c, int i) {
        boolean resultado;
        // se i>0 faz a recursao
        if (i > 0) {

            char[] temp = expressao.toCharArray();
            // verifica se o parentese fecha para fazer a operacao

            // and
            if (expressao.charAt(i) == '(' && expressao.charAt(i - 1) == 'd') {
                // verifica se tem 2 variaveis
                if (temp[i + 4] == ')') {
                    boolean variavela, variavelb;
                    if (temp[i + 1] == '0') {
                        variavela = false;
                    } else {
                        variavela = true;
                    }
                    if (temp[i + 3] == '0') {
                        variavelb = false;
                    } else {
                        variavelb = true;
                    }
                    resultado = and2(variavela, variavelb);
                    if (resultado == true) {
                        temp[i - 3] = '1';
                    } else {
                        temp[i - 3] = '0';
                    }
                    temp[i - 2] = ' ';
                    temp[i - 1] = ' ';
                    temp[i] = ' ';
                    temp[i + 1] = ' ';
                    temp[i + 2] = ' ';
                    temp[i + 3] = ' ';
                    temp[i + 4] = ' ';
                    String novaString = new String(temp);
                    novaString = novaString.replaceAll(" ", "");
                    return testeTresVariaveis(novaString, a, b, c, novaString.length() - 1);
                }
                // and 3 variaveis
                else {
                    boolean variavela, variavelb, variavelc;
                    if (temp[i + 1] == '0') {
                        variavela = false;
                    } else {
                        variavela = true;
                    }
                    if (temp[i + 3] == '0') {
                        variavelb = false;
                    } else {
                        variavelb = true;
                    }
                    if (temp[i + 5] == '0') {
                        variavelc = false;
                    } else {
                        variavelc = true;
                    }
                    resultado = and3(variavela, variavelb, variavelc);
                    if (resultado == true) {
                        temp[i - 3] = '1';
                    } else {
                        temp[i - 3] = '0';
                    }
                    temp[i - 2] = ' ';
                    temp[i - 1] = ' ';
                    temp[i] = ' ';
                    temp[i + 1] = ' ';
                    temp[i + 2] = ' ';
                    temp[i + 3] = ' ';
                    temp[i + 4] = ' ';
                    temp[i + 5] = ' ';
                    temp[i + 6] = ' ';
                    String novaString = new String(temp);
                    novaString = novaString.replaceAll(" ", "");
                    return testeTresVariaveis(novaString, a, b, c, novaString.length() - 1);
                }
            }
            
            else if (expressao.charAt(i) == '(' && expressao.charAt(i - 1) == 'r') {
                // or 2 variaveis
                if (temp[i + 4] == ')') {
                    boolean variavela, variavelb;
                    if (temp[i + 1] == '0') {
                        variavela = false;
                    } else {
                        variavela = true;
                    }
                    if (temp[i + 3] == '0') {
                        variavelb = false;
                    } else {
                        variavelb = true;
                    }
                    resultado = or2(variavela, variavelb);
                    if (resultado == true) {
                        temp[i - 2] = '1';
                    } else {
                        temp[i - 2] = '0';
                    }
                    temp[i - 1] = ' ';
                    temp[i] = ' ';
                    temp[i + 1] = ' ';
                    temp[i + 2] = ' ';
                    temp[i + 3] = ' ';
                    temp[i + 4] = ' ';
                    String novaString = new String(temp);
                    novaString = novaString.replaceAll(" ", "");
                    return testeTresVariaveis(novaString, a, b, c, novaString.length() - 1);

                }
                // or 3 variaveis
                else if (temp[i + 6] == ')') {
                    boolean variavela, variavelb, variavelc;
                    if (temp[i + 1] == '0') {
                        variavela = false;
                    } else {
                        variavela = true;
                    }
                    if (temp[i + 3] == '0') {
                        variavelb = false;
                    } else {
                        variavelb = true;
                    }
                    if (temp[i + 5] == '0') {
                        variavelc = false;
                    } else {
                        variavelc = true;
                    }
                    resultado = or3(variavela, variavelb, variavelc);
                    if (resultado == true) {
                        temp[i - 2] = '1';
                    } else {
                        temp[i - 2] = '0';
                    }
                    temp[i - 1] = ' ';
                    temp[i] = ' ';
                    temp[i + 1] = ' ';
                    temp[i + 2] = ' ';
                    temp[i + 3] = ' ';
                    temp[i + 4] = ' ';
                    temp[i + 5] = ' ';
                    temp[i + 6] = ' ';

                    String novaString = new String(temp);
                    novaString = novaString.replaceAll(" ", "");
                    return testeTresVariaveis(novaString, a, b, c, novaString.length() - 1);
                }                
                // or 4 variaveis                
                else if(temp[i + 8] == ')'&&((temp[i + 7] == '0'||temp[i + 7] == '1')
                    &&(temp[i + 5] == '0'||temp[i + 5] == '1')
                    &&(temp[i + 3] == '0'||temp[i + 3] == '1')
                    &&(temp[i + 1] == '0'||temp[i + 1] == '1'))){
                    boolean variavela, variavelb, variavelc, variaveld;
                    if (temp[i + 1] == '0') {
                        variavela = false;
                    } else {
                        variavela = true;
                    }
                    if (temp[i + 3] == '0') {
                        variavelb = false;
                    } else {
                        variavelb = true;
                    }
                    if (temp[i + 5] == '0') {
                        variavelc = false;
                    } else {
                        variavelc = true;
                    }
                    if (temp[i + 7] == '0') {
                        variaveld = false;
                    } else {
                        variaveld = true;
                    }
                    resultado = or4(variavela, variavelb, variavelc, variaveld);
                    if (resultado == true) {
                        temp[i - 2] = '1';
                    } else {
                        temp[i - 2] = '0';
                    }                    
                    temp[i - 1] = ' ';
                    temp[i] = ' ';
                    temp[i + 1] = ' ';
                    temp[i + 2] = ' ';
                    temp[i + 3] = ' ';
                    temp[i + 4] = ' ';
                    temp[i + 5] = ' ';
                    temp[i + 6] = ' ';
                    temp[i + 7] = ' ';
                    temp[i + 8] = ' ';
                    String novaString = new String(temp);
                    novaString = novaString.replaceAll(" ", "");
                    return testeTresVariaveis(novaString, a, b, c, novaString.length() - 1);
                }
            }
            // not
            else if (expressao.charAt(i) == '(' && expressao.charAt(i - 1) == 't') {
                boolean variavel;
                if (temp[i + 1] == '0') {
                    variavel = false;
                } else {
                    variavel = true;
                }
                resultado = not(variavel);
                if (resultado == true) {
                    temp[i - 3] = '1';
                } else {
                    temp[i - 3] = '0';
                }
                temp[i - 2] = ' ';
                temp[i - 1] = ' ';
                temp[i] = ' ';
                temp[i + 1] = ' ';
                temp[i + 2] = ' ';
                String novaString = new String(temp);
                novaString = novaString.replaceAll(" ", "");
                return testeTresVariaveis(novaString, a, b, c, novaString.length() - 1);
            }
            // se nenhum parentese fecha, chama a funcao com i--
            else {
                return testeTresVariaveis(expressao, a, b, c, i - 1);
            }
        } 
            return expressao;
        
    }

    public static boolean and2(boolean a, boolean b) {
        return a && b;
    }

    public static boolean and3(boolean a, boolean b, boolean c) {
        return a && b && c;
    }

    public static boolean or2(boolean a, boolean b) {
        return a || b;
    }

    public static boolean or3(boolean a, boolean b, boolean c) {
        return a || b || c;
    }

    public static boolean or4(boolean a, boolean b, boolean c, boolean d) {
        return a || b || c || d;
    }

    public static boolean not(boolean a) {
        return !a;
    }
}
