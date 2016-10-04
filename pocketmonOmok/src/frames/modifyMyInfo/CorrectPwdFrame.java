package frames.modifyMyInfo;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import actions.modifyMyInfo.ModifyMyInfoAction;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import enums.frames.CorrectEnum;
import enums.frames.SearchIDEnum;
import enums.frames.SearchPwdEnum;

public class CorrectPwdFrame extends JFrame{
	private Image backGround;
	private JLabel pwdLabel;
	private JLabel pwderror;
	private JPasswordField pwdField;
	private JButton confirm;
	private JButton reset;
	private ModifyMyInfoAction modifyAction;
	private ModifyMyInfoFrame modifyFrame;
	private String id;
	
	public CorrectPwdFrame(ModifyMyInfoFrame modifyFrame, ModifyMyInfoAction modifyAction) {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				modifyFrame.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		this.modifyAction = modifyAction;
		this.modifyFrame = modifyFrame;
		try {
			this.backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
					CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width,
					CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height,
			        Image.SCALE_SMOOTH);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		this.setContentPane(new JLabel(new ImageIcon(backGround)));
		
		this.pwdLabel	 = new JLabel("PW �Է�");
		this.pwderror  	 = new JLabel("��й�ȣ ���� �Դϴ�.");
		this.pwdField = new JPasswordField();
		Font pwdLabelfont = new Font("a��������", Font.BOLD, 18);
		Font pwderrorfont = new Font("a��������", Font.BOLD, 15);
		
		this.setBounds(CorrectEnum.PWD_FRAME_SIZE_RECT.getRect());
		
		this.pwdLabel.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		this.pwdLabel.setBounds(CorrectEnum.PWD_TEXT_LABEL_RECT.getRect());
		
		this.pwdField.setBounds(CorrectEnum.PWD_INPUT_RECT.getRect());
			
		this.pwderror.setFont(SearchPwdEnum.LABELFONT_DEFAULT.getFont());
		this.pwderror.setForeground(Color.red);
		this.pwderror.setBounds(CorrectEnum.PWD_ERROR_RECT.getRect());
		
		
		this.confirm = new JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/myData/confirm.kor.png")), 
						0, 
						0, 
						CorrectEnum.PWD_CONFIRM_RECT.getRect().width,
						CorrectEnum.PWD_CONFIRM_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }	
		};
		this.confirm.setFocusPainted(false);
		this.confirm.setBorderPainted(false);
		this.confirm.setContentAreaFilled(false);
		this.confirm.setBounds(CorrectEnum.PWD_CONFIRM_RECT.getRect());

		this.reset = new JButton() {
			@Override
            protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/myData/reset.kor.png")), 
						0, 
						0, 
						CorrectEnum.PWD_RESET_RECT.getRect().width,
						CorrectEnum.PWD_RESET_RECT.getRect().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
	            }      
	        }	
		};
		this.reset.setFocusPainted(false);
		this.reset.setBorderPainted(false);
		this.reset.setContentAreaFilled(false);
		this.reset.setBounds(CorrectEnum.PWD_RESET_RECT.getRect());
		
		this.add(pwdLabel);
//		this.add(pwderror);
		this.add(pwdField);
		this.add(confirm);
		this.add(reset);
		this.confirm.setName("confirm");
		this.reset.setName("reset");
		this.confirm.addActionListener(this.modifyAction);
		this.reset.addActionListener(this.modifyAction);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void confirmButtonClick() {
		this.id = this.modifyFrame.getBasicFrame().getUserID();
		char[] pwArr = this.pwdField.getPassword();
		String pw = new String(pwArr, 0, pwArr.length);
		
		UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_MODIFY_MY_INFO);
		personalDTO.setUserAction(UserActionEnum.USER_MODIFY_DROP);
		personalDTO.setUserID(id);
		personalDTO.setUserPasswd(pw);
		
		this.modifyFrame.getBasicFrame().sendDTO(personalDTO);	
	}
	
	public void dropPwCheckFail() {
		JOptionPane.showMessageDialog(this, "�н����尡 ��ġ���� �ʽ��ϴ�.");
	}
	
	public void resetClick() {
		this.modifyFrame.setVisible(true);
		this.setVisible(false);
		this.dispose();
	}
	
	public void dropPwCheckSuccess() {
		int result = JOptionPane.showConfirmDialog(this, "������ Ż���Ͻðڽ��ϱ�?", "", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_MODIFY_MY_INFO);
			personalDTO.setUserAction(UserActionEnum.USER_DROP_CERTAIN);
			personalDTO.setUserID(this.id);
			
			this.modifyFrame.getBasicFrame().sendDTO(personalDTO);
			
		} else {
			
		}
	}
	
	public void dropOutSuccess() {
		JOptionPane.showMessageDialog(this, "ȸ��Ż�� �Ϸ�Ǿ����ϴ�.");
		this.setVisible(false);
		this.dispose();
		this.modifyFrame.getBasicFrame().setVisible(true);
		this.modifyFrame.getBasicFrame().showLoginPanel();
	}
	
	public void dropOutFail() {
		JOptionPane.showMessageDialog(this, "ó���� ������ �߻��Ͽ����ϴ�. �ٽ� �õ����ּ���.","����", JOptionPane.WARNING_MESSAGE);
		
	}
}
