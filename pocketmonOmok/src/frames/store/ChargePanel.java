package frames.store;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import enums.frames.ChargeEnum;
import enums.frames.JoinSizesEnum;
import enums.frames.SearchIdEnum;
import enums.frames.SearchPwdEnum;

public class ChargePanel extends JPanel {
	
	private JLabel chargeLabel;
	private JButton aThousand;
	private JButton fiveThousand;
	private JButton tenThousand;
	private JButton fiftyThousand;
	
	private JLabel emailLabel;
	private JTextField emailInput;
	private JLabel at;
	private JTextField emailAddr;
	private JComboBox<String> emailAddrComboBox;
	private String[] emailAddrstr;
	
	private JButton certify; 
	private JTextField certifyText;
	private JTextArea certifyTextArea;
	private JTextArea certifyTime;
	
	private JButton confirm;
	private JButton reset;

	private JPanel chargePanel;
	private JPanel emailPanel;
	private JPanel certifyPanel;
	private JPanel buttonsPanel;
	
	//생성자
	public ChargePanel() {
		
		this.setLayout(null);
		this.setVisible(true);
		
		this.chargePanel  = new JPanel();
		this.emailPanel   = new JPanel();
		this.certifyPanel = new JPanel();
		this.buttonsPanel = new JPanel();
		
		this.setchargePanel();
		this.setemailPanel();
		this.setcertifyPanel();
		this.setbuttonsPanel();
		
	}
	
	public void setchargePanel() {
		this.chargePanel.setLayout(null);
		this.chargePanel.setBounds(ChargeEnum.CHARGE_PANEL_SIZE_RECT.getRect());
		this.chargePanel.setOpaque(false);
		
		this.chargeLabel = new JLabel("충전할금액");
		this.chargeLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.chargeLabel.setBounds(ChargeEnum.CHARGE_LABEL_SIZE_RECT.getRect());
		
		//1000원 버튼
		this.aThousand = new JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/store/1000W.png")), 
						0, 
						0, 
						ChargeEnum.CHARGE_1000_SIZE_RECT.getRect().width,
						ChargeEnum.CHARGE_1000_SIZE_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }	
		};
		this.aThousand.setFocusPainted(false);
		this.aThousand.setBorderPainted(false);
		this.aThousand.setContentAreaFilled(false);
		this.aThousand.setBounds(ChargeEnum.CHARGE_1000_SIZE_RECT.getRect());
		
		//5000원 버튼
		this.fiveThousand = new JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/store/5000W.png")), 
						0, 
						0, 
						ChargeEnum.CHARGE_5000_SIZE_RECT.getRect().width,
						ChargeEnum.CHARGE_5000_SIZE_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }
		};
		this.fiveThousand.setFocusPainted(false);
		this.fiveThousand.setBorderPainted(false);
		this.fiveThousand.setContentAreaFilled(false);
		this.fiveThousand.setBounds(ChargeEnum.CHARGE_5000_SIZE_RECT.getRect());
		
		//10000원 버튼
		this.tenThousand = new JButton(){
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/store/10000W.png")), 
						0, 
						0, 
						ChargeEnum.CHARGE_10000_SIZE_RECT.getRect().width,
						ChargeEnum.CHARGE_10000_SIZE_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }
		};
		this.tenThousand.setFocusPainted(false);
		this.tenThousand.setBorderPainted(false);
		this.tenThousand.setContentAreaFilled(false);
		this.tenThousand.setBounds(ChargeEnum.CHARGE_10000_SIZE_RECT.getRect());
		
		//50000원 버튼
		this.fiftyThousand = new JButton(){
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/store/50000W.png")), 
						0, 
						0, 
						ChargeEnum.CHARGE_50000_SIZE_RECT.getRect().width,
						ChargeEnum.CHARGE_50000_SIZE_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }
		};
		this.fiftyThousand.setFocusPainted(false);
		this.fiftyThousand.setBorderPainted(false);
		this.fiftyThousand.setContentAreaFilled(false);	
		this.fiftyThousand.setBounds(ChargeEnum.CHARGE_50000_SIZE_RECT.getRect());
		
		//add
		this.chargePanel.add(chargeLabel);
		this.chargePanel.add(aThousand);
		this.chargePanel.add(fiveThousand);
		this.chargePanel.add(tenThousand);
		this.chargePanel.add(fiftyThousand);
		this.add(this.chargePanel);
		
	}
	
	public void setemailPanel() {
		this.emailPanel.setOpaque(false);
		this.emailPanel.setLayout(null);
		this.emailPanel.setBounds(ChargeEnum.EMAIL_PANEL_SIZE_RECT.getRect());

		this.emailLabel = new JLabel("email 입력");
		this.emailLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.emailLabel.setBounds(ChargeEnum.EMAIL_LABEL_SIZE_RECT.getRect());
	
		this.emailInput = new JTextField();
		this.emailInput.setBounds(ChargeEnum.EMAIL_INPUT_SIZE_RECT.getRect());
		
		this.at = new JLabel("@");
		this.at.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		this.at.setBounds(ChargeEnum.EMAIL_AT_SIZE_RECT.getRect());
		
		this.emailAddr = new JTextField();
		this.emailAddr.setBounds(ChargeEnum.EMAIL_ADDRESS_SIZE_RECT.getRect());
		
		this.emailAddrComboBox = new JComboBox<String>();
		this.emailAddrstr = ChargeEnum.EMAIL_ADDRESS.getStrArr();
		for(int i = 0, size = emailAddrstr.length; i < size; i++) {
			emailAddrComboBox.addItem(emailAddrstr[i]);
		}
		this.emailAddrComboBox.setBounds(ChargeEnum.EMAIL_ADDRESS_COMBOBOX_SIZE_RECT.getRect());
		
		
		
		this.emailPanel.add(emailAddrComboBox);
		this.emailPanel.add(emailAddr);
		this.emailPanel.add(at);
		this.emailPanel.add(emailInput);
		this.emailPanel.add(emailLabel);
		this.add(this.emailPanel);
		
			
	}
	
	public void setcertifyPanel() {
		this.certifyPanel.setOpaque(false);
		this.certifyPanel.setLayout(null);
		this.certifyPanel.setBounds(ChargeEnum.CERTIFY_PANEL_SIZE_RECT.getRect());

		this.certify = new JButton() {
			@Override
	        protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/certify/certify.kor.png")), 
						0, 
						0, 
						ChargeEnum.CERTIFY_BUTTON_SIZE_RECT.getRect().width,
						ChargeEnum.CERTIFY_BUTTON_SIZE_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
		        }      
		    }
		};
		this.certify.setFocusPainted(false);
		this.certify.setBorderPainted(false);
		this.certify.setContentAreaFilled(false);	
		this.certify.setBounds(ChargeEnum.CERTIFY_BUTTON_SIZE_RECT.getRect());
		
		this.certifyText = new JTextField();
		this.certifyText.setBounds(ChargeEnum.CERTIFY_NUM_SIZE_RECT.getRect());
		
		this.certifyTextArea = new JTextArea("인증번호를 다시 확인하세요");
		this.certifyTextArea.setForeground(Color.RED);
		this.certifyTextArea.setFont(SearchPwdEnum.LABELFONT_DEFAULT.getFont());
		this.certifyTextArea.setOpaque(false);
		this.certifyTextArea.setBounds(ChargeEnum.CERTIFY_TEXTAREA_SIZE_RECT.getRect());
		
		
		this.certifyTime = new JTextArea("3:00");
		this.certifyTime.setForeground(Color.RED);
		this.certifyTime.setFont(JoinSizesEnum.LABELFONT_DEFAULT.getFont());
		this.certifyTime.setOpaque(false);
		this.certifyTime.setBounds(ChargeEnum.CERTIFY_TIME_SIZE_RECT.getRect());
		
		
		
		this.certifyPanel.add(certifyTime);
		this.certifyPanel.add(certifyTextArea);
		this.certifyPanel.add(certifyText);
		this.certifyPanel.add(certify);
		this.add(this.certifyPanel);
		
	}
	
	public void setbuttonsPanel() {
		this.buttonsPanel.setOpaque(false);
		this.buttonsPanel.setLayout(null);
		this.buttonsPanel.setBounds(ChargeEnum.BUTTONS_PANEL_SIZE_RECT.getRect());

		//확인버튼
		this.confirm = new JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/certify/confirm.kor.png")), 
						0, 
						0, 
						ChargeEnum.CONFIRM_BUTTON_SIZE_RECT.getRect().width,
						ChargeEnum.CONFIRM_BUTTON_SIZE_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }	
		};
		
		this.confirm.setFocusPainted(false);
		this.confirm.setBorderPainted(false);
		this.confirm.setContentAreaFilled(false);
		this.confirm.setBounds(ChargeEnum.CONFIRM_BUTTON_SIZE_RECT.getRect());
		
		//취소 버튼
		this.reset = new JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/myData/reset.kor.png")), 
						0, 
						0, 
						ChargeEnum.RESET_BUTTON_SIZE_RECT.getRect().width,
						ChargeEnum.RESET_BUTTON_SIZE_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }	
		};
		
		this.reset.setFocusPainted(false);
		this.reset.setBorderPainted(false);
		this.reset.setContentAreaFilled(false);
		this.reset.setBounds(ChargeEnum.RESET_BUTTON_SIZE_RECT.getRect());
	
		this.buttonsPanel.add(confirm);
		this.buttonsPanel.add(reset);
		this.add(this.buttonsPanel);		
	}
}
