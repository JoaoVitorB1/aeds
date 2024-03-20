#include <stdio.h>
#include <stdlib.h>
#include <string.h>

long getFileSize(FILE *file)
{
    long size;
    // Move o ponteiro do arquivo para o final
    if (fseek(file, 0, SEEK_END) != 0)
    {
        // Erro ao mover o ponteiro
        return -1;
    }
    // Obtém a posição atual do ponteiro, que é o tamanho do arquivo
    size = ftell(file);
    // Move o ponteiro do arquivo de volta para o início
    fseek(file, 0, SEEK_SET);
    return size;
}

int main(int argc, char *argv[])
{
    char linha[300];
    int quant;
    scanf("%d", &quant);
    FILE *p = fopen("questao 9.txt", "w");
    for (int i = 0; i < quant; i++)
    {
        double num;
        scanf("%lf", &num);
        getchar();
        fprintf(p, "%.8g\n", num);
    }
    fclose(p);
    p = fopen("questao 9.txt", "r"); // Reutilizando o ponteiro 'p'
    // Obter o tamanho do arquivo
    fseek(p, 0, SEEK_END);              // Usando o ponteiro 'p'
    long tamanhoArquivo = ftell(p); // Usando o ponteiro 'p'

    // Colocar a posição na última posição do arquivo
    long posicao = tamanhoArquivo;

    // Iterar sobre o arquivo de trás para frente
    FILE *po = fopen("questao 91.txt", "w");
    while (posicao > 0)
    {
        posicao--;
        fseek(p, posicao, SEEK_SET); // Usando o ponteiro 'p'
        char caractere = fgetc(p);   // Usando o ponteiro 'p'

        // Se o caractere for uma quebra de linha ou o início do arquivo, processar a linha
        if (caractere == '\n' || caractere == '\r' || posicao == 0)
        {
            // Inverter a ordem dos caracteres na linha, já que estamos lendo de trás para frente

            for (int i = strlen(linha) - 1; i >= 0; i--)
            {
                printf("%c", linha[i]);
                fprintf(po, "%c", linha[i]);
            }
            // Limpar a linha para a próxima leitura
            memset(linha, 0, sizeof(linha));
            fprintf(po, "\n");
            printf("\n");
        }
        else
        {
            // Adicionar o caractere à linha
            if (caractere == '\0' || caractere == ' ' || caractere == '\n' || caractere == '\r')
            {
            }
            else
            {
                linha[strlen(linha)] = caractere;
                linha[strlen(linha) + 1] = '\0';
            }
        }
        while (fgets(linha, sizeof(linha), "questao 91.txt") != NULL) {
        printf("%s", linha);
    }
    }

    // Fechar o arquivo
    fclose(po);
    fclose(p);
    return 0;
}
