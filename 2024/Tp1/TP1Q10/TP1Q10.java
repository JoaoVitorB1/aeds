//pronto
class TP1Q10 {
    static boolean IsPalindromo(String x, int i) {

        if (i < (x.length() / 2)) {
            if (x.charAt(i) != x.charAt(x.length() - i - 1)) {
                return false;
            } else {
                return IsPalindromo(x, i + 1);
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
            if (x.length() == 3 && x.charAt(0) == 'F' && x.charAt(1) == 'I' && x.charAt(2) == 'M') {
                fim = true;
            } else {
                if (IsPalindromo(x, 0) == false) {
                    System.out.println("NAO");
                } else {
                    System.out.println("SIM");
                }
            }
        }
    }
}
