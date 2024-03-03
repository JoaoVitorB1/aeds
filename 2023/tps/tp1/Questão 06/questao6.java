public class questao6 {
    //maetodo verifica se a string tem somente vogal
    static boolean isVogal(String x) {
        boolean vogais = true;
        for (int i = 0; i < x.length(); i++) {//verificar caracter por caracter
            if (x.charAt(i) != 'a' && x.charAt(i) != 'e' && x.charAt(i) != 'i' && x.charAt(i) != 'o' && x.charAt(i) != 'u' &&
                x.charAt(i) != 'A' && x.charAt(i) != 'E' && x.charAt(i) != 'I' && x.charAt(i) != 'O'&& x.charAt(i) != 'U') {//verifica se a string tem caracteres diferentes de vogais
                vogais = false;//se sim a variavel booleana é trocada para falsa
                break;//força a parada do for
            }
        }
        return vogais;//retorna o resultado
    }
    //metodo para verificar se a string tem somente consoante
    static boolean isConsoante(String x) {
        boolean consoantes = true;
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) != 'b' && x.charAt(i) != 'c' && x.charAt(i) != 'd' && //verifica se a string tem algum caracteres diferente de consoante
                x.charAt(i) != 'f' && x.charAt(i) != 'g' && x.charAt(i) != 'h' && 
                x.charAt(i) != 'j' && x.charAt(i) != 'k' && x.charAt(i) != 'l' && 
                x.charAt(i) != 'm' && x.charAt(i) != 'n' && x.charAt(i) != 'p' && 
                x.charAt(i) != 'q' && x.charAt(i) != 'r' && x.charAt(i) != 's' && 
                x.charAt(i) != 't' && x.charAt(i) != 'v' && x.charAt(i) != 'w' && 
                x.charAt(i) != 'x' && x.charAt(i) != 'y' && x.charAt(i) != 'z' &&
                x.charAt(i) != 'B' && x.charAt(i) != 'C' && x.charAt(i) != 'D' && 
                x.charAt(i) != 'F' && x.charAt(i) != 'G' && x.charAt(i) != 'H' && 
                x.charAt(i) != 'J' && x.charAt(i) != 'K' && x.charAt(i) != 'L' && 
                x.charAt(i) != 'M' && x.charAt(i) != 'N' && x.charAt(i) != 'P' && 
                x.charAt(i) != 'Q' && x.charAt(i) != 'R' && x.charAt(i) != 'S' && 
                x.charAt(i) != 'T' && x.charAt(i) != 'V' && x.charAt(i) != 'W' && 
                x.charAt(i) != 'X' && x.charAt(i) != 'Y' && x.charAt(i) != 'Z') {
                consoantes = false;//se sim a variavel booleana é trocada para falsa
                break;//força a parada do for
            }
        }
        return consoantes;//retorna o resultado
    }
    //metodo para verificar se é somente inteiros
    static boolean isInteiros(String x){
        boolean inteiro=true;
        for (int i = 0; i < x.length(); i++) {
            if (!Character.isDigit(x.charAt(i))) {//verifica se a string tem algum caractere diferente de numero inteiro
                inteiro = false;//se sim a variavel booleana é trocada para falsa
                break;//força a parada do for
            }
        }
        return inteiro;//retorna o resultado
    }
    //metodo para verificar se é somente inteiros
    static boolean isReal(String x){
        boolean real=true;
        
            for(int i=0;i<x.length();i++){
                
                if(x.charAt(0)=='-' ||x.charAt(i)=='0' || x.charAt(i)=='1' || x.charAt(i)=='2' || x.charAt(i)=='3' || x.charAt(i)=='4' ||
                   x.charAt(i)=='5' || x.charAt(i)=='6' || x.charAt(i)=='7' || x.charAt(i)=='8' || x.charAt(i)=='9' || x.charAt(i)=='.' || x.charAt(i)==','){
                    
                }else{
                    real=false;
                    break;
                }
            }
        return real;//retorna o resultado
    }

    public static void main(String[] args) {
        boolean fim = false;
        String x;
        //enquanto a variavel fim for false o programa ficara rodando
        while (fim == false) {
            x = MyIO.readLine();
            // verifica se a entrada foi igual a FIM
            if (x.length() == 3 && x.charAt(0) == 'F' && x.charAt(1) == 'I' && x.charAt(2) == 'M') {
                fim = true;//se sim, a varial fim é trocada para true encerrado o programa
            } else {

                //chama os metodos
                boolean vogais = isVogal(x);
                boolean consoantes = isConsoante(x);
                boolean inteiro = isInteiros(x);
                boolean real = isReal(x);

                //print os resultados
                if(vogais==true){
                    System.out.print("SIM ");
                }else{
                    System.out.print("NAO ");
                }
                if(consoantes==true){
                    System.out.print("SIM ");
                }else{
                    System.out.print("NAO ");
                }
                if(inteiro==true){
                    System.out.print("SIM ");
                }else{
                    System.out.print("NAO ");
                }
                if(real==true){
                    System.out.print("SIM \n");
                }else{
                    System.out.print("NAO \n");
                }

            }
        }
    }
}
