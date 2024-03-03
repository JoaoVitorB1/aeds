import java.util.Scanner;
class espelho1 {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String x=scanner.nextLine();
            String[] partes=x.split(" ");
            int a=Integer.parseInt(partes[0]);
            int b=Integer.parseInt(partes[1]);
            String y="";
            for(int i=a;i<=b;i++){
                y+=Integer.toString(i);
            }
            char[] yChar=y.toCharArray();
            char[] y2=new char[yChar.length*2];
            for(int i=0;i<y.length();i++){
                y2[i]=yChar[i];
            }
            for(int j=y.length(),i=y.length()-1;i>=0;j++,i--){
                y2[j]=y2[i];
            }
            System.out.println(y2);
        }
        scanner.close();
    }    
}
