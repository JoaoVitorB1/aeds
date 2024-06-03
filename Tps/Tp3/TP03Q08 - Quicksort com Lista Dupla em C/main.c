//Questoes finalizadas: 2,4,7,8
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#include <ctype.h>
#include <time.h>
#define MAXTAM     200
#define MAXTAMFILA 6


clock_t startTime;
clock_t endTime;
int Comp = 0;

void startTimer() {startTime = clock();}
void endTimer() {endTime = clock();}
typedef struct {
    char Lista[500];
}Lista;

void criaArqLog() {
    FILE* arqLog;
    arqLog = fopen("matricula_quicksort2.txt", "w");

    if (arqLog == NULL) {
        printf("Erro ao criar o arquivo de log\n");
        return;
    }
    int totalTime = ((double)(endTime - startTime)) / CLOCKS_PER_SEC;
    fprintf(arqLog, "694520\t%.2f\t%d\n",totalTime, Comp);
    fclose(arqLog);
}

typedef struct {
    char id[200];
    char name[200];
    Lista alternate_names;
    char house[200];
    char ancestry[200];
    char species[200];
    char patronus[200];
    char hogwartsStaff[10];
    char hogwartsStudent[10];
    char actorName[200];
    char alive[10];
    char dateOfBirth[200];
    int yearOfBirth;
    char eyeColour[200];
    char gender[200];
    char hairColor[200];
    char wizard[10];
    
}Personagem;

Personagem constr(char id[], char name[], char alternate_names[], char house[], char ancestry[], char species[], char patronus[], char hogwartsStaff[], char hogwartsStudent[], char actorName[], char alive[], char dateOfBirth[],
int yearOfBirth, char eyeColour[], char gender[], char hairColor[], char wizard[]){
    Personagem personagem;

    strcpy(personagem.id, id);
    strcpy(personagem.name, name);
    strcpy(personagem.alternate_names.Lista, alternate_names);
    strcpy(personagem.house, house);
    strcpy(personagem.ancestry, ancestry);
    strcpy(personagem.patronus, patronus);
    strcpy(personagem.species, species);    
    strcpy(personagem.hogwartsStaff,hogwartsStaff);    
    strcpy(personagem.hogwartsStudent,hogwartsStudent);
    strcpy(personagem.actorName, actorName);    
    strcpy(personagem.alive,alive);
    strcpy(personagem.dateOfBirth,dateOfBirth);
    personagem.yearOfBirth = yearOfBirth;
    strcpy(personagem.eyeColour, eyeColour);
    strcpy(personagem.gender, gender);
    strcpy(personagem.hairColor, hairColor);
    strcpy(personagem.wizard,wizard);

    return personagem;
}

Personagem constrVazio(){
    Personagem personagem;
    strcpy(personagem.id, "");
    strcpy(personagem.name, "");
    strcpy(personagem.alternate_names.Lista, "");
    strcpy(personagem.house, "");
    strcpy(personagem.ancestry, "");
    strcpy(personagem.patronus, "");
    strcpy(personagem.species, "");
    strcpy(personagem.hogwartsStaff,"");    
    strcpy(personagem.hogwartsStudent,"");
    strcpy(personagem.actorName, "");
    strcpy(personagem.alive,"");
    strcpy(personagem.dateOfBirth,"");
    personagem.yearOfBirth = 0;
    strcpy(personagem.eyeColour, "");
    strcpy(personagem.gender, "");
    strcpy(personagem.hairColor, "");
    strcpy(personagem.wizard,"");

    return personagem;
}


//getters
char* getId(Personagem *personagem){return personagem->id;}
char* getName(Personagem *personagem){return personagem->name;}
char* getAlternate_names(Personagem *personagem){return personagem->alternate_names.Lista;}
char* getHouse(Personagem *personagem){return personagem->house;}
char* getAncestry(Personagem *personagem){return personagem->ancestry;}
char* getSpecies(Personagem *personagem){return personagem->species;}
char* getPatronus(Personagem *personagem){return personagem->patronus;}
char* getHogwartsStaff(Personagem *personagem){return personagem->hogwartsStaff;}
char* getHogwartsStudent(Personagem *personagem){return personagem->hogwartsStudent;}
char* getActorName(Personagem *personagem){return personagem->actorName;}
char* getAlive(Personagem *personagem){return personagem->alive;}
char* getDateOfBirth(Personagem *personagem){return personagem->dateOfBirth;}
int getYearOfBith(Personagem *personagem){return personagem->yearOfBirth;}
char* getEyeColour(Personagem *personagem){return personagem->eyeColour;}
char* getGender(Personagem *personagem){return personagem->gender;}
char* getHairColor(Personagem *personagem){return personagem->hairColor;}
char* getWizard(Personagem *personagem){return personagem->wizard;}

//setters
void setId(char *id, Personagem *personagem){strcpy(personagem->id, id);}
void setName(char *name, Personagem *personagem){strcpy(personagem->name, name);}
void setAlternate_names(char *alternate_names, Personagem *personagem){strcpy(personagem->alternate_names.Lista, alternate_names);}
void setHouse(char *house, Personagem *personagem){strcpy(personagem->house, house);}
void setAncestry(char *species, Personagem *personagem){strcpy(personagem->species, species);}
void setSpecies(char *species, Personagem *personagem){strcpy(personagem->species, species);}
void setPatronus(char *patronus, Personagem *personagem){strcpy(personagem->patronus, patronus);}
void setHogwartsStaff(char *hogwartsStaff, Personagem *personagem){strcpy(personagem->hogwartsStaff,hogwartsStaff);}
void setHogwartsStudent(char * hogwartsStudent, Personagem *personagem){strcpy(personagem->hogwartsStaff,hogwartsStudent);}
void setActorName(char *actorName, Personagem *personagem){strcpy(personagem->actorName, actorName);}
void setAlive(char *alive, Personagem *personagem){strcpy(personagem->hogwartsStaff,alive);}
void setDateOfBirth(char *dateOfBirth, Personagem *personagem){strcpy(personagem->dateOfBirth, dateOfBirth);}
void setYearOfBith(int yearOfBirth, Personagem *personagem){personagem->yearOfBirth = yearOfBirth;}
void setEyeColour(char *eyeColour, Personagem *personagem){strcpy(personagem->eyeColour, eyeColour);}
void setGender(char *gender, Personagem *personagem){strcpy(personagem->gender, gender);}
void setHairColor(char *hairColor, Personagem *personagem){strcpy(personagem->hairColor, hairColor);}
void setWizard(char *wizard, Personagem *personagem){strcpy(personagem->hogwartsStaff,wizard);}

//ler
char **ler(char line[]){
    int tam_line = strlen(line);
    int start = 0;
    int count = 0;
    char **elementos = malloc(18 * sizeof(char*));

    for (int i = 0; i < 18; i++) {
        elementos[i] = malloc(500);
    }


    for(int i = 0; i < tam_line; i++){
        if(line[i] == ';' && line[i-1] == ';'){
            strcpy(elementos[count], "");
            start = i + 1;
            count++;
        } else if (line[i] == ';'){
            strncpy(elementos[count], line + start, i-start);
            elementos[count][i-start] = '\0';
            start = i + 1;
            count++;
        }
    }

    strncpy(elementos[17], line + start, (tam_line-start) - 1);

    return elementos;
}

//imprimir
void imprimir(Personagem *personagem){
    printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %s ## %s]\n",
     personagem->id, personagem->name, personagem->alternate_names.Lista, personagem->house, personagem->ancestry, personagem->species, personagem->patronus,
     personagem->hogwartsStaff, personagem->hogwartsStudent, personagem->actorName, personagem->alive, personagem->dateOfBirth, 
     personagem->yearOfBirth, personagem->eyeColour, personagem->gender, personagem->hairColor, personagem->wizard) ;
}
void removerCaractere(char* parte, char c){
    int i = 0;
    int j = 0;
    while (parte[i] != '\0') {
        if (parte[i] != c) parte[j++] = parte[i];
        i++;
    }   
    parte[j] = '\0';
}
void preencherVetor(Personagem personagens[]){
    FILE *arq;
    char line[1200];
    if((arq = fopen("/tmp/characters.csv", "r")) != NULL){
        
        int i = 0;
        fgets(line,500,arq);
        while( fgets(line,1200,arq) != NULL){
            char **partes = ler(line);            
            removerCaractere(partes[2], '\'');
            for(int i = 0; partes[2][i] != '\0'; i++){
                if(partes[2][i] == '['){
                    partes[2][i] = '{';
                }else if(partes[2][i] == ']'){
                   partes[2][i] = '}';
                }
            }           
            if(strcmp(partes[7],"VERDADEIRO")==0){
                strcpy(partes[7],"true");
            }else{
                strcpy(partes[7],"false");
            }
            if(strcmp(partes[8],"VERDADEIRO")==0){
                strcpy(partes[8],"true");
            }else{
                strcpy(partes[8],"false");
            }
            if(strcmp(partes[10],"VERDADEIRO")==0){
                strcpy(partes[10],"true");
            }else{
                strcpy(partes[10],"false");
            }
            if(strcmp(partes[17],"VERDADEIRO")==0){
                strcpy(partes[17],"true");
            }else{
                strcpy(partes[17],"false");
            }
            personagens[i] = constr(partes[0],partes[1],partes[2],partes[3],partes[4],
            partes[5], partes[6], partes[7], partes[8], partes[9], partes[10],
            partes[12],atoi(partes[13]), partes[14], partes[15], partes[16],partes[17]);
            
            i++;
            free(partes);
        }
        fclose(arq);
    }else {
        printf("Erro ao abrir o arquivo");
    }
    
}

//TIPO CELULA ===================================================================
typedef struct CelulaDupla {
	Personagem elemento;        // Elemento inserido na celula.
	struct CelulaDupla* prox; // Aponta a celula prox.
   struct CelulaDupla* ant;  // Aponta a celula anterior.
} CelulaDupla;

CelulaDupla* novaCelulaDupla(Personagem elemento) {
   CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
   nova->elemento = elemento;
   nova->ant = nova->prox = NULL;
   return nova;
}

//LISTA DUPLA PROPRIAMENTE DITA =======================================================
CelulaDupla* primeiro;
CelulaDupla* ultimo;
int quantElementos;

/**
 * Cria uma lista dupla sem elementos (somente no cabeca).
 */
void startListaDupla () {
   primeiro = novaCelulaDupla(constrVazio());
   ultimo = primeiro;
   quantElementos=0;
}


/**
 * Insere um elemento na primeira posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirInicioListaDupla(Personagem x) {
   CelulaDupla* tmp = novaCelulaDupla(x);

   tmp->ant = primeiro;
   tmp->prox = primeiro->prox;
   primeiro->prox = tmp;
   if (primeiro == ultimo) {                    
      ultimo = tmp;
   } else {
      tmp->prox->ant = tmp;
   }
   tmp = NULL;
}


/**
 * Insere um elemento na ultima posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirFimListaDupla(Personagem x) {
   ultimo->prox = novaCelulaDupla(x);
   ultimo->prox->ant = ultimo;
   ultimo = ultimo->prox;
   quantElementos++;
}

/**
 * Mostra os elementos da lista separados por espacos.
 */
void mostrarListaDupla() {
   CelulaDupla* i;   
   for (i = primeiro->prox; i != NULL; i = i->prox) {
      printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %s ## %s]\n",
     i->elemento.id, i->elemento.name, i->elemento.alternate_names.Lista, i->elemento.house, i->elemento.ancestry, i->elemento.species, i->elemento.patronus,
     i->elemento.hogwartsStaff, i->elemento.hogwartsStudent, i->elemento.actorName, i->elemento.alive, i->elemento.dateOfBirth, 
     i->elemento.yearOfBirth, i->elemento.eyeColour, i->elemento.gender, i->elemento.hairColor, i->elemento.wizard) ;
   }
   
}

CelulaDupla* getUltimoNo(CelulaDupla* i){
    while (i && i -> prox){
        i = i->prox;
    }
    return i;
}

CelulaDupla* part(CelulaDupla* esq, CelulaDupla* dir){
    Personagem pivo = dir->elemento;
    CelulaDupla* i = esq->ant;

    for(CelulaDupla* j = esq;j != dir; j=j->prox){
        Comp++;
        if(strcmp(j->elemento.house,pivo.house)<0){
            i = (i == NULL) ? esq : i -> prox;

            Personagem temp = i->elemento;
            i->elemento=j->elemento;
            j->elemento=temp;
        }else {
            Comp+=2;
            if(strcmp(j->elemento.house,pivo.house)==0&&strcmp(j->elemento.name,pivo.name)<0){
                i = (i == NULL) ? esq : i -> prox;

                Personagem temp = i->elemento;
                i->elemento=j->elemento;
                j->elemento=temp;
            }
        }
    }
    i = (i==NULL) ? esq : i->prox;
    Personagem temp = i->elemento;
    i->elemento = dir->elemento;
    dir->elemento = temp;
    return i;
}

void quickSortRec(CelulaDupla* esq, CelulaDupla* dir){
    if(dir != NULL && esq != dir && esq != dir->prox){
        CelulaDupla* i = part(esq,dir);
        quickSortRec(esq, i->ant);
        quickSortRec(i->prox,dir);
    }
}
void quicksort(){
    CelulaDupla* i = getUltimoNo(primeiro->prox);
    quickSortRec(primeiro->prox,i);
}

//Questao 8
void Questao8(Personagem personagens[]){
    startListaDupla();
    char linha[200];
    scanf(" %s",linha);
    while(strcmp(linha,"FIM")!=0){
        for(int i=0;i<405;i++){
            if(strcmp(personagens[i].id,linha)==0){
                inserirFimListaDupla(personagens[i]);
                i=500;
            }
        }
        scanf(" %s",linha);
    }
    quicksort();
    mostrarListaDupla();
}
int main(){
    startTimer();
    Personagem personagens[405];
    preencherVetor(personagens);
    Questao8(personagens);
    endTimer();
    criaArqLog();
    return 0;
}