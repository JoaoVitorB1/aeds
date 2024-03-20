
//pronto
import java.io.*;
import java.net.*;

public class teste {

    // funcao para analisar o codigo da pagina
    public static double getHtml(String nome, String endereco) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;
        double preco=0;

        try {
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            // analisa todas as linhas da pagina
            while ((line = br.readLine()) != null) {
                String[] palavras = line.split(" ");

                for (int i = 0; i < palavras.length - 1; i++) {
                    if (palavras[i].equals("on") && palavras[i + 1].equals("Steam</a>")) {
                        System.out.println(nome);
                        //System.out.print("R$");
                        palavras[i-1]=palavras[i-1].replaceAll(",", ".");
                        preco=Double.parseDouble(palavras[i-1]);
                        //MyIO.print(palavras[i - 1] + " " + palavras[i] + " Steam");

                        //System.out.print("\n");                        

                    }

                }

            }
            

        } catch (

        MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here

        }
        return preco;

    }

    public static void main(String[] args) {
        

        String nome1 = "Paris 2023 Contenders Autograph Stickers";
        String endereco1 = "https://csgostash.com/stickers/capsule/391/Paris-2023-Contenders-Autograph-Capsule";
        double quant1 =24;
        double atual1;
        String nome2 = "Paris 2023 Contenders Sticker Capsule";
        String endereco2 = "https://csgostash.com/stickers/capsule/381/Paris-2023-Contenders-Sticker-Capsule";
        double quant2 =52;
        double atual2;
        String nome3 = "Paris 2023 Challengers Autograph";
        String endereco3 = "https://csgostash.com/stickers/capsule/390/Paris-2023-Challengers-Autograph-Capsule";
        double quant3 =20;
        double atual3;

        double resultado=0;
        

        atual1=getHtml(nome1, endereco1);
        double precoEpoca=1.29;
        double gasto = (precoEpoca*quant1);
        double lucro=(quant1*atual1)-(precoEpoca*quant1);
        System.out.println("Quantidade: 24\n"+"Preco Epoca: R$1.29\n"+ "Total: R$30.96\n\nHoje");
        MyIO.print("Preco Atual: R$");        
        System.out.print(atual1);
        MyIO.print("\nTotal: R$");
        double total=quant1*atual1;
        System.out.print(total);
        System.out.print("\nLucro: R$");
        System.out.print(lucro);
        System.out.println("\n-----------\n");
        resultado+=lucro;

        
        atual2=getHtml(nome2, endereco2);
        precoEpoca=1.29;
        gasto = gasto+(precoEpoca*quant2);
        lucro=(quant2*atual2)-(precoEpoca*quant2);
        System.out.println("Quantidade: 52\n"+"Preco Epoca: R$1.29\n"+ "Total: R$67.08\n\nHoje");
        MyIO.print("Preco Atual: R$");        
        System.out.print(atual2);
        MyIO.print("\nTotal: R$");
        total=quant2*atual2;
        System.out.print(total);
        System.out.print("\nLucro: R$");
        System.out.print(lucro);
        System.out.println("\n-----------\n");
        resultado+=lucro;


        atual3=getHtml(nome3, endereco3);
        precoEpoca=1.10;
        gasto = gasto+(precoEpoca*quant3);
        lucro=(quant3*atual3)-(precoEpoca*quant3);
        System.out.println("Quantidade: 20\n"+"Preco Epoca: R$1.10\n"+ "Total: R$22.00\n\nHoje");
        MyIO.print("Preco Atual: R$");        
        System.out.print(atual3);
        MyIO.print("\nTotal: R$");
        total=quant3*atual3;
        System.out.print(total);
        System.out.print("\nLucro: R$");
        MyIO.print(lucro);
        resultado+=lucro;

        System.out.println("\n-----------");
        System.out.print("\nResumo");
        MyIO.print("\nInvestimento: R$ ");
        MyIO.print(gasto);
        MyIO.print("\nLucro Total: R$ ");
        MyIO.print(resultado);
        MyIO.print("\nResultado: R$ ");
        MyIO.print(gasto+resultado);
        System.out.println("\n");



        
        

    }
}
