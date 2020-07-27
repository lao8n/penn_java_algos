import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.*;

import org.junit.Test;

public class MyTests {
	
	/*TODO: Add your own test cases here!
	 * We've provided a sample test case for each problem below
	 * You can use these as building blocks to write your own test cases
	 */
	
	@Test
	public void HuffmanSampleTest() {
		String input = "abc";
		Huffman h = new Huffman(input);
		String encoding = h.encode();
        assertEquals("encoding test", "10011", encoding);
		assertEquals(input, h.decode(encoding));
		assertEquals("huffman abc compression", Huffman.compressionRatio(input), 0.20833, 0.01);
	}
    
    @Test
    public void TestOneDecodeCharacter(){
        String input = "abc";
        Huffman h = new Huffman(input);
        assertEquals("test a", "a", h.decode("10"));
        assertEquals("test b", "b", h.decode("0"));
        assertEquals("test c", "c", h.decode("11"));
    }
    
    @Test
    public void SortByFinishTimeTest1(){
        GreedyDynamicAlgorithms.Interval blue0 = new GreedyDynamicAlgorithms.Interval(0, 6);
        GreedyDynamicAlgorithms.Interval blue1 = new GreedyDynamicAlgorithms.Interval(2, 4);        
        GreedyDynamicAlgorithms.Interval blue2 = new GreedyDynamicAlgorithms.Interval(1, 5);
        ArrayList<GreedyDynamicAlgorithms.Interval> blues = new ArrayList<>();
        blues.add(blue0);
 		blues.add(blue1);
 		blues.add(blue2);
        GreedyDynamicAlgorithms.Interval.sortByFinishTime(blues);
        assertEquals(GreedyDynamicAlgorithms.Interval.sameInterval(blues.get(0), blue1), true);
        assertEquals(GreedyDynamicAlgorithms.Interval.sameInterval(blues.get(1), blue2), true);
        assertEquals(GreedyDynamicAlgorithms.Interval.sameInterval(blues.get(2), blue0), true);
        GreedyDynamicAlgorithms.Interval.sortByStartTime(blues);
        assertEquals(GreedyDynamicAlgorithms.Interval.sameInterval(blues.get(0), blue0), true);
        assertEquals(GreedyDynamicAlgorithms.Interval.sameInterval(blues.get(1), blue2), true);
        assertEquals(GreedyDynamicAlgorithms.Interval.sameInterval(blues.get(2), blue1), true);
    }
    
    @Test 
    public void testOverlap(){
        GreedyDynamicAlgorithms.Interval blue = new GreedyDynamicAlgorithms.Interval(2, 4);
        GreedyDynamicAlgorithms.Interval red0 = new GreedyDynamicAlgorithms.Interval(0, 2);
        GreedyDynamicAlgorithms.Interval red1 = new GreedyDynamicAlgorithms.Interval(3, 5);
        GreedyDynamicAlgorithms.Interval red2 = new GreedyDynamicAlgorithms.Interval(6, 7);
        assertEquals("overlap 1", true, GreedyDynamicAlgorithms.Interval.doIntervalsOverlap(blue, red0));
        assertEquals("overlap 2", true, GreedyDynamicAlgorithms.Interval.doIntervalsOverlap(blue, red1));
        assertEquals("overlap 3", false, GreedyDynamicAlgorithms.Interval.doIntervalsOverlap(blue, red2));        
    }
    
    @Test 
    public void testOverlap2(){
        GreedyDynamicAlgorithms.Interval blue = new GreedyDynamicAlgorithms.Interval(2, 3);
 		GreedyDynamicAlgorithms.Interval red0 = new GreedyDynamicAlgorithms.Interval(5, 7);
        assertEquals("overlap 4", false, GreedyDynamicAlgorithms.Interval.doIntervalsOverlap(red0, blue));
    }
   
    
    @Test
    public void getLatestRedForBlue(){
        GreedyDynamicAlgorithms.Interval blue = new GreedyDynamicAlgorithms.Interval(2, 4);
        ArrayList<GreedyDynamicAlgorithms.Interval> blues = new ArrayList<>();
        blues.add(blue);
 		GreedyDynamicAlgorithms.Interval red0 = new GreedyDynamicAlgorithms.Interval(0, 2);
 		GreedyDynamicAlgorithms.Interval red1 = new GreedyDynamicAlgorithms.Interval(3, 5);
 		GreedyDynamicAlgorithms.Interval red2 = new GreedyDynamicAlgorithms.Interval(6, 7);
        ArrayList<GreedyDynamicAlgorithms.Interval> reds = new ArrayList<>();
        reds.add(red0);
        reds.add(red1);
        reds.add(red2);
        GreedyDynamicAlgorithms.Interval.sortByFinishTime(reds);
        GreedyDynamicAlgorithms.Interval latestRedOverlap = 
            GreedyDynamicAlgorithms.getLatestInterval(reds, blue);
        assertEquals("latest red", red1, latestRedOverlap);
    }
    
    @Test
    public void getLatestRedForBlueNoOverlap(){
        GreedyDynamicAlgorithms.Interval blue = new GreedyDynamicAlgorithms.Interval(2, 3);
        ArrayList<GreedyDynamicAlgorithms.Interval> blues = new ArrayList<>();
        blues.add(blue);
 		GreedyDynamicAlgorithms.Interval red0 = new GreedyDynamicAlgorithms.Interval(5, 7);
 		GreedyDynamicAlgorithms.Interval red1 = new GreedyDynamicAlgorithms.Interval(4, 6);
 		GreedyDynamicAlgorithms.Interval red2 = new GreedyDynamicAlgorithms.Interval(0, 1);
        ArrayList<GreedyDynamicAlgorithms.Interval> reds = new ArrayList<>();
        reds.add(red0);
        reds.add(red1);
        reds.add(red2);
        GreedyDynamicAlgorithms.Interval.sortByFinishTime(reds);
        GreedyDynamicAlgorithms.Interval latestRedOverlap = 
            GreedyDynamicAlgorithms.getLatestInterval(reds, blue);
        assertEquals("null overlap", null, latestRedOverlap);
    }
	
	@Test
	public void IntervalSampleTest1() {
		GreedyDynamicAlgorithms.Interval red = new GreedyDynamicAlgorithms.Interval(1, 3);
		GreedyDynamicAlgorithms.Interval blue = new GreedyDynamicAlgorithms.Interval(0, 4);
		ArrayList<GreedyDynamicAlgorithms.Interval> reds = new ArrayList<>();
		ArrayList<GreedyDynamicAlgorithms.Interval> blues = new ArrayList<>();
		reds.add(red);
		blues.add(blue);
		int expectedOptimal = 1;
		int actualOptimal = GreedyDynamicAlgorithms.optimalIntervals(reds, blues);
		assertEquals("interval 1 red 1 blue", expectedOptimal, actualOptimal);
	}
    
    @Test
	public void IntervalSampleTest2() {
		GreedyDynamicAlgorithms.Interval red1 = new GreedyDynamicAlgorithms.Interval(0, 4);
        GreedyDynamicAlgorithms.Interval red2 = new GreedyDynamicAlgorithms.Interval(2, 5);
		GreedyDynamicAlgorithms.Interval red3 = new GreedyDynamicAlgorithms.Interval(4, 8);
		GreedyDynamicAlgorithms.Interval red4 = new GreedyDynamicAlgorithms.Interval(9, 10);
        GreedyDynamicAlgorithms.Interval red5 = new GreedyDynamicAlgorithms.Interval(9, 11);
        GreedyDynamicAlgorithms.Interval red6 = new GreedyDynamicAlgorithms.Interval(10, 12);
        GreedyDynamicAlgorithms.Interval red7 = new GreedyDynamicAlgorithms.Interval(11, 12);
        GreedyDynamicAlgorithms.Interval blue1 = new GreedyDynamicAlgorithms.Interval(0, 2);
        GreedyDynamicAlgorithms.Interval blue2 = new GreedyDynamicAlgorithms.Interval(5, 5);
        GreedyDynamicAlgorithms.Interval blue3 = new GreedyDynamicAlgorithms.Interval(7, 10);
        GreedyDynamicAlgorithms.Interval blue4 = new GreedyDynamicAlgorithms.Interval(11, 13);
		ArrayList<GreedyDynamicAlgorithms.Interval> reds = new ArrayList<>();
		ArrayList<GreedyDynamicAlgorithms.Interval> blues = new ArrayList<>();
		reds.add(red1);
		reds.add(red2);
		reds.add(red3);
		reds.add(red4);
		reds.add(red5);
		reds.add(red6);
		reds.add(red7);
		blues.add(blue1);
		blues.add(blue2);
		blues.add(blue3);
		blues.add(blue4);
		int expectedOptimal = 2;
		int actualOptimal = GreedyDynamicAlgorithms.optimalIntervals(reds, blues);
		assertEquals("interval 7 red 4 blue", expectedOptimal, actualOptimal);
	}
    
    @Test
	public void IntervalSampleTest3() {
		GreedyDynamicAlgorithms.Interval red1 = new GreedyDynamicAlgorithms.Interval(0, 3);
        GreedyDynamicAlgorithms.Interval red2 = new GreedyDynamicAlgorithms.Interval(2, 4);
        GreedyDynamicAlgorithms.Interval blue1 = new GreedyDynamicAlgorithms.Interval(0, 2);
        GreedyDynamicAlgorithms.Interval blue2 = new GreedyDynamicAlgorithms.Interval(4, 5);
		ArrayList<GreedyDynamicAlgorithms.Interval> reds = new ArrayList<>();
		ArrayList<GreedyDynamicAlgorithms.Interval> blues = new ArrayList<>();
		reds.add(red1);
		reds.add(red2);
		blues.add(blue1);
		blues.add(blue2);
		int expectedOptimal = 1;
		int actualOptimal = GreedyDynamicAlgorithms.optimalIntervals(reds, blues);
		assertEquals("interval 2 red 2 blue", expectedOptimal, actualOptimal);
	}
    
    @Test
	public void IntervalSampleTest4() {
		GreedyDynamicAlgorithms.Interval red1 = new GreedyDynamicAlgorithms.Interval(3, 3);
        GreedyDynamicAlgorithms.Interval red2 = new GreedyDynamicAlgorithms.Interval(4, 6);
        GreedyDynamicAlgorithms.Interval blue1 = new GreedyDynamicAlgorithms.Interval(0, 2);
        GreedyDynamicAlgorithms.Interval blue2 = new GreedyDynamicAlgorithms.Interval(7, 8);
		ArrayList<GreedyDynamicAlgorithms.Interval> reds = new ArrayList<>();
		ArrayList<GreedyDynamicAlgorithms.Interval> blues = new ArrayList<>();
		reds.add(red1);
		reds.add(red2);
		blues.add(blue1);
		blues.add(blue2);
		int expectedOptimal = -1;
		int actualOptimal = GreedyDynamicAlgorithms.optimalIntervals(reds, blues);
		assertEquals("interval 2 red 2 blue", expectedOptimal, actualOptimal);
	}
    
    @Test
    public void TestGetLowest(){
        int[][] cumGrid = new int[2][3];
        cumGrid[0][0] = 2;
        cumGrid[0][1] = 4;
        cumGrid[0][2] = 5;
        cumGrid[1][0] = 6;
        cumGrid[1][1] = 6;
        int lowestCost = GreedyDynamicAlgorithms.getLowestOfAboveAndLeftCost(cumGrid, 1, 2);
        assertEquals("get lowest", 5, lowestCost);
    }
    
    @Test 
    public void GetCumulativeGridTest1(){
        int[][] grid = new int[3][4];
        grid[0][0] = 2;
        grid[0][1] = 2;
        grid[0][2] = 1;
        grid[0][3] = 1;
        grid[1][0] = 4;
        grid[1][1] = 2;
        grid[1][2] = 3;
        grid[1][3] = 5;
        grid[2][0] = 1;
        grid[2][1] = 4;
        grid[2][2] = 2;
        grid[2][3] = 1;
        int[][] cumGrid = GreedyDynamicAlgorithms.getCumulativeGrid(grid);
        assertEquals("getCumulativeGrid3x4[0][0]", 2, cumGrid[0][0]);
        assertEquals("getCumulativeGrid3x4[0][1]", 4, cumGrid[0][1]);
        assertEquals("getCumulativeGrid3x4[0][2]", 5, cumGrid[0][2]);
        assertEquals("getCumulativeGrid3x4[0][3]", 6, cumGrid[0][3]);
        assertEquals("getCumulativeGrid3x4[1][0]", 6, cumGrid[1][0]);
        assertEquals("getCumulativeGrid3x4[1][1]", 6, cumGrid[1][1]);
        assertEquals("getCumulativeGrid3x4[1][2]", 8, cumGrid[1][2]);
        assertEquals("getCumulativeGrid3x4[1][3]", 11, cumGrid[1][3]);
        assertEquals("getCumulativeGrid3x4[2][0]", 7, cumGrid[2][0]);
        assertEquals("getCumulativeGrid3x4[2][1]", 10, cumGrid[2][1]);
        assertEquals("getCumulativeGrid3x4[2][2]", 10, cumGrid[2][2]);
        assertEquals("getCumulativeGrid3x4[2][3]", 11, cumGrid[2][3]);
    }

    @Test 
    public void GetOptimalGridPathTest1(){
        int[][] grid = new int[3][2];
        grid[0][0] = 1;
        grid[0][1] = 3;
        grid[1][0] = 2;
        grid[1][1] = 1;
        grid[2][0] = 4;
        grid[2][1] = 2;
        List<GreedyDynamicAlgorithms.Direction> directions = GreedyDynamicAlgorithms.optimalGridPath(grid);
        assertEquals("optimalGridPath3x2[0]", GreedyDynamicAlgorithms.Direction.DOWN, directions.get(0));
        assertEquals("optimalGridPath3x2[1]", GreedyDynamicAlgorithms.Direction.RIGHT, directions.get(1));
        assertEquals("optimalGridPath3x2[2]", GreedyDynamicAlgorithms.Direction.DOWN, directions.get(2));

    }
    
    @Test 
    public void GetOptimalGridPathTest2(){
        int[][] grid = new int[3][3];
        grid[0][0] = 1;
        grid[0][1] = 4;
        grid[0][2] = 1;
        grid[1][0] = 1;
        grid[1][1] = 3;
        grid[1][2] = 5;
        grid[2][0] = 0;
        grid[2][1] = 2;
        grid[2][2] = 1;
        List<GreedyDynamicAlgorithms.Direction> directions = GreedyDynamicAlgorithms.optimalGridPath(grid);
        assertEquals("optimalGridPath3x3[0]", GreedyDynamicAlgorithms.Direction.DOWN, directions.get(0));
        assertEquals("optimalGridPath3x3[1]", GreedyDynamicAlgorithms.Direction.DOWN, directions.get(1));
        assertEquals("optimalGridPath3x3[2]", GreedyDynamicAlgorithms.Direction.RIGHT, directions.get(2));
        assertEquals("optimalGridPath3x3[3]", GreedyDynamicAlgorithms.Direction.RIGHT, directions.get(3));
    }
    
    @Test 
    public void GetOptimalGridPathTest3(){
        int[][] grid = new int[3][4];
        grid[0][0] = 2;
        grid[0][1] = 2;
        grid[0][2] = 1;
        grid[0][3] = 1;
        grid[1][0] = 4;
        grid[1][1] = 2;
        grid[1][2] = 3;
        grid[1][2] = 5;
        grid[2][0] = 1;
        grid[2][1] = 4;
        grid[2][2] = 2;
        grid[2][3] = 1;
        List<GreedyDynamicAlgorithms.Direction> directions = GreedyDynamicAlgorithms.optimalGridPath(grid);
        assertEquals("optimalGridPath3x4[0]", GreedyDynamicAlgorithms.Direction.RIGHT, directions.get(0));
        assertEquals("optimalGridPath3x4[1]", GreedyDynamicAlgorithms.Direction.RIGHT, directions.get(1));
        assertEquals("optimalGridPath3x4[2]", GreedyDynamicAlgorithms.Direction.RIGHT, directions.get(2));
        assertEquals("optimalGridPath3x4[3]", GreedyDynamicAlgorithms.Direction.DOWN, directions.get(3));
        assertEquals("optimalGridPath3x4[4]", GreedyDynamicAlgorithms.Direction.DOWN, directions.get(4));        
    }
}
