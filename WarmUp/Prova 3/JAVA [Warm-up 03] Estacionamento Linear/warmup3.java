import java.util.ArrayList;
import java.util.Scanner;

class warmup3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String x;
        boolean fim = false;
        boolean resp = true;
        int quantMotorista = 0, capacidade = 0;
        x = scanner.nextLine();
        while (fim == false) {
            String[] partes = x.split(" ");
            quantMotorista = Integer.parseInt(partes[0]);
            capacidade = Integer.parseInt(partes[1]);
            int quant = 0;
            ArrayList<Integer> entrada = new ArrayList<>();
            ArrayList<Integer> saida = new ArrayList<>();

            for (int i = 0; i < quantMotorista; i++) {
                x = scanner.nextLine();
                String[] partes2 = x.split(" ");
                if (quant == 0) { // insere se nao tiver ninguem
                    entrada.add(Integer.parseInt(partes2[0]));
                    saida.add(Integer.parseInt(partes2[1]));
                    quant++;
                } else {
                    // verifica se o novo carro esta entrando antes do primeiro da pilha sair
                    // se verdadeiro, remove o carro e insere o novo
                    if (saida.get(quant - 1) <= Integer.parseInt(partes2[0])) {
                        entrada.remove(quant - 1);
                        saida.remove(quant - 1);
                        entrada.add(Integer.parseInt(partes2[0]));
                        saida.add(Integer.parseInt(partes2[1]));
                    } else {// caso contrario
                        // verifica se a quantidade eh menor que a capacidade
                        if (quant < capacidade) {// se verdadeiro insere
                            entrada.add(Integer.parseInt(partes2[0]));
                            saida.add(Integer.parseInt(partes2[1]));
                            quant++;
                        } else {// caso contrario
                            resp = false;
                            i = quantMotorista;
                        }
                    }
                }
            }

            // verifica se os primeiros carros a entrar no estacionamento vao sair antes dos ultimos a entrar
            // se verdadeiro resp == FALSE caso contrario resp = TRUE
            if (resp != false) {
                for (int i = 0; i < saida.size() - 1; i++) {
                    int menor = saida.get(i);
                    for (int j = 1; j < saida.size(); j++) {
                        if (menor < saida.get(j)) {
                            resp = false;
                            i = saida.size();
                        }
                    }
                }
            }
            System.out.println(resp ? "Sim" : "Nao");
            resp = true;
            x = scanner.nextLine();
            if (x.equals("0 0")) {
                fim = true;
            }
        }
        scanner.close();
    }
}