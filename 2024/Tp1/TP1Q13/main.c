// por algum motivo a seed 4 nao esta certo
#include <stdio.h>
#include <string.h>
#include <stdlib.h> //para srand funcionar

//tentantiva para gerar os
unsigned long long next = -1;
int nextInt()
{
    next = next * 0x5DEECE66DLL + 0xBLL;
    return (int)(next >> 16) & 0x7FFFFFFF;
}
void setSeed(unsigned long long seed)
{
    next = (seed ^ 0x5DEECE66DLL) & ((1LL << 48) - 1);
}

//alteracao aleatoria
void alteracaoAleatoria(char x[], int i, char a, char b)
{
    if (i <= strlen(x) - 1)
    {
        if (x[i] == a)
        {
            x[i] = b;
        }
        return alteracaoAleatoria(x, ++i, a, b);
    }
    printf("%s", x);
}
int main()
{
    setSeed(4);
    srand(4); // gerador
    char x[499];
    int fim = 0;
    while (fim == 0)
    {
        fgets(x, 499, stdin);
        if (strlen(x) == 4 && x[0] == 'F' && x[1] == 'I' && x[2] == 'M')
        {
            fim = 1;
        }
        else
        {
            //PROBLEMA NO GERADOR
            char a = (char)('a' + (rand() % 26)); // gera a primeira letra aleatoria
            char b = (char)('a' + (rand() % 26)); // gera a segunda letra aleatoria
            // printf("%c,%c ", a, b);                // para teste
            alteracaoAleatoria(x, 0, a, b); // chama alteracaoAleatoria
        }
    }
    return 0;
}