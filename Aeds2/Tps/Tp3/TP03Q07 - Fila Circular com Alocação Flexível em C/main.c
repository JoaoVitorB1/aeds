//Questoes finalizadas: 2,4,7
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#define MAXTAM     200
#define MAXTAMFILA 6

typedef struct {
    char Lista[500];
}Lista;

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

//TIPO CELULA 
typedef struct Celula {
	Personagem elemento;
	struct Celula* prox;
} Celula;

Celula* novaCelula(Personagem elemento) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
   return nova;
}

//FILA FLEXIVEL PROPRIAMENTE DITA 
Celula* primeiro;
Celula* ultimo;
int quantElementos;

void startFilaFlexivel () {
   primeiro = novaCelula(constrVazio());
   ultimo = primeiro;
   quantElementos = 0;
}

char* removerFilaFlexivel() {
   if (primeiro == ultimo) {
      printf("Erro ao remover!");
      exit(1);  
   }
   Celula* tmp = primeiro;
   primeiro = primeiro->prox;
   char *resp=primeiro->elemento.name;
   
   tmp->prox = NULL;
   free(tmp);
   tmp = NULL;
   quantElementos--;
   return resp;
}

int getMediaYear(){
    Celula* i;
    int resp=0;
    for(i=primeiro->prox;i!=NULL;i=i->prox){
        resp+=i->elemento.yearOfBirth;
    }
    resp=resp/quantElementos;
    return resp;
}

void inserirFilaFlexivel(Personagem x) {
    char *tmp;
    if(quantElementos==5){
        tmp=removerFilaFlexivel();
    }
    ultimo->prox = novaCelula(x);
    ultimo = ultimo->prox;
    quantElementos++;
    printf(">> Year Birthday Average: %d\n",getMediaYear());
}

void mostrarFilaFlexivel() {
   Celula* i;
   int pos=0;
   printf("[ Head ]\n");
   for (i = primeiro->prox; i != NULL; i = i->prox) {      
      printf("[%d ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %s ## %s]\n", pos,
     i->elemento.id, i->elemento.name, i->elemento.alternate_names.Lista, i->elemento.house, i->elemento.ancestry, i->elemento.species, i->elemento.patronus,
     i->elemento.hogwartsStaff, i->elemento.hogwartsStudent, i->elemento.actorName, i->elemento.alive, i->elemento.dateOfBirth, 
     i->elemento.yearOfBirth, i->elemento.eyeColour, i->elemento.gender, i->elemento.hairColor, i->elemento.wizard) ;
        pos++;
   }
   printf("[ Tail ]\n");
}

//Questao 7
void Questao7(Personagem personagens[]){
    startFilaFlexivel();
    char linha[200];
    int quant=0;
    scanf("%s",linha);
    while(strcmp(linha,"FIM")!=0){
        for(int i=0;i<405;i++){
            if(strcmp(personagens[i].id,linha)==0){
                inserirFilaFlexivel(personagens[i]);
                i=500;
            }
        }
        scanf("%s",linha);
    }
    scanf(" %d",&quant);
    for(int i=0;i<=quant;i++){
        scanf(" %s",linha);        
        if(linha[0]=='R'){
            printf("(R) %s\n",removerFilaFlexivel());
        }else if(linha[0]=='I'){
            scanf(" %s",linha);
            for(int j=0;j<405;j++){
                if(strcmp(personagens[j].id,linha)==0){
                    inserirFilaFlexivel(personagens[j]);
                    j=500;
                }
            }
        }
    }
    mostrarFilaFlexivel();
}
int main(){ 
    Personagem personagens[405];
    preencherVetor(personagens);
    Questao7(personagens);
    return 0;
}