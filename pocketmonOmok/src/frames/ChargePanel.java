package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import enums.frames.ClientJoinSizesEnum;

public class ChargePanel extends JPanel {
	
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
		
		Font font = new Font("a으라차차",Font.BOLD, 18);
		JLabel chargeLabel = new JLabel("충전할금액");
		chargeLabel.setBounds(ChargeEnum.CHARGE_LABEL_SIZE_RECT.getRect());
		chargeLabel.setFont(font);
		
		//1000원 버튼
		JButton aThousand = new JButton() {
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
		aThousand.setFocusPainted(false);
		aThousand.setBorderPainted(false);
		aThousand.setContentAreaFilled(false);
		aThousand.setBounds(ChargeEnum.CHARGE_1000_SIZE_RECT.getRect());
		
		//5000원 버튼
		JButton fiveThousand = new JButton() {
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
		fiveThousand.setFocusPainted(false);
		fiveThousand.setBorderPainted(false);
		fiveThousand.setContentAreaFilled(false);
		fiveThousand.setBounds(ChargeEnum.CHARGE_5000_SIZE_RECT.getRect());
		
		//10000원 버튼
		JButton tenThousand = new JButton(){
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
		tenThousand.setFocusPainted(false);
		tenThousand.setBorderPainted(false);
		tenThousand.setContentAreaFilled(false);
		tenThousand.setBounds(ChargeEnum.CHARGE_10000_SIZE_RECT.getRect());
		
		//50000원 버튼
		JButton fiftyThousand = new JButton(){
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
		fiftyThousand.setFocusPainted(false);
		fiftyThousand.setBorderPainted(false);
		fiftyThousand.setContentAreaFilled(false);	
		fiftyThousand.setBounds(ChargeEnum.CHARGE_50000_SIZE_RECT.getRect());
		
		//add
		this.chargePanel.add(chargeLabel);
		this.chargePanel.add(aThousand);
		this.chargePanel.add(fiveThousand);
		this.chargePanel.add(tenThousand);
		this.chargePanel.add(fiftyThousand);
		this.add(this.chargePanel);
		
	}
	
	public void setemailPanel() {
		this.emailPanel.setLayout(null);
		this.emailPanel.setBounds(ChargeEnum.EMAIL_PANEL_SIZE_RECT.getRect());

		Font font = new Font("a으라차차",Font.BOLD, 18);
		JLabel emailLabel = new JLabel("email 입력");
		emailLabel.setFont(font);
		emailLabel.setBounds(ChargeEnum.EMAIL_LABEL_SIZE_RECT.getRect());
	
		JTextField emailInput = new JTextField();
		emailInput.setBounds(ChargeEnum.EMAIL_INPUT_SIZE_RECT.getRect());
		
		JLabel at = new JLabel("@");
		at.setFont(font);
		at.setBounds(ChargeEnum.EMAIL_AT_SIZE_RECT.getRect());
		
		JTextField emailAddr = new JTextField();
		emailAddr.setBounds(ChargeEnum.EMAIL_ADDRESS_SIZE_RECT.getRect());
		
		JComboBox<String> emailAddrComboBox = new JComboBox<String>();
		String[] emailAddrstr = ChargeEnum.EMAIL_ADDRESS.getStrArr();
		for(int i = 0, size = emailAddrstr.length; i < size; i++) {
			emailAddrComboBox.addItem(emailAddrstr[i]);
		}
		emailAddrComboBox.setBounds(ChargeEnum.EMAIL_ADDRESS_COMBOBOX_SIZE_RECT.getRect());
		
		
		
		this.emailPanel.add(emailAddrComboBox);
		this.emailPanel.add(emailAddr);
		this.emailPanel.add(at);
		this.emailPanel.add(emailInput);
		this.emailPanel.add(emailLabel);
		this.add(this.emailPanel);
		
			
	}
	
	public void setcertifyPanel() {
		
		Font font = new Font("a으라차차",Font.BOLD, 15);
		
		this.certifyPanel.setLayout(null);
		this.certifyPanel.setBounds(ChargeEnum.CERTIFY_PANEL_SIZE_RECT.getRect());

		JButton certify = new JButton() {
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
		certify.setFocusPainted(false);
		certify.setBorderPainted(false);
		certify.setContentAreaFilled(false);	
		certify.setBounds(ChargeEnum.CERTIFY_BUTTON_SIZE_RECT.getRect());
		
		JTextField certifyText = new JTextField();
		certifyText.setBounds(ChargeEnum.CERTIFY_NUM_SIZE_RECT.getRect());
		
		JTextArea certifyTextArea = new JTextArea("인증번호를 다시 확인하세요");
		certifyTextArea.setForeground(Color.RED);
		certifyTextArea.setFont(font);
		certifyTextArea.setOpaque(false);
		certifyTextArea.setBounds(ChargeEnum.CERTIFY_TEXTAREA_SIZE_RECT.getRect());
		
		JTextArea certifyTime = new JTextArea("3:00");
		certifyTime.setForeground(Color.RED);
		certifyTime.setFont(font);
		certifyTime.setOpaque(false);
		certifyTime.setBounds(ChargeEnum.CERTIFY_TIME_SIZE_RECT.getRect());
		
		
		
		this.certifyPanel.add(certifyTime);
		this.certifyPanel.add(certifyTextArea);
		this.certifyPanel.add(certifyText);
		this.certifyPanel.add(certify);
		this.add(this.certifyPanel);
		
	}
	
	public void setbuttonsPanel() {
		this.buttonsPanel.setLayout(null);
		this.buttonsPanel.setBounds(ChargeEnum.BUTTONS_PANEL_SIZE_RECT.getRect());

		//확인버튼
		JButton confirm = new JButton() {
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
		
		confirm.setFocusPainted(false);
		confirm.setBorderPainted(false);
		confirm.setContentAreaFilled(false);
		confirm.setBounds(ChargeEnum.CONFIRM_BUTTON_SIZE_RECT.getRect());
		
		//취소 버튼
		JButton reset = new JButton() {
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
		
		reset.setFocusPainted(false);
		reset.setBorderPainted(false);
		reset.setContentAreaFilled(false);
		reset.setBounds(ChargeEnum.RESET_BUTTON_SIZE_RECT.getRect());
		
	
		
		this.buttonsPanel.add(confirm);
		this.buttonsPanel.add(reset);
		this.add(this.buttonsPanel);
		
	}


}
