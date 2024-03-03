public class questao10 {
    //verificar se a string é palindromo de forma recursiva
    static boolean isPalin(String x,int j,int y){
        if(y<=j){
            if(x.charAt(y)==x.charAt(j)){
                return isPalin(x,j--,y+1);
            }else{
                return false;
            }
        }else{
            return true;
        }

    }
    public static void main(String[] args){
        String x;
        boolean fim=false;
        boolean verPalin;
        int y=0;
        //enquanto a entrada for diferente de FIM o programa continuara rodando
        while(fim==false){
            x=MyIO.readLine();
            //verifica se a entrada foi igual a FIM
            if(x.length()==3&&x.charAt(0)=='F'&&x.charAt(1)=='I'&&x.charAt(2)=='M'){
                fim=true;
            }else{
                //tira todos os espacos e -
                x=x.replaceAll(" ","");
                x=x.replaceAll("-","");
                //chama o metodo verPalin para verificar se a entrada e palindromo
                int tam=x.length()-1;
                verPalin=isPalin(x,tam,y);
                
                if(verPalin==true){
                    System.out.println("SIM");//se a entrada for palindromo vai aparecer SIM na tela
                }else{
                    System.out.println("NAO");//caso contrario o NAO vai aparecer na tela
                }
            }

        }
    }
}
