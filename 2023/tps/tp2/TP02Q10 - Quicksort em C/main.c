#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

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

int compararEstado(const void *a, const void *b)
{
    const Jogador *jogadorA = (const Jogador *)a;
    const Jogador *jogadorB = (const Jogador *)b;

    int estado = strcmp(jogadorA->estadoNascimento, jogadorB->estadoNascimento);

    if (estado == 0)
    {

        return strcmp(jogadorA->nome, jogadorB->nome);
    }

    return estado;
}

int main()
{
    FILE *arquivo = fopen("/tmp/players.csv", "r");

    char l[5000];

    Jogador jogador;

    int numerosJog = 0;

    while (fgets(l, sizeof(l), arquivo) != NULL)
    {
        numerosJog++;
    }

    rewind(arquivo);

    Jogador *vetorJog = (Jogador *)malloc(numerosJog * sizeof(Jogador));

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

    FILE *log = fopen("/tmp/694520_bolha.txt", "w");
    int comp = 0, mov = 0;
    clock_t inicio = clock();

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

    Jogador *vetorOrd = (Jogador *)malloc(numIDs * sizeof(Jogador));

    for (int j = 0; j < numIDs; j++)
    {
        for (i = 0; i < numerosJog; i++)
        {
            if (vetorJog[i].id == ids[j])
            {
                vetorOrd[j] = vetorJog[i];
                break;
            }
        }
    }

    qsort(vetorOrd, numIDs, sizeof(Jogador), compararEstado);

    for (int j = 0; j < numIDs; j++)
    {

        if (strlen(vetorOrd[j].nome) > 0)
        {
            imprimir(vetorOrd[j]);
        }
    }

    free(vetorJog);
    free(vetorOrd);

    clock_t fim = clock();
    double tempoExecucao = (double)(fim - inicio) / CLOCKS_PER_SEC;

    fprintf(log, "694520\t%d\t%d\t%f\n", comp, mov, tempoExecucao);
    fclose(log);

    return 0;
}
