#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Jogador
{
    int id;
    char nome[100];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[100];
    char estadoNascimento[100];
};

void lerJogador(struct Jogador *jogador, char linha[])
{
    char *token = strtok(linha, ",");
    jogador->id = atoi(token);

    token = strtok(NULL, ",");
    strcpy(jogador->nome, token);

    token = strtok(NULL, ",");
    jogador->altura = atoi(token);

    token = strtok(NULL, ",");
    jogador->peso = atoi(token);

    token = strtok(NULL, ",");
    strcpy(jogador->universidade, token);

    token = strtok(NULL, ",");
    jogador->anoNascimento = atoi(token);

    token = strtok(NULL, ",");
    strcpy(jogador->cidadeNascimento, token);

    token = strtok(NULL, ",");
    strcpy(jogador->estadoNascimento, token);
}

int main()
{
    FILE *file = fopen("/tmp/players.csv", "r");

    char linha[1024];
    struct Jogador jogadores[10000];
    int contador = 0;

    fgets(linha, sizeof(linha), file); // lê a primeira linha (cabeçalho)

    while (fgets(linha, sizeof(linha), file))
    {
        lerJogador(&jogadores[contador], linha);
        contador++;
    }

    fclose(file);

    char entrada[100];
    while (1)
    {
        printf("Digite o ID do jogador ou 'FIM' para sair: ");
        scanf("%s", entrada);

        if (strcmp(entrada, "FIM") == 0)
        { 
            break;
        }
        else
        {
            int id = atoi(entrada);
            int encontrado = 0;
            for (int i = 0; i < contador; i++)
            {
                if (jogadores[i].id == id)
                {
                    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
                           jogadores[i].id, jogadores[i].nome, jogadores[i].altura,
                           jogadores[i].peso, jogadores[i].anoNascimento,
                           jogadores[i].universidade, jogadores[i].cidadeNascimento,
                           jogadores[i].estadoNascimento);
                    encontrado = 1;
                    break;
                }
            }

            if (!encontrado)
            {
                printf("Jogador com ID %d não encontrado.\n", id);
            }
        }
    }

    return 0;
}
