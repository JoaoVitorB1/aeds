//terminar
class TP1Q08 {
    public static void main(String[] args) {
        String x;
        x = MyIO.readLine();

        Arq.openWrite("questao 8.txt");
        
        for(int i=0;i<Integer.parseInt(x);i++){
            double num=MyIO.readDouble();
            Arq.println(num);
        }
        Arq.close();        
       
        
    }
}
