
//Questoes prontas 1,3,5,6,11
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Main {
    static double inicio = System.currentTimeMillis();
    static int comp = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Questao3();
        scanner.close();
    }

    // ---------------------------------------------Questoes---------------------------------------------
    // Questao 1
    static void Questao1() {
        String x;
        boolean fim = false;
        ListaQuestao1 listaQuestao1 = new ListaQuestao1(99);
        ArrayList<Personagem> personagens = readArq();
        while (fim == false) {
            x = scanner.nextLine();
            if (x.length() == 3 && x.charAt(0) == 'F' && x.charAt(1) == 'I' && x.charAt(2) == 'M') {
                fim = true;
            } else {
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(x)) {
                        try {
                            listaQuestao1.inserirFim(personagem);
                        } catch (Exception erro) {
                            System.out.println(erro.getMessage());
                        }
                        break;
                    }
                }
            }
        }
        x = scanner.nextLine();
        int quant = Integer.parseInt(x);

        for (int i = 0; i < quant; i++) {
            x = scanner.nextLine();
            if (x.startsWith("II")) {
                String[] partes = x.split(" ");
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(partes[1])) {
                        try {
                            listaQuestao1.inserirInicio(personagem);
                        } catch (Exception erro) {
                            System.out.println(erro.getMessage());
                        }
                        break;
                    }
                }
            } else if (x.startsWith("IF")) {
                String[] partes = x.split(" ");
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(partes[1])) {
                        try {
                            listaQuestao1.inserirFim(personagem);
                        } catch (Exception erro) {
                            System.out.println(erro.getMessage());
                        }
                        break;
                    }
                }
            } else if (x.startsWith("I*")) {
                String[] partes = x.split(" ");
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(partes[2])) {
                        int pos = Integer.parseInt(partes[1]);
                        try {
                            listaQuestao1.inserir(personagem, pos);
                        } catch (Exception erro) {
                            System.out.println(erro.getMessage());
                        }
                        break;

                    }
                }
            } else if (x.startsWith("RI")) {
                try {
                    System.out.println("(R) " + listaQuestao1.removerInicio());
                } catch (Exception erro) {
                    System.out.println(erro.getMessage());
                }
            } else if (x.startsWith("RF")) {
                try {
                    System.out.println("(R) " + listaQuestao1.removerFim());
                } catch (Exception erro) {
                    System.out.println(erro.getMessage());
                }
            } else if (x.startsWith("R*")) {
                String[] partes = x.split(" ");
                try {
                    System.out.println("(R) " + listaQuestao1.remover(Integer.parseInt(partes[1])));
                } catch (Exception erro) {
                    System.out.println(erro.getMessage());
                }
            }
        }
        listaQuestao1.mostrar();
    }

    // Questao 3
    static void Questao3() {
        String x;
        boolean fim = false;
        PilhaSequencial pilhaSequencial = new PilhaSequencial();
        ArrayList<Personagem> personagens = readArq();
        while (fim == false) {
            x = scanner.nextLine();
            if (x.equals("FIM")) {
                fim = true;
            } else {
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(x)) {
                        try {
                            pilhaSequencial.inserir(personagem);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                }
            }
        }
        x = scanner.nextLine();
        int quant = Integer.parseInt(x);
        for (int i = 0; i < quant; i++) {
            x = scanner.nextLine();
            if (x.equals("R")) {
                try {
                    System.out.println("(R) " + pilhaSequencial.remover());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                String[] partes = x.split(" ");
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(partes[1])) {
                        try {
                            pilhaSequencial.inserir(personagem);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                }
            }
        }
        pilhaSequencial.mostrar();

    }

    // Questao 5
    static void Questao5() {
        String x;
        boolean fim = false;
        ListaDinamica lista = new ListaDinamica();
        ArrayList<Personagem> personagens = readArq();
        while (fim == false) {
            x = scanner.nextLine();
            if (x.length() == 3 && x.charAt(0) == 'F' && x.charAt(1) == 'I' && x.charAt(2) == 'M') {
                fim = true;
            } else {
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(x)) {
                        lista.inserirFim(personagem);
                    }
                }
            }
        }
        x = scanner.nextLine();
        int quant = Integer.parseInt(x);
        for (int i = 0; i < quant; i++) {
            x = scanner.nextLine();
            if (x.startsWith("II")) {
                String[] partes = x.split(" ");
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(partes[1])) {
                        lista.inserirInicio(personagem);
                    }
                }
            } else if (x.startsWith("IF")) {
                String[] partes = x.split(" ");
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(partes[1])) {
                        lista.inserirFim(personagem);
                    }
                }
            } else if (x.startsWith("I*")) {
                String[] partes = x.split(" ");
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(partes[2])) {
                        int pos = Integer.parseInt(partes[1]);
                        try {
                            lista.inserir(personagem, pos);
                        } catch (Exception erro) {

                        }

                    }
                }
            } else if (x.startsWith("RI")) {
                try {
                    System.out.println("(R) " + lista.removerInicio());
                } catch (Exception erro) {
                    System.out.println(erro.getMessage());
                }
            } else if (x.startsWith("RF")) {
                try {
                    System.out.println("(R) " + lista.removerFim());
                } catch (Exception erro) {
                    System.out.println(erro.getMessage());
                }
            } else {
                String[] partes = x.split(" ");
                try {
                    System.out.println("(R) " + lista.remover(Integer.parseInt(partes[1])));
                } catch (Exception erro) {
                    System.out.println(erro.getMessage());
                }
            }
        }
        lista.mostrar();
    }

    // Questao 6
    static void Questao6() {
        String x;
        boolean fim = false;
        PilhaDinamica pilha = new PilhaDinamica();
        ArrayList<Personagem> personagens = readArq();
        while (fim == false) {
            x = scanner.nextLine();
            if (x.length() == 3 && x.charAt(0) == 'F' && x.charAt(1) == 'I' && x.charAt(2) == 'M') {
                fim = true;
            } else {
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(x)) {
                        pilha.inserir(personagem);
                    }
                }
            }
        }
        x = scanner.nextLine();
        int quant = Integer.parseInt(x);
        for (int i = 0; i < quant; i++) {
            x = scanner.nextLine();
            if (x.startsWith("I")) {
                String[] partes = x.split(" ");
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(partes[1])) {
                        pilha.inserir(personagem);
                    }
                }
            } else {
                try {
                    System.out.println("(R) " + pilha.remover());
                } catch (Exception erro) {
                    System.out.println(erro.getMessage());
                }
            }
        }
        pilha.mostrar();
    }

    // Questao 9
    private static void Questao9() {

        Matriz m = new Matriz();
        Matriz m2 = new Matriz();
        int rep = scanner.nextInt();
        for (int i = 0; i < rep; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            m = new Matriz(x, y);
            m.ler();
            x = scanner.nextInt();
            y = scanner.nextInt();
            m2 = new Matriz(x, y);
            m2.ler();
            m.mostrarDiagonalPrincipal();
            m.mostrarDiagonalSecundaria();
            Matriz m3 = m.soma(m2);
            m3.show();
            m3 = m.multiplicacao(m2);
            m3.show();
        }
    }

    // Questao 11
    public static void Questao11() {
        String x;
        boolean fim = false;
        ListaDupla listaDupla = new ListaDupla();
        ArrayList<Personagem> personagens = readArq();
        while (fim == false) {
            x = scanner.nextLine();
            if (x.equals("FIM")) {
                fim = true;
            } else {
                for (Personagem personagem : personagens) {
                    if (personagem.getId().equals(x)) {
                        listaDupla.inserirFim(personagem);
                        break;
                    }
                }
            }
        }
        listaDupla.quicksort();
        listaDupla.mostrar();
        criaArqLog();

    }

    // ---------------------------------------------Classes_Questoes---------------------------------------------

    // Lista Sequencial - Questao 1
    static class ListaQuestao1 {
        private Personagem[] array;
        private int n;

        public ListaQuestao1() {
            this(99);
        }

        public ListaQuestao1(int tamanho) {
            array = new Personagem[tamanho];
            n = 0;
        }

        public void inserirInicio(Personagem x) throws Exception {

            if (n >= array.length) {
                throw new Exception("Erro ao inserir Inicio!");
            }

            for (int i = n; i > 0; i--) {
                array[i] = array[i - 1];
            }

            array[0] = x;
            n++;
        }

        public void inserirFim(Personagem x) throws Exception {

            if (n >= array.length) {
                throw new Exception("Erro ao inserir Fim!");
            }

            array[n] = x;
            n++;
        }

        public void inserir(Personagem x, int pos) throws Exception {

            if (n >= array.length || pos < 0 || pos > n) {
                throw new Exception("Erro ao inserir!");
            }

            for (int i = n; i > pos; i--) {
                array[i] = array[i - 1];
            }

            array[pos] = x;
            n++;
        }

        public String removerInicio() throws Exception {

            if (n == 0) {
                throw new Exception("Erro ao remover!");
            }

            String resp = array[0].getName();
            n--;

            for (int i = 0; i < n; i++) {
                array[i] = array[i + 1];
            }

            return resp;
        }

        public String removerFim() throws Exception {

            if (n == 0) {
                throw new Exception("Erro ao remover!");
            }

            return array[--n].getName();
        }

        public String remover(int pos) throws Exception {

            if (n == 0 || pos < 0 || pos >= n) {
                throw new Exception("Erro ao remover!");
            }

            String resp = array[pos].getName();
            n--;

            for (int i = pos; i < n; i++) {
                array[i] = array[i + 1];
            }

            return resp;
        }

        public void mostrar() {
            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd-MM-yyyy");

            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    String dataFormatada = formatoSaida.format(array[i].getDateOfBirth());
                    System.out.print("[" + i + " ## " + array[i].getId() + " ## " + array[i].getName() + " ## "
                            + array[i].getAlternateNames()
                            + " ## " +
                            array[i].getHouse() + " ## " + array[i].getAncestry() + " ## " + array[i].getSpecies()
                            + " ## " + array[i].getPatronus() + " ## "
                            + array[i].getHogwartsStaff()
                            + " ## " + array[i].getHogwartsStudent() + " ## " + array[i].getActorName() + " ## "
                            + array[i].getAlive() + " ## "
                            + dataFormatada + " ## " + array[i].getYearOfBirth()
                            + " ## " + array[i].getEyeColor() + " ## " + array[i].getGender() + " ## "
                            + array[i].getHairColour() + " ## " + array[i].getWizard()
                            + "]\n");
                }

            }
        }

        public boolean pesquisar(Personagem x) {
            boolean retorno = false;
            for (int i = 0; i < n && retorno == false; i++) {
                retorno = (array[i] == x);
            }
            return retorno;
        }
    }

    // Pilha Sequencial - Questao 3
    static public class PilhaSequencial {
        private Personagem[] array;
        private int n;

        public PilhaSequencial() {
            array = new Personagem[99];
            n = 0;
        }

        public void inserir(Personagem x) throws Exception {

            if (n >= array.length) {
                throw new Exception("Erro ao inserir Inicio!");
            }

            for (int i = n; i > 0; i--) {
                array[i] = array[i - 1];
            }

            array[0] = x;
            n++;
        }

        public String remover() throws Exception {

            if (n == 0) {
                throw new Exception("Pilha vazia");
            }

            String resp = array[0].getName();
            n--;

            for (int i = 0; i < n; i++) {
                array[i] = array[i + 1];
            }

            return resp;
        }

        public void mostrar() {
            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println("[ Top ]");
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    String dataFormatada = formatoSaida.format(array[i].getDateOfBirth());
                    System.out.print("[" + i + " ## " + array[i].getId() + " ## " + array[i].getName() + " ## "
                            + array[i].getAlternateNames()
                            + " ## " +
                            array[i].getHouse() + " ## " + array[i].getAncestry() + " ## " + array[i].getSpecies()
                            + " ## " + array[i].getPatronus() + " ## "
                            + array[i].getHogwartsStaff()
                            + " ## " + array[i].getHogwartsStudent() + " ## " + array[i].getActorName() + " ## "
                            + array[i].getAlive() + " ## "
                            + dataFormatada + " ## " + array[i].getYearOfBirth()
                            + " ## " + array[i].getEyeColor() + " ## " + array[i].getGender() + " ## "
                            + array[i].getHairColour() + " ## " + array[i].getWizard()
                            + "]\n");
                }

            }
            System.out.println("[ Bottom ]");
        }

    }

    // Lista simples - Questao 5
    static class ListaDinamica {
        private Celula primeiro;
        private Celula ultimo;

        public ListaDinamica() {
            primeiro = new Celula();
            ultimo = primeiro;
        }

        public void inserirInicio(Personagem x) {
            Celula tmp = new Celula(x);
            tmp.prox = primeiro.prox;
            primeiro.prox = tmp;
            if (primeiro == ultimo) {
                ultimo = tmp;
            }
            tmp = null;
        }

        public void inserirFim(Personagem x) {
            ultimo.prox = new Celula(x);
            ultimo = ultimo.prox;
        }

        public String removerInicio() throws Exception {
            if (primeiro == ultimo) {
                throw new Exception("Erro: Lista Vazia");
            }
            Celula tmp = primeiro;
            primeiro = primeiro.prox;
            String resp = primeiro.elemento.getName();
            tmp.prox = null;
            tmp = null;
            return resp;
        }

        public String removerFim() throws Exception {
            if (primeiro == ultimo) {
                throw new Exception("Erro: Lista Vazia");
            }
            Celula i;
            for (i = primeiro; i.prox != ultimo; i = i.prox)
                ;
            String resp = ultimo.elemento.getName();
            ultimo = i;
            i = ultimo.prox = null;
            return resp;
        }

        public void inserir(Personagem x, int pos) throws Exception {

            int tamanho = tamanho();

            if (pos < 0 || pos > tamanho) {
                throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
            } else if (pos == 0) {
                inserirInicio(x);
            } else if (pos == tamanho) {
                inserirFim(x);
            } else {
                Celula i = primeiro;
                for (int j = 0; j < pos; j++, i = i.prox)
                    ;

                Celula tmp = new Celula(x);
                tmp.prox = i.prox;
                i.prox = tmp;
                tmp = i = null;
            }
        }

        public String remover(int pos) throws Exception {
            String resp;
            int tamanho = tamanho();

            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover (vazia)!");

            } else if (pos < 0 || pos >= tamanho) {
                throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
            } else if (pos == 0) {
                resp = removerInicio();
            } else if (pos == tamanho - 1) {
                resp = removerFim();
            } else {
                Celula i = primeiro;
                for (int j = 0; j < pos; j++, i = i.prox)
                    ;
                Celula tmp = i.prox;
                resp = tmp.elemento.getName();
                i.prox = tmp.prox;
                tmp.prox = null;
                i = tmp = null;
            }

            return resp;
        }

        public void mostrar() {
            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd-MM-yyyy");

            int pos = 0;
            for (Celula i = primeiro.prox; i != null; i = i.prox) {
                String dataFormatada = formatoSaida.format(i.elemento.getDateOfBirth());
                System.out.print("[" + pos + " ## " + i.elemento.getId() + " ## " + i.elemento.getName() + " ## "
                        + i.elemento.getAlternateNames()
                        + " ## " +
                        i.elemento.getHouse() + " ## " + i.elemento.getAncestry() + " ## " + i.elemento.getSpecies()
                        + " ## " + i.elemento.getPatronus() + " ## "
                        + i.elemento.getHogwartsStaff()
                        + " ## " + i.elemento.getHogwartsStudent() + " ## " + i.elemento.getActorName() + " ## "
                        + i.elemento.getAlive() + " ## "
                        + dataFormatada + " ## " + i.elemento.getYearOfBirth()
                        + " ## " + i.elemento.getEyeColor() + " ## " + i.elemento.getGender() + " ## "
                        + i.elemento.getHairColour() + " ## " + i.elemento.getWizard()
                        + "]\n");
                pos++;
            }

        }

        public int tamanho() {
            int tamanho = 0;
            for (Celula i = primeiro; i != ultimo; i = i.prox, tamanho++)
                ;
            return tamanho;
        }

    }

    // Pilha Dinamica - Questao 6
    static public class PilhaDinamica {
        private Celula topo;

        public PilhaDinamica() {
            topo = null;
        }

        public void inserir(Personagem x) {
            Celula tmp = new Celula(x);
            tmp.prox = topo;
            topo = tmp;
            tmp = null;
        }

        public String remover() throws Exception {
            if (topo == null) {
                throw new Exception("Erro ao remover!");
            }
            String resp = topo.elemento.getName();
            Celula tmp = topo;
            topo = topo.prox;
            tmp.prox = null;
            tmp = null;
            return resp;
        }

        public void mostrar() {
            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd-MM-yyyy");
            int pos = 0;
            System.out.println("[ Top ]");
            for (Celula i = topo; i != null; i = i.prox) {
                String dataFormatada = formatoSaida.format(i.elemento.getDateOfBirth());
                System.out.print("[" + pos + " ## " + i.elemento.getId() + " ## " + i.elemento.getName() + " ## "
                        + i.elemento.getAlternateNames()
                        + " ## " +
                        i.elemento.getHouse() + " ## " + i.elemento.getAncestry() + " ## " + i.elemento.getSpecies()
                        + " ## " + i.elemento.getPatronus() + " ## "
                        + i.elemento.getHogwartsStaff()
                        + " ## " + i.elemento.getHogwartsStudent() + " ## " + i.elemento.getActorName() + " ## "
                        + i.elemento.getAlive() + " ## "
                        + dataFormatada + " ## " + i.elemento.getYearOfBirth()
                        + " ## " + i.elemento.getEyeColor() + " ## " + i.elemento.getGender() + " ## "
                        + i.elemento.getHairColour() + " ## " + i.elemento.getWizard()
                        + "]\n");
                pos++;
            }
            System.out.println("[ Bottom ]");

        }

    }

    // Celula - Questao 5 e 6
    static class Celula {
        public Personagem elemento;
        public Celula prox;

        public Celula() {
            this(null);
        }

        public Celula(Personagem elemento) {
            this.elemento = elemento;
            this.prox = null;
        }
    }

    // Matriz - Questao 9
    static class Matriz {
        private CelulaMatriz inicio;
        private int linha, coluna;

        public Matriz() {
            this(3, 3);
        }

        public Matriz(int linha, int coluna) {
            this.linha = linha;
            this.coluna = coluna;
            inicio = new CelulaMatriz();

            CelulaMatriz aux = inicio;

            for (int j = 1; j < coluna; j++) {
                aux.dir = new CelulaMatriz(0, null, null, aux, null);
                aux = aux.dir;
            }

            aux = inicio;
            for (int i = 1; i < linha; i++) {
                CelulaMatriz tmp = new CelulaMatriz(0, aux, null, null, null);
                aux.sup = tmp;
                aux = tmp;
                for (int j = 1; j < coluna; j++) {
                    tmp.dir = new CelulaMatriz(0, tmp.inf != null ? tmp.inf.dir : null, null, tmp, null);
                    if (tmp.inf != null) {
                        tmp.inf.dir.sup = tmp.dir;
                    }
                    tmp = tmp.dir;
                }
            }
        }

        public void show() {
            for (CelulaMatriz aux = inicio; aux != null; aux = aux.sup) {
                for (CelulaMatriz tmp = aux; tmp != null; tmp = tmp.dir) {
                    System.out.print(tmp.elemento + " ");
                }
                System.out.println();
            }
        }

        public Matriz soma(Matriz m2) {
            if (this.linha != m2.linha || this.coluna != m2.coluna) {
                System.out.println("Matrizes incompativeis");
                return null;
            }

            Matriz resp = new Matriz(this.linha, this.coluna);
            CelulaMatriz thisInicio = this.inicio;
            CelulaMatriz m2Inicio = m2.inicio;

            for (CelulaMatriz aux = resp.inicio; aux != null; aux = aux.sup, thisInicio = thisInicio.sup, m2Inicio = m2Inicio.sup) {
                CelulaMatriz thisAux = thisInicio;
                CelulaMatriz m2Aux = m2Inicio;
                for (CelulaMatriz tmp = aux; tmp != null; tmp = tmp.dir, thisAux = thisAux.dir, m2Aux = m2Aux.dir) {
                    tmp.elemento = m2Aux.elemento + thisAux.elemento;
                }
            }
            return resp;
        }

        public Object getElement(int x, int y) {
            if (x >= this.linha || y >= this.coluna) {
                System.out.println("Tamanho incompativel");
                return null;
            }

            CelulaMatriz resp = inicio;
            for (int i = 0; i < x; i++) {
                resp = resp.sup;
            }
            for (int j = 0; j < y; j++) {
                resp = resp.dir;
            }

            return resp.elemento;
        }

        public CelulaMatriz getCell(int x, int y) {
            if (x >= this.linha || y >= this.coluna) {
                System.out.println("Tamanho incompativel");
                return null;
            }

            CelulaMatriz resp = inicio;
            for (int i = 0; i < x; i++) {
                resp = resp.sup;
            }
            for (int j = 0; j < y; j++) {
                resp = resp.dir;
            }

            return resp;
        }

        public Matriz multiplicacao(Matriz m2) {
            if (this.coluna != m2.linha) {
                System.out.println("Matrizes incompativeis");
                return null;
            }

            Matriz resp = new Matriz(this.linha, m2.coluna);
            for (int i = 0; i < this.linha; i++) {
                for (int j = 0; j < m2.coluna; j++) {
                    for (int k = 0; k < this.coluna; k++) {
                        CelulaMatriz celResp = resp.getCell(i, j);
                        celResp.elemento += (int) this.getElement(i, k) * (int) m2.getElement(k, j);
                    }
                }
            }

            return resp;
        }

        public boolean isQuadrada() {
            return this.linha == this.coluna;
        }

        public void mostrarDiagonalPrincipal() {
            if (isQuadrada()) {
                for (int i = 0; i < this.linha; i++) {
                    System.out.print(this.getElement(i, i) + " ");
                }
                System.out.println();
            }
        }

        public void mostrarDiagonalSecundaria() {
            if (isQuadrada()) {
                for (int j = 0; j < this.coluna; j++) {
                    System.out.print(this.getElement(j, this.coluna - 1 - j) + " ");
                }
                System.out.println();
            }
        }

        public void ler() {
            for (CelulaMatriz aux = inicio; aux != null; aux = aux.sup) {
                for (CelulaMatriz tmp = aux; tmp != null; tmp = tmp.dir) {
                    tmp.elemento = scanner.nextInt();
                }
            }
        }
    }

    // Celula da Matriz - Questao 9
    static class CelulaMatriz {
        public int elemento;
        public CelulaMatriz inf, sup, esq, dir;

        public CelulaMatriz() {
            this(0, null, null, null, null);
        }

        public CelulaMatriz(int elemento) {
            this(elemento, null, null, null, null);
        }

        public CelulaMatriz(int elemento, CelulaMatriz inf, CelulaMatriz sup, CelulaMatriz esq, CelulaMatriz dir) {
            this.elemento = elemento;
            this.inf = inf;
            this.sup = sup;
            this.esq = esq;
            this.dir = dir;
        }
    }

    // Lista Dupla - Questao 11
    static class ListaDupla {
        private CelulaDupla primeiro;
        private CelulaDupla ultimo;

        public ListaDupla() {
            primeiro = new CelulaDupla();
            ultimo = primeiro;
        }

        public void inserirFim(Personagem x) {
            ultimo.prox = new CelulaDupla(x);
            ultimo.prox.ant = ultimo;
            ultimo = ultimo.prox;
        }

        public void mostrar() {
            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd-MM-yyyy");
            for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
                String dataFormatada = formatoSaida.format(i.elemento.getDateOfBirth());
                System.out.print("[" + i.elemento.getId() + " ## " + i.elemento.getName() + " ## "
                        + i.elemento.getAlternateNames()
                        + " ## " +
                        i.elemento.getHouse() + " ## " + i.elemento.getAncestry() + " ## " + i.elemento.getSpecies()
                        + " ## " + i.elemento.getPatronus() + " ## "
                        + i.elemento.getHogwartsStaff()
                        + " ## " + i.elemento.getHogwartsStudent() + " ## " + i.elemento.getActorName() + " ## "
                        + i.elemento.getAlive() + " ## "
                        + dataFormatada + " ## " + i.elemento.getYearOfBirth()
                        + " ## " + i.elemento.getEyeColor() + " ## " + i.elemento.getGender() + " ## "
                        + i.elemento.getHairColour() + " ## " + i.elemento.getWizard()
                        + "]\n");
            }
        }

        public CelulaDupla getUltimoNo() {
            CelulaDupla temp = primeiro;
            while (temp.prox != null) {
                temp = temp.prox;
            }
            return temp;
        }

        private CelulaDupla part(CelulaDupla esq, CelulaDupla dir) {
            Personagem pivo = dir.elemento;
            CelulaDupla i = esq.ant;

            for (CelulaDupla j = esq; j != dir; j = j.prox) {
                comp++;
                if (j.elemento.getHouse().compareToIgnoreCase(pivo.getHouse()) < 0) {
                    i = (i == null) ? esq : i.prox;
                    Personagem temp = i.elemento;
                    i.elemento = j.elemento;
                    j.elemento = temp;
                } else {
                    comp += 2;
                    if (j.elemento.getHouse().compareToIgnoreCase(pivo.getHouse()) == 0 && j.elemento.getName().compareToIgnoreCase(pivo.getName()) < 0) {
                        i = (i == null) ? esq : i.prox;
                        Personagem temp = i.elemento;
                        i.elemento = j.elemento;
                        j.elemento = temp;
                    }
                }
            }
            i = (i == null) ? esq : i.prox;
            Personagem temp = i.elemento;
            i.elemento = dir.elemento;
            dir.elemento = temp;

            return i;
        }

        private void quicksortRec(CelulaDupla esq, CelulaDupla dir) {
            if (dir != null && esq != dir && esq != dir.prox) {
                CelulaDupla i = part(esq, dir);
                quicksortRec(esq, i.ant);
                quicksortRec(i.prox, dir);
            }
        }

        public void quicksort() {
            CelulaDupla i = getUltimoNo();
            quicksortRec(primeiro.prox, i);
        }

    }

    // Celula Dupla - Questao 11
    static class CelulaDupla {
        public Personagem elemento;
        public CelulaDupla ant;
        public CelulaDupla prox;

        public CelulaDupla() {
            this(null);
        }

        public CelulaDupla(Personagem elemento) {
            this.elemento = elemento;
            this.ant = this.prox = null;
        }
    }

    // ---------------------------------------------CriaArqLog---------------------------------------------
    static void criaArqLog() {
        Arq.openWrite("matricula_quicksort2.txt");
        Arq.print("694520\t" + (System.currentTimeMillis() - inicio / 1000) + "\t" + comp);
        Arq.close();
    }

    // ---------------------------------------------readArq---------------------------------------------
    public static ArrayList<Personagem> readArq() {
        String x;
        ArrayList<Personagem> personagens = new ArrayList<>();
        Arq.openRead("/tmp/characters.csv");
        x = Arq.readLine();
        while (Arq.hasNext()) {
            x = Arq.readLine();
            Personagem personagem = new Personagem();
            x = x.replaceAll(";;", "; ;").replaceAll("\\[", "{").replaceAll("]", "}");
            personagem.ler(x);
            personagens.add(personagem);
        }
        Arq.close();
        return personagens;
    }

    // ---------------------------------------------Classe_Personagem---------------------------------------------
    static class Personagem {
        private String id;
        private String name;
        private List<String> alternate_names = new ArrayList<String>();
        private String house;
        private String ancestry;
        private String species;
        private String patronus;
        private boolean hogwartsStaff;
        private String hogwartsStudent;
        private String actorName;
        private boolean alive;
        private Date dateOfBirth;
        private int yearOfBirth;
        private String eyeColor;
        private String gender;
        private String hairColour;
        private boolean wizard;

        public Personagem() {
            id = "";
            name = "";
            alternate_names = new ArrayList<String>();
            house = "";
            ancestry = "";
            species = "";
            patronus = "";
            hogwartsStaff = false;
            hogwartsStudent = "";
            actorName = "";
            alive = false;
            dateOfBirth = new Date();
            yearOfBirth = 0;
            eyeColor = "";
            gender = "";
            hairColour = "";
            wizard = false;
        }

        public Personagem(String id, String name, ArrayList<String> alternate_names, String house, String ancestry,
                String species,
                String patronus, boolean hogwartsStaff,
                String hogwartsStudent, String actorName, boolean alive, Date dateOfBirth, int yearOfBirth,
                String eyeColor, String gender, String hairColour, boolean wizard) {
            this.id = id;
            this.name = name;
            this.alternate_names = new ArrayList<String>();
            for (String temp : alternate_names) {
                this.alternate_names.add(temp);
            }
            this.house = house;
            this.ancestry = ancestry;
            this.species = species;
            this.patronus = patronus;
            this.hogwartsStaff = hogwartsStaff;
            this.hogwartsStudent = hogwartsStudent;
            this.actorName = actorName;
            this.alive = alive;
            this.dateOfBirth = new Date();
            this.yearOfBirth = yearOfBirth;
            this.eyeColor = eyeColor;
            this.gender = gender;
            this.hairColour = hairColour;
            this.wizard = wizard;
        }

        // gets
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAlternateNames() {
            StringBuilder sb = new StringBuilder();
            for (String name : alternate_names) {
                sb.append(name).append(", ");
            }
            // Remover a última vírgula e espaço, se houver
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 2);
            }
            return sb.toString();
        }

        public String getHouse() {
            return house;
        }

        public String getAncestry() {
            return ancestry;
        }

        public String getSpecies() {
            return species;
        }

        public String getPatronus() {
            return patronus;
        }

        public boolean getHogwartsStaff() {
            return hogwartsStaff;
        }

        public String getHogwartsStudent() {
            return hogwartsStudent;
        }

        public String getActorName() {
            return actorName;
        }

        public boolean getAlive() {
            return alive;
        }

        public Date getDateOfBirth() {
            return dateOfBirth;
        }

        public int getYearOfBirth() {
            return yearOfBirth;
        }

        public String getEyeColor() {
            return eyeColor;
        }

        public String getGender() {
            return gender;
        }

        public String getHairColour() {
            return hairColour;
        }

        public boolean getWizard() {
            return wizard;
        }

        // sets
        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            if (name.equals(" ")) {
                name = "";
            }
            this.name = name;
        }

        public void setAlternateNames(String nomesAlterados) {
            if (nomesAlterados.charAt(0) == ' ') {
                StringBuilder nomesAlteradosNovo = new StringBuilder();
                for (int i = 1; i < nomesAlterados.length(); i++) {
                    nomesAlteradosNovo.append(nomesAlterados.charAt(i));
                }
                String x = nomesAlteradosNovo.toString();
                this.alternate_names.add(x);
            } else {
                this.alternate_names.add(nomesAlterados);
            }

        }

        public void setHouse(String house) {
            if (house.equals(" ")) {
                house = "";
            }
            this.house = house;
        }

        public void setAncestry(String ancestry) {
            if (ancestry.equals(" ")) {
                ancestry = "";
            }
            this.ancestry = ancestry;
        }

        public void setSpecies(String species) {
            if (species.equals(" ")) {
                species = "";
            }
            this.species = species;
        }

        public void setPatronus(String patronus) {
            if (patronus.equals(" ")) {
                patronus = "";
            }
            this.patronus = patronus;
        }

        public void setHogwartsStaff(boolean hogwartsStaff) {
            this.hogwartsStaff = hogwartsStaff;
        }

        public void setHogwartsStudent(String hogwartsStudent) {
            if (hogwartsStudent.equals(" ")) {
                hogwartsStudent = "";
            }
            this.hogwartsStudent = hogwartsStudent;
        }

        public void setActorName(String actorName) {
            if (actorName.equals(" ")) {
                actorName = "";
            }
            this.actorName = actorName;
        }

        public void setAlive(boolean alive) {
            this.alive = alive;
        }

        public void setDateOfBirth(String dateOfBirth) {
            if (dateOfBirth.equals(" ")) {
                dateOfBirth = "";
            }
            String[] partes = dateOfBirth.split("-");
            StringBuilder temp = new StringBuilder();

            if (partes[0].length() < 2) {
                temp.append("0");
                temp.append(partes[0] + "-");
            } else {
                temp.append(partes[0] + "-");
            }
            if (partes[1].length() < 2) {
                temp.append("0");
                temp.append(partes[1] + "-");
            } else {
                temp.append(partes[1] + "-");
            }
            temp.append(partes[2]);
            String tempString = temp.toString();
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            try {
                this.dateOfBirth = formato.parse(tempString);
            } catch (ParseException e) {

            }
        }

        public void setYearOfBirth(String yearOfBirth) {
            int anoNascimento = 0;
            try {
                anoNascimento = Integer.parseInt(yearOfBirth);
                this.yearOfBirth = anoNascimento;
            } catch (Exception e) {
                this.yearOfBirth = 0;
            }

        }

        public void setEyeColor(String eyeColor) {
            if (eyeColor.equals(" ")) {
                eyeColor = "";
            }
            this.eyeColor = eyeColor;
        }

        public void setGender(String gender) {
            if (gender.equals(" ")) {
                gender = "";
            }
            this.gender = gender;
        }

        public void setHairColour(String hairColour) {
            if (hairColour.equals(" ")) {
                hairColour = "";
            }
            this.hairColour = hairColour;
        }

        public void setWizard(boolean wizard) {
            this.wizard = wizard;
        }

        // clone
        public Personagem clone() {
            Personagem clone = new Personagem();
            clone.id = this.id;
            clone.name = this.name;
            clone.alternate_names = this.alternate_names;
            clone.house = this.house;
            clone.ancestry = this.ancestry;
            clone.species = this.species;
            clone.patronus = this.patronus;
            clone.hogwartsStaff = this.hogwartsStaff;
            clone.hogwartsStudent = this.hogwartsStudent;
            clone.actorName = this.actorName;
            clone.alive = this.alive;
            clone.dateOfBirth = this.dateOfBirth;
            clone.yearOfBirth = this.yearOfBirth;
            clone.eyeColor = this.eyeColor;
            clone.gender = this.gender;
            clone.hairColour = this.hairColour;
            clone.wizard = this.wizard;
            return clone;
        }

        public void ler(String x) {
            // 0 = id 1 = name 2 = alternate_names 3 = house
            // 4 = ancestry 5 = species 6 = patronus 7 = hogwartsStaff
            // 8 = hogwartsStudent 9 = actorName 10 = alive 12 = dateOfBirth
            // 13 = yearOfBirth 14 = eyeColor 15 = gender 16 = hairColour
            // 17 = wizard

            String[] partes = x.split(";");

            setId(partes[0]);
            setName(partes[1]);
            if (partes[2].length() >= 1) {
                partes[2] = partes[2].replaceAll("'", "");
                String[] nomesAlternados = partes[2].split(",");
                for (int i = 0; i < nomesAlternados.length; i++) {
                    setAlternateNames(nomesAlternados[i]);
                }
            } else {
                setAlternateNames("");
            }
            setHouse(partes[3]);
            setAncestry(partes[4]);
            setSpecies(partes[5]);
            setPatronus(partes[6]);

            if (partes[7].equals("FALSO")) {
                setHogwartsStaff(false);
            } else if (partes[7].equals("VERDADEIRO")) {
                setHogwartsStaff(true);
            }

            if (partes[8].equals("FALSO")) {
                setHogwartsStudent("false");
            } else if (partes[8].equals("VERDADEIRO")) {
                setHogwartsStudent("true");
            }

            setActorName(partes[9]);

            if (partes[10].equals("FALSO")) {
                setAlive(false);
            } else if (partes[10].equals("VERDADEIRO")) {
                setAlive(true);
            }

            setDateOfBirth(partes[12]);

            setYearOfBirth(partes[13]);

            setEyeColor(partes[14]);
            setGender(partes[15]);
            setHairColour(partes[16]);

            if (partes[17].equals("FALSO")) {
                setWizard(false);
            } else if (partes[17].equals("VERDADEIRO")) {
                setWizard(true);
            }
        }

        public void imprimir(int pos) {
            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd-MM-yyyy");
            String dataFormatada = formatoSaida.format(this.dateOfBirth);
            System.out.print("[" + pos + " ## " + this.id + " ## " + this.name + " ## " + this.getAlternateNames()
                    + " ## " +
                    this.house + " ## " + this.ancestry + " ## " + this.species + " ## " + this.patronus + " ## "
                    + this.hogwartsStaff
                    + " ## " + this.hogwartsStudent + " ## " + this.actorName + " ## " + this.alive + " ## "
                    + dataFormatada + " ## " + this.yearOfBirth
                    + " ## " + this.eyeColor + " ## " + this.gender + " ## " + this.hairColour + " ## " + this.wizard
                    + "]\n");
        }

    }
}