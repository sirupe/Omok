package painter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MsPaint extends JFrame implements ActionListener {
	private JLabel x1L, y1L, x2L, y2L, z1L, z2L;
	private JTextField x1T, y1T, x2T, y2T, z1T, z2T;
	private JCheckBox fill;
	private JRadioButton line, circle, rect, roundRect, pen, rubber;
	private JComboBox<String> combo;
	private JButton drawB;
	private DrCanvas canvas;

	public MsPaint() { 
		x1L = new JLabel("x1");
		y1L = new JLabel("y1");
		x2L = new JLabel("x2");
		y2L = new JLabel("y2");
		z1L = new JLabel("z1");
		z2L = new JLabel("z2");

		x1T = new JTextField("0", 3);
		y1T = new JTextField("0", 3);
		x2T = new JTextField("0", 3);
		y2T = new JTextField("0", 3);
		z1T = new JTextField("50", 3);
		z2T = new JTextField("50", 3);

		fill = new JCheckBox("채우기");

		line = new JRadioButton("선", true);
		circle = new JRadioButton("원");
		rect = new JRadioButton("사각형");
		roundRect = new JRadioButton("둥근 사각형");
		pen = new JRadioButton("펜");
		rubber = new JRadioButton("지우개");

		String[] choice = {"빨강", "초록", "파랑", "보라", "하늘"};

		combo = new JComboBox<String>(choice);
		// combo.addItem("빨강");
		drawB = new JButton("그리기");

		canvas = new DrCanvas(this);

		setFrame();
	}

	public void setFrame() {
		Container c =  getContentPane();

		JPanel positionP = new JPanel();
		JPanel buttonP = new JPanel();

		ButtonGroup group = new ButtonGroup();

		positionP.add(x1L);
		positionP.add(x1T);
		positionP.add(y1L);
		positionP.add(y1T);
		positionP.add(x2L);
		positionP.add(x2T);
		positionP.add(y2L);
		positionP.add(y2T);
		positionP.add(z1L);
		positionP.add(z1T);
		positionP.add(z2L);
		positionP.add(z2T);
		positionP.add(fill);

		group.add(line);
		group.add(circle);
		group.add(rect);
		group.add(roundRect);
		group.add(pen);
		group.add(rubber);

		buttonP.add(line);
		buttonP.add(circle);
		buttonP.add(rect);
		buttonP.add(roundRect);
		buttonP.add(pen);
		buttonP.add(rubber);
		buttonP.add(combo);
		buttonP.add(drawB);

		c.add("North", positionP);
		c.add("South", buttonP);
		c.add("Center", canvas);

		setTitle("Paint");
		setSize(600,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public void event() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closingEvent();
			}
		});

		drawB.addActionListener(this);
	}

	public void closingEvent() {
		int result = JOptionPane.showConfirmDialog(this, "종료하시겠습니까?", "종료", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(result == JOptionPane.OK_OPTION) System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) { canvas.repaint(); }

	public JTextField getX1T() { return x1T; }
	public JTextField getY1T() { return y1T; }
	public JTextField getX2T() { return x2T; }
	public JTextField getY2T() { return y2T; }
	public JTextField getZ1T() { return z1T; }
	public JTextField getZ2T() { return z2T; }

	public JCheckBox getFill() { return fill; }

	public JRadioButton getLine() { return line; }
	public JRadioButton getCircle() { return circle; }
	public JRadioButton getRect() { return rect; }
	public JRadioButton getRoundRect() { return roundRect; }
	public JRadioButton getPen() { return pen; }
	public JRadioButton getRubber() { return rubber; }

	public JComboBox<String> getCombo() { return combo; }

	public static void main(String[] args) {
		new MsPaint().event();
	}
}