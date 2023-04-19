package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		mergeSort(array, leftIndex, rightIndex);
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

