//pronto
class TP1Q06{
    public static void main(String[] args){
        String x;
        boolean fim=false;
        while(fim==false){
            x=MyIO.readLine();
                if(x.length()==3&&x.charAt(0)=='F'&&x.charAt(1)=='I'&&x.charAt(2)=='M'){
                    fim=true;
                }else{
                    String y=x.replaceAll(" ","");
                    boolean IsVogal=true;
                    boolean IsConsoante=true;
                    boolean IsNumero=true;
                    boolean IsReal=true;
                
                    //verifica se eh so vogal
                    for(int i=0;i<y.length();i++){
                        if(y.charAt(i)!='a'&&y.charAt(i)!='e'&&y.charAt(i)!='i'&&y.charAt(i)!='o'&&y.charAt(i)!='o'&&
                        y.charAt(i)!='A'&&y.charAt(i)!='E'&&y.charAt(i)!='I'&&y.charAt(i)!='O'&&y.charAt(i)!='U'){
                            IsVogal=false;
                            break;
                        }
                    }

                    //verifica se eh so consoante
                    for(int i=0;i<y.length();i++){
                        if(y.charAt(i)=='a'||y.charAt(i)=='e'||y.charAt(i)=='i'||y.charAt(i)=='o'||y.charAt(i)=='o'||
                        y.charAt(i)=='A'||y.charAt(i)=='E'||y.charAt(i)=='I'||y.charAt(i)=='O'||y.charAt(i)=='U'){
                            IsConsoante=false;
                            break;
                        }
                    }

                    //verifica se eh so numero inteiro
                    for(int i=0;i<y.length();i++){
                        if(y.charAt(i)!='0'&&y.charAt(i)!='1'&&y.charAt(i)!='2'&&y.charAt(i)!='3'&&y.charAt(i)!='4'&&
                        y.charAt(i)!='5'&&y.charAt(i)!='6'&&y.charAt(i)!='7'&&y.charAt(i)!='8'&&y.charAt(i)!='9'){
                            IsNumero=false;
                            break;
                        }
                    }

                    //verifica se eh so numero real
                    for(int i=0;i<y.length();i++){
                        if(y.charAt(i)!='0'&&y.charAt(i)!='1'&&y.charAt(i)!='2'&&y.charAt(i)!='3'&&y.charAt(i)!='4'&&
                        y.charAt(i)!='5'&&y.charAt(i)!='6'&&y.charAt(i)!='7'&&y.charAt(i)!='8'&&y.charAt(i)!='9'&&y.charAt(i)!='.'&&y.charAt(i)!=','){
                            IsReal=false;
                            break;
                        }
                    }

                    if(IsVogal==true){
                        System.out.print("SIM");
                    }else{
                        System.out.print("NAO");
                    }
                    if(IsConsoante==true){
                        System.out.print(" SIM");
                    }else{
                        System.out.print(" NAO");
                    }
                    if(IsNumero==true){
                        System.out.print(" SIM");
                    }else{
                        System.out.print(" NAO");
                    }
                    if(IsReal==true){
                        System.out.print(" SIM\n");
                    }else{
                        System.out.print(" NAO\n");
                    }
                }
        }
    }
}