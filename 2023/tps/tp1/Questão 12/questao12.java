public class questao12 {
    //rever esta questao
    static char[] ciframento(char[] y,int pos){
        if(pos<y.length){
            int valor = (int) y[pos];
            y[pos]=(char) (valor+3);
            return ciframento(y, (pos+1));
        }else{
            return y;
        }
        
    }
    public static void main(String[] args){
        boolean fim=false;
        String x;
        int pos=0;      
        while(fim==false){
            x=MyIO.readLine();
            if(x.length()==3&&x.charAt(0)=='F'&&x.charAt(1)=='I'&&x.charAt(2)=='M'){
                fim=true;            
            }else{
                char[] y=x.toCharArray();
                System.out.println(ciframento(y,pos));
            }
        }    
    }
}
