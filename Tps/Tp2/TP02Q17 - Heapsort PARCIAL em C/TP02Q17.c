#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>


clock_t startTime;
clock_t endTime;
int Comp = 0;

void startTimer() {startTime = clock();}
void endTimer() {endTime = clock();}

void criaArqLog() {
    FILE* arqLog;
    arqLog = fopen("matricula_heapsort.txt", "w");

    if (arqLog == NULL) {
        printf("Erro ao criar o arquivo de log\n");
        return;
    }
    int totalTime = ((double)(endTime - startTime)) / CLOCKS_PER_SEC;
    fprintf(arqLog, "694520\t%.2f\t%d\n",totalTime, Comp);
    fclose(arqLog);
}

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
    bool hogwartsStaff;
    bool hogwartsStudent;
    char actorName[200];
    bool alive;
    char dateOfBirth[200];
    int yearOfBirth;
    char eyeColour[200];
    char gender[200];
    char hairColor[200];
    bool wizard;
    
}Personagem;

Personagem constr(char id[], char name[], char alternate_names[], char house[], char ancestry[], char species[], char patronus[], bool hogwartsStaff, bool hogwartsStudent, char actorName[], bool alive, char dateOfBirth[],
int yearOfBirth, char eyeColour[], char gender[], char hairColor[], bool wizard){
    Personagem personagem;

    strcpy(personagem.id, id);
    strcpy(personagem.name, name);
    strcpy(personagem.alternate_names.Lista, alternate_names);
    strcpy(personagem.house, house);
    strcpy(personagem.ancestry, ancestry);
    strcpy(personagem.patronus, patronus);
    strcpy(personagem.species, species);
    personagem.hogwartsStaff = hogwartsStaff;
    personagem.hogwartsStudent = hogwartsStudent;
    strcpy(personagem.actorName, actorName);
    personagem.alive = alive;
    strcpy(personagem.dateOfBirth,dateOfBirth);
    personagem.yearOfBirth = yearOfBirth;
    strcpy(personagem.eyeColour, eyeColour);
    strcpy(personagem.gender, gender);
    strcpy(personagem.hairColor, hairColor);
    personagem.wizard = wizard;

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
    personagem.hogwartsStaff = 0;
    personagem.hogwartsStudent = 0;
    strcpy(personagem.actorName, "");
    personagem.alive = 0;
    strcpy(personagem.dateOfBirth,"");
    personagem.yearOfBirth = 0;
    strcpy(personagem.eyeColour, "");
    strcpy(personagem.gender, "");
    strcpy(personagem.hairColor, "");
    personagem.wizard = 0;

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
char* getHogwartsStaff(Personagem *personagem){int value = personagem->hogwartsStaff;return value == 1? "true": "false";}
char* getHogwartsStudent(Personagem *personagem){int value = personagem->hogwartsStudent;return value == 1? "true": "false";}
char* getActorName(Personagem *personagem){return personagem->actorName;}
char* getAlive(Personagem *personagem){int value = personagem->alive;return value == 1? "true": "false";}
char* getDateOfBirth(Personagem *personagem){return personagem->dateOfBirth;}
int getYearOfBith(Personagem *personagem){return personagem->yearOfBirth;}
char* getEyeColour(Personagem *personagem){return personagem->eyeColour;}
char* getGender(Personagem *personagem){return personagem->gender;}
char* getHairColor(Personagem *personagem){return personagem->hairColor;}
char* getWizard(Personagem *personagem){int value = personagem->wizard;return value == 1? "true": "false";}

//setters
void setId(char *id, Personagem *personagem){strcpy(personagem->id, id);}
void setName(char *name, Personagem *personagem){strcpy(personagem->name, name);}
void setAlternate_names(char *alternate_names, Personagem *personagem){strcpy(personagem->alternate_names.Lista, alternate_names);}
void setHouse(char *house, Personagem *personagem){strcpy(personagem->house, house);}
void setAncestry(char *species, Personagem *personagem){strcpy(personagem->species, species);}
void setSpecies(char *species, Personagem *personagem){strcpy(personagem->species, species);}
void setPatronus(char *patronus, Personagem *personagem){strcpy(personagem->patronus, patronus);}
void setHogwartsStaff(char *hogwartsStaff, Personagem *personagem){
    if(strcmp(hogwartsStaff, "true") == 0){
        personagem->hogwartsStaff = 1;    
    }else {
        personagem->hogwartsStaff = 0;
    }
}
void setHogwartsStudent(char * hogwartsStudent, Personagem *personagem){
      if(strcmp(hogwartsStudent, "true") == 0){
        personagem->hogwartsStaff = 1;    
    }else {
        personagem->hogwartsStaff = 0;
    }
}
void setActorName(char *actorName, Personagem *personagem){strcpy(personagem->actorName, actorName);}
void setAlive(char *alive, Personagem *personagem){
    if(strcmp(alive, "true") == 0){
        personagem->alive = 1;    
    }else {
        personagem->alive = 0;
    }
}
void setDateOfBirth(char *dateOfBirth, Personagem *personagem){strcpy(personagem->dateOfBirth, dateOfBirth);}
void setYearOfBith(int yearOfBirth, Personagem *personagem){personagem->yearOfBirth = yearOfBirth;}
void setEyeColour(char *eyeColour, Personagem *personagem){strcpy(personagem->eyeColour, eyeColour);}
void setGender(char *gender, Personagem *personagem){strcpy(personagem->gender, gender);}
void setHairColor(char *hairColor, Personagem *personagem){strcpy(personagem->hairColor, hairColor);}
void setWizard(char *wizard, Personagem *personagem){
    if(strcmp(wizard, "true") == 0){
        personagem->wizard = 1;    
    }else {
        personagem->wizard = 0;
    }
}

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
     personagem->hogwartsStaff == 1? "true": "false", personagem->hogwartsStudent == 1? "true": "false", personagem->actorName, personagem->alive == 1? "true": "false", personagem->dateOfBirth, 
     personagem->yearOfBirth, personagem->eyeColour, personagem->gender, personagem->hairColor, personagem->wizard == 1? "true": "false") ;
}

// Remover o char 'c' da string
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

            personagens[i] = constr(partes[0],partes[1],partes[2],partes[3],partes[4],
            partes[5], partes[6], strcmp(partes[7], "VERDADEIRO") == 0? true: false, strcmp(partes[8], "VERDADEIRO") == 0? true: false, partes[9], partes[10],
            partes[12],atoi(partes[13]), partes[14], partes[15], partes[16], strcmp(partes[17], "VERDADEIRO") == 0? true: false);
            
            i++;
            free(partes);
        }
        fclose(arq);
    }else {
        printf("Erro ao abrir o arquivo");
    }
    
}



void construir(Personagem *vetorTmp,int tam){
    for(int i=tam;i>1;i/=2){
        Comp++;
        if(strcmp(vetorTmp[i].hairColor,vetorTmp[i/2].hairColor)>0){
            Personagem temp=vetorTmp[i];
            vetorTmp[i]=vetorTmp[i/2];
            vetorTmp[i/2]=temp;
        }else{
            Comp+=2;
            if((strcmp(vetorTmp[i].hairColor,vetorTmp[i/2].hairColor)==0)&&(strcmp(vetorTmp[i].name,vetorTmp[i/2].name)>0)){
                Personagem temp=vetorTmp[i];
                vetorTmp[i]=vetorTmp[i/2];
                vetorTmp[i/2]=temp;
            }
        } 
    }
}

int getMaiorFilho(Personagem *vetorTmp,int i,int tam){
    int filho;
    Comp++;
    if(2*i==tam||strcmp(vetorTmp[2*i].hairColor,vetorTmp[2*i+1].hairColor)>0){
        filho=2*i;
    }else{
        Comp+=2;
        if(2*i==tam||((strcmp(vetorTmp[2*i].hairColor,vetorTmp[2*i+1].hairColor)==0)&&strcmp(vetorTmp[2*i].name,vetorTmp[2*i+1].name)>0)){
            filho=2*i;
        }else{
            filho=2*i+1;
        }
    } 
}

void reconstruir(Personagem *vetorTmp,int tam){
    int i=1;
    while(i<=(tam/2)){
        int filho=getMaiorFilho(vetorTmp,i,tam);
            if(strcmp(vetorTmp[i].hairColor,vetorTmp[filho].hairColor)<0){
                Personagem temp=vetorTmp[i];
                vetorTmp[i]=vetorTmp[filho];
                vetorTmp[filho]=temp;
                i=filho;         
            }else if((strcmp(vetorTmp[i].hairColor,vetorTmp[filho].hairColor)==0)&&(strcmp(vetorTmp[i].name,vetorTmp[filho].name)<0)){
                Personagem temp=vetorTmp[i];
                vetorTmp[i]=vetorTmp[filho];
                vetorTmp[filho]=temp;
                i=filho;         
            }else{
                i=tam;
            }
    }
    
}

void heapsort(Personagem *vetor,int n){
    //Alterar o vetor ignorando a posicao zero
    Personagem vetorTmp[n+1];
    for(int i=0;i<n;i++){
        vetorTmp[i+1]=vetor[i];
    }

    //Construcao do heap
    for(int tam=2;tam<=n;tam++){
        construir(vetorTmp,tam);
    }

    //Ordenacao propriamente dita
    int tam=n;
    while(tam>1){
        Personagem temp=vetorTmp[1];
        vetorTmp[1]=vetorTmp[tam];
        vetorTmp[tam]=temp;
        tam--;
        reconstruir(vetorTmp,tam);
    }
    for(int i=0;i<n;i++){
        vetor[i]=vetorTmp[i+1];
    }
}
int main() {
  startTimer();
  bool fim = false;
  char id[500];
  
  Personagem personagens[405];
  preencherVetor(personagens);
  Personagem vetor[27];

    char aux[500];
    int j=0;
    scanf(" %s",aux);
    while(strcmp(aux,"FIM")){
        int index=0;
        for(int i=0;i<=405;i++){
            if(strcmp(personagens[i].id,aux)==0){
                index=i;
                break;
            }
        }
        vetor[j++]=personagens[index];
        scanf(" %s",aux);
        
    }
    int n=sizeof(vetor)/sizeof(vetor[0]);
    heapsort(vetor,n);
    for(int x=0;x<10;x++){
        imprimir(&vetor[x]);
    }

    endTimer();
    criaArqLog();

  return 0;
}