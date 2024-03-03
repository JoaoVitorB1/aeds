import java.util.Scanner;
class espelho {
    public static void  main(String[] args){
        //scanner
        Scanner ler = new Scanner(System.in);
        String x;
        //enquanto tiver linha vai ficar rodando
        while(ler.hasNext()){
            x = ler.nextLine();
            String y="";
            //separa entrada em 2 strings
            String[] partes=x.split(" ");
            //converte string para int
            int a = Integer.parseInt(partes[0]);
            int b = Integer.parseInt(partes[1]);
            //coloca todos os numeros do intervalo em uma string
            for(int i = a;i<=b;i++){
                y+=Integer.toString(i);//inteiro para string
            }
            char[] yChar=y.toCharArray();//criar um vetor de char com igual string
            char[] resp= new char[yChar.length*2];//cria um vetor com o dobro de tamanho

            for(int i=0;i<y.length();i++){//passa todos os termos para o vetor maior
                resp[i]=yChar[i];
            }
            for(int i=y.length()-1,j=y.length();i>=0;i--,j++){//pega o ultimo termo e coloca no primeiro espaco vazio
                resp[j]=yChar[i];
            }
            System.out.println(resp);//print tudo
        }
        ler.close();//fecha scanner

    }
}