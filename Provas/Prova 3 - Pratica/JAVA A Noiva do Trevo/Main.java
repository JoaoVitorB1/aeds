import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String aux;

        //Arraylists para armazenar
        //nomesAntesMeiaNoite, horasAntesMeiaNoite, minAntesMeiaNoite
        //nomesDepoisMeiaNoite, horasDepoisMeiaNoite, minDepoisMeiaNoite
        ArrayList<String>verdadeAnome=new ArrayList<>();
        ArrayList<Integer>verdadeAhMN=new ArrayList<>();
        ArrayList<Integer>verdadeAmMN=new ArrayList<>();
        ArrayList<String>verdadeDnome=new ArrayList<>();
        ArrayList<Integer>verdadeDhMN=new ArrayList<>();
        ArrayList<Integer>verdadeDmMN=new ArrayList<>();
        
        //declaracao variaveis para minutos limite e quant de relatos
        int minLimite;
        int relatos;

        //atribuindo valores minLimite e relatos
        aux=scanner.nextLine();
        String[] partes=aux.split(" ");
        minLimite=Integer.parseInt(partes[0]);
        relatos=Integer.parseInt(partes[1]);
        
        //ler relatos enquanto relatos>0 
        while (relatos>0) {
            aux=scanner.nextLine();

            //separa o nome da pessoa e o horario
            partes=aux.split(" ");

            //separa o horario para obter horas e minutos
            String[] partes2=partes[0].split(":");
            int hora=Integer.parseInt(partes2[0]);
            int min=Integer.parseInt(partes2[1]);

            //se relato for antes de meia noite
            if(hora==23){
                //calcular minutos minimo para ser verdade
                //se verdade add nos arraylists antes meianoite
                int minMinimo=60-minLimite;
                if(min>=minMinimo){
                    verdadeAnome.add(partes[1]);
                    verdadeAhMN.add(hora);
                    verdadeAmMN.add(min);
                }
            }
            //se relato for depois de meia noite
            if(hora==0){
                //calcular minutos maximo para ser verdade
                //se verdade add nos arraylists depois meianoite            
                int minMaximo=minLimite;
                if(min<=minMaximo){
                    verdadeDnome.add(partes[1]);
                    verdadeDhMN.add(hora);
                    verdadeDmMN.add(min);
                }
            }
            //relatos --
            relatos--;
        }

        // organizar por hora o arraylist antes meia noite
        for(int i=0;i<verdadeAmMN.size()-1;i++){
            int menor=verdadeAmMN.get(i);
            for(int j=i+1;j<verdadeAmMN.size();j++){
                if(menor>verdadeAmMN.get(j)){
                    String tempNome=verdadeAnome.get(i);
                    verdadeAnome.set(i, verdadeAnome.get(j));
                    verdadeAnome.set(j, tempNome);
                    int tempMin=verdadeAmMN.get(i);
                    verdadeAmMN.set(i, verdadeAmMN.get(j));
                    verdadeAmMN.set(j, tempMin);
                    menor=verdadeAmMN.get(i);
                }
            }
        }

        // ordenar por hora o arraylist depois meia noite
        for(int i=0;i<verdadeDmMN.size()-1;i++){
            int menor=verdadeDmMN.get(i);
            for(int j=i+1;j<verdadeDmMN.size();j++){
                if(menor>verdadeDmMN.get(j)){
                    String tempNome=verdadeDnome.get(i);
                    verdadeDnome.set(i, verdadeDnome.get(j));
                    verdadeDnome.set(j, tempNome);
                    int tempMin=verdadeDmMN.get(i);
                    verdadeDmMN.set(i, verdadeDmMN.get(j));
                    verdadeDmMN.set(j, tempMin);
                    menor=verdadeDmMN.get(i);
                }
            }
        }

        //imprimir em ordem as pessoas que falaram a verdade antes de meia noite
        for(int i=0;i<verdadeAnome.size();i++){
            System.out.println(verdadeAnome.get(i));
        }
        //imprimir em ordem as pessoas que falaram a verdade depois de meia noite
        for(int i=0;i<verdadeDnome.size();i++){
            System.out.println(verdadeDnome.get(i));
        }

        scanner.close();
    }
}
