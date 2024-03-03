#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>

typedef struct
{
    int id;
    char nome[100];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[100];
    char estadoNascimento[100];
} Jogador;

void imprimir(Jogador jogador)
{
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           jogador.id, jogador.nome, jogador.altura, jogador.peso,
           jogador.anoNascimento, jogador.universidade,
           jogador.cidadeNascimento, jogador.estadoNascimento);
}

bool pesquisaBinaria(Jogador *vetor, int n, int id)
{
    int esquerda = 0;
    int direita = n - 1;

    while (esquerda <= direita)
    {
        int meio = esquerda + (direita - esquerda) / 2;

        if (vetor[meio].id == id)
        {

            return true;
        }
        else if (vetor[meio].id < id)
        {
            esquerda = meio + 1;
        }
        else
        {
            direita = meio - 1;
        }
    }

    return false;
}

int main()
{
    FILE *arquivo = fopen("/tmp/players.csv", "r");

    char l[4000];

    Jogador jogador;

    int numJog = 0;

    while (fgets(l, sizeof(l), arquivo) != NULL)
    {
        numJog++;
    }

    rewind(arquivo);

    Jogador *vetorJog = (Jogador *)malloc(numJog * sizeof(Jogador));

    int i = 0;

    while (fgets(l, sizeof(l), arquivo) != NULL)
    {
        strcpy(vetorJog[i].universidade, "nao informado");
        strcpy(vetorJog[i].cidadeNascimento, "nao informado");
        strcpy(vetorJog[i].estadoNascimento, "nao informado");
        sscanf(l, "%d,%99[^,],%d,%d,%99[^,],%d,%99[^,],%99[^,\n]",
               &vetorJog[i].id, vetorJog[i].nome, &vetorJog[i].altura, &vetorJog[i].peso,
               vetorJog[i].universidade, &vetorJog[i].anoNascimento,
               vetorJog[i].cidadeNascimento, vetorJog[i].estadoNascimento);

        i++;
    }

    fclose(arquivo);

    FILE *log = fopen("694520_binaria.txt", "w");
    int comp = 0;

    int ids[4000];
    int numIDs = 0;

    while (1)
    {
        char resp[10];
        scanf("%s", resp);

        if (strcmp(resp, "FIM") == 0)
            break;

        ids[numIDs] = atoi(resp);
        numIDs++;
    }

    while (1)
    {
        int id;
        scanf("%d", &id);

        if (id == -1)
            break;

        bool resultado = pesquisaBinaria(vetorJog, numJog, id);

        if (resultado==true)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
    }

    fprintf(log, "694520\t%f\t%d\n", comp);
    fclose(log);

    free(vetorJog);

    return 0;
}
