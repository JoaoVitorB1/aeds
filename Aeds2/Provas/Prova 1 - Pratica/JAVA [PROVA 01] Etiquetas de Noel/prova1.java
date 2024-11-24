
class prova1{
    /**
     * 
     */
    public static void main(String[] args){      
        int x=MyIO.readInt();
        String[] idiomas = new String[x];
        String[] traducao = new String[x];
        for(int i=0;i<x;i++){
            idiomas[i]=MyIO.readLine();
            traducao[i]=MyIO.readLine();
        }
        x=MyIO.readInt();
        String nome;
        String nacionalidade;
        for(int i=0;i<x;i++){
            nome=MyIO.readLine();
            nacionalidade=MyIO.readLine();
            for(int j=0;j<idiomas.length;j++){
                if(idiomas[j].contentEquals(nacionalidade)){
                    System.out.println(nome);
                    System.out.println(traducao[j]+"\n");
                    j=idiomas.length;
                }
                
            }
        }

    }
}