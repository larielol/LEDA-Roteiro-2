package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int pivot = particionar(array, leftIndex, rightIndex);
		sort(array, leftIndex, pivot - 1);
		sort(array, pivot + 1, rightIndex);
	}
	
	private int particionar(T[] array, int leftIndex, int rightIndex) {
		
		T pivot = array[rightIndex - 1];
		int i = rightIndex - 1;
		
		for (int j = i - 1; j >= leftIndex; j--) {
            if (array[j].compareTo(pivot) > 0 || array[j].compareTo(pivot) == 0) {
                i--;
                Util.swap(array, i, j);
            }
        }
		
        Util.swap(array, rightIndex, i);
        return i;
	}
	
	private int media(T[] array, int start, int end) {
		
		int mid = (start + end) / 2;
		
		if(array[start].compareTo(array[end]) > 0) {
			Util.swap(array, start, end);
		}
		
		if(array[start].compareTo(array[mid]) > 0) {
			Util.swap(array, start, mid);
		}
		
		if(array[mid].compareTo(array[end]) > 0) {
			Util.swap(array, start, mid);
		}
		
		return mid;
	}
}
