//faltando terminar
public class TP1Q05 {
  static avaliarAnd(String x){
    if (expressao.startsWith("and")){
      if()
    }
  }

  public static void main(String[] args) {
    String x;
    boolean fim = false;
    while (fim == false) {
      x = MyIO.readLine();

      //verifica se a entrada eh 0
      if (x.length() == 1 && x.charAt(0) == '0') {
        fim = true;
      } else {
        if (x.charAt(0) == '3') {
          //atribui os valores das variaveis
          boolean a = x.charAt(2) == '1';
          boolean b = x.charAt(4) == '1';
          boolean c = x.charAt(6) == '1';

          x = x.replaceAll("A", String.valueOf(x.charAt(2)));
          x = x.replaceAll("B", String.valueOf(x.charAt(4)));
          x = x.replaceAll("C", String.valueOf(x.charAt(6)));

          String[] partes = x.split(" ");
          String expressao = "";
          for (int i = 4; i < partes.length; i++) {
            expressao += partes[i];
          }

          expressao = expressao.replaceAll("0", "false");
          expressao = expressao.replaceAll("1", "true");

          System.out.println(expressao);
        }

        //
        if (x.charAt(0) == '2') {
          //atribui os valores das variaveis
          boolean a = x.charAt(2) == '1';
          boolean b = x.charAt(4) == '1';

          x = x.replaceAll("A", String.valueOf(x.charAt(2))); //muda todos os A da expressao para o valor da variavel
          x = x.replaceAll("B", String.valueOf(x.charAt(4))); //muda todos os B da expressao para o valor da variavel

          String[] partes = x.split(" "); //retira o espaco da expressao

          //junta todas as partes da exprassao em uma varialvel
          String expressao1 = "";
          for (int i = 3; i < partes.length; i++) {
            expressao1 += partes[i];
          }
          

          expressao1 = expressao1.replaceAll("0", "false"); //altera os valores de 0 para false
          expressao1 = expressao1.replaceAll("1", "true"); //altera os valores de 1 para true

          boolean resultado = avaliarExpressao(expressao1);
          if (resultado == true) {
            System.out.println("1");
          } else {
            System.out.println("0");
          }
        }
      }
    }
  }

  // Método para avaliar expressões booleanas
  public static boolean avaliarExpressao(String expressao) {
    // Remove espaços em branco
    expressao = expressao.replaceAll("\\s", "");

    // Se a expressão começa com "not", avalia o operando e nega o resultado
    if (expressao.startsWith("not")) {
      String operando = expressao.substring(4, expressao.length() - 1); // Remove "not(...)"
      return !avaliarExpressao(operando);
    }

    // Se a expressão começa com "and", avalia os operandos e aplica a operação "and"
    if (expressao.startsWith("and")) {
      String[] operandos = extrairOperandos(
        expressao.substring(4, expressao.length() - 1)
      ); // Remove "and(...)"
      return and(
        avaliarExpressao(operandos[0]),
        avaliarExpressao(operandos[1])
      );
    }

    // Se a expressão começa com "or", avalia os operandos e aplica a operação "or"
    if (expressao.startsWith("or")) {
      String[] operandos = extrairOperandos(
        expressao.substring(3, expressao.length() - 1)
      ); // Remove "or(...)"
      return or(avaliarExpressao(operandos[0]), avaliarExpressao(operandos[1]));
    }

    // Se a expressão é um valor booleano (true ou false), converte para booleano e retorna
    return Boolean.parseBoolean(expressao);
  }

  // Método para extrair os operandos de uma expressão
  private static String[] extrairOperandos(String expressao) {
    // Divide a expressão em operandos, removendo as vírgulas
    return expressao.split(",", 2);
  }

  // Métodos para operações lógicas "and", "or" e "not"
  public static boolean and(boolean x, boolean y) {
    return x && y;
  }

  public static boolean or(boolean x, boolean y) {
    return x || y;
  }

  public static boolean not(boolean x) {
    return !x;
  }
}
