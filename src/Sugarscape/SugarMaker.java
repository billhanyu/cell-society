package Sugarscape;


public class SugarMaker {
	static int[] p = new int[]{35, 15};
	static int[] q = new int[]{20, 30};
	static int[][] grid = new int[49][49];
	
	public static void main(String[] args) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				double distanceP = distance(i, j, p[0], p[1]);
				double distanceQ = distance(i, j, q[0], q[1]);
				if (distanceP < 5 || distanceQ < 5) {
					grid[i][j] = 4;
				}
				else if (distanceP < 10 || distanceQ < 10) {
					grid[i][j] = 3;
				}
				else if (distanceP < 20 || distanceQ < 15) {
					grid[i][j] = 2;
				}
				else if (distanceP < 40 || distanceQ < 20) {
					grid[i][j] = 1;
				}
			}
		}
		output();
	}
	
	private static void output() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 4) {
					System.out.println("<full>" + i + " " + j + "</full>");
				}
				else if (grid[i][j] == 3) {
					System.out.println("<three>" + i + " " + j + "</three>");
				}
				else if (grid[i][j] == 2) {
					System.out.println("<two>" + i + " " + j + "</two>");
				}
				else if (grid[i][j] == 1) {
					System.out.println("<one>" + i + " " + j + "</one>");
				}
			}
		}
	}
	
	private static double distance(int x1, int y1, int x2, int y2) {
		return Math.pow(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2), 0.5);
	}
}
