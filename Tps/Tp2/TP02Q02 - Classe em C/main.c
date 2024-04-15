#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
typedef struct
{
    char id[400];
    char name[400];
    //(String), alternate names (Lista)1
    char house[400];
    char ancestry[400];
    char species[400];
    char patronus[400];
    bool hogwartsStaff;
    char hogwartsStudent[400];
    char actorName[400];
    bool alive;

    int yearOfBirth;
    char eyeColour[400];
    char gender[400];
    char hairColour[400];
    bool wizard;
} Personagem;

typedef struct
{
    int dia;
    int mes;
    int ano;
} Data;

// faltando alternate names
Personagem createPersonagem(const char *id, const char *name, const char *house, const char *ancestry, const char *species, const char *patronus, bool hogwartsStaff,
                            const char *hogwartsStudent, const char *actorName, bool alive, int yearOfBirth, const char *eyeColour, const char *gender, const char *hairColour, bool wizard)
{
    Personagem personagem;
    strcpy(personagem.id, id);
    strcpy(personagem.name, name);
    // aternate names
    strcpy(personagem.house, house);
    strcpy(personagem.ancestry, ancestry);
    strcpy(personagem.species, species);
    strcpy(personagem.patronus, patronus);
    personagem.hogwartsStaff = hogwartsStaff;
    strcpy(personagem.hogwartsStudent, hogwartsStudent);
    strcpy(personagem.actorName, actorName);
    personagem.alive = alive;
    // data
    personagem.yearOfBirth = yearOfBirth;
    strcpy(personagem.eyeColour, eyeColour);
    strcpy(personagem.gender, gender);
    strcpy(personagem.hairColour, hairColour);
    personagem.wizard = wizard;
    return personagem;
}

Data createData(int dia, int mes, int ano)
{
    Data data;
    data.dia = dia;
    data.mes = mes;
    data.ano = ano;
    return data;
}

// gets
// faltando alternate names
const char *getId(Personagem *personagem) { return personagem->id; }
const char *getName(Personagem *personagem) { return personagem->name; }
// alternate names
const char *getHouse(Personagem *personagem) { return personagem->house; }
const char *getAncestry(Personagem *personagem) { return personagem->ancestry; }
const char *getSpecies(Personagem *personagem) { return personagem->species; }
const char *getPatronus(Personagem *personagem) { return personagem->patronus; }
bool getHogwartsStaff(Personagem *personagem) { return personagem->hogwartsStaff; }
const char *getHogwartsStudent(Personagem *personagem) { return personagem->hogwartsStudent; }
const char *getActorName(Personagem *personagem) { return personagem->actorName; }
bool getAlive(Personagem *personagem) { return personagem->alive; }
int getDia(Data *data) { return data->dia; }
int getMes(Data *data) { return data->mes; }
int getAno(Data *data) { return data->ano; }
int getYearOfBirth(Personagem *personagem) { return personagem->yearOfBirth; }
const char *getEyeColour(Personagem *personagem) { return personagem->eyeColour; }
const char *getGenger(Personagem *personagem) { return personagem->gender; }
const char *getHairColour(Personagem *personagem) { return personagem->hairColour; }
bool getWizard(Personagem *personagem) { return personagem->wizard; }

// sets
// faltando alternate names
void setId(Personagem *personagem, const char *novoId) { strcpy(personagem->id, novoId); }
void setName(Personagem *personagem, const char *novoNome) { strcpy(personagem->name, novoNome); }
// alternate names
void setHouse(Personagem *personagem, const char *novoHouse) { strcpy(personagem->house, novoHouse); }
void setAncestry(Personagem *personagem, const char *novoAncestry) { strcpy(personagem->ancestry, novoAncestry); }
void setSpecies(Personagem *personagem, const char *novoSpecies) { strcpy(personagem->species, novoSpecies); }
void setPatronus(Personagem *personagem, const char *novoPatronus) { strcpy(personagem->patronus, novoPatronus); }
void setHogwartsStaff(Personagem *personagem, bool novoHogwartsStaff) { personagem->hogwartsStaff = novoHogwartsStaff; }
void setHogwartsStudent(Personagem *personagem, const char *novoHogwartsStudent) { strcpy(personagem->hogwartsStudent, novoHogwartsStudent); }
void setActorName(Personagem *personagem, const char *novoActorName) { strcpy(personagem->actorName, novoActorName); }
void setAlive(Personagem *personagem, bool novoAlive) { personagem->alive = novoAlive; }
void setDia(Data *data, int novoDia) { data->dia = novoDia; }
void setMes(Data *data, int novoMes) { data->mes = novoMes; }
void setAno(Data *data, int novoAno) { data->ano = novoAno; }
void setYearOfBirth(Personagem *personagem, int novoYearOfBirth) { personagem->yearOfBirth = novoYearOfBirth; }
void setEyeColour(Personagem *personagem, const char *novoEyeColour) { strcpy(personagem->eyeColour, novoEyeColour); }
void setGender(Personagem *personagem, const char *novoGender) { strcpy(personagem->gender, novoGender); }
void setHairColour(Personagem *personagem, const char *novoHairColour) { strcpy(personagem->hairColour, novoHairColour); }
void setWizard(Personagem *personagem, bool novoWizard) { personagem->wizard = novoWizard; }

void ler(const char *x, Personagem* personagem, Data* data)
{
    // 0 = id 1 = name 2 = alternate_names 3 = house
    // 4 = ancestry 5 = species 6 = patronus 7 = hogwartsStaff
    // 8 = hogwartsStudent 9 = actorName 10 = alive 12 = dateOfBirth
    // 13 = yearOfBirth 14 = eyeColor 15 = gender 16 = hairColour
    // 17 = wizard
    char string[400];
    int j = 0;

    for (int i = 0; i < strlen(x); i++)
    {
        if (x[i] == '[' || x[i] == ']')
        {
            i++;
        }
        else
        {
            string[j] = x[i];
            j++;
        }
    }
    string[j] = '\0';

    const char partes[2] = ";";
    char *token;
    
    
    // 0
    token = strtok(string, partes);
    setId(personagem, token);
    // 1
    token = strtok(NULL, partes);
    setName(personagem, token);
    // 2
    // alternates
    token = strtok(NULL, partes);
    // 3
    token = strtok(NULL, partes);
    setHouse(personagem, token);
    // 4
    token = strtok(NULL, partes);
    setAncestry(personagem, token);
    // 5
    token = strtok(NULL, partes);
    setSpecies(personagem, token);
    // 6
    token = strtok(NULL, partes);
    setPatronus(personagem, token);
    // 7
    token = strtok(NULL, partes);
    if (strcmp(token, "FALSO") == 0)
    {
        setHogwartsStaff(personagem, false);
    }
    else
    {
        setHogwartsStaff(personagem, true);
    }
    // 8
    token = strtok(NULL, partes);
    if (strcmp(token, "FALSO") == 0)
    {
        setHogwartsStudent(personagem, "false");
    }
    else
    {
        setHogwartsStudent(personagem, "true");
    }
    // 9
    token = strtok(NULL, partes);
    setActorName(personagem, token);
    // 10
    if (strcmp(token, "FALSO") == 0)
    {
        setAlive(personagem, false);
    }
    else
    {
        setAlive(personagem, true);
    }
    // 11
    token = strtok(NULL, partes);
    // 12
    const char dataTmp[2] = "-";
    char *token2;
    token2 = strtok(token, dataTmp);
    setDia(data, atoi(token2));
    token2 = strtok(NULL, dataTmp);
    setMes(data, atoi(token2));
    token2 = strtok(NULL, dataTmp);
    setAno(data, atoi(token2));
    // 13
    token = strtok(NULL, partes);
    setYearOfBirth(personagem, atoi(token));
    // 14
    token = strtok(NULL, partes);
    setEyeColour(personagem, token);
    // 15
    token = strtok(NULL, partes);
    setGender(personagem, token);
    // 16
    token = strtok(NULL, partes);
    setHairColour(personagem, token);
    // 17
    token = strtok(NULL, partes);
    if (strcmp(token, "FALSO") == 0)
    {
        setWizard(personagem, false);
    }
    else
    {
        setWizard(personagem, true);
    }
    
}

void imprimir(Personagem* personagem, Data* data)
{
    printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d-%d-%d ## %d ## %s ## %s ## %s ## %s]",
           personagem->id, personagem->name, personagem->house, personagem->ancestry, personagem->species, personagem->patronus, personagem->hogwartsStaff ? "true" : "false",
           personagem->hogwartsStudent, personagem->actorName, personagem->alive ? "true" : "false", data->dia, data->mes, data->ano, personagem->yearOfBirth, personagem->eyeColour,
           personagem->gender, personagem->hairColour, personagem->wizard ? "true" : "false");
}
void destroyPersonagem(Personagem *personagem) {
    free(personagem);
}
void destroyData(Data *data) {
    free(data);
}

int main()
{
    char linha[500];
    FILE *arq = fopen("characters.csv", "r");
    bool fim = false;
    Personagem personagens[1000];
    Data datas[1000];
    int numPersonagens = 0;

    while (fscanf(arq, "%499[^\\n]%*c", linha) == 1)
    {
        ler(linha, &personagens[numPersonagens], &datas[numPersonagens]);
        numPersonagens++;
    }
    fclose(arq);

    char id[50];
    while (fim == false)
    {
        fgets(id, 50, stdin);
        id[strcspn(id, "\n")] = '\0';
        if (id[0] == 'F' && id[1] == 'I' && id[2] == 'M')
        {
            fim = true;
        }
        else
        {
            for (int i = 0; i < numPersonagens; i++)
            {
                if (strcmp(personagens[i].id, id) == 0)
                {
                    imprimir(&personagens[i], &datas[i]);
                    break;
                }
            }
        }
    }
    
    return 0;
}
