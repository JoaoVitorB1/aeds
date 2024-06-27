import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Main {
    static double inicio = System.currentTimeMillis();
    static int comp = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Questao1();
        scanner.close();
    }

    // ---------------------------------------------Questoes---------------------------------------------
    // Questao 1
    static void Questao1() { 
        String x;       
        ArrayList<Personagem> personagens = readArq();        
        
        boolean fim = false;
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        while (fim == false) {
            x = scanner.nextLine();
            if (x.equals("FIM")) {
                fim = true;
            } else {
                for (Personagem personagem : personagens) {
                    if (personagem.getId().compareTo(x) == 0) {
                        try {
                            arvoreBinaria.inserir(personagem);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

                }
            }
        }
        fim = false;
        while (fim == false) {
            x = scanner.nextLine();
            if (x.equals("FIM")) {
                fim = true;
            } else {
                System.out.print(x + " => raiz ");  
                System.out.println( arvoreBinaria.pesquisar(x) ? "SIM" : "NAO");                
            }
        }
        criaArqLog();
    }

    static class No {
        public Personagem elemento; // Conteudo do no.
        public No esq, dir; // Filhos da esq e dir.

        public No(Personagem elemento) {
            this(elemento, null, null);
        }

        public No(Personagem elemento, No esq, No dir) {
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
        }
    }

    static public class ArvoreBinaria {
        private No raiz; // Raiz da arvore.

        public ArvoreBinaria() {
            raiz = null;
        }

        public boolean pesquisar(String x) {
            return pesquisar(x, raiz);
        }

        private boolean pesquisar(String x, No i) {
            boolean resp;
            comp++;
            if (i == null) {
                resp = false;
            } else {
                comp++;
                if (x.equals(i.elemento.getName())) {
                    resp = true;
                } else {
                    comp++;
                    if (x.compareTo(i.elemento.getName()) < 0) {
                        System.out.print("esq ");                        
                        resp = pesquisar(x, i.esq);
                    } else {
                        System.out.print("dir ");  
                        resp = pesquisar(x, i.dir);
                    }
                }
            }
            return resp;
        }

        public void inserir(Personagem x) throws Exception {
            raiz = inserir(x, raiz);
        }

        private No inserir(Personagem x, No i) throws Exception {
            comp++;
            if (i == null) {
                i = new No(x);
            } else {
                comp++;
                if (x.getName().compareTo(i.elemento.getName()) < 0) {
                    i.esq = inserir(x, i.esq);
                } else {
                    comp++;
                    if (x.getName().compareTo(i.elemento.getName()) > 0) {
                        i.dir = inserir(x, i.dir);
                    } else {
                        throw new Exception("Erro ao inserir!");
                    }
                }
            }

            return i;
        }

        public void inserirPai(Personagem x) throws Exception {
            if (raiz == null) {
                raiz = new No(x);
            } else if (x.getName().compareTo(raiz.elemento.getName()) < 0) {
                inserirPai(x, raiz.esq, raiz);
            } else if (x.getName().compareTo(raiz.elemento.getName()) > 0) {
                inserirPai(x, raiz.dir, raiz);
            } else {
                throw new Exception("Erro ao inserirPai!");
            }
        }

        private void inserirPai(Personagem x, No i, No pai) throws Exception {
            if (i == null) {
                if (x.getName().compareTo(pai.elemento.getName()) < 0) {
                    pai.esq = new No(x);
                } else {
                    pai.dir = new No(x);
                }
            } else if (x.getName().compareTo(i.elemento.getName()) < 0) {
                inserirPai(x, i.esq, i);
            } else if (x.getName().compareTo(i.elemento.getName()) > 0) {
                inserirPai(x, i.dir, i);
            } else {
                throw new Exception("Erro ao inserirPai!");
            }
        }
    }

    // ---------------------------------------------CriaArqLog---------------------------------------------
    static void criaArqLog() {


        Arq.openWrite("matricula_arvoreBinaria.txt");
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