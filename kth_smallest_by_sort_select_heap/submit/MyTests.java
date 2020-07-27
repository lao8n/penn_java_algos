
import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;

/**
 * This class contains some test cases to help you write your code
 * 
 * You are encouraged to write larger test cases!
 * Note that when you submit, our tests also check that you've implementing the three
 * functions correctly (they make an appropriate number of comparisons)
 */
public class MyTests {

	/**
	 * Helper function for converting an array of ints to an array of our CompareInts
	 */
	private static CompareInt[] convert(int[] arr) {
		CompareInt[] newArr = new CompareInt[arr.length];
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = new CompareInt(arr[i]);
		}
		return newArr;
	}
	
    @Test
	public void KthSmallestTestSampleHeapTest1() {
		CompareInt[] arr = convert(new int[]{4, 1, 3});
		assertEquals(1, KthSmallest.heapImpl(1, arr));
	}
    
	@Test
	public void KthSmallestTestSampleHeapTest2() {
		CompareInt[] arr = convert(new int[]{4, 1, 3});
		assertEquals(3, KthSmallest.heapImpl(2, arr));
	}
	
    @Test
	public void KthSmallestTestSampleHeapTest3() {
		CompareInt[] arr = convert(new int[]{4, 1, 3});
		assertEquals(4, KthSmallest.heapImpl(3, arr));
	}
    
    @Test
	public void KthSmallestTestSampleHeapTest4() {
		CompareInt[] arr = convert(new int[]{4});
		assertEquals(4, KthSmallest.heapImpl(1, arr));
	}
    
    @Test
	public void KthSmallestTestSampleHeapTest5() {
		CompareInt[] arr = convert(new int[]{4, 1});
		assertEquals(1, KthSmallest.heapImpl(1, arr));
	}
    
	@Test
	public void KthSmallestTestSampleMergeTest1() {
		CompareInt[] arr = convert(new int[]{4, 1, 3});
		assertEquals(1, KthSmallest.mergeSortImpl(1, arr));
	}
    
    @Test
	public void KthSmallestTestSampleMergeTest2() {
		CompareInt[] arr = convert(new int[]{4, 1, 3});
		assertEquals(3, KthSmallest.mergeSortImpl(2, arr));
	}
    
    @Test
	public void KthSmallestTestSampleMergeTest3() {
		CompareInt[] arr = convert(new int[]{4, 1, 3});
		assertEquals(4, KthSmallest.mergeSortImpl(3, arr));
	}
    
    @Test
	public void KthSmallestTestSampleQuickSelectTest1() {
		CompareInt[] arr = convert(new int[]{4, 1, 3});
		assertEquals(1, KthSmallest.quickSelectImpl(1, arr));
	}
	
	@Test
	public void KthSmallestTestSampleQuickSelectTest2() {
		CompareInt[] arr = convert(new int[]{4, 1, 3});
		assertEquals(3, KthSmallest.quickSelectImpl(2, arr));
	}
	
    @Test
	public void KthSmallestTestSampleQuickSelectTest3() {
		CompareInt[] arr = convert(new int[]{4, 1, 3});
		assertEquals(4, KthSmallest.quickSelectImpl(3, arr));
	}
}
