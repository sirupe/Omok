package frames.gameRoom;

public class StonePositionCheck {	
	// ���� üũ
	public int stoneWidthCheck(int x, int y, int[][] arr) {
		int check = 0;
		int i = 0;
		try {			
			while(arr[x][y - i] == arr[x][y]) {
				check++;
				i++;
			}
			i = 1;
			while(arr[x][y + i] == arr[x][y]) {
				check++;
				i++;
			}
		} catch (Exception e) {}
		return check;
	}
	
	// ���� üũ
	public int stoneHeightCheck(int x, int y, int[][] arr) {
		int check = 0;
		int i = 0;
		try {			
			while(arr[x + i][y] == arr[x][y]) {
				check++;
				i++;
			}
			i = 1;
			while(arr[x - i][y] == arr[x][y]) {
				check++;
				i++;
			}
		} catch (Exception e) {
		}
		return check;
	}
	
	// �밢�� / üũ
	public int stoneDiagonalRightCheck(int x, int y, int[][] arr) {
		int check = 0;
		int i = 0;
		
		try {			
			while(arr[x + i][y - i] == arr[x][y]) {
				check++;
				i++;
			}
			i = 1;
			while(arr[x - i][y + i] == arr[x][y]) {
				check++;
				i++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
		
		return check;
	}
	
	// �밢�� \ üũ
	public int stoneDiagonalLeftCheck(int x, int y, int[][] arr) {
		int check = 0;
		int i = 0;
		
		try {			
			while(arr[x - i][y - i] == arr[x][y]) {
				check++;
				i++;
			}
			i = 1;
			while(arr[x + i][y + i] == arr[x][y]) {
				check++;
				i++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
		
		return check;
	}
}
