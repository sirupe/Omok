package painter;
class ShapeDTO {
	private int[] pointer = new int[6];
	private int color;
	private boolean fill;
	private int form;
	

	public ShapeDTO(int form, boolean fill, int color, int ... pointer) {
		this.pointer = pointer;
		this.color = color;
		this.fill = fill;
		this.form = form;
	}

	public int[] getPointer() {
		return pointer;
	}

	public int getColor() {
		return color;
	}

	public boolean getFill() {
		return fill;
	}

	public int getForm() {
		return form;
	}
}