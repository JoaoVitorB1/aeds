
//faltando fazer a leitura de tras pra frente
import java.io.*;
import java.io.RandomAccessFile;

class TP1Q08 {
    public static void main(String[] args) throws Exception {
        String x;
        x = MyIO.readLine();
        // abre o arquivo
        Arq.openWrite("questao 8.txt");

        // coloca numero no arquivo
        for (int i = 0; i < Integer.parseInt(x); i++) {
            double num = MyIO.readDouble();
            Arq.println(num);
        }

        // fecha o arquivo
        Arq.close();

        //a partir daqui nao funciona
        RandomAccessFile raf = new RandomAccessFile("questao 8.txt", "rw");
        long fileSize = raf.length();// tamanho do arquivo
        long currentPosition=fileSize;//comecar a partir do final
        byte[] buffer=new byte[1024];
        raf.seek(fileSize);
        while (currentPosition > 0) {
            raf.seek(currentPosition);//more o ponteiro para a posicao atual            
            int bytesRead=raf.read(buffer);
            for(int i=bytesRead-1;i>=0;i--){
                System.out.println((char)buffer[i]);
            }
        }

        raf.close();

    }
}
