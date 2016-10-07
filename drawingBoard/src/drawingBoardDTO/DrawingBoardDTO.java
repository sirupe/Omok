package drawingBoardDTO;

public class DrawingBoardDTO {
	private int[] position = new int[4];	//x1,x2,y1,y2°ª 
	
	
	public DrawingBoardDTO(int ... position) {
		
	}
	
	public int[] getPosition() {
		return position;
	}

}
