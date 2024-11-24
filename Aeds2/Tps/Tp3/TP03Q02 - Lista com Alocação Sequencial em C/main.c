//Questoes finalizadas: 2
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

//Lista Sequencial

Personagem arraySequencial[MAXTAM];
int n;

void startListaSequencial(){
    n=0;
}

void inserirInicioListaSequencial(Personagem x){
    int i;

    //validar insercao
    if(n >= MAXTAM){
        printf("Erro ao inserir!");
        exit(1);
    } 

    //levar elementos para o fim do array
    for(i = n; i > 0; i--){
        arraySequencial[i] = arraySequencial[i-1];
    }

    arraySequencial[0] = x;
    n++;
    
}

void inserirFimListaSequencial(Personagem x){
    //validar insercao
    if(n >= MAXTAM){
        printf("Erro ao inserir!");
        exit(1);
    }
    arraySequencial[n] = x;
    n++;
    
}

void inserirListaSequencial(Personagem x, int pos){
    int i;
    //validar insercao
    if(n >= MAXTAM || pos < 0 || pos > n){
        printf("Erro ao inserir!");
        exit(1);
    }

    //levar elementos para o fim do arraySequencial
    for(i = n; i > pos; i--){
        arraySequencial[i] = arraySequencial[i-1];
    }

    arraySequencial[pos] = x;
    n++;
    
}
char* removerInicioListaSequencial() {
    int i;
    static char resp[50];
    //validar remocao
    if (n == 0) {
       printf("Erro ao remover!");
       exit(1);
    }

    strcpy(resp, arraySequencial[0].name);
    n--;

    for(i = 0; i < n; i++){
       arraySequencial[i] = arraySequencial[i+1];
    }

    return resp;
}
char* removerFimListaSequencial() {

    //validar remocao
    if (n == 0) {
       printf("Erro ao remover!");
       exit(1);
    }    
    return arraySequencial[--n].name;
}
char* removerListaSequencial(int pos) {
    int i;
    static char resp[50];
    //validar remocao
    if (n == 0 || pos < 0 || pos >= n) {
       printf("Erro ao remover!");
       exit(1);
    }

    strcpy(resp, arraySequencial[pos].name);
    n--;    

    for(i = pos; i < n; i++){
       arraySequencial[i] = arraySequencial[i+1];
    }
    
    return resp;
}

void mostrarListaSequencial (){
    int i;    

    for(i = 0; i < n; i++){
           printf("[%d ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %s ## %s]\n", i,
     arraySequencial[i].id, arraySequencial[i].name, arraySequencial[i].alternate_names.Lista, arraySequencial[i].house, arraySequencial[i].ancestry, arraySequencial[i].species, arraySequencial[i].patronus,
     arraySequencial[i].hogwartsStaff, arraySequencial[i].hogwartsStudent, arraySequencial[i].actorName, arraySequencial[i].alive, arraySequencial[i].dateOfBirth, 
     arraySequencial[i].yearOfBirth, arraySequencial[i].eyeColour, arraySequencial[i].gender, arraySequencial[i].hairColor, arraySequencial[i].wizard);
    }
    
}

// Questao 2
void Questao2(Personagem personagens[]){
    char id[200];
    int result;
    int quant=0;
    scanf("%s", id);
    startListaSequencial();
    while(strcmp(id, "FIM") != 0){
        for(int i = 0; i < 405; i++){            
            result = strcmp(personagens[i].id,id);            
            if( result == 0){
                inserirFimListaSequencial(personagens[i]);
                i = 500;
            }
        }        
        scanf("%s", id);
    }

    scanf(" %d",&quant);
    char linha[200];    
    
    for(int i=0;i<quant+1;i++){   
        fgets(linha,200,stdin);
        int x=0;
                
        if(linha[0]=='I'){
            char idNovo[34];
            char* token;
            token=strtok(linha," ");            
            if(linha[1]=='I'){
                token=strtok(NULL," ");
                for(int a=0;a<36;a++){
                    idNovo[a]=token[a];
                }                
                while(x<405){
                    result=strcmp(personagens[x].id,idNovo);                    
                    if(result==0){
                        inserirInicioListaSequencial(personagens[x]);
                        x=500;
                    }
                    x++;                    
                }
            }else if(linha[1]=='F'){
                token=strtok(NULL," ");
                for(int a=0;a<36;a++){
                    idNovo[a]=token[a];
                }
                while(x<405){
                    result=strcmp(personagens[x].id,idNovo);                    
                    if(result==0){
                        inserirFimListaSequencial(personagens[x]);
                        x=500;
                    }
                    x++;                    
                } 
            }else if(linha[1]=='*'){
                token=strtok(NULL," ");
                int pos;
                pos = atoi(token);
                token=strtok(NULL," ");
                for(int a=0;a<36;a++){
                    idNovo[a]=token[a];
                }
                while(x<405){
                    result=strcmp(personagens[x].id,idNovo);                    
                    if(result==0){
                        inserirListaSequencial(personagens[x],pos);
                        x=500;
                    }
                    x++;                    
                } 
            }
        }
        else if(linha[0]=='R'){
            if(linha[1]=='I'){
                printf("(R) %s\n",removerInicioListaSequencial());            
            }else if(linha[1]=='F'){
                printf("(R) %s\n",removerFimListaSequencial());
            }else if(linha[1]=='*'){
                char* token;
                token=strtok(linha," ");
                token=strtok(NULL," ");                          
                int pos=atoi(token);            
                printf("(R) %s\n",removerListaSequencial(pos));
            }
        }       
        
    }
    mostrarListaSequencial();
}


int main(){
    
    Personagem personagens[405];
    preencherVetor(personagens);
    Questao2(personagens);    
    return 0;

}