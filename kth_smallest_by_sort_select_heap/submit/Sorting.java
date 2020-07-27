import java.util.Arrays;

public class Sorting {
	
	/**
	 * Implement the mergesort function, which should sort the array of
	 * integers in place
	 * 
	 * You will probably want to use helper functions here, as described in the lecture recordings
	 * (ex. merge(), a helper mergesort function)
	 * @param arr
	 */
	public static void mergeSort(CompareInt[] arr) {
        treeAndMerge(arr, 0, arr.length);
	}
    
    public static void treeAndMerge(CompareInt[] arr, int leftIndex, int rightIndex){
        // base case we have an array of length 1
        if(rightIndex - leftIndex <= 1){
            return;
        }
        // arr is longer than 1 element so break in half
        else {
            int midPoint = (leftIndex + rightIndex)/ 2;
            treeAndMerge(arr, leftIndex, midPoint);
            treeAndMerge(arr, midPoint, rightIndex);
            merge(arr, leftIndex, midPoint, rightIndex);
        }
    }
    
    public static void merge(CompareInt[] arr, int leftIndex, int midPoint, int rightEnd){
        // copy left and right parts into two separate arrays
        CompareInt[] tempLeftArr = getTempArr(arr, leftIndex, midPoint);
        CompareInt[] tempRightArr = getTempArr(arr, midPoint, rightEnd);   
        
        // for the shared length of array we iterate through comparing and copying
        int tempLeftIndex = 0;
        int tempRightIndex = 0;
        int mergeIndex = leftIndex;
        // merging arrays where both overlap
        while(tempLeftIndex < tempLeftArr.length && tempRightIndex < tempRightArr.length){
            CompareInt tempLeftValue = tempLeftArr[tempLeftIndex];
            CompareInt tempRightValue = tempRightArr[tempRightIndex];
            int comparison = tempLeftValue.compareTo(tempRightValue);
            // leftArr value smaller
            if(comparison < 0){
                // add leftArr value
                arr[mergeIndex++] = tempLeftArr[tempLeftIndex++];
            }
            // rightArr value smaller
            else if (comparison > 0){
                arr[mergeIndex++] = tempRightArr[tempRightIndex++];
            }
            // same value
            else {
                arr[mergeIndex++] = tempLeftArr[tempLeftIndex++];
                arr[mergeIndex++] = tempRightArr[tempRightIndex++];
            }
        }
        while(tempLeftIndex < tempLeftArr.length){
            arr[mergeIndex++] = tempLeftArr[tempLeftIndex++];
        }
        while(tempRightIndex < tempRightArr.length){
            arr[mergeIndex++] = tempRightArr[tempRightIndex++];
        }
    }
	
	/**
	 * Implement the quickSelect
	 * 
	 * Again, you will probably want to use helper functions here
	 * (ex. partition(), a helper quickselect function)
	 */
	public static CompareInt quickSelect(int k, CompareInt[] arr) {
        // kth smallest relates to k - 1 index because start at 0
		return treeAndSearch(k - 1, arr, 0, arr.length);
	}
    
    public static CompareInt treeAndSearch(int p, CompareInt[] arr, int leftIndex, int rightIndex){
        // base case we have an array of length 1
        if(leftIndex == rightIndex){
            return arr[leftIndex];
        }
        // all the values to the left of the pivotIndex are smaller
        // all the values to the right of the pivotIndex are bigger
        int pivotIndex = partition(arr, leftIndex, rightIndex);
        
        // we have found the 'kth' smallest at the pth (k-1) position
        if(pivotIndex == p){
            return arr[p];
        }
        // the pivotIndex is larger, e.g. we have found the 5th smallest and the 
        // four smaller than it but we are looking for say the 3rd smallest
        // therefore we look on the left hand side but can exclude the pivotIndex
        else if(pivotIndex > p){
            return treeAndSearch(p, arr, leftIndex, pivotIndex);
        }
        // in this case the pivotIndex is smaller so we search the right-hand side
        // knowing that it cannot be in the pivotIndex position otherwise we 
        // would have returned that 
        else{
            return treeAndSearch(p, arr, pivotIndex + 1, rightIndex);
        }
    }
    
    
    public static int partition(CompareInt[] arr, int leftIndex, int rightIndex){
        // to simplify we always pivot on whatever value is in the leftIndex
        CompareInt pivotValue = arr[leftIndex];
       
        // we want to go through and partition values so that those bigger 
        // than the pivot are to the right and those smaller than the pivotValue
        // are to the left 
        // we get a temporary array which are all values except the pivot value 
        CompareInt[] tempArr = getTempArr(arr, leftIndex + 1, rightIndex);
        // we loop through these values 
        for(int tempIndex = 0; tempIndex < tempArr.length; tempIndex++){
            // assigning them either to the left hand side of the arr
            // where we increment leftIndex after assignment
            if(tempArr[tempIndex].compareTo(pivotValue) <= 0){
                arr[leftIndex++] = tempArr[tempIndex];
            }
            // or the right hand side of the array
            // where we decrement rightIndex before assignment as rightIndex is exclusive
            else {
                arr[--rightIndex] = tempArr[tempIndex];
            }
        }
        // we then copy the pivotValue back into the original arr at the right location
        arr[leftIndex] = pivotValue;
        return leftIndex;
    }

    private static CompareInt[] getTempArr(CompareInt[] arr, int leftIndex, int rightIndex){
        int tempLength = rightIndex - leftIndex;
        CompareInt[] tempArr = new CompareInt[tempLength];
        System.arraycopy(arr, leftIndex, tempArr, 0, tempLength);
        return tempArr;
    }
    

}
