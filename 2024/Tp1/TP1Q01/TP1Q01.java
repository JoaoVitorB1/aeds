//Pronto
class TP1Q01{
    public static void main(String[] args){
        String x;
        boolean fim=false;
        while(fim==false){
            x=MyIO.readLine();
            //verifica se o FIM
            if(x.length()==3&&x.charAt(0)=='F'&&x.charAt(1)=='I'&&x.charAt(2)=='M'){
                fim=true;
            
            }else{
                boolean IsPalindromo=true;
                
                //verifica se a palavra eh palindromo
                for(int i=0;i<x.length()/2;i++){
                    if(x.charAt(i)!=x.charAt(x.length()-1-i)){
                        IsPalindromo=false;
                        break;
                    }
                }
                if(IsPalindromo==true){
                    System.out.println("SIM");
                }else{
                    System.out.println("NAO");
                }
            }
        }
    }
}