import java.util.Scanner;

class combinador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String x;
        while (scanner.hasNext()) {
            x = scanner.nextLine();
            String[] partes = x.split(" ");
            char[] str1=partes[0].toCharArray();
            char[] str2=partes[1].toCharArray();
            char[] resp=new char[x.length()-1];
            
            if (str1.length > str2.length) {
                for (int i = 0,j=0; j < str2.length;j++,i++) {
                    resp[i]=str1[j];
                    i++;
                    resp[i]=str2[j];                    
                }

                int pos=str2.length*2;
                for(int i=str2.length,j=pos;i<str1.length;i++,j++){
                    resp[j]=str1[i];
                }
            }else if(str1.length < str2.length){
                for (int i = 0,j=0; j < str1.length; i++,j++) {
                    resp[i]=str1[j];
                    i++;
                    resp[i]=str2[j];
                    
                }
                int pos=str1.length*2;
                for(int i=str1.length,j=pos;i<str2.length;i++,j++){
                    resp[j]=str2[i];
                }
            }else{
                for (int i = 0,j=0; j < str1.length; i++,j++) {
                    resp[i]=str1[j];
                    i++;
                    resp[i]=str2[j];
                }
            }
            System.out.println(resp);
        }
        scanner.close();
    }
}