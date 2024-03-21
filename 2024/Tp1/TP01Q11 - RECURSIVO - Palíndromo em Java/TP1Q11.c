// Pronto
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_SIZE 499
int IsPalindromo(char x[], int i)
{    
    if (i < (strlen(x) / 2))
    {
        if (x[i] == x[strlen(x) - 2 - i])
        {
            
            IsPalindromo(x, ++i);
        }
        else
        {
            return 0;
        }
    }
    else
    {
        return 1;
    }
}
int main()
{
    char x[MAX_SIZE];
    int fim = 0;
    while (fim == 0)
    {
        fgets(x, MAX_SIZE, stdin);
        // Verifica se a entrada e fim
        if (strlen(x) == 4 && x[0] == 'F' && x[1] == 'I' && x[2] == 'M')
        {
            return 0;
        }
        else
        {
            if (IsPalindromo(x, 0) == 1)
            {
                printf("SIM\n");
            }
            else
            {
                printf("NAO\n");
            }            
        }
    }
    
}