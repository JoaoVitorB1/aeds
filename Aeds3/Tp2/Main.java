import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static BTree bTree;
    static Directory directory;
    public static void main(String[] args) throws IOException {
        String inputCSV = "characters.csv";
        String outputBinary = "characters.bin";
        String hashExtensivel = "hash.bin";
        String indexHash = "";
        int bucketCapacity = 8;
        Directory directory = new Directory(bucketCapacity);
        int ordem = 8;
        BTree bTree = new BTree(ordem); // Inicializa a árvore B
        InvertedList invertedList = new InvertedList("casaIndex.txt", "anoNascimentoIndex.txt"); // Inicializa lista
                                                                                                 // invertida
        int idbin = 0;
        long posicaoAtual = 0; // Posição atual no arquivo binário

        try (BufferedReader br = new BufferedReader(new FileReader(inputCSV));
                FileOutputStream fos = new FileOutputStream(outputBinary);
                DataOutputStream dos = new DataOutputStream(fos);
                RandomAccessFile indexFile = new RandomAccessFile(hashExtensivel, "rw")) {

            // int bucketCapacity = 8;
            // Directory directory = new Directory(bucketCapacity);
            // Pula a primeira linha (cabeçalho)
            br.readLine();

            String line;
            int lastID = 0;
            dos.writeInt(lastID);
            posicaoAtual += Integer.BYTES; // Incrementa pela escrita de lastID (4 bytes)

            while ((line = br.readLine()) != null) {
                byte[] lineBytes;
                Personagem personagem = new Personagem();
                personagem.ler(line);

                long posicaoID = posicaoAtual; // Armazena a posição do ID atual

                dos.writeBoolean(false); // lápide -> false (1 byte)
                posicaoAtual += 1;
                int tamanho = getTamanhoPersonagem(personagem); // Escreve o tamanho do Personagem
                dos.writeInt(tamanho); // (4 bytes)
                posicaoAtual += Integer.BYTES;

                // Insere o ID e a posição na árvore B
                bTree.insert(idbin, posicaoID);

                dos.writeInt(idbin++); // Escreve o ID binário (4 bytes)
                posicaoAtual += Integer.BYTES;

                // Grava cada campo e atualiza a posição com o número de bytes escritos
                // ID
                dos.writeUTF(personagem.getId());
                posicaoAtual += personagem.getId().getBytes().length + 2; // `writeUTF` adiciona 2 bytes extras para o
                                                                          // comprimento

                // Name
                dos.writeUTF(personagem.getName());
                posicaoAtual += personagem.getName().getBytes().length + 2;

                // AlternateNames
                dos.writeUTF(personagem.getAlternateNames());
                posicaoAtual += personagem.getAlternateNames().getBytes().length + 2;

                // House
                String casa = personagem.getHouse();
                dos.writeUTF(casa);
                posicaoAtual += personagem.getHouse().getBytes().length + 2;

                // Ancestry
                dos.writeUTF(personagem.getAncestry());
                posicaoAtual += personagem.getAncestry().getBytes().length + 2;

                // Species
                dos.writeUTF(personagem.getSpecies());
                posicaoAtual += personagem.getSpecies().getBytes().length + 2;

                // Patronus
                dos.writeUTF(personagem.getPatronus());
                posicaoAtual += personagem.getPatronus().getBytes().length + 2;

                // HogwartsStaff
                dos.writeUTF(personagem.getHogwartsStaff());
                posicaoAtual += personagem.getHogwartsStaff().getBytes().length + 2;

                // HogwartsStudent
                dos.writeUTF(personagem.getHogwartsStudent());
                posicaoAtual += personagem.getHogwartsStudent().getBytes().length + 2;

                // ActorName
                dos.writeUTF(personagem.getActorName());
                posicaoAtual += personagem.getActorName().getBytes().length + 2;

                // Alive
                dos.writeUTF(personagem.getAlive());
                posicaoAtual += personagem.getAlive().getBytes().length + 2;

                // DateOfBirth
                long timeInMillis = personagem.getDateOfBirth().getTime();
                dos.writeLong(timeInMillis); // Escreve 8 bytes para a data
                posicaoAtual += Long.BYTES;

                // EyeColor
                dos.writeUTF(personagem.getEyeColor());
                posicaoAtual += personagem.getEyeColor().getBytes().length + 2;

                // Gender
                dos.writeUTF(personagem.getGender());
                posicaoAtual += personagem.getGender().getBytes().length + 2;

                // HairColour
                dos.writeUTF(personagem.getHairColour());
                posicaoAtual += personagem.getHairColour().getBytes().length + 2;

                // Wizard
                dos.writeUTF(personagem.getWizard());
                posicaoAtual += personagem.getWizard().getBytes().length + 2;

                // YearOfBirth
                int anoNasc = personagem.getYearOfBirth();
                dos.writeInt(anoNasc); // 4 bytes para o ano de nascimento
                posicaoAtual += Integer.BYTES;
                invertedList.insert(casa, anoNasc, posicaoAtual);
                directory.insert(lastID, posicaoID, indexFile);
                lastID++;
            }
            System.out.println("Arquivo binário criado com sucesso: " + outputBinary);

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao processar os arquivos.");
            e.printStackTrace();
        }
        try {
            // Abre o arquivo em modo leitura e escrita
            RandomAccessFile arquivo = new RandomAccessFile("characters.bin", "rwd");

            // Define a posição do cabeçalho (por exemplo, início do arquivo)
            long posicaoCabecalho = 0; // A posição onde o int deve ser escrito

            // Move o ponteiro de leitura/escrita para a posição do cabeçalho
            arquivo.seek(posicaoCabecalho);

            // Escreve o int no cabeçalho
            idbin--;
            arquivo.writeInt(idbin);

            // Fecha o arquivo
            arquivo.close();

            System.out.println("Cabeçalho atualizado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // COMANDOS CRUD
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "COMANDOS:\n1- Create\n2- Read\n3- Update\n4- Delete\n5- Ordenar\n6- Load\n7- Save\nFIM para terminar o programa.\n");
        String input = scanner.nextLine();

        while (!input.equals("FIM")) {
            String idString, nome, alternate_names = "[", house, ancestry, species, patronus, hogwartsStaff,
                    hogwartsStudent, actorName, alive, dateOfBirth, yearOfBirth, eyeColor, gender, hairColour,
                    wizard;
            switch (input) {
                case "Create":
                    Personagem personagemNovo = new Personagem();

                    // Recebendo informações do novo personagem

                    System.out.println("Digite o id: "); // idString
                    idString = scanner.nextLine();
                    personagemNovo.setId(idString);

                    System.out.println("Digite o nome: "); // Nome
                    nome = scanner.nextLine();
                    personagemNovo.setName(nome);

                    System.out.println("Digite o alternate_names(Ex: Nome1, Nome2): "); // alternate_Names
                    alternate_names += scanner.nextLine();
                    alternate_names += "]";
                    if (alternate_names.length() >= 1) {
                        alternate_names = alternate_names.replaceAll("'", "");
                        String[] nomesAlternados = alternate_names.split(",");
                        for (int i = 0; i < nomesAlternados.length; i++) {
                            personagemNovo.setAlternateNames(nomesAlternados[i]);
                        }
                    } else {
                        personagemNovo.setAlternateNames("");
                    }

                    System.out.println("Digite o house: "); // House
                    house = scanner.nextLine();
                    personagemNovo.setHouse(house);

                    System.out.println("Digite o ancestry: "); // Ancestry
                    ancestry = scanner.nextLine();
                    personagemNovo.setAncestry(ancestry);

                    System.out.println("Digite o species: "); // Species
                    species = scanner.nextLine();
                    personagemNovo.setSpecies(species);

                    System.out.println("Digite o patronus: "); // Patronus
                    patronus = scanner.nextLine();
                    personagemNovo.setPatronus(patronus);

                    System.out.println("Digite o hogwartsStaff: "); // hogwartsStaff
                    hogwartsStaff = scanner.nextLine();
                    personagemNovo.setHogwartsStaff(hogwartsStaff);

                    System.out.println("Digite o hogwartsStudent: "); // hogwartsStudent
                    hogwartsStudent = scanner.nextLine();
                    personagemNovo.setHogwartsStudent(hogwartsStudent);

                    System.out.println("Digite o actorName: "); // actorName
                    actorName = scanner.nextLine();
                    personagemNovo.setActorName(actorName);

                    System.out.println("Digite o alive: "); // Alive
                    alive = scanner.nextLine();
                    personagemNovo.setAlive(alive);

                    System.out.println("Digite o dateOfBirth(formato: dd-MM-yyyy): "); // dateOfBirth
                    dateOfBirth = scanner.nextLine();
                    personagemNovo.setDateOfBirth(dateOfBirth);

                    System.out.println("Digite o yearOfBirth: "); // yearOfBirth
                    yearOfBirth = scanner.nextLine();
                    personagemNovo.setYearOfBirth(yearOfBirth);

                    System.out.println("Digite o eyeColor: "); // eyeColor
                    eyeColor = scanner.nextLine();
                    personagemNovo.setEyeColor(eyeColor);

                    System.out.println("Digite o gender: "); // gender
                    gender = scanner.nextLine();
                    personagemNovo.setGender(gender);

                    System.out.println("Digite o hairColour: "); // hairColour
                    hairColour = scanner.nextLine();
                    personagemNovo.setHairColour(hairColour);

                    System.out.println("Digite o wizard: "); // wizard
                    wizard = scanner.nextLine();
                    personagemNovo.setWizard(wizard);

                    Create(outputBinary, "new_characters.csv", ++idbin, personagemNovo, directory, hashExtensivel);
                    try { // Atualiza cabeçalho abre o arquivo em modo leitura e escrita
                        RandomAccessFile arquivo = new RandomAccessFile("characters.bin", "rwd");
                        arquivo.seek(0); // Posiciona o ponteiro no cabeçalho
                        arquivo.writeInt(idbin);// Escreve o int no cabeçalho
                        // Fecha o arquivo
                        arquivo.close();
                        System.out.println("Cabeçalho atualizado com sucesso!");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case "Read":
                    System.out.println("Insira o id do registro a ser lido:");
                    int id = scanner.nextInt(); // idSequencial do personagem a ser lido
                    Read("characters.bin", id, hashExtensivel, directory);
                    break;
                case "Update":
                    personagemNovo = new Personagem();
                    System.out.println("Insira o id do registro a ser atualizado:");
                    int idAtulizar;
                    idAtulizar = scanner.nextInt();
                    scanner.nextLine();
                    // Recebendo informações do personagem que vai ser atualizado

                    System.out.println("Digite o id: "); // idString
                    idString = scanner.nextLine();
                    personagemNovo.setId(idString);

                    System.out.println("Digite o nome: "); // Nome
                    nome = scanner.nextLine();
                    personagemNovo.setName(nome);

                    System.out.println("Digite o alternate_names(Ex: Nome1, Nome2): "); // alternate_Names
                    alternate_names += scanner.nextLine();
                    alternate_names += "]";
                    if (alternate_names.length() >= 1) {
                        alternate_names = alternate_names.replaceAll("'", "");
                        String[] nomesAlternados = alternate_names.split(",");
                        for (int i = 0; i < nomesAlternados.length; i++) {
                            personagemNovo.setAlternateNames(nomesAlternados[i]);
                        }
                    } else {
                        personagemNovo.setAlternateNames("");
                    }

                    System.out.println("Digite o house: "); // House
                    house = scanner.nextLine();
                    personagemNovo.setHouse(house);

                    System.out.println("Digite o ancestry: "); // Ancestry
                    ancestry = scanner.nextLine();
                    personagemNovo.setAncestry(ancestry);

                    System.out.println("Digite o species: "); // Species
                    species = scanner.nextLine();
                    personagemNovo.setSpecies(species);

                    System.out.println("Digite o patronus: "); // Patronus
                    patronus = scanner.nextLine();
                    personagemNovo.setPatronus(patronus);

                    System.out.println("Digite o hogwartsStaff: "); // hogwartsStaff
                    hogwartsStaff = scanner.nextLine();
                    personagemNovo.setHogwartsStaff(hogwartsStaff);

                    System.out.println("Digite o hogwartsStudent: "); // hogwartsStudent
                    hogwartsStudent = scanner.nextLine();
                    personagemNovo.setHogwartsStudent(hogwartsStudent);

                    System.out.println("Digite o actorName: "); // actorName
                    actorName = scanner.nextLine();
                    personagemNovo.setActorName(actorName);

                    System.out.println("Digite o alive: "); // Alive
                    alive = scanner.nextLine();
                    personagemNovo.setAlive(alive);

                    System.out.println("Digite o dateOfBirth(formato: dd-MM-yyyy): "); // dateOfBirth
                    dateOfBirth = scanner.nextLine();
                    personagemNovo.setDateOfBirth(dateOfBirth);

                    System.out.println("Digite o yearOfBirth: "); // yearOfBirth
                    yearOfBirth = scanner.nextLine();
                    personagemNovo.setYearOfBirth(yearOfBirth);

                    System.out.println("Digite o eyeColor: "); // eyeColor
                    eyeColor = scanner.nextLine();
                    personagemNovo.setEyeColor(eyeColor);

                    System.out.println("Digite o gender: "); // gender
                    gender = scanner.nextLine();
                    personagemNovo.setGender(gender);

                    System.out.println("Digite o hairColour: "); // hairColour
                    hairColour = scanner.nextLine();
                    personagemNovo.setHairColour(hairColour);

                    System.out.println("Digite o wizard: "); // wizard
                    wizard = scanner.nextLine();
                    personagemNovo.setWizard(wizard);

                    Update("characters.bin", idAtulizar, personagemNovo, directory, hashExtensivel);
                    break;
                case "Delete":
                    System.out.println("Insira o id do registro a ser excluido:");
                    int idExcluir = scanner.nextInt();
                    Delete("characters.bin", idExcluir, directory, hashExtensivel);
                    break;
                case "Ordenar":
                    ordenacaoExterna("characters.bin");
                    System.out.println("Arquivo ordenado ordered_characters.bin criado.");
                    break;
                case "Save":
                    bTree.saveTreeToFile("btree_indices.txt");
                    System.out.println("Salvando árvore no arquivo: \"btree_indices.txt\"");
                    break;

                case "Load":
                    bTree.loadTreeFromFile("btree_indices.txt");
                    System.out.println("Carregando árvore do arquivo: \"btree_indices.txt\"");
                    bTree.traversePreOrder();
                    break;
                case "Read arvore":
                    int xave = scanner.nextInt();
                    bTree.search(xave);
                    break;
                default:
                    break;
            }
            input = scanner.nextLine();
        }

        // escreve os buckets no hash.txt
        try (FileWriter fileWriter = new FileWriter("hash.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            for (int i = 0; i < Math.pow(2, directory.globalDepth); i++) {
                String aux = Integer.toBinaryString(i);
                // ajusta o tamanho da string binaria com zeros a esquerda
                aux = String.format("%" + directory.globalDepth + "s", aux).replace(' ', '0');

                indexHash = "Indice:" + aux + "\n";

                Bucket bucket = directory.table.get(i);
                if (bucket != null) {
                    indexHash += bucket.toString();
                }

                indexHash += "\n";

                bufferedWriter.write(indexHash);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String outputCSV = "new_characters.csv";
        convertBinaryToCSV(outputBinary, outputCSV);
        //ordenacaoExterna("characters.bin");
        scanner.close();
    }// FIM MAIN

    private static void ordenacaoExterna(String inputBinary) {
        String outputBinary = "ordered_characters.bin";
        try (FileInputStream fis = new FileInputStream(inputBinary);
                DataInputStream dis = new DataInputStream(fis);
                FileOutputStream fos = new FileOutputStream(outputBinary);
                DataOutputStream dos = new DataOutputStream(fos)) {

            // Lê o último ID inserido
            int ultimoID = dis.readInt();

            // Escreve o último ID no arquivo binário de saída
            dos.writeInt(ultimoID);

            while (dis.available() > 0) {
                // Verifica se é um registro lápide
                boolean isLapide = dis.readBoolean();
                // Escreve a lápide no arquivo binário de saída

                if (isLapide) {
                    int tamanhoLapide = dis.readInt();
                    dis.skipBytes(tamanhoLapide); // Pula os bytes do registro se for lápide
                } else {
                    // Lê o tamanho do registro
                    dos.writeBoolean(isLapide);
                    int tamanho = dis.readInt();
                    dos.writeInt(tamanho); // Escreve o tamanho no arquivo binário de saída

                    // Lê o ID e escreve no arquivo binário de saída
                    int id = dis.readInt();
                    dos.writeInt(id);

                    // Lê e escreve o ID do personagem
                    String aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve o nome do personagem
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve os nomes alternativos
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve a casa
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve a ascendência
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve a espécie
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve o patrono
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve se é funcionário de Hogwarts
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve se é estudante de Hogwarts
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve o nome do ator
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve se está vivo
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve a data de nascimento
                    long dateOfBirthMillis = dis.readLong();
                    dos.writeLong(dateOfBirthMillis); // Escreve a data em milissegundos

                    // Lê e escreve a cor dos olhos
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve o gênero
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve a cor do cabelo
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve se é bruxo
                    aux = dis.readUTF();
                    dos.writeUTF(aux);

                    // Lê e escreve o ano de nascimento
                    int ano = dis.readInt();
                    dos.writeInt(ano);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertBinaryToCSV(String inputBinary, String outputCSV) {
        try (FileInputStream fis = new FileInputStream(inputBinary);
                DataInputStream dis = new DataInputStream(fis);
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputCSV))) {

            // Escreve cabeçalho do CSV
            bw.write(
                    "idSequencial;idString;nome;alternate_names;house;ancestry;species;patronus;hogwartsStaff;hogwartsStudent;actorName;alive;dateOfBirth;eyeColor;gender;hairColour;wizard;ano\n");

            // Lê o último ID inserido
            int ultimoID = dis.readInt();
            System.out.println("Último id: " + ultimoID);

            while (dis.available() > 0) {
                String aux;

                // Verifica se é um registro lápide
                boolean isLapide = dis.readBoolean();
                if (isLapide) {
                    bw.write('X');
                    bw.newLine();
                    dis.skipBytes(dis.readInt()); // Pula os bytes do registro se for lápide
                } else {
                    // Lê o tamanho do registro
                    int tamanho = dis.readInt();
                    System.out.println("Tamanho do registro: " + tamanho);

                    // Lê o ID
                    int id = dis.readInt();
                    bw.write(id + ";");

                    // Lê e escreve o ID do personagem
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve o nome do personagem
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve os nomes alternativos
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve a casa
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve a ascendência
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve a espécie
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve o patrono
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve se é funcionário de Hogwarts
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve se é estudante de Hogwarts
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve o nome do ator
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve se está vivo
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve a data de nascimento
                    long dateOfBirthMillis = dis.readLong();
                    Date dateOfBirth = new Date(dateOfBirthMillis);
                    bw.write(new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth) + ";");

                    // Lê e escreve a cor dos olhos
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve o gênero
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve a cor do cabelo
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve se é bruxo
                    aux = dis.readUTF();
                    bw.write(aux + ";");

                    // Lê e escreve o ano de nascimento
                    int ano = dis.readInt();
                    bw.write(ano + ";");

                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // CREATE
    private static void Create(String inputBinary, String outputCSV, int id, Personagem personagem, Directory directory,
            String hashExtensivel) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("characters.bin", "rw");

        byte[] lineBytes;
        raf.seek(raf.length()); // posiciona o ponteiro no fim do arquivo

        long endereco = raf.getFilePointer();// armazena posicao

        FileDescriptor fd = raf.getFD();
        FileOutputStream fos = new FileOutputStream(fd);
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));

        dos.writeBoolean(false); // lapide -> false-não é lápide / true-é lápide
        int tamanho = getTamanhoPersonagem(personagem); // Escreve o tamanho do Personagem

        dos.writeInt(tamanho);
        dos.writeInt(id);

        // id
        lineBytes = personagem.getId().getBytes(); // transforma ID em um array de bytes
        dos.writeUTF(personagem.getId()); // Escreve o ID

        // name
        lineBytes = personagem.getName().getBytes(); // transforma Name em um array de bytes
        dos.writeUTF(personagem.getName()); // Escreve o Name

        // AlternateNames
        lineBytes = personagem.getAlternateNames().getBytes();
        dos.writeUTF(personagem.getAlternateNames());

        // House
        lineBytes = personagem.getHouse().getBytes();
        dos.writeUTF(personagem.getHouse());

        // Ancestry
        lineBytes = personagem.getAncestry().getBytes();
        dos.writeUTF(personagem.getAncestry());

        // Species
        lineBytes = personagem.getSpecies().getBytes();
        dos.writeUTF(personagem.getSpecies());

        // Patronus
        lineBytes = personagem.getPatronus().getBytes();
        dos.writeUTF(personagem.getPatronus());

        // HogwartsStaff
        lineBytes = personagem.getHogwartsStaff().getBytes();
        dos.writeUTF(personagem.getHogwartsStaff());

        // HogwartsStudent
        lineBytes = personagem.getHogwartsStudent().getBytes();
        dos.writeUTF(personagem.getHogwartsStudent());

        // ActorName
        lineBytes = personagem.getActorName().getBytes();
        dos.writeUTF(personagem.getActorName());

        // Alive
        lineBytes = personagem.getAlive().getBytes();
        dos.writeUTF(personagem.getAlive());

        // DateOfBirth
        // Converte a data para milissegundos desde 1 de janeiro de 1970
        long timeInMillis = personagem.getDateOfBirth().getTime();
        lineBytes = ByteBuffer.allocate(Long.BYTES).putLong(timeInMillis).array();
        dos.write(lineBytes);

        // EyeColor
        lineBytes = personagem.getEyeColor().getBytes();
        dos.writeUTF(personagem.getEyeColor());

        // Gender
        lineBytes = personagem.getGender().getBytes();
        dos.writeUTF(personagem.getGender());

        // HairColour
        lineBytes = personagem.getHairColour().getBytes();
        dos.writeUTF(personagem.getHairColour());

        // Wizard
        lineBytes = personagem.getWizard().getBytes();
        dos.writeUTF(personagem.getWizard());

        // YearOfBirth
        dos.writeInt(personagem.getYearOfBirth());

        // adiciona novo personagem no hash
        try (RandomAccessFile indexFile = new RandomAccessFile(hashExtensivel, "rw")) {
            directory.insert(id, endereco, indexFile);
        }
        dos.close();

        raf.close();
    }

    // READ
    private static void Read(String inputBinary, int id, String hashExtensivel, Directory directory) {
        try (RandomAccessFile raf = new RandomAccessFile(inputBinary, "r");
                RandomAccessFile indexFile = new RandomAccessFile(hashExtensivel, "r")) {

            // Busca a posição do registro no índice
            Long posicao = directory.search(id, indexFile);

            if (posicao != null) {
                raf.seek(posicao);

                String aux;
                String line = "";

                // ler lapide
                raf.readByte();
                // ler id
                raf.readInt();
                // ler tamanho personagem
                raf.readInt();

                // idString
                int tamanhoDados = raf.readUnsignedShort();// Lê o numero de bytes que o registro ocupa
                byte[] data = new byte[tamanhoDados];// Lê o numero exato de bytes
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // nome
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // alternate_names
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // house
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // ancestry
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // species
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // patronus
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // hogwartsStaff
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // hogwartsStudent
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // actorName
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // alive
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // dateOfBirth
                // transforma data em string para facilitar a leitura no printf
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date dataNascimento = new Date(raf.readLong());
                aux = sdf.format(dataNascimento);
                line += aux;
                line += ";";

                // eyeColor
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // gender
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // hairColour
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // wizard
                tamanhoDados = raf.readUnsignedShort();
                data = new byte[tamanhoDados];
                raf.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;

                // Lê o ano (últimos 4 bytes do registro, pois são armazenados como int)
                int ano = raf.readInt();

                // Converte os dados lidos em string usando UTF-8
                System.out.println("Registro encontrado: " + line + "; AnoNascimento: " + ano);

                raf.close();

            } else {
                System.out.println("Registro não encontrado.");
            }

        } catch (EOFException e) {
            System.err.println("Fim do arquivo alcançado inesperadamente: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // UPDATE
    private static void Update(String inputBinary, int id, Personagem personagemNovo, Directory directory,
            String hashExtensivel) throws IOException {
        int tamanhoPersonagemNovo = 0;
        try (RandomAccessFile raf = new RandomAccessFile(inputBinary, "rw");
                RandomAccessFile indexFile = new RandomAccessFile(hashExtensivel, "rw")) {// ; Abre para leitura e
                                                                                          // escrita
            // Lê o ultimo ID
            raf.readInt();

            Long posicao = directory.search(id, indexFile);

            if (posicao != null) { // Verifica se o ID existe

                raf.seek(posicao);
                byte lapide = raf.readByte();
                int tamanho = raf.readInt();
                int idAtual = raf.readInt();

                // Salva a posição da lapide
                long posLapide = posicao;

                if (lapide == 0 && idAtual == id) {

                    tamanhoPersonagemNovo = getTamanhoPersonagem(personagemNovo); // calcula tamanho do personagem
                                                                                  // atualizado
                    if (tamanhoPersonagemNovo > tamanho || tamanhoPersonagemNovo < tamanho) { // se personagem
                                                                                              // atualizado > personagem
                                                                                              // desatualizado
                        // Posiciona o ponteiro no início da lápide e escreve 1
                        raf.seek(posLapide);
                        raf.writeByte(1);
                        raf.seek(raf.length());
                        posicao = raf.getFilePointer();

                        directory.remove(id, indexFile); // deleta id no index
                        Create(inputBinary, "new_characters.csv", id, personagemNovo, directory, hashExtensivel);// cria
                                                                                                                 // um
                                                                                                                 // novo
                                                                                                                 // personagem

                        System.out.println("Atualizado com sucesso.");

                        raf.close();
                    } else if (tamanhoPersonagemNovo == tamanho) {
                        // se personagem atualizado <= personagem desatualizado marca o registro antigo
                        // como lapide e adiciona o novo ao final
                        raf.seek(posLapide);

                        raf.writeByte(0); // marca como lapide

                        raf.writeInt(tamanhoPersonagemNovo);
                        raf.writeInt(id);

                        raf.writeUTF(personagemNovo.getId());
                        raf.writeUTF(personagemNovo.getName());
                        raf.writeUTF(personagemNovo.getAlternateNames());
                        raf.writeUTF(personagemNovo.getHouse());
                        raf.writeUTF(personagemNovo.getAncestry());
                        raf.writeUTF(personagemNovo.getSpecies());
                        raf.writeUTF(personagemNovo.getPatronus());
                        raf.writeUTF(personagemNovo.getHogwartsStaff());
                        raf.writeUTF(personagemNovo.getHogwartsStudent());
                        raf.writeUTF(personagemNovo.getActorName());
                        raf.writeUTF(personagemNovo.getAlive());

                        // DataOfBirth
                        long timeInMillis = personagemNovo.getDateOfBirth().getTime();
                        raf.writeLong(timeInMillis);

                        raf.writeUTF(personagemNovo.getEyeColor());
                        raf.writeUTF(personagemNovo.getGender());
                        raf.writeUTF(personagemNovo.getHairColour());
                        raf.writeUTF(personagemNovo.getWizard());
                        raf.writeInt(personagemNovo.getYearOfBirth());

                        System.out.println("Registro substituído por um novo.");
                    }
                }
            } else {
                System.out.println("Registro não encontrado.");
            }

        } catch (EOFException e) {
            System.err.println("Fim do arquivo alcançado inesperadamente: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    private static void Delete(String inputBinary, int id, Directory directory, String hashExtensivel) {
        try (RandomAccessFile raf = new RandomAccessFile(inputBinary, "rw");
                RandomAccessFile indexFile = new RandomAccessFile(hashExtensivel, "rw")) {

            Long position = directory.search(id, indexFile);
            if (position != null) { // verifica se o ID existe
                raf.seek(position);
                raf.writeByte(1); // marca como lapide

                // remove o ID do bucket
                directory.remove(id, indexFile);
                int hashValue = directory.hash(id);
                Bucket bucket = directory.table.get(hashValue);

                // reescreve o bucket no arquivo
                if (!bucket.ids.isEmpty()) {
                    indexFile.seek(bucket.positions.get(0));
                    for (int i = 0; i < bucket.ids.size(); i++) {
                        indexFile.writeInt(bucket.ids.get(i));
                        indexFile.writeLong(bucket.positions.get(i));
                    }
                }

                System.out.println("Registro excluído.");
            } else {
                System.out.println("Registro não encontrado.");
            }

        } catch (EOFException e) {
            System.err.println("Fim do arquivo alcançado inesperadamente: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Conta quantos bytes o personagem vai usar
    public static int getTamanhoPersonagem(Personagem personagem) throws IOException {
        // Inicializa o tamanho com o tamanho do campo ID
        int tamanho = 4;

        // Calcula o tamanho de cada campo em bytes, usando UTF-8 explicitamente
        byte[] lineBytes = personagem.getId().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getName().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getAlternateNames().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getHouse().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getAncestry().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getSpecies().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getPatronus().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getHogwartsStaff().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getHogwartsStudent().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getActorName().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getAlive().getBytes();
        tamanho += lineBytes.length + 2;

        tamanho += 8; // dateOfBirth

        tamanho += 4; // yearOfBirth

        lineBytes = personagem.getEyeColor().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getGender().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getHairColour().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getWizard().getBytes();
        tamanho += lineBytes.length + 2;

        System.out.println("Tamanho: " + tamanho);
        return tamanho;
    }

    private static void escreverRegistro(RandomAccessFile raf, Personagem personagem, int id) throws IOException {
        // Escreve um novo registro no arquivo
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(raf.getFD())));

        dos.writeBoolean(false); // lápide
        int tamanho = getTamanhoPersonagem(personagem);
        dos.writeInt(tamanho);
        dos.writeInt(id);

        dos.writeUTF(personagem.getId());
        dos.writeUTF(personagem.getName());
        dos.writeUTF(personagem.getAlternateNames());
        dos.writeUTF(personagem.getHouse());
        dos.writeUTF(personagem.getAncestry());
        dos.writeUTF(personagem.getSpecies());
        dos.writeUTF(personagem.getPatronus());
        dos.writeUTF(personagem.getHogwartsStaff());
        dos.writeUTF(personagem.getHogwartsStudent());
        dos.writeUTF(personagem.getActorName());
        dos.writeUTF(personagem.getAlive());

        // DataOfBirth
        long timeInMillis = personagem.getDateOfBirth().getTime();
        dos.writeLong(timeInMillis);

        dos.writeUTF(personagem.getEyeColor());
        dos.writeUTF(personagem.getGender());
        dos.writeUTF(personagem.getHairColour());
        dos.writeUTF(personagem.getWizard());
        dos.writeInt(personagem.getYearOfBirth());

        dos.flush();
        dos.close();
    }

    static class Personagem {
        private String id;
        private String name;
        private List<String> alternate_names = new ArrayList<String>();
        private String house;
        private String ancestry;
        private String species;
        private String patronus;
        private String hogwartsStaff;
        private String hogwartsStudent;
        private String actorName;
        private String alive;
        private Date dateOfBirth;
        private int yearOfBirth;
        private String eyeColor;
        private String gender;
        private String hairColour;
        private String wizard;

        // --------------------------------------------------Classe_Personagem--------------------------------------------------

        // Contrutor
        public Personagem() {
            id = "";
            name = "";
            alternate_names = new ArrayList<String>();
            house = "";
            ancestry = "";
            species = "";
            patronus = "";
            hogwartsStaff = "";
            hogwartsStudent = "";
            actorName = "";
            alive = "";
            dateOfBirth = new Date();
            yearOfBirth = 0;
            eyeColor = "";
            gender = "";
            hairColour = "";
            wizard = "";
        }

        // Contrutor
        public Personagem(String id, String name, ArrayList<String> alternate_names, String house, String ancestry,
                String species,
                String patronus, String hogwartsStaff,
                String hogwartsStudent, String actorName, String alive, Date dateOfBirth, int yearOfBirth,
                String eyeColor, String gender, String hairColour, String wizard) {
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

        // getters
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

        public String getHogwartsStaff() {
            return hogwartsStaff;
        }

        public String getHogwartsStudent() {
            return hogwartsStudent;
        }

        public String getActorName() {
            return actorName;
        }

        public String getAlive() {
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

        public String getWizard() {
            return wizard;
        }

        // Setters
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

        public void setHogwartsStaff(String hogwartsStaff) {
            if (hogwartsStaff.equals(" ")) {
                hogwartsStaff = "";
            }
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

        public void setAlive(String alive) {
            if (alive.equals(" ")) {
                alive = "";
            }
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
                System.err.println("Erro ao inserir data nascimento.");
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

        public void setWizard(String wizard) {
            this.wizard = wizard;
        }

        public void ler(String x) {
            // 0 = id; 1 = name; 2 = alternate_names; 3 = house;
            // 4 = ancestry; 5 = species; 6 = patronus; 7 = hogwartsStaff;
            // 8 = hogwartsStudent; 9 = actorName; 10 = alive; 12 = dateOfBirth;
            // 13 = yearOfBirth; 14 = eyeColor; 15 = gender; 16 = hairColour;
            // 17 = wizard;

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
                setHogwartsStaff("false");
            } else if (partes[7].equals("VERDADEIRO")) {
                setHogwartsStaff("true");
            }
            if (partes[8].equals("FALSO")) {
                setHogwartsStudent("false");
            } else if (partes[8].equals("VERDADEIRO")) {
                setHogwartsStudent("true");
            }
            setActorName(partes[9]);
            if (partes[10].equals("FALSO")) {
                setAlive("false");
            } else if (partes[10].equals("VERDADEIRO")) {
                setAlive("true");
            }
            setDateOfBirth(partes[12]);
            setYearOfBirth(partes[13]);
            setEyeColor(partes[14]);
            setGender(partes[15]);
            setHairColour(partes[16]);
            if (partes[17].equals("FALSO")) {
                setWizard("false");
            } else if (partes[17].equals("VERDADEIRO")) {
                setWizard("true");
            }
        }
    }

    // Classe da árvore B
    public static class BTree {

        static class BTreeNode {
            int[] keys;
            int t; // Ordem da árvore B
            long[] positions; // Armazena as posições correspondentes no arquivo binário
            BTreeNode[] children;
            int numKeys;
            boolean isLeaf;

            public BTreeNode(int t, boolean isLeaf) {
                this.t = t;
                this.isLeaf = isLeaf;
                this.keys = new int[2 * t - 1];
                this.positions = new long[2 * t - 1]; // Armazena as posições para cada chave
                this.children = new BTreeNode[2 * t];
                this.numKeys = 0;
            }
        }

        BTreeNode root;
        int t; // Ordem mínima da árvore B
        int nodeIdCounter; // Contador de IDs únicos para os nós

        public BTree(int t) {
            this.root = null;
            this.t = t;
            this.nodeIdCounter = 0;
        }

        public void insert(int key, long position) {
            // Se a árvore estiver vazia, cria a raiz
            if (root == null) {
                root = new BTreeNode(t, true);
                root.keys[0] = key;
                root.positions[0] = position; // Armazena a posição
                root.numKeys = 1;
            } else {
                // Se a raiz estiver cheia, precisamos dividir
                if (root.numKeys == 2 * t - 1) {
                    BTreeNode newRoot = new BTreeNode(t, false);
                    newRoot.children[0] = root;
                    splitChild(newRoot, 0, root);
                    root = newRoot;
                }
                insertNonFull(root, key, position);
            }
        }

        private void splitChild(BTreeNode parent, int i, BTreeNode fullChild) {
            BTreeNode newNode = new BTreeNode(fullChild.t, fullChild.isLeaf);
            newNode.numKeys = t - 1;

            // Copia as últimas (t-1) chaves e posições para o novo nó
            for (int j = 0; j < t - 1; j++) {
                newNode.keys[j] = fullChild.keys[j + t];
                newNode.positions[j] = fullChild.positions[j + t];
            }

            // Se o nó não for folha, copia os filhos também
            if (!fullChild.isLeaf) {
                for (int j = 0; j < t; j++) {
                    newNode.children[j] = fullChild.children[j + t];
                }
            }

            fullChild.numKeys = t - 1;

            // Move os filhos do pai para a direita para abrir espaço para o novo nó
            for (int j = parent.numKeys; j >= i + 1; j--) {
                parent.children[j + 1] = parent.children[j];
            }

            // Insere o novo nó como filho
            parent.children[i + 1] = newNode;

            // Move as chaves e posições do pai para a direita para abrir espaço para a nova
            // chave
            for (int j = parent.numKeys - 1; j >= i; j--) {
                parent.keys[j + 1] = parent.keys[j];
                parent.positions[j + 1] = parent.positions[j];
            }

            // Insere a chave mediana do filho cheio no pai
            parent.keys[i] = fullChild.keys[t - 1];
            parent.positions[i] = fullChild.positions[t - 1];
            parent.numKeys++;
        }

        private void insertNonFull(BTreeNode node, int key, long position) {
            int i = node.numKeys - 1;

            if (node.isLeaf) {
                // Move as chaves maiores para a direita
                while (i >= 0 && key < node.keys[i]) {
                    node.keys[i + 1] = node.keys[i];
                    node.positions[i + 1] = node.positions[i];
                    i--;
                }

                // Insere a nova chave e sua posição
                node.keys[i + 1] = key;
                node.positions[i + 1] = position;
                node.numKeys++;
            } else {
                // Encontra o filho que receberá a nova chave
                while (i >= 0 && key < node.keys[i]) {
                    i--;
                }

                // Se o filho estiver cheio, divide-o
                if (node.children[i + 1].numKeys == 2 * t - 1) {
                    splitChild(node, i + 1, node.children[i + 1]);

                    if (key > node.keys[i + 1]) {
                        i++;
                    }
                }

                insertNonFull(node.children[i + 1], key, position);
            }
        }

        public void search(int key) {
            searchNode(root, key);
        }

        private void searchNode(BTreeNode node, int key) {
            int i = 0;

            // Procura pela chave no nó
            while (i < node.numKeys && key > node.keys[i]) {
                i++;
            }

            // Se a chave foi encontrada
            if (i < node.numKeys && key == node.keys[i]) {
                long position = node.positions[i];
                try {
                    // Verifica se o registro encontrado é uma lápide
                    if (!isTombstone(position)) {
                        // Se não for uma lápide, lê e imprime o registro
                        readAndPrintRecord(position);
                    } else {
                        System.out.println("Chave " + key + " encontrada, mas o registro foi excluído (lápide).");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (node.isLeaf) {
                System.out.println("Chave não encontrada.");
            } else {
                // Se não for uma folha, continua a busca recursivamente nos filhos
                searchNode(node.children[i], key);
            }
        }

        // Método para verificar se o registro na posição fornecida é uma lápide
        private boolean isTombstone(long position) {
            try (RandomAccessFile file = new RandomAccessFile("characters.bin", "r")) {
                file.seek(position); // Move para a posição no arquivo
                byte lapide = file.readByte(); // Lê o valor da lápide (1 = lápide, 0 = não lápide)
                return lapide == 1; // Retorna true se for 1 (lápide), false caso contrário
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false; // Retorna false em caso de erro
        }

        // Método para ler e imprimir o registro a partir de uma posição no arquivo
        // binário
        private void readAndPrintRecord(long position) throws IOException {
            try (RandomAccessFile file = new RandomAccessFile("characters.bin", "r")) {
                file.seek(position); // Move para a posição no arquivo

                // Lê o registro na posição (assumindo a estrutura conforme descrita
                // anteriormente)
                byte tombstone = file.readByte();
                int recordSize = file.readInt(); // Tamanho do registro
                int id = file.readInt(); // ID do registro
                String name = file.readUTF(); // Nome
                String house = file.readUTF(); // Casa
                // Continue lendo os outros atributos conforme a estrutura do registro...

                // Exemplo de impressão dos dados do personagem
                System.out.println("Registro encontrado:");
                System.out.println("ID: " + id);
                System.out.println("ID-String: " + name);
                System.out.println("Nome: " + house);
                // Imprima os outros atributos conforme necessário...
            }
        }

        // Salva a árvore B em um arquivo de índice
        public void saveTreeToFile(String filename) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                if (root != null) {
                    Map<BTreeNode, Integer> nodeIdMap = new HashMap<>();
                    nodeIdCounter = 0;
                    saveNode(root, writer, nodeIdMap, -1);
                } else {
                    writer.write("Árvore está vazia.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void saveNode(BTreeNode node, BufferedWriter writer, Map<BTreeNode, Integer> nodeIdMap,
                Integer parentId) throws IOException {

            // Atribui um ID único para o nó atual
            int nodeId = nodeIdCounter++;
            nodeIdMap.put(node, nodeId);

            // Escreve o ID do nó, se ele é folha, e a relação com o pai
            writer.write("No ID: " + nodeId + ", Filho de: " + (parentId == null ? "Raiz" : "No " + parentId)
                    + ", Folha: " + node.isLeaf);

            // Se o nó tiver chaves, escreve as chaves e suas posições
            if (node.numKeys > 0) {
                writer.write(", Chaves: ");
                for (int i = 0; i < node.numKeys; i++) {
                    writer.write(node.keys[i] + "(" + node.positions[i] + ") "); // Chave e posição
                }
            }

            writer.newLine();

            // Chama recursivamente para salvar os filhos, agora mostrando a relação com o
            // pai
            if (!node.isLeaf) { // Somente chama para os filhos se o nó não for folha
                for (int i = 0; i <= node.numKeys; i++) {
                    saveNode(node.children[i], writer, nodeIdMap, nodeId); // Passa o ID do nó atual como pai
                }
            }
        }

        // Método para carregar a árvore a partir de um arquivo de índices
        public void loadTreeFromFile(String filename) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                Map<Integer, BTreeNode> nodeIdMap = new HashMap<>(); // Mapa para armazenar os nós por ID
                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("No ID:")) {
                        String[] parts = line.split(", ");

                        // Extrai o ID do nó
                        int nodeId = Integer.parseInt(parts[0].split(": ")[1]);

                        // Extrai o ID do pai
                        String parentInfo = parts[1].split(": ")[1];
                        int parentId = parentInfo.equals("Raiz") ? -1 : Integer.parseInt(parentInfo.replace("No ", ""));

                        // Verifica se o nó é folha
                        boolean isLeaf = Boolean.parseBoolean(parts[2].split(": ")[1]);

                        // Cria o nó e armazena no mapa
                        BTreeNode node = new BTreeNode(t, isLeaf);
                        nodeIdMap.put(nodeId, node);

                        // Se o nó tem chaves, processa as chaves e suas posições
                        if (parts.length > 3 && parts[3].startsWith("Chaves:")) {
                            String[] keyParts = parts[3].split(": ")[1].split(" ");
                            for (String keyPart : keyParts) {
                                if (!keyPart.isEmpty()) {
                                    String[] keyPos = keyPart.split("\\(");
                                    node.keys[node.numKeys] = Integer.parseInt(keyPos[0]);
                                    node.positions[node.numKeys] = Long.parseLong(keyPos[1].replace(")", ""));
                                    node.numKeys++;
                                }
                            }
                        }

                        // Se o nó tem um pai, o conecta ao pai
                        if (parentId != -1) {
                            BTreeNode parentNode = nodeIdMap.get(parentId);
                            for (int i = 0; i <= parentNode.numKeys; i++) {
                                if (parentNode.children[i] == null) {
                                    parentNode.children[i] = node;
                                    break;
                                }
                            }
                        }

                        // Se o nó é a raiz, define-o como tal
                        if (parentId == -1) {
                            root = node;
                        }
                    }
                }

                System.out.println("Árvore carregada com sucesso!");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Método para realizar o caminhamento em ordem (in-order traversal) da árvore B
        public void traverseInOrder() {
            if (root != null) {
                traverseInOrder(root);
            }
        }

        // Caminhamento em ordem para um nó específico
        private void traverseInOrder(BTreeNode node) {
            int i;

            // Percorre as chaves e filhos do nó
            for (i = 0; i < node.numKeys; i++) {
                // Se o nó não for folha, percorre o filho à esquerda antes de imprimir a chave
                if (!node.isLeaf) {
                    traverseInOrder(node.children[i]);
                }

                // Imprime a chave e sua posição correspondente
                System.out.print(node.keys[i] + "(" + node.positions[i] + ") ");
            }

            // Após percorrer todas as chaves, percorre o último filho à direita
            if (!node.isLeaf) {
                traverseInOrder(node.children[i]);
            }
        }

        /// Método para realizar o caminhamento em pré-ordem (pre-order traversal) da
        /// árvore B
        public void traversePreOrder() {
            if (root != null) {
                // Para a raiz, não há pai, então passamos um valor indicativo, como -1
                traversePreOrder(root, -1);
            }
        }

        // Caminhamento em pré-ordem para um nó específico
        private void traversePreOrder(BTreeNode node, int parentId) {
            // Atribui um ID único ao nó atual
            int nodeId = nodeIdCounter++;

            // Imprime o nó atual (suas chaves, posições e o ID do nó pai)
            System.out.print("Nó ID: " + nodeId + ", Filho do nó: " + parentId + " -> ");
            for (int i = 0; i < node.numKeys; i++) {
                System.out.print(node.keys[i] + "(" + node.positions[i] + ") ");
            }
            System.out.println();

            // Se o nó não for uma folha, percorre recursivamente os filhos
            if (!node.isLeaf) {
                for (int i = 0; i <= node.numKeys; i++) {
                    traversePreOrder(node.children[i], nodeId); // Passa o ID do nó atual como o ID do pai
                }
            }
        }
    }

    // LISTA INVERTIDA
    public static class InvertedList {
        private Map<String, List<Long>> invertedListCasa; // Lista invertida para "Casa"
        private Map<Integer, List<Long>> invertedListAnoNascimento; // Lista invertida para "Ano de Nascimento"
        private String filePathCasa;
        private String filePathAno;

        public InvertedList(String filePathCasa, String filePathAno) {
            this.filePathCasa = "arquivoCasa.txt";
            this.filePathAno = "arquivoAno.txt";
            this.invertedListCasa = new HashMap<>();
            this.invertedListAnoNascimento = new HashMap<>();
            loadInvertedLists();
        }

        // Carregar as listas invertidas dos arquivos
        private void loadInvertedLists() {
            // Carregar a lista invertida da "Casa"
            try (BufferedReader reader = new BufferedReader(new FileReader(filePathCasa))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    String casa = parts[0];
                    List<Long> positions = parsePositions(parts[1]);
                    invertedListCasa.put(casa, positions);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Carregar a lista invertida do "Ano de Nascimento"
            try (BufferedReader reader = new BufferedReader(new FileReader(filePathAno))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    int anoNascimento = Integer.parseInt(parts[0]);
                    List<Long> positions = parsePositions(parts[1]);
                    invertedListAnoNascimento.put(anoNascimento, positions);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Converte a string de posições em uma lista de Long
        private List<Long> parsePositions(String positionsString) {
            String[] posArray = positionsString.trim().replace("[", "").replace("]", "").split(", ");
            List<Long> positions = new ArrayList<>();
            for (String pos : posArray) {
                positions.add(Long.parseLong(pos));
            }
            return positions;
        }

        // Salva a lista invertida de volta nos arquivos
        private void saveInvertedLists() {
            // Salvar lista invertida de "Casa"
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathCasa))) {
                for (String casa : invertedListCasa.keySet()) {
                    writer.write(casa + ": " + invertedListCasa.get(casa).toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Salvar lista invertida de "Ano de Nascimento"
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathAno))) {
                for (Integer anoNascimento : invertedListAnoNascimento.keySet()) {
                    writer.write(anoNascimento + ": " + invertedListAnoNascimento.get(anoNascimento).toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Insere um novo registro na lista invertida
        public void insert(String casa, int anoNascimento, long position) {
            // Inserir na lista invertida de "Casa"
            invertedListCasa.putIfAbsent(casa, new ArrayList<>());
            invertedListCasa.get(casa).add(position);

            // Inserir na lista invertida de "Ano de Nascimento"
            invertedListAnoNascimento.putIfAbsent(anoNascimento, new ArrayList<>());
            invertedListAnoNascimento.get(anoNascimento).add(position);

            saveInvertedLists(); // Salva as alterações
        }

        // Remove um registro da lista invertida
        public void remove(String casa, int anoNascimento, long position) {
            // Remover da lista invertida de "Casa"
            if (invertedListCasa.containsKey(casa)) {
                invertedListCasa.get(casa).remove(position);
                if (invertedListCasa.get(casa).isEmpty()) {
                    invertedListCasa.remove(casa);
                }
            }

            // Remover da lista invertida de "Ano de Nascimento"
            if (invertedListAnoNascimento.containsKey(anoNascimento)) {
                invertedListAnoNascimento.get(anoNascimento).remove(position);
                if (invertedListAnoNascimento.get(anoNascimento).isEmpty()) {
                    invertedListAnoNascimento.remove(anoNascimento);
                }
            }

            saveInvertedLists(); // Salva as alterações
        }

        // Busca por uma casa
        public List<Long> searchByCasa(String casa) {
            return invertedListCasa.getOrDefault(casa, new ArrayList<>());
        }

        // Busca por ano de nascimento
        public List<Long> searchByAnoNascimento(int anoNascimento) {
            return invertedListAnoNascimento.getOrDefault(anoNascimento, new ArrayList<>());
        }

        // Busca combinada (interseção)
        public List<Long> search(String casa, int anoNascimento) {
            List<Long> resultadoCasa = searchByCasa(casa);
            List<Long> resultadoAno = searchByAnoNascimento(anoNascimento);
            return intersectLists(resultadoCasa, resultadoAno);
        }

        // Interseção de duas listas
        private List<Long> intersectLists(List<Long> lista1, List<Long> lista2) {
            List<Long> intersecao = new ArrayList<>();
            int i = 0, j = 0;
            while (i < lista1.size() && j < lista2.size()) {
                if (lista1.get(i).equals(lista2.get(j))) {
                    intersecao.add(lista1.get(i));
                    i++;
                    j++;
                } else if (lista1.get(i) < lista2.get(j)) {
                    i++;
                } else {
                    j++;
                }
            }
            return intersecao;
        }
    }

    // DIRETORIO BUCKET
    static class Bucket {
        int capacity;
        List<Integer> ids;
        List<Long> positions;

        public Bucket(int capacity) {
            this.capacity = capacity;
            ids = new ArrayList<>();
            positions = new ArrayList<>();
        }

        public boolean isFull() {
            return ids.size() >= capacity;
        }

        public void add(int id, long position) {
            ids.add(id);
            positions.add(position);
        }

        public boolean contains(int id) {
            return ids.contains(id);
        }

        public long getPosition(int id) {
            int index = ids.indexOf(id);
            return positions.get(index);
        }

        public void remove(int id) {
            int index = ids.indexOf(id);
            if (index != -1) {
                ids.remove(index);
                positions.remove(index);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Bucket").append("\n");
            for (int i = 0; i < ids.size(); i++) {
                sb.append(" ID: ").append(ids.get(i))
                        .append(" - Posicao: ").append(positions.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    // DIRETORIO BUCKET
    static class Directory {
        HashMap<Integer, Bucket> table;
        int globalDepth;
        int bucketCapacity;

        public Directory(int bucketCapacity) {
            this.table = new HashMap<>();
            this.globalDepth = 1;
            this.bucketCapacity = bucketCapacity;

            // Inicializa com dois buckets
            table.put(0, new Bucket(bucketCapacity));
            table.put(1, new Bucket(bucketCapacity));
        }

        public int hash(int id) {
            return id % (int) Math.pow(2, globalDepth);
        }

        public void insert(int id, long position, RandomAccessFile indexFile) throws IOException {
            int hashValue = hash(id);
            Bucket bucket = table.get(hashValue);

            if (bucket.isFull()) {
                // se o bucket estiver cheio vai dividir
                splitBucket(hashValue);
                // insere o novo elemento
                bucket = table.get(hash(id)); // recalcula o bucket depois a divisao
            }

            bucket.add(id, position);
            // Grava o índice no arquivo index.bin
            indexFile.seek(indexFile.length());
            indexFile.writeInt(id);
            indexFile.writeLong(position);
        }

        private void splitBucket(int hashValue) {
            Bucket fullBucket = table.get(hashValue);

            // se necessario aumenta a profundidade global
            if (hashValue == (int) Math.pow(2, globalDepth) - 1) {
                globalDepth++;
                for (int i = 0; i < Math.pow(2, globalDepth - 1); i++) {
                    table.put(i + (int) Math.pow(2, globalDepth - 1), new Bucket(bucketCapacity));
                }
            }

            // redistribuir os elementos manualmente
            List<Integer> idsToRedistribute = new ArrayList<>(fullBucket.ids);
            List<Long> positionsToRedistribute = new ArrayList<>(fullBucket.positions);

            fullBucket.ids.clear();
            fullBucket.positions.clear();

            for (int i = 0; i < idsToRedistribute.size(); i++) {
                int id = idsToRedistribute.get(i);
                long pos = positionsToRedistribute.get(i);
                int newHash = hash(id); // recalcula o hash com a nova profundidade
                Bucket targetBucket = table.get(newHash);
                targetBucket.add(id, pos); // adiciona o elemento no bucket correto
            }
        }

        public Long search(int id, RandomAccessFile indexFile) throws IOException {
            int hashValue = hash(id);
            Bucket bucket = table.get(hashValue);

            if (bucket.contains(id)) {
                return bucket.getPosition(id);
            } else {
                return null;
            }
        }

        public void remove(int id, RandomAccessFile indexFile) throws IOException {
            int hashValue = hash(id);
            Bucket bucket = table.get(hashValue);
            if (bucket.contains(id)) {
                bucket.remove(id);
            }
        }
    }
}