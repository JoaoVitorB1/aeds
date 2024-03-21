class TP1Q12{
    static String Ciframento(String x, char[] y,int i){
        char caractereInvalido = '\uFFFD';
        //ciframento
        if(i<x.length()){
            if(x.charAt(i)!=caractereInvalido){
                y[i]=(char)(x.charAt(i)+3);
            }else{
                y[i]=caractereInvalido;
            }
            return Ciframento(x,y,++i);
        }
        //retorna a string com o ciframento 3
        System.out.println(y);      
        return String.valueOf(y);        
    }
    public static void main(String[] args){
        String x;
        boolean fim=false;
        MyIO.setCharset("UTF-8");
        while(fim==false){
            x=MyIO.readLine();
            //verifica se a entrada eh FIM
            if(x.length()==3&&x.charAt(0)=='F'&&x.charAt(1)=='I'&&x.charAt(2)=='M'){
                fim=true;            
            }else{
                char[] y=new char[x.length()];
                //chama o metodo ciframento e printa na tela              
                Ciframento(x,y,0);
            }
        }
    }
}