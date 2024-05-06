import java.util.ArrayList;
import java.util.Scanner;

class WarmUpJava {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        String x;

        while (scanner.hasNext()) {
            x = scanner.nextLine();
            if (x.equals(" ")||x.equals("")) {
            } // se a entrada eh so espaco nao fazer nada
            else {
                String temp = x;
                temp = x.toLowerCase(); // passa tudo para letra minuscula
                temp = temp.replaceAll("[^a-zA-Z0-9 ]", " ");// substitui caracteres especiais por espaco
                String[] partesTmp = temp.split(" ");
                for (int i = 0; i < partesTmp.length; i++) {// se partesTmp.[i]==vazio ou " " nao fazer nada
                    if ((partesTmp[i].equals(" ")) || (partesTmp[i].equals(""))) {
                    } else {
                        arrayList.add(partesTmp[i]);
                    }
                }
            }
        }
        scanner.close();
        for (int i = 0; i < arrayList.size() - 1; i++) {// tira elementos repetidos
            for (int j = i + 1; j < arrayList.size(); j++) {
                if (arrayList.get(i).equals(arrayList.get(j))) {
                    arrayList.remove(j);
                }
            }
        }
        for (int i = 0; i < arrayList.size() - 1; i++) {// coloca em ordem alfabetica
            int menor = i;
            for (int j = i + 1; j < arrayList.size(); j++) {
                if (arrayList.get(menor).compareTo(arrayList.get(j)) > 0) {
                    menor = j;
                }
            }
            x = arrayList.get(i);
            arrayList.set(i, arrayList.get(menor));
            arrayList.set(menor, x);
        }
        
        for (int i = 0; i < arrayList.size(); i++) { // imprime todos os elementos
            System.out.println(arrayList.get(i));
        }

    }
}
