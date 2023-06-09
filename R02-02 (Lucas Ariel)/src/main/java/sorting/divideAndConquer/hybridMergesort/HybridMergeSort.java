package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(rightIndex - leftIndex + 1 <= SIZE_LIMIT) {
			insertionSort(array, leftIndex, rightIndex);
			INSERTIONSORT_APPLICATIONS += 1;
		} else {
			mergeSort(array, leftIndex, rightIndex);
			MERGESORT_APPLICATIONS += 1;
		}
	}
	
	private void insertionSort(T[] array, int leftIndex, int rightIndex) {
		
		int i = leftIndex + 1;
		
		while(i <= rightIndex) {
			int j = i;
			while(j > leftIndex && array[j].compareTo(array[j-1]) < 0) {
				Util.swap(array, j, j-1);
				j--;
			}
			i++;
		}
	}
	
	private void mergeSort(T[] array, int start, int end) {
		int mid = (start + end) / 2;
		mergeSort(array, start, end);
		mergeSort(array, mid + 1, end);
		merge(array, start, mid, end);
	}
	
	private void merge(T[] array, int start, int mid, int end) {
		
		T[] arrayAuxiliar = (T[]) new Comparable[array.length];
		
        for (int i = start; i <= end; i++) {
            arrayAuxiliar[i] = array[i];
        }
        
        int i = start;
        int j = mid + 1;
        int k = start;
        
        while (i <= mid && j <= end) {
            
            if (arrayAuxiliar[i].compareTo(arrayAuxiliar[j]) < 0 || arrayAuxiliar[i].compareTo(arrayAuxiliar[j]) == 0) {
                array[k] = arrayAuxiliar[i];
                i++;
            } else {
                array[k] = arrayAuxiliar[j];
                j++;
            }
            k++;    
        }
        
        while (i <= mid) {
            array[k] = arrayAuxiliar[i];
            i++;
            k++;
        }
        
        while (j <= end) {
            array[k] = arrayAuxiliar[j];
            j++;
            k++;
        }
    }
}
