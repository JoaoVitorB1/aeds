// teste
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
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
    srand(4);
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
            char a = ('a' + (rand() % 26));
            char b = ('a' + (rand() % 26));   
            //printf("%c,%c\n",a,b);         
            alteracaoAleatoria(x, 0, a, b);
        }
    }
    return 0;
}