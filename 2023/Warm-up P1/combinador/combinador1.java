import java.util.Scanner;
class combinador1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String x=scanner.hasNext();
            String[] partes=x.split(" ");
            char[] x1=partes[0].toCharArray();
            char[] x2=partes[1].toCharArray();
            char[] resultado=new char[x1.length+x2.length];
            if(x1.length>x2.length){
                for(int i=0,j=0;i<x2.length;i++,j++){
                    resultado[j]=x1[i];
                    i++;
                    resultado[j]=x2[i];
                }
                int pos=x2.length*2;
                for(int i=pos,j=x2.length-1;i<resultado.length;i++,j++){
                    resultado[i]=x1[j];
                }
            }else if(x2.length>x1.length){
                for(int i=0,j=0;i<x1.length;i++,j++){
                    resultado[j]=x1[i];
                    i++;
                    resultado[j]=x2[i];
                }
                int pos=x1.length*2;
                for(int i=pos,j=x1.length-1;i<resultado.length;i++,j++){
                    resultado[i]=x1[j];
                }
            }else{
                for(int i=0,j=0;i<resultado.length;i++,j++){
                    resultado[i]=x1[j];
                    j++;
                    resultado[i]=x2[j];
                }
            }
            System.out.println(resultado);
        }
        scanner.close();
    }
}
