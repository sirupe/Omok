package frames.store;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enums.frames.GamePayRoomEnum;
import enums.frames.SearchIdEnum;

public class GamePayRoomFrame extends JFrame {
	private Image icon;
	private JLabel imageIcon;
	private JLabel basicMoneyLabel;
	private JLabel divLabel;
	private JTextField userAmountText;
	private JLabel equalsLabel;
	private JLabel totalAmountLabel;
	
	private JPanel itemquanity;
	
	private JButton payButton;
	private JButton cancelButton;
	private Image backGround;
	
	public GamePayRoomFrame() throws IOException {
		
		this.itemquanity = new JPanel();
		this.imageIcon = new JLabel(""); //�ȿ� ������ �������� ��� ���� �Ѵ�.
		
		this.imageIcon.setOpaque(true);
		this.imageIcon.setBackground(Color.pink);
		

		this.basicMoneyLabel  = new JLabel("1000");
		this.basicMoneyLabel.setOpaque(true);
		//this.basicMoneyLabel.setBackground(Color.pink);
		this.divLabel         = new JLabel("X");
		this.equalsLabel      = new JLabel("=");
		this.totalAmountLabel = new JLabel();
		this.totalAmountLabel.setOpaque(true);
	
		
		this.payButton = new JButton();
		this.payButton.setBorderPainted(false);
		this.payButton.setFocusPainted(false);
		this.payButton.setContentAreaFilled(false);
		
		this.cancelButton = new JButton();
		this.cancelButton.setBorderPainted(false);
		this.cancelButton.setFocusPainted(false);
		this.cancelButton.setContentAreaFilled(false);
//������ ȭ�� ũ�� ��ġ
		//���ȭ��	
		
				backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
						GamePayRoomEnum.GAME_PAY_ROOM_WIDTH.getSize(),
						GamePayRoomEnum.GAME_PAY_ROOM_HEIGHT.getSize(),
		                Image.SCALE_SMOOTH);

				this.setContentPane(new JLabel(new ImageIcon(backGround)));
		
		
		this.setBounds(
				GamePayRoomEnum.GAME_PAY_ROOM_POSITION_X.getSize(),
				GamePayRoomEnum.GAME_PAY_ROOM_POSITION_Y.getSize(),
				GamePayRoomEnum.GAME_PAY_ROOM_WIDTH.getSize(),
				GamePayRoomEnum.GAME_PAY_ROOM_HEIGHT.getSize()
		);
		
		this.setImageLine();
		this.setPayLine();
		this.setButtonLine();
		
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	public void setImageLine() {
		//��ǰ�� �����ϴ»���� ���� �������� �ٸ��ϱ�..? �󺧷� ó�� �߾��ϴ�
		this.imageIcon.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_ITEM_LABEL.getRectangle());
		this.add(imageIcon);	
	}
	//��������
	public void setPayLine() {
		//������ *���� -- > �гη� ����
		this.itemquanity.setBounds(GamePayRoomEnum.GAME_ROOM_AMOUNT_PANEL.getRectangle());
		this.itemquanity.setLayout(null);
		this.itemquanity.setOpaque(false);
//		this.itemquanity.setBackground(Color.blue);
		
		//������ ���� �� �ִ� �ؽ�Ʈ �ʵ� 
		JTextField userAmountText = new JTextField();
		userAmountText.setBounds(GamePayRoomEnum.GAME_ROOM_USER_AMOUNT_PANEL.getRectangle());
		//��Ʈ  searchIdEnum���� ������
		userAmountText.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		userAmountText.setBorder(GamePayRoomEnum.LABEL_LINE.getMatterBorder());
		userAmountText.setOpaque(false);

		//�����ϴ� ��ư
		JButton up = new JButton() {	
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/store/up.png")), 
						0, 
						0, 
						GamePayRoomEnum.GAME_ROOM_UP_ADD.getRectangle().width,
						GamePayRoomEnum.GAME_ROOM_UP_ADD.getRectangle().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		// �����ϴ� ��ư
		JButton down = new JButton() {	
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/store/down.png")), 
						0, 
						0, 
						GamePayRoomEnum.GAME_ROOM_DOWN_SUB.getRectangle().width,
						GamePayRoomEnum.GAME_ROOM_DOWN_SUB.getRectangle().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		up.setBounds(GamePayRoomEnum.GAME_ROOM_UP_ADD.getRectangle());
		up.setIconTextGap(up.getIconTextGap() - 15);
		
		down.setBounds(GamePayRoomEnum.GAME_ROOM_DOWN_SUB.getRectangle());
		down.setIconTextGap(down.getIconTextGap() - 15);

		

		this.basicMoneyLabel.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_BASICMONEY_LABEL.getRectangle());
		this.basicMoneyLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.basicMoneyLabel.setOpaque(false);
		
		this.divLabel.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_DIV_LABEL.getRectangle());
		this.divLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.divLabel.setOpaque(false);
		
		this.equalsLabel.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_EQUAL_LABEL.getRectangle());
		this.equalsLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		
		
		this.totalAmountLabel.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_TOTALMONEY_LABEL.getRectangle());
		this.totalAmountLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.totalAmountLabel.setBorder(GamePayRoomEnum.LABEL_LINE.getMatterBorder());
		this.totalAmountLabel.setOpaque(false);

		this.add(totalAmountLabel);
		this.add(equalsLabel);
		this.add(basicMoneyLabel);
		this.add(divLabel);
//		itemquanity.setOpaque(true);
		
		
		up.setFocusPainted(false);
		up.setBorderPainted(false);
		up.setContentAreaFilled(false);
		
		down.setFocusPainted(false);
		down.setBorderPainted(false);
		down.setContentAreaFilled(false);
		
		this.itemquanity.add(up);
		this.itemquanity.add(down);
		this.itemquanity.add(userAmountText);
		this.add(itemquanity);
		
	}
	public void setButtonLine() throws IOException {
		//���� ��ư
		this.payButton.setIconTextGap(this.payButton.getIconTextGap() - 15 );
		this.payButton.setIcon(
				new ImageIcon(ImageIO.read(
						new File("resources/store/pay.png")).getScaledInstance(
								GamePayRoomEnum.GAME_ROOM_PAY_BUTTON.getRectangle().width,
								GamePayRoomEnum.GAME_ROOM_PAY_BUTTON.getRectangle().height,
	    						Image.SCALE_AREA_AVERAGING))
	    		);
						
		this.payButton.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_BUTTON.getRectangle());
		
		//��� ��ư
		this.cancelButton.setIconTextGap(this.cancelButton.getIconTextGap() - 15 );
		this.cancelButton.setIcon(
				new ImageIcon(ImageIO.read(
						new File("resources/myData/reset.kor.png")).getScaledInstance(
								GamePayRoomEnum.GAME_ROOM_PAY_CANCEL_BUTTON.getRectangle().width,
								GamePayRoomEnum.GAME_ROOM_PAY_CANCEL_BUTTON.getRectangle().height,
	    						Image.SCALE_AREA_AVERAGING))
	    		);
						
		this.cancelButton.setBounds(GamePayRoomEnum.GAME_ROOM_PAY_CANCEL_BUTTON.getRectangle());
		
	
		this.add(payButton);
		this.add(cancelButton);
	}
	
	public static void main(String[] args) throws IOException {
		new GamePayRoomFrame();

	}

}
