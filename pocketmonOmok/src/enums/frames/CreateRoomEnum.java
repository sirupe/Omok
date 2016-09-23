package enums.frames;

import java.awt.Dimension;
import java.awt.Rectangle;

public class CreateRoomEnum {
	
	
	
	private Rectangle rect;
	private int size;
	private Dimension dimension;
	
	
	private CreateRoomEnum() {}
	
	
	//set
	private CreateRoomEnum(int x) {
		   this.size = x;
	}
	private CreateRoomEnum(Rectangle rect) {
		this.rect = rect;
	}
	private CreateRoomEnum(Dimension dimension) {
		  this.dimension = dimension;
	}

	//get
	public int getSize() {
		return size;
	}
	public Rectangle getRect() {
		return rect;
	}
	public Dimension getDimension() {
		  return dimension;
	}

	

}
