package br.com.junitsetup.exercicio;

public class VetorUtil implements IVetorUtil {

  /**
   * Copia os valores de {@code a} para as primeiras posições de {@code b}.
   * O vetor de destino pode ser maior que o de origem; as posições
   * excedentes de {@code b} não são alteradas.
   *
   * @throws IllegalArgumentException se algum vetor for nulo ou se {@code b}
   *                                  for menor que {@code a}
   */
  @Override
  public void copiarValores(int[] a, int[] b) {
    validarOrigemDestino(a, b);

    for (int i = 0; i < a.length; i++) {
      b[i] = a[i];
    }
  }

  /**
   * Copia os valores de {@code a} para {@code b} na ordem inversa, ou seja,
   * {@code b[i] = a[a.length - 1 - i]}. O vetor de destino pode ser maior que
   * o de origem; as posições excedentes de {@code b} não são alteradas.
   *
   * @throws IllegalArgumentException se algum vetor for nulo ou se {@code b}
   *                                  for menor que {@code a}
   */
  @Override
  public void copiarValoresInverso(int[] a, int[] b) {
    validarOrigemDestino(a, b);

    for (int i = 0; i < a.length; i++) {
      b[i] = a[a.length - 1 - i];
    }
  }

  /**
   * Soma posição a posição os elementos de {@code a} e {@code b}.
   *
   * @throws IllegalArgumentException se algum vetor for nulo ou se os vetores
   *                                  tiverem tamanhos diferentes
   */
  @Override
  public int[] somarVetores(int[] a, int[] b) {
    validarNaoNulos(a, b);

    if (a.length != b.length) {
      throw new IllegalArgumentException("Os vetores devem ter o mesmo tamanho");
    }

    int[] resultado = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      resultado[i] = a[i] + b[i];
    }
    return resultado;
  }

  /**
   * Intercala os elementos de {@code a} e {@code b} (a0, b0, a1, b1, ...).
   * Quando os tamanhos diferem, os elementos restantes do maior vetor são
   * adicionados ao final, na ordem original.
   *
   * @throws IllegalArgumentException se algum vetor for nulo
   */
  @Override
  public int[] intercalarElementos(int[] a, int[] b) {
    validarNaoNulos(a, b);

    int[] resultado = new int[a.length + b.length];
    int menor = Math.min(a.length, b.length);
    int posicao = 0;

    for (int i = 0; i < menor; i++) {
      resultado[posicao++] = a[i];
      resultado[posicao++] = b[i];
    }

    for (int i = menor; i < a.length; i++) {
      resultado[posicao++] = a[i];
    }

    for (int i = menor; i < b.length; i++) {
      resultado[posicao++] = b[i];
    }

    return resultado;
  }

  private void validarNaoNulos(int[] a, int[] b) {
    if (a == null || b == null) {
      throw new IllegalArgumentException("Os vetores não podem ser nulos");
    }
  }

  private void validarOrigemDestino(int[] a, int[] b) {
    validarNaoNulos(a, b);

    if (b.length < a.length) {
      throw new IllegalArgumentException("O vetor de destino deve ter tamanho maior ou igual ao de origem");
    }
  }
}
