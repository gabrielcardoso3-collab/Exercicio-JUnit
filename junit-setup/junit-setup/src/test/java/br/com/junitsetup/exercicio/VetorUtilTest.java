package br.com.junitsetup.exercicio;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("VetorUtil")
public class VetorUtilTest {

  private IVetorUtil vetorUtil;

  @BeforeEach
  void setUp() {
    vetorUtil = new VetorUtil();
  }

  @Nested
  @DisplayName("copiarValores")
  class CopiarValores {

    @Test
    @DisplayName("copia todos os valores quando os vetores têm o mesmo tamanho")
    void copiaVetoresDeMesmoTamanho() {
      int[] a = { 1, 2, 3, 4 };
      int[] b = new int[4];

      vetorUtil.copiarValores(a, b);

      assertArrayEquals(new int[] { 1, 2, 3, 4 }, b, "b deve ser igual a a");
    }

    @Test
    @DisplayName("não altera o vetor de origem")
    void naoAlteraOrigem() {
      int[] a = { 1, 2, 3 };
      int[] b = new int[3];

      vetorUtil.copiarValores(a, b);

      assertArrayEquals(new int[] { 1, 2, 3 }, a, "a não deve ser modificado");
    }

    @Test
    @DisplayName("sobrescreve valores previamente existentes no destino")
    void sobrescreveDestino() {
      int[] a = { 7, 8, 9 };
      int[] b = { 1, 1, 1 };

      vetorUtil.copiarValores(a, b);

      assertArrayEquals(new int[] { 7, 8, 9 }, b);
    }

    @Test
    @DisplayName("copia para o início do destino quando ele é maior, preservando o restante")
    void destinoMaior() {
      int[] a = { 1, 2 };
      int[] b = { 9, 9, 9, 9 };

      vetorUtil.copiarValores(a, b);

      assertArrayEquals(new int[] { 1, 2, 9, 9 }, b);
    }

    @Test
    @DisplayName("aceita vetores vazios sem alterar nada")
    void vetoresVazios() {
      int[] a = {};
      int[] b = {};

      vetorUtil.copiarValores(a, b);

      assertArrayEquals(new int[] {}, b);
    }

    @Test
    @DisplayName("copia valores negativos e zero")
    void valoresNegativosEZero() {
      int[] a = { -5, 0, 5 };
      int[] b = new int[3];

      vetorUtil.copiarValores(a, b);

      assertArrayEquals(new int[] { -5, 0, 5 }, b);
    }

    @Test
    @DisplayName("lança exceção quando o vetor de origem é nulo")
    void origemNula() {
      assertThrows(IllegalArgumentException.class, () -> vetorUtil.copiarValores(null, new int[3]));
    }

    @Test
    @DisplayName("lança exceção quando o vetor de destino é nulo")
    void destinoNulo() {
      assertThrows(IllegalArgumentException.class, () -> vetorUtil.copiarValores(new int[3], null));
    }

    @Test
    @DisplayName("lança exceção quando o destino é menor que a origem")
    void destinoMenor() {
      assertThrows(IllegalArgumentException.class, () -> vetorUtil.copiarValores(new int[] { 1, 2, 3 }, new int[2]));
    }
  }

  @Nested
  @DisplayName("copiarValoresInverso")
  class CopiarValoresInverso {

    @Test
    @DisplayName("copia os valores na ordem inversa")
    void copiaInverso() {
      int[] a = { 1, 2, 3, 4 };
      int[] b = new int[4];

      vetorUtil.copiarValoresInverso(a, b);

      assertArrayEquals(new int[] { 4, 3, 2, 1 }, b);
    }

    @Test
    @DisplayName("não altera o vetor de origem")
    void naoAlteraOrigem() {
      int[] a = { 1, 2, 3 };
      int[] b = new int[3];

      vetorUtil.copiarValoresInverso(a, b);

      assertArrayEquals(new int[] { 1, 2, 3 }, a, "a não deve ser modificado");
    }

    @Test
    @DisplayName("funciona com quantidade ímpar de elementos")
    void quantidadeImpar() {
      int[] a = { 1, 2, 3 };
      int[] b = new int[3];

      vetorUtil.copiarValoresInverso(a, b);

      assertArrayEquals(new int[] { 3, 2, 1 }, b);
    }

    @Test
    @DisplayName("funciona com um único elemento")
    void umElemento() {
      int[] a = { 42 };
      int[] b = new int[1];

      vetorUtil.copiarValoresInverso(a, b);

      assertArrayEquals(new int[] { 42 }, b);
    }

    @Test
    @DisplayName("copia para o início do destino quando ele é maior, preservando o restante")
    void destinoMaior() {
      int[] a = { 1, 2, 3 };
      int[] b = { 9, 9, 9, 9, 9 };

      vetorUtil.copiarValoresInverso(a, b);

      assertArrayEquals(new int[] { 3, 2, 1, 9, 9 }, b);
    }

    @Test
    @DisplayName("aceita vetores vazios sem alterar nada")
    void vetoresVazios() {
      int[] a = {};
      int[] b = {};

      vetorUtil.copiarValoresInverso(a, b);

      assertArrayEquals(new int[] {}, b);
    }

    @Test
    @DisplayName("lança exceção quando o vetor de origem é nulo")
    void origemNula() {
      assertThrows(IllegalArgumentException.class, () -> vetorUtil.copiarValoresInverso(null, new int[3]));
    }

    @Test
    @DisplayName("lança exceção quando o vetor de destino é nulo")
    void destinoNulo() {
      assertThrows(IllegalArgumentException.class, () -> vetorUtil.copiarValoresInverso(new int[3], null));
    }

    @Test
    @DisplayName("lança exceção quando o destino é menor que a origem")
    void destinoMenor() {
      assertThrows(IllegalArgumentException.class,
          () -> vetorUtil.copiarValoresInverso(new int[] { 1, 2, 3 }, new int[2]));
    }
  }

  @Nested
  @DisplayName("somarVetores")
  class SomarVetores {

    @Test
    @DisplayName("soma os elementos posição a posição")
    void somaBasica() {
      int[] a = { 1, 2, 3 };
      int[] b = { 4, 5, 6 };

      assertArrayEquals(new int[] { 5, 7, 9 }, vetorUtil.somarVetores(a, b));
    }

    @Test
    @DisplayName("retorna um novo vetor e não altera os de entrada")
    void naoAlteraEntradas() {
      int[] a = { 1, 2, 3 };
      int[] b = { 4, 5, 6 };

      int[] resultado = vetorUtil.somarVetores(a, b);

      assertNotSame(a, resultado);
      assertNotSame(b, resultado);
      assertArrayEquals(new int[] { 1, 2, 3 }, a);
      assertArrayEquals(new int[] { 4, 5, 6 }, b);
    }

    @Test
    @DisplayName("soma valores negativos e zero")
    void negativosEZero() {
      int[] a = { -1, 0, 10 };
      int[] b = { 1, 0, -20 };

      assertArrayEquals(new int[] { 0, 0, -10 }, vetorUtil.somarVetores(a, b));
    }

    @Test
    @DisplayName("soma vetores com um único elemento")
    void umElemento() {
      assertArrayEquals(new int[] { 7 }, vetorUtil.somarVetores(new int[] { 3 }, new int[] { 4 }));
    }

    @Test
    @DisplayName("retorna vetor vazio quando ambos são vazios")
    void vetoresVazios() {
      assertArrayEquals(new int[] {}, vetorUtil.somarVetores(new int[] {}, new int[] {}));
    }

    @Test
    @DisplayName("lança exceção quando o primeiro vetor é nulo")
    void primeiroNulo() {
      assertThrows(IllegalArgumentException.class, () -> vetorUtil.somarVetores(null, new int[3]));
    }

    @Test
    @DisplayName("lança exceção quando o segundo vetor é nulo")
    void segundoNulo() {
      assertThrows(IllegalArgumentException.class, () -> vetorUtil.somarVetores(new int[3], null));
    }

    @Test
    @DisplayName("lança exceção quando o primeiro vetor é maior")
    void primeiroMaior() {
      assertThrows(IllegalArgumentException.class, () -> vetorUtil.somarVetores(new int[3], new int[2]));
    }

    @Test
    @DisplayName("lança exceção quando o segundo vetor é maior")
    void segundoMaior() {
      assertThrows(IllegalArgumentException.class, () -> vetorUtil.somarVetores(new int[2], new int[3]));
    }
  }

  @Nested
  @DisplayName("intercalarElementos")
  class IntercalarElementos {

    @Test
    @DisplayName("intercala vetores de mesmo tamanho")
    void mesmoTamanho() {
      int[] a = { 1, 3, 5 };
      int[] b = { 2, 4, 6 };

      assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6 }, vetorUtil.intercalarElementos(a, b));
    }

    @Test
    @DisplayName("adiciona ao final os elementos restantes quando A é maior")
    void aMaior() {
      int[] a = { 1, 3, 5, 7, 9 };
      int[] b = { 2, 4 };

      assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 7, 9 }, vetorUtil.intercalarElementos(a, b));
    }

    @Test
    @DisplayName("adiciona ao final os elementos restantes quando B é maior")
    void bMaior() {
      int[] a = { 1 };
      int[] b = { 2, 4, 6, 8 };

      assertArrayEquals(new int[] { 1, 2, 4, 6, 8 }, vetorUtil.intercalarElementos(a, b));
    }

    @Test
    @DisplayName("retorna cópia de B quando A é vazio")
    void aVazio() {
      assertArrayEquals(new int[] { 1, 2, 3 }, vetorUtil.intercalarElementos(new int[] {}, new int[] { 1, 2, 3 }));
    }

    @Test
    @DisplayName("retorna cópia de A quando B é vazio")
    void bVazio() {
      assertArrayEquals(new int[] { 1, 2, 3 }, vetorUtil.intercalarElementos(new int[] { 1, 2, 3 }, new int[] {}));
    }

    @Test
    @DisplayName("retorna vetor vazio quando ambos são vazios")
    void ambosVazios() {
      assertArrayEquals(new int[] {}, vetorUtil.intercalarElementos(new int[] {}, new int[] {}));
    }

    @Test
    @DisplayName("intercala vetores com um elemento cada")
    void umElementoCada() {
      assertArrayEquals(new int[] { 1, 2 }, vetorUtil.intercalarElementos(new int[] { 1 }, new int[] { 2 }));
    }

    @Test
    @DisplayName("retorna um novo vetor e não altera os de entrada")
    void naoAlteraEntradas() {
      int[] a = { 1, 3 };
      int[] b = { 2, 4 };

      int[] resultado = vetorUtil.intercalarElementos(a, b);

      assertNotSame(a, resultado);
      assertNotSame(b, resultado);
      assertArrayEquals(new int[] { 1, 3 }, a);
      assertArrayEquals(new int[] { 2, 4 }, b);
    }

    @Test
    @DisplayName("lança exceção quando o primeiro vetor é nulo")
    void primeiroNulo() {
      assertThrows(IllegalArgumentException.class, () -> vetorUtil.intercalarElementos(null, new int[3]));
    }

    @Test
    @DisplayName("lança exceção quando o segundo vetor é nulo")
    void segundoNulo() {
      assertThrows(IllegalArgumentException.class, () -> vetorUtil.intercalarElementos(new int[3], null));
    }

    @Test
    @DisplayName("lança exceção quando ambos os vetores são nulos")
    void ambosNulos() {
      assertThrows(IllegalArgumentException.class, () -> vetorUtil.intercalarElementos(null, null));
    }
  }
}
