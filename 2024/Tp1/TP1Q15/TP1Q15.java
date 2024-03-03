//pronto
class TP1Q15 {

  static boolean IsVogal(String y, int i) {
    //verifica se eh so vogal
    if (i < y.length()) {
      if (
        y.charAt(i) != 'a' &&
        y.charAt(i) != 'e' &&
        y.charAt(i) != 'i' &&
        y.charAt(i) != 'o' &&
        y.charAt(i) != 'u' &&
        y.charAt(i) != 'A' &&
        y.charAt(i) != 'E' &&
        y.charAt(i) != 'I' &&
        y.charAt(i) != 'O' &&
        y.charAt(i) != 'U'
      ) {
        return false;
      } else {
        return IsVogal(y, ++i);
      }
    } else {
      return true;
    }
  }

  //verifica se eh so consoante
  static boolean IsConsoante(String y, int i) {
    if (i < y.length()) {
      if (
        y.charAt(i) == 'a' ||
        y.charAt(i) == 'e' ||
        y.charAt(i) == 'i' ||
        y.charAt(i) == 'o' ||
        y.charAt(i) == 'o' ||
        y.charAt(i) == 'A' ||
        y.charAt(i) == 'E' ||
        y.charAt(i) == 'I' ||
        y.charAt(i) == 'O' ||
        y.charAt(i) == 'U'
      ) {
        return false;
      } else {
        return IsConsoante(y, ++i);
      }
    } else {
      return true;
    }
  }

  //verifica se eh so numero inteiro
  static boolean IsNumero(String y, int i) {
    if (i < y.length()) {
      if (
        y.charAt(i) != '0' &&
        y.charAt(i) != '1' &&
        y.charAt(i) != '2' &&
        y.charAt(i) != '3' &&
        y.charAt(i) != '4' &&
        y.charAt(i) != '5' &&
        y.charAt(i) != '6' &&
        y.charAt(i) != '7' &&
        y.charAt(i) != '8' &&
        y.charAt(i) != '9'
      ) {
        return false;
      } else {
        return IsNumero(y, ++i);
      }
    } else {
      return true;
    }
  }

  //verifica se eh so numero real
  static boolean IsReal(String y, int i) {
    if (i < y.length()) {
      if (
        y.charAt(i) != '0' &&
        y.charAt(i) != '1' &&
        y.charAt(i) != '2' &&
        y.charAt(i) != '3' &&
        y.charAt(i) != '4' &&
        y.charAt(i) != '5' &&
        y.charAt(i) != '6' &&
        y.charAt(i) != '7' &&
        y.charAt(i) != '8' &&
        y.charAt(i) != '9' &&
        y.charAt(i) != '.' &&
        y.charAt(i) != ','
      ) {
        return false;
      } else {
        return IsReal(y, ++i);
      }
    } else {
      return true;
    }
  }

  public static void main(String[] args) {
    String x;
    boolean fim = false;
    while (fim == false) {
      x = MyIO.readLine();
      if (
        x.length() == 3 &&
        x.charAt(0) == 'F' &&
        x.charAt(1) == 'I' &&
        x.charAt(2) == 'M'
      ) {
        fim = true;
      } else {
        String y = x.replaceAll(" ", "");

        if (IsVogal(y, 0) == true) {
          System.out.print("SIM");
        } else {
          System.out.print("NAO");
        }
        if (IsConsoante(y, 0) == true) {
          System.out.print(" SIM");
        } else {
          System.out.print(" NAO");
        }
        if (IsNumero(y, 0) == true) {
          System.out.print(" SIM");
        } else {
          System.out.print(" NAO");
        }
        if (IsReal(y, 0) == true) {
          System.out.print(" SIM\n");
        } else {
          System.out.print(" NAO\n");
        }
      }
    }
  }
}
