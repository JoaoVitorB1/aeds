#include <stdbool.h>
#include <stdio.h>
#include <string.h>

int main(void) {
  char x[400];
  bool fim = false;
  bool palindromo = true;
  while (fim == false) {
    fgets(x, 400, stdin);
    int tam = strlen(x) - 1;
    if (tam == 3 && x[0] == 'F' && x[1] == 'I' && x[2] == 'M') {
      fim = true;
    } else {
      //verifica se é palindromo
      for (int i = 0, j = strlen(x) - 2; i < j; i++, j--) {
        if (x[i] != x[j]) {
          palindromo = false;
          break;
        }
      }
      // resultado do teste palindromo
      if (palindromo == true) {
        printf("SIM\n");
      } else {
        printf("NAO\n");
      }
      palindromo = true;
    }
  }
}