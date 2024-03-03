
public class questao1 {
    static boolean isPalin(String x){
        boolean pa=true;
        //verifica se a String e um palindromo
        for(int i=0,j=x.length()-1;i<j;i++,j--){
            if(x.charAt(i)!=x.charAt(j)){
                pa=false;
                break;
            }
        }
        return pa;
    }
    public static void main(String[] args){
        boolean fim=false;
        String x;
        boolean verPalin;
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
                verPalin=isPalin(x);
                
                if(verPalin==true){
                    System.out.println("SIM");//se a entrada for palindromo vai aparecer SIM na tela
                }else{
                    System.out.println("NAO");//caso contrario o NAO vai aparecer na tela
                }
            }

        }
    }
}
