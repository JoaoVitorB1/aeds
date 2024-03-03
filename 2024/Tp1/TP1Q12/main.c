//pronto
#include <stdio.h>
#include <string.h>
#define MAX_SIZE 499
void Ciframento(char x[],char y[],int i)
{
    if(i<strlen(x)){
        y[i]=(char)(x[i]+3);
        return Ciframento(x,y,++i);
    }
    printf("%s\n",y);
}
int main()
{
    char x[MAX_SIZE];
    int fim = 0;
    while (fim == 0)
    {
        fgets(x, MAX_SIZE, stdin);
        if (strlen(x) == 4 && x[0] == 'F' && x[1] == 'I' && x[2] == 'M')
        {
            return 0;
        }
        else
        {
            char y[strlen(x)];
            Ciframento(x,y,0);
        }
    }    
}