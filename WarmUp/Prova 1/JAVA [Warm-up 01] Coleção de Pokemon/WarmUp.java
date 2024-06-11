class WarmUp {
    public static void main(String[] args) {
        int quant;
        quant=MyIO.readInt();        
        String[] x=new String[quant];
        int iguais=0;
        for(int i=0;i<quant;i++){
            x[i]=MyIO.readLine();
        }
        for(int i=0;i<quant-1;i++){
            for(int j=1;j<quant;j++){
                if(x[i]==x[j]){
                    iguais+=1;
                }
            }
        }

        
        System.out.print("Falta(m) ");
        MyIO.print(151-iguais);
        System.out.print(" pomekon(s).");

    }
}