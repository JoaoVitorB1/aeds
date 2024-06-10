import java.sql.Array;
import java.util.ArrayList;

class prova2 {
    public static void main(String[]args){
        String x;
        boolean fim=false;
        ArrayList<String> norte=new ArrayList<>();
        ArrayList<String> sul=new ArrayList<>();
        ArrayList<String> leste=new ArrayList<>();
        ArrayList<String> oeste=new ArrayList<>();
        String resp="";
        String direcao="";
        int cont=0;
        while(fim==false){

            if(cont==0){
                direcao=MyIO.readLine();
                cont++;
            }
            
            if(direcao.equals("0")){
                fim=true;
            }else if(direcao.equals("-4")){
                x=MyIO.readLine();
                while(x.charAt(0)=='A'){
                    leste.add(x);
                    x=MyIO.readLine();
                    
                }
                direcao=x;
                
            }else if(direcao.equals("-3")){
                x=MyIO.readLine();
                while(x.charAt(0)=='A'){
                    norte.add(x);
                    x=MyIO.readLine();
                    
                }
                direcao=x;
                
            }else if(direcao.equals("-2")){
                x=MyIO.readLine();
                while(x.charAt(0)=='A'){
                    sul.add(x);
                    x=MyIO.readLine();
                    
                }
                direcao=x;
                
            }else if(direcao.equals("-1")){
                x=MyIO.readLine();
                while(x.charAt(0)=='A'){
                    oeste.add(x);
                    x=MyIO.readLine();
                    
                }
                direcao=x;
                
            }                
            
        }
        int maior=oeste.size();
        if(maior<norte.size()){
            maior=norte.size();
        }if(maior<sul.size()){
            maior=sul.size();
        }if(maior<leste.size()){
            maior=leste.size();
        }
        for(int i=0;i<maior;i++){
            if(i<oeste.size()){
                resp+=oeste.get(i);
                resp+=" ";
            }
            if(i<norte.size()){
                resp+=norte.get(i);
                resp+=" ";
            }
            if(i<sul.size()){
                resp+=sul.get(i);
                resp+=" ";
            }
            if(i<leste.size()){
                resp+=leste.get(i);
                resp+=" ";
            }
        }               
        System.out.println(resp); 
    }    
}
