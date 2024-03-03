public class questao3 {
    //rever esta questao
    static void ciframento(String x){
        char[] y=x.toCharArray();
        for(int i=0;i<x.length();i++){
            int valor = (int) y[i];
            
                y[i]=(char) (valor+3);
                              
        }
        System.out.println(y);
                
    }
    public static void main(String[] args){
        String x;
        boolean fim=false;
        while(fim==false){            
            x=MyIO.readLine();
            if(x.length()==3&&x.charAt(0)=='F'&&x.charAt(1)=='I'&&x.charAt(2)=='M'){
                fim=true;
            }else{
                ciframento(x);
            }
            
        }
    }
}
