#include <stdio.h>

int main()
{
    int quantPilhas = 0;
    int nCaixas = 0;
    int fim = 0;
    int aux = 0;

    scanf(" %d", &nCaixas);
    scanf(" %d", &quantPilhas);
    while (fim == 0)
    {
        int cont = 0;
        int achou = 0;
        int pos = 0;
        for (int i = 0; i < quantPilhas; i++) // olhando cada pilha
        {
            scanf(" %d", &aux); // numero de caixas na pilha
            for (int j = 0; j < aux; j++)
            { // olhando as pilhas
                int tmpCaixa = 0;
                scanf(" %d", &tmpCaixa);
                if (tmpCaixa == 1) // ao encontrar a caixa 1, guarda a posicao da caixa
                {
                    achou = 1;
                    pos = j;
                }
                else if (achou == 1 && j >= pos) // depois de encontrar a caixa 1 verifica se a posicao da outra caixa esta na mesma altura da caixa 1 ou acima
                // se estiver, joazinho vai ter que desempilhar +1 caixa;
                {
                    cont++;
                }
            }
        }
        printf("%d\n", cont);
        scanf(" %d", &nCaixas);
        scanf(" %d", &quantPilhas);
        if (nCaixas == 0 && quantPilhas == 0)
        {
            fim = 1;
        }
    }
    return 0;
}