import java.util.ArrayList;
import java.util.Scanner;


// Varios erros na prova de c
// Resolvi tentar fazer em java e funcionou
public class novac {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String aux;
        
        while (scanner.hasNext()) {

            //array list para armazenar os pacotes
            ArrayList<String> pacote = new ArrayList<>(); 

            //leitura do bit para ver se esta iniciando
            aux=scanner.nextLine();
            
            //se bit == 1
            if(aux.equals("1")){
                //ler pacote
                aux=scanner.nextLine();

                //enquanto entrada != 0 add no arraylist
                while(aux.compareTo("0")!=0){
                    pacote.add(aux);
                    aux=scanner.nextLine();
                }

                //organizar ordem pacotes
                for(int i=0;i<pacote.size()-1;i++){
                    String menor=pacote.get(i);
                    for(int j=i+1;j<pacote.size();j++){
                        if(menor.compareTo(pacote.get(j))>0){
                            String tempPacote=pacote.get(i);
                            pacote.set(i,pacote.get(j));
                            pacote.set(j, tempPacote);                            
                            menor=pacote.get(i);
                        }
                    }
                }

                //imprimir pacotes
                for(int i=0;i<pacote.size();i++){                    
                    System.out.println(pacote.get(i));                   
                }

                //linhas em branco
                System.out.println();
                System.out.println();
                
            }    
        }
        scanner.close();

    }
}
