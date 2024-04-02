class palindromo {

    public static void main(String[] args) {
        String x;
        boolean fim = false;
        while (fim == false) {
            x = MyIO.readLine();
            if (x.length() == 3 && x.charAt(0) == 'F' && x.charAt(1) == 'I' && x.charAt(2) == 'M') {
                fim = true;
            } else {
                boolean IsPalindromo=true;
                for(int i=0;i<x.length()/2;i++){               
                    if (x.charAt(i) != x.charAt(x.length() - i - 1)) {
                        IsPalindromo=false;
                        break;
                    }
                }

                if (IsPalindromo == false) {
                    System.out.println("NAO");
                } else {
                    System.out.println("SIM");
                }
            }
        }
    }
}
