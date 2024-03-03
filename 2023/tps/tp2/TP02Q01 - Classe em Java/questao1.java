import java.util.ArrayList;
import java.util.List;

class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {
        id = 0;
        nome = "";
        altura = 0;
        peso = 0;
        universidade = "";
        anoNascimento = 0;
        cidadeNascimento = "";
        estadoNascimento = "";
    }

    public Jogador(int cod, String nom, int altur, int pes, String universidad,
            int anoNasciment, String cidadeNasciment, String estadoNasciment) {
        id = cod;
        nome = nom;
        altura = altur;
        peso = pes;
        universidade = universidad;
        anoNascimento = anoNasciment;
        cidadeNascimento = cidadeNasciment;
        estadoNascimento = estadoNasciment;
    }

    // gets
    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getUniversidade() {
        return this.universidade;
    }

    public String getCidadeNascimento() {
        return this.cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return this.estadoNascimento;
    }

    public int getAltura() {
        return this.altura;
    }

    public int getPeso() {
        return this.peso;
    }

    public int getAnoNascimento() {
        return this.anoNascimento;
    }

    // sets
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    // clone
    public Jogador clone() {
        return new Jogador(
                this.id,
                this.nome,
                this.altura,
                this.peso,
                this.universidade,
                this.anoNascimento,
                this.cidadeNascimento,
                this.estadoNascimento);
    }

    // ler
    public void ler(String x) {
        x = x.replaceAll(",,", ",nao informado,");
        int tam = x.length() - 1;
        if (x.charAt(tam) == ',') {
            x += "nao informado";
        }
        String[] partes = x.split(",");
        setId(Integer.parseInt(partes[0]));
        setNome(partes[1]);
        setAltura(Integer.parseInt(partes[2]));
        setPeso(Integer.parseInt(partes[3]));
        setUniversidade(partes[4]);
        setAnoNascimento(Integer.parseInt(partes[5]));
        setCidadeNascimento(partes[6]);
        setEstadoNascimento(partes[7]);

    }

    // imprimir
    public void imprimir() {
        System.out.println("[" + this.getId() + " ## " + this.getNome() + " ## " + this.getAltura() + " ## "
                + this.getPeso() + " ## " + this.getAnoNascimento() + " ## " + this.getUniversidade() + " ## "
                + this.getCidadeNascimento() + " ## " + this.getEstadoNascimento() + "]");
    }

}

class questao1 {

    public static void main(String[] args) {
        String x;
        List<Jogador> jogadores = new ArrayList<>();

        Arq.openRead("/tmp/players.csv");
        x = Arq.readLine();
        while (Arq.hasNext()) {
            x = Arq.readLine();
            Jogador jogador = new Jogador();
            jogador.ler(x);
            jogadores.add(jogador);
        }
        Arq.close();
        while (true) {
            x = MyIO.readLine();
            if (x.equals("FIM")) {
                break;
            } else {
                int id = Integer.parseInt(x);
                for (Jogador jogador : jogadores) {
                    if (jogador.getId() == id) {
                        jogador.imprimir();
                        break;
                    }
                }

            }
        }

    }
}
