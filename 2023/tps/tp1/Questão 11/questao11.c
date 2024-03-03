#include <stdbool.h>
#include <stdio.h>
#include <string.h>
//função para testar se é palindromo
bool isPalindromo(char x[], int j, int y) {
  if (y <= j) {
    if (x[y] == x[j]) {
      return isPalindromo(x, j--, y++);
    } else {
      return false;
    }
  }
  return true;
}

int main(void) {
  char x[400];
  bool fim = false;
  bool palindromo = true;
  int y = 0, teste;
  while (fim == false) {
    fgets(x, 400, stdin);
    int tam = strlen(x) - 1;
    if (tam == 3 && x[0] == 'F' && x[1] == 'I' && x[2] == 'M') {
      fim = true;
    } else {
      teste = strlen(x) - 1;
      // chama a função para ver se é palindromo
      palindromo = isPalindromo(x, teste, y);
      if (palindromo == true) {
        printf("SIM\n");
      } else {
        printf("NAO\n");
      }
      palindromo = true;
    }
  }
}