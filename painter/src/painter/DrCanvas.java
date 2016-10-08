package painter;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
class DrCanvas extends Canvas {
	private MsPaint painter;
	private Image bufferImg;
	private Graphics bufferG;
	private ArrayList<ShapeDTO> list = new ArrayList<ShapeDTO>();
	private boolean draggChecker;
	private int x1, y1, x2, y2, z1, z2, form, color;
	private boolean fill;

	public DrCanvas(MsPaint painter) {
		this.painter = painter;
		setBackground(Color.WHITE);
		event();
	}

	public void event() {
		MouseAdapter ma = new MouseAdapter() { 
			public void mousePressed(MouseEvent e) {
				x1 = e.getX();
				y1 = e.getY();
				painter.getX1T().setText(e.getX() + "");
				painter.getY1T().setText(e.getY() + "");
			}
			public void mouseDragged(MouseEvent e) {
				draggChecker = true;
				x2 = e.getX();
				y2 = e.getY();
				painter.getX2T().setText(e.getX() + "");
				painter.getY2T().setText(e.getY() + "");
				// repaint();
				if(painter.getPen().isSelected()) {
					saveShape();
					x1 = x2;
					y1 = y2;
				}
				repaint();
			}
			public void mouseReleased(MouseEvent e) {
				saveShape();
				draggChecker = false;
			}
		};

		addMouseListener(ma);
		addMouseMotionListener(ma);
	}

	public void saveShape() {
		if(draggChecker) {
			list.add(new ShapeDTO(form, fill, color, x1, y1, x2, y2, z1, z2));
		}
	}

	public void setRepaint() {
		Dimension d = this.getSize();

		if(bufferG == null) {
			bufferImg = createImage(d.width, d.height);
			bufferG = bufferImg.getGraphics();
		}

		bufferG.setColor(this.getBackground());
		bufferG.fillRect(0,0,d.width,d.height);
	}

	public void draw() {
		z1 = Integer.parseInt(painter.getZ1T().getText().trim());
		z2 = Integer.parseInt(painter.getZ2T().getText().trim());
		color = painter.getCombo().getSelectedIndex();
		fill = painter.getFill().isSelected();
		
		switch(color) {
			case 0 : bufferG.setColor(Color.RED); break;
			case 1 : bufferG.setColor(Color.GREEN); break;
			case 2 : bufferG.setColor(Color.BLUE); break;
			case 3 : bufferG.setColor(Color.MAGENTA); break;
			case 4 : bufferG.setColor(Color.CYAN); break;
		}

		if(painter.getLine().isSelected()) {
			form = 1;
			bufferG.drawLine(x1, y1, x2, y2);
		}else if(painter.getPen().isSelected()) {
			form = 5;
			bufferG.drawLine(x1, y1, x2, y2);
		}else {
			int width = Math.abs(x1 - x2);
			int height = Math.abs(y1 - y2);

			int x = Math.min(x1,x2);
			int y = Math.min(y1,y2);

			if(fill) {
				if(painter.getCircle().isSelected()) {
					form = 2;
					bufferG.fillOval(x, y, width, height);
				}else if(painter.getRect().isSelected()) {
					form = 3;
					bufferG.fillRect(x, y, width, height);
				}else if(painter.getRoundRect().isSelected()) {
					form = 4;
					bufferG.fillRoundRect(x, y, width, height, z1, z2);
				}
			}else {
				if(painter.getCircle().isSelected()) {
					form = 2;
					bufferG.drawOval(x, y, width, height);
				}else if(painter.getRect().isSelected()) {
					form = 3;
					bufferG.drawRect(x, y, width, height);
				}else if(painter.getRoundRect().isSelected()) {
					form = 4;
					bufferG.drawRoundRect(x, y, width, height, z1, z2);
				}
			}
		}
	}

	public void draw(int[] pointer, boolean fill, int form, int color) {
		int x1 = pointer[0];
		int y1 = pointer[1];
		int x2 = pointer[2];
		int y2 = pointer[3];
		int z1 = pointer[4];
		int z2 = pointer[5];

		switch(color) {
			case 0 : bufferG.setColor(Color.RED); break;
			case 1 : bufferG.setColor(Color.GREEN); break;
			case 2 : bufferG.setColor(Color.BLUE); break;
			case 3 : bufferG.setColor(Color.MAGENTA); break;
			case 4 : bufferG.setColor(Color.CYAN); break;
		}

		if(form == 1) {
			bufferG.drawLine(x1, y1, x2, y2);
		}else if(form == 5) {
			bufferG.drawLine(x1, y1, x2, y2);
		}else {
			int width = Math.abs(x1 - x2);
			int height = Math.abs(y1 - y2);

			int x = Math.min(x1,x2);
			int y = Math.min(y1,y2);

			if(fill) {
				if(form == 2) {
					bufferG.fillOval(x, y, width, height);
				}else if(form == 3) {
					bufferG.fillRect(x, y, width, height);
				}else if(form == 4) {
					bufferG.fillRoundRect(x, y, width, height, z1, z2);
				}
			}else {
				if(form == 2) {
					bufferG.drawOval(x, y, width, height);
				}else if(form == 3) {
					bufferG.drawRect(x, y, width, height);
				}else if(form == 4) {
					bufferG.drawRoundRect(x, y, width, height, z1, z2);
				}
			}
		}
	}

	@Override
	public void update(Graphics g) {
		System.out.println("update");
		setRepaint();
		for(int i = 0; i < list.size(); i ++) {
			draw(list.get(i).getPointer(), list.get(i).getFill(), list.get(i).getForm(), list.get(i).getColor());
		}
		draw();
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		System.out.println("paint");
		g.drawImage(bufferImg, 0,0,this);
	}
}