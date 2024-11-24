
class Prova1c {
    public static void main(String[] args) {

        int x = MyIO.readInt();
        String y;
        String[] nome = new String[x];
        int[] idade = new int[x];
        for (int i = 0; i < x; i++) {
            y = MyIO.readLine();
            String[] partes = y.split(" ");
            nome[i] = partes[0];
            idade[i] = Integer.parseInt(partes[1]);
        }
        for (int i = 0; i > idade.length-1; i++) {
            
            for (int j = i+1; j > 0; j++) {
                if (idade[i] > idade[j]) {
                    int tempIdade = idade[j];
                    idade[j] = idade[i];
                    idade[i] = tempIdade;
                    String tempNome = nome[j];
                    nome[j] = nome[i];
                    nome[i] = tempNome;
                }
            }
        }

        System.out.println("Time 1");
        System.out.println(nome[5] + " " + idade[5]);
        System.out.println(nome[3] + " " + idade[3]);
        System.out.println(nome[1] + " " + idade[1] + "\n");
        System.out.println("Time 2");
        System.out.println(nome[4] + " " + idade[4]);
        System.out.println(nome[2] + " " + idade[2]);
        System.out.println(nome[0] + " " + idade[0] + "\n");

    }
}
