import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String aux;
        int pos = 0;

        boolean fim = false;
        aux = scanner.nextLine();
        String[] partes = aux.split(" ");

        while (fim == false) {
            boolean achou = false;
            int desempilhado = 0;
            int quantCaixas = Integer.parseInt(partes[0]);
            int quantPilhas = Integer.parseInt(partes[1]);
            for (int i = 0; i < quantPilhas; i++) {
                aux = scanner.nextLine();
                partes = aux.split(" ");
                int numCaixasNaPilha = Integer.parseInt(partes[0]);
                int[] pilha = new int[numCaixasNaPilha];
                for (int j = 0; j < numCaixasNaPilha; j++) {
                    if (Integer.parseInt(partes[j + 1]) == 1) {
                        achou = true;
                        pos = j;
                    } else if (achou == true && j >= pos) {
                        desempilhado++;
                    }
                }
            }
            System.out.println(desempilhado);
            aux = scanner.nextLine();
            if (aux.compareTo("0 0") == 0)
                fim = true;
        }
        scanner.close();
    }
}
