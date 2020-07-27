
import java.util.*;

public class GreedyDynamicAlgorithms {

	/**
	 * Goal: find the smallest number of red intervals to select, such that
	 * every blue interval overlaps with at least one of the selected red intervals.
	 * Output this number
	 * 
	 * @param red - the list of red intervals
	 * @param blue - the list blue intervals
	 * @return
	 */
	public static int optimalIntervals(ArrayList<Interval> red, ArrayList<Interval> blue) {
        // we sort reds and blues
        Interval.sortByFinishTime(red);
        Interval.sortByFinishTime(blue);
        ArrayList<Interval> coverReds = new ArrayList<Interval>();
        // we loop through blue intervals from earliest finish to latest finish
        for(int blue_i = 0; blue_i < blue.size(); blue_i++){
            // test if coverReds cover 
            if(getLatestInterval(coverReds, blue.get(blue_i)) != null){
                continue;
            }
            else {
                // for each blue we look for the latest finish red that covers it
                Interval latestRed = getLatestInterval(red, blue.get(blue_i));
                if(latestRed != null){
                    coverReds.add(latestRed);
                }
                else{
                    return -1;
                }
            }
        }
        return coverReds.size();
	}
    
    public static Interval getLatestInterval(ArrayList<Interval> sortedRed, Interval blue){
        int red_i = sortedRed.size() - 1;
        while(red_i >= 0){
            if(Interval.doIntervalsOverlap(sortedRed.get(red_i), blue)){
                return sortedRed.get(red_i);
            }
            red_i--;
        }
        return null;
    }
	
	/**
	 * Goal: find any path of lowest cost from the top-left of the grid (grid[0][0])
	 * to the bottom right of the grid (grid[m-1][n-1]).  Output this sequence of directions
	 * 
	 * @param grid - the 2d grid containing the cost of each location in the grid.
	 * @return
	 */
	public static List<Direction> optimalGridPath(int[][] grid) {
        // 1. q: greedy or dynamic?
        //    a: dynamic, no greedy choice will work, but does have optimal substructure 
        //       & memoization so dynamic works
        //    
        // 2. q: bottom-up or top-down?
        //    a: there is a choice between top-down or bottom-up. not sure how top-down would 
        //       works so go bottom-up. should be able to re-use a lot of computation 
        //       
        // 3. q: what bottom-up question?
        //    a: what is the lowest cost to get to this square? only two options from above 
        //       or from the left 
        // 
        // 4. q: what is the base-case?
        //    a: you start in the top-left hand corner so that is a start, and then first 
        //       row and column only have one way to get there so just cumulate cost 
        //       and iterate through
        //       
        // 5. q: what is guess at complexity?
        //    a: you will have O(n^2) memory for an additional grid (maybe we can re-use the 
        //       input grid (not sure). and then for each square we need to do one calculation 
        //       i.e. compare top to left of that square so O(n^2) calculations as well 
        //       
        // 6. q: what is the best way to get the path?
        //    a: seem to be two choices 1. store the paths at every entry along with cost, 
        //       problem is that waste a lot of memory storing path 
        //       other approach is to store all costs and then traverse path for lowest cost 
        //       afterwards. 
        
        // get cumulative costs grid
        int[][] cumGrid = getCumulativeGrid(grid);
        
        // traverse grid for lowest cost
        // need to go backwards here 
        List<Direction> listDirections = getGridPath(cumGrid);
        
		return listDirections;
	}
    
    public static int[][] getCumulativeGrid(int[][] grid){
        int num_rows = grid.length;
        int num_cols = grid[0].length;
        int[][] cumGrid = new int[num_rows][num_cols];
        for(int row=0; row < num_rows; row++){
            for(int col=0; col < num_cols; col++){
                int currentVal = grid[row][col];
                // base case: top-left
                if(col==0 && row==0){
                    cumGrid[row][col] = currentVal;
                }
                // base case: first col 
                else if(col==0){
                    cumGrid[row][col] = cumGrid[row-1][col] + currentVal;
                }
                // base case: first row 
                else if(row==0){
                    cumGrid[row][col] = cumGrid[row][col-1] + currentVal;
                }
                // inductive case
                else{
                    // pick lowest of left and above cost
                    cumGrid[row][col] = getLowestOfAboveAndLeftCost(cumGrid, row, col) + currentVal;
                }
            }
        }
        return cumGrid;
    }    
    public static List<Direction> getGridPath(int[][] grid){
        // 1. q: what data structure should we use?
        //    a: we want to insert list of directions in reverse order 
        //       inserting at the beginning is expensive but we now the
        //       length of the path in advance as num_rows - 1 + num_cols - 1
        
        int row = grid.length - 1;
        int col = grid[0].length - 1;
        int path_len = row + col;
        
        List<Direction> path = new LinkedList<Direction>();
        // base case is reach top left hand corner
        while(!(row == 0 && col == 0)){
            //base case reach first col can only have gone DOWN
            if(col == 0){
                path.add(Direction.DOWN);
                row--;
            }
            // base case reach first row can only have gone RIGHT 
            else if(row == 0){
                path.add(Direction.RIGHT);
                col--;
            }
            // if inside somewhere should choose lowest cost 
            else {
                Direction direction = getDirectionOfAboveAndLeftCost(grid, row, col);
                if(direction == Direction.DOWN){
                    row--;
                }
                else{
                    col--;
                }
                path.add(direction);
            }
        }
        Collections.reverse(path);
        return path;
    }
    
    
    public static int getLowestOfAboveAndLeftCost(int[][] cumGrid, int row, int col){
        int above_cost = cumGrid[row-1][col];
        int left_cost = cumGrid[row][col-1];
        if(above_cost<left_cost){
            return above_cost;
        }
        else{
            return left_cost;
        }
    }
    
    public static Direction getDirectionOfAboveAndLeftCost(int[][] grid, int row, int col){
        int above_cost = grid[row-1][col];
        int left_cost = grid[row][col-1];
        if(above_cost<left_cost){
            return Direction.DOWN;
        }
        else{
            return Direction.RIGHT;
        }
    }
	
	/**
	 * A simple Direction enum
	 * directions can be either DOWN or RIGHT
	 * You will output a list of these in the grid-path problem
	 */
	public static enum Direction {
		DOWN, RIGHT
	}

	/**
	 * A private Interval class to help with the interval question
	 */
	public static class Interval {
		
		int start;
		int finish;
        boolean empty=true;
		
		public Interval(int start, int finish) {
			this.start = start;
			this.finish = finish;
            this.empty=false;
            //this.empty = false;
		}
		
        public Interval(){
        }
        
        public static boolean doIntervalsOverlap(Interval red, Interval blue){
            if(red.finish < blue.start ){
                return false;
            }
            else if(blue.finish < red.start){
                return false;
            }
            else{
                return true;
            }
        }
        
        public static boolean sameInterval(Interval red, Interval blue){
            if(red.start == blue.start && red.finish == blue.finish){
                return true;
            }
            return false;
        }
		/**
		 * sorts a list of intervals by start time, you are free to use this on the first question
		 */
		public static void sortByStartTime(ArrayList<Interval> l) {
			Collections.sort(l, new Comparator<Interval>() {
				public int compare(Interval o1, Interval o2) {
					Interval i1 = (Interval) o1;
					Interval i2 = (Interval) o2;
					return i1.start - i2.start;
				}
			});
		}
		
		/**
		 * sorts a list of intervals by finish time, you are free to use this on the first question
		 */
		public static void sortByFinishTime(ArrayList<Interval> l) {
			Collections.sort(l, new Comparator<Interval>() {
				public int compare(Interval o1, Interval o2) {
					Interval i1 = (Interval) o1;
					Interval i2 = (Interval) o2;
					return i1.finish - i2.finish;
				}
			});
		}
	}
	
}
