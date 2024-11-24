
//pronto
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

class TP1Q08 {
    public static String removeZerosDireita(double num) {
        // Define o padrão de formatação
        DecimalFormat df = new DecimalFormat("#.#############");

        // Formata o número
        return df.format(num);
    }

    public static void main(String[] args) throws Exception {
        String x;
        x = MyIO.readLine();
        // abre o arquivo
        Arq.openWrite("questao 8.txt");

        // coloca numero no arquivo
        for (int i = 0; i < Integer.parseInt(x); i++) {

            Double num = MyIO.readDouble();
            String y = removeZerosDireita(num);
            Arq.println(y);

        }

        // fecha o arquivo
        Arq.close();

        RandomAccessFile raf = new RandomAccessFile("questao 8.txt", "r");

        // tamanho do arquivo
        long tamanhoArqivo = raf.length();

        // coloca a posicao na ultima posicao do arquivo
        long posicao = tamanhoArqivo-1;

        // variavel que armazena cada linha do arquivo        
        StringBuilder linha = new StringBuilder();

        // Iterar sobre o arquivo de trás para frente
        while (posicao > 0) {
            posicao--;
            raf.seek(posicao);
            char caractere = (char) raf.read();

            // Se o caractere for uma quebra de linha ou o início do arquivo, processar a linha
            if (caractere == '\n' || caractere == '\r' || posicao == 0) {
                // Inverter a ordem dos caracteres na linha, já que estamos lendo de trás para frente
                linha.reverse();

                // Imprimir a linha
                System.out.println(linha.toString());

                // Limpar o StringBuilder para a próxima linha
                linha.setLength(0);
            } else {
                // Adicionar o caractere ao StringBuilder
                linha.append(caractere);
            }

        }
        raf.close();

    }
}
