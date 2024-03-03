import java.util.Random;
    
public class questao4 {
    public static void main(String[]args){
        Random gerador = new Random();
        gerador.setSeed(4);
        String x;
        boolean fim=false;
        //enquanto fim == false o programa vai continuar rodando
        while(fim==false){
            x=MyIO.readLine();
            //verifica se a entrada é == FIM
            if(x.length()==3&&x.charAt(0)=='F'&&x.charAt(1)=='I'&&x.charAt(2)=='M'){
                fim=true;//se sim fim==true
            }else{//caso contrario continuara rodando

                //gera 2 letras aleatorias
                char a=(char) ('a' + (Math.abs(gerador.nextInt()) % 26));
                char b=(char) ('a' + (Math.abs(gerador.nextInt()) % 26));  
                
                alterar(x,a,b);
            }
        }
    }
    //realiza a troca da primeira letra gerada pela segunda
    static void alterar(String x,char a, char b){        
        String xab =x.replaceAll(String.valueOf(a), String.valueOf(b));
        //print na tela o resultado final
        System.out.println(xab);
    }
}
