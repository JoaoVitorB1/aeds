//Pronto
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_SIZE 499
int main(){
    char x[MAX_SIZE];
    int fim=0;
    while (fim==0)
    {
        fgets(x,MAX_SIZE,stdin);
        //Verifica se a entrada e fim
        if(strlen(x)==4&&x[0]=='F'&&x[1]=='I'&&x[2]=='M'){
            fim=1;
        }else{
            
            int IsPalindromo=1;

            //Verifica se a string eh igual ate a metade da string
            for(int i=0;i<strlen(x)/2;i++){
                if(x[i]==x[strlen(x)-2-i]){
                    
                }else{
                    IsPalindromo=0;
                    break;
                }
            }
            if(IsPalindromo==1){
                printf("SIM\n");
            }else{
                printf("NAO\n");
            }
        }
        
    }
    return 0;
}