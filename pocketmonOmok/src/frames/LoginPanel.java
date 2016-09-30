package frames;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import actions.login.LoginClientAction;
import enums.etc.ImageEnum;
import enums.frames.LoginSizesEnum;
import frames.searchFrames.SearchPwdPanel;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
	private JButton joinButton;
	private JButton loginButton;    
	private JButton searchIdButton;  
	private JButton searchPwButton; 
	private JButton idImage;      
	private JButton passwordImage;  
	
	private JTextField idField;
	private JPasswordField pwField;
	
	private Font textFieldFont;
	
	private JLabel loginFailLabel;
	
	//�̹��� ȭ�� ������ ���缭 �ٲ�� �ϱ� ���� �̹��� ���
	private Image idReimage, PW, PWreimage;
	private Image joinreimage;
	private Image loginreimage;
	private Image searchidreimage;
	private Image searchpwreimage;
	
	private JPanel loginPanel;
	private BasicFrame basicFrame;
	
	private LoginClientAction loginAction;

	private SearchPwdPanel searchPwdPanel; 
	
	public LoginPanel(BasicFrame basicFrame) throws IOException {
		this.loginAction = new LoginClientAction(this);
		
		this.loginPanel	= new JPanel();
		
		this.loginPanel.setLayout(null);
		this.loginPanel.setOpaque(false);
		this.basicFrame = basicFrame;
		this.searchPwdPanel = searchPwdPanel;
		this.loginFailLabel = new JLabel();
		
		//������ ȭ�� ��� ��ġ ����
		this.setBounds(   
		      LoginSizesEnum.LOGIN_FRAME_POSITION_X.getSize(),
		      LoginSizesEnum.LOGIN_FRAME_POSITION_Y.getSize(),
		      LoginSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize(), 
		      LoginSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize()

		);
		
		//���̵�, ��й�ȣ �Է��ϴ� �ؽ�Ʈ�ʵ� ����
		this.setTextFieldPosition();
		
		//�α���, ȸ������, ���̵�ã��, ��й�ȣ ã�� ��ư ��ġ ����
		this.setButtonPosition();
		
		//���̵�, ��й�ȣ �Է� �˷��ִ� �̹��� ����
		this.setIdPwImagePositon();
		
		
		
		this.add(this.loginPanel);
		this.setLayout(new CardLayout());	
	}
	
	//���̵� �Ǵ� ��й�ȣ ������ �˷��ִ� �ؽ�Ʈ
	public void loginFail(String errMsg){
		this.setLayout(null);
		this.textFieldFont = new Font("", Font.BOLD, 20);      
		this.loginFailLabel.setBounds(
					LoginSizesEnum.LOGIN_FAIL_TEXT_POSITION_X.getSize(),
					LoginSizesEnum.LOGIN_FAIL_TEXT_POSITION_Y.getSize(),
					LoginSizesEnum.LOGIN_FAIL_TEXT_WIDTH.getSize(), 
					LoginSizesEnum.LOGIN_FAIL_TEXT_HEIGTH.getSize()
		);
		this.loginFailLabel.setOpaque(false);
		this.add(this.loginFailLabel);
		
		this.loginFailLabel.setFont(new Font("", Font.PLAIN, 13));
		this.loginFailLabel.setForeground(Color.RED);
		this.loginFailLabel.setText(errMsg);
	}
	
	public void loginFailLabelReset(){
		String init = "";
		this.loginFailLabel.setText(init);
	}
	
	public void setIdPwImagePositon() throws IOException{
		this.idReimage = ImageIO.read(new File("resources/login/ID.png")).getScaledInstance(
			               LoginSizesEnum.SIZE_LABEL_WIDTH.getSize(),
			               LoginSizesEnum.SIZE_LABEL_HEIGHT.getSize(),
			               Image.SCALE_SMOOTH);
		
		this.idImage   = new JButton(new ImageIcon(this.idReimage));
		
		//���̵� �̹��� ��ġ ����
		this.idImage.setBounds(
					LoginSizesEnum.LOGIN_RESOURCE_ID_POSITION_X.getSize(),
					LoginSizesEnum.LOGIN_RESOURCE_ID_POSITION_Y.getSize(),
					LoginSizesEnum.SIZE_LABEL_WIDTH.getSize(), 
					LoginSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.idImage.setBorderPainted(false);
		this.idImage.setContentAreaFilled(false);
		this.idImage.setFocusPainted(false);
		
		this.PW        = ImageIO.read(new File("resources/login/PW.png"));
		this.PWreimage = PW.getScaledInstance(
					LoginSizesEnum.SIZE_LABEL_WIDTH.getSize(), 
					LoginSizesEnum.SIZE_LABEL_HEIGHT.getSize(),
					Image.SCALE_SMOOTH);
		
		this.passwordImage = new JButton(new ImageIcon(this.PWreimage));  
		
		//��й�ȣ �̹��� ��ġ ����
		this.passwordImage.setBounds(
					LoginSizesEnum.LOGIN_RESOURCE_PASSWORD_POSITION_X.getSize(),
					LoginSizesEnum.LOGIN_RESOURCE_PASSWORD_POSITION_Y.getSize(), 
					LoginSizesEnum.SIZE_LABEL_WIDTH.getSize(), 
					LoginSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.passwordImage.setBorderPainted(false);
		this.passwordImage.setContentAreaFilled(false);
		this.passwordImage.setFocusPainted(false);
		
		this.loginPanel.add(this.idImage);
		this.loginPanel.add(this.passwordImage);
	}
	
	public void setButtonPosition() throws IOException{
	   //�̹��� ���� ��ȯ
		this.loginreimage = ImageIO.read(new File(ImageEnum.LOGINPANEL_LOGIN.getImageDir())).getScaledInstance(
		               LoginSizesEnum.LOGIN_ICON_WIDTH.getSize(),
		               LoginSizesEnum.LOGIN_ICON_HEIGHT.getSize(),
		               Image.SCALE_AREA_AVERAGING);
		this.loginButton = new JButton(new ImageIcon(this.loginreimage));
	  
		this.joinreimage = ImageIO.read(new File(ImageEnum.LOGINPANEL_SIGHUP.getImageDir())).getScaledInstance(
		               LoginSizesEnum.ICON_SIZE_WIDTH.getSize(),
		               LoginSizesEnum.ICON_SIZE_HEIGHT.getSize(),
		               Image.SCALE_AREA_AVERAGING);
		this.joinButton = new JButton(new ImageIcon(this.joinreimage));
		
		this.searchidreimage = ImageIO.read(new File(ImageEnum.LOGINPANEL_SEARCHID.getImageDir())).getScaledInstance(
		               LoginSizesEnum.ICON_SIZE_WIDTH.getSize(),
		               LoginSizesEnum.ICON_SIZE_HEIGHT.getSize(),
		               Image.SCALE_AREA_AVERAGING);
		this.searchIdButton = new JButton(new ImageIcon(this.searchidreimage));
		
		this.searchpwreimage = ImageIO.read(new File(ImageEnum.LOGINPANEL_SEARCHPW.getImageDir())).getScaledInstance(
		               LoginSizesEnum.ICON_SIZE_WIDTH.getSize(),
		               LoginSizesEnum.ICON_SIZE_HEIGHT.getSize(),
		               Image.SCALE_AREA_AVERAGING);
		this.searchPwButton = new JButton(new ImageIcon(this.searchpwreimage));
		
		//�α��� ��ư ��ġ ����
		this.loginButton.setBounds(
					LoginSizesEnum.LOGIN_RESOURCE_LOGIN_BUTTON_POSITION_X.getSize(),
					LoginSizesEnum.LOGIN_RESOURCE_LOGIN_BUTTON_POSITION_Y.getSize(),
					LoginSizesEnum.LOGIN_ICON_WIDTH.getSize(), 
					LoginSizesEnum.LOGIN_ICON_HEIGHT.getSize()
		);
		this.loginButton.setName("login");
		this.loginButton.setBorderPainted(false);
		this.loginButton.setContentAreaFilled(false);
		this.loginButton.setFocusPainted(false);
		this.loginButton.setToolTipText("�α���");
		
		//���߿� �׼� ��� �Ҷ� this�� ������ ��
		this.loginButton.addActionListener(this.loginAction);
		this.loginButton.addMouseListener(this.loginAction);
		
		//ȸ������ ��ư ��ġ ����
		this.joinButton.setBounds(
					LoginSizesEnum.LOGIN_RESOURCE_JOIN_BUTTON_POSITION_X.getSize(),
					LoginSizesEnum.LOGIN_RESOURCE_JOIN_BUTTON_POSITION_Y.getSize(),
					LoginSizesEnum.ICON_SIZE_WIDTH.getSize(), 
					LoginSizesEnum.ICON_SIZE_HEIGHT.getSize()
		);
		this.joinButton.setName(LoginSizesEnum.BUTTON_NAME_SIGNUP.getButtonName());
		this.joinButton.setBorderPainted(false);
		this.joinButton.setContentAreaFilled(false);
		this.joinButton.setFocusPainted(false);
		this.joinButton.setToolTipText("ȸ������");
		this.joinButton.addMouseListener(this.loginAction);
		//���̵�ã�� ��ư ��ġ ����
		this.searchIdButton.setBounds(
					LoginSizesEnum.LOGIN_RESOURCE_SEARCHID_BUTTON_POSITION_X.getSize(),
					LoginSizesEnum.LOGIN_RESOURCE_SEARCHID_BUTTON_POSITION_Y.getSize(),
					LoginSizesEnum.ICON_SIZE_WIDTH.getSize(), 
					LoginSizesEnum.ICON_SIZE_HEIGHT.getSize()
		);
		this.searchIdButton.setName(LoginSizesEnum.BUTTON_NAME_SEARCHID.getButtonName());
		this.searchIdButton.setBorderPainted(false);
		this.searchIdButton.setContentAreaFilled(false);
		this.searchIdButton.setFocusPainted(false);
		this.searchIdButton.setToolTipText("���̵� ã��");
		this.searchIdButton.addMouseListener(this.loginAction);
		
		//��й�ȣã�� ��ư ��ġ ����
		this.searchPwButton.setBounds(
					LoginSizesEnum.LOGIN_RESOURCE_SEARCHPW_BUTTON_POSITION_X.getSize(),
					LoginSizesEnum.LOGIN_RESOURCE_SEARCHPW_BUTTON_POSITION_Y.getSize(),
					LoginSizesEnum.ICON_SIZE_WIDTH.getSize(), 
					LoginSizesEnum.ICON_SIZE_HEIGHT.getSize()
		);
		this.searchPwButton.setName(LoginSizesEnum.BUTTON_NAME_SEARCHPW.getButtonName());
		this.searchPwButton.setBorderPainted(false);
		this.searchPwButton.setContentAreaFilled(false);
		this.searchPwButton.setFocusPainted(false);
		this.searchPwButton.setToolTipText("��й�ȣ ã��");
		this.searchPwButton.addMouseListener(this.loginAction);

		this.searchPwButton.addActionListener(this.loginAction);
		
		
		this.loginPanel.add(this.loginButton);
		this.loginPanel.add(this.joinButton);
		this.loginPanel.add(this.searchIdButton);
		this.loginPanel.add(this.searchPwButton);
	}
	
	public void setTextFieldPosition(){		
		this.idField = new JTextField("", 10);
		this.pwField = new JPasswordField("", 10);
		
		//���̵� �Է�â
		this.idField.setBounds(
					LoginSizesEnum.LOGIN_RESOURCE_ID_FIELD_POSITION_X.getSize(),
					LoginSizesEnum.LOGIN_RESOURCE_ID_FIELD_POSITION_Y.getSize(),
					LoginSizesEnum.SIZE_TEXT_WIDTH.getSize(), 
					LoginSizesEnum.SIZE_TEXT_HEIGHT.getSize()
		);
		this.idField.setFont(this.textFieldFont);
		
		//��й�ȣ �Է�â
		this.pwField.setBounds(
					LoginSizesEnum.LOGIN_RESOURCE_PW_FIELD_POSITION_X.getSize(),
					LoginSizesEnum.LOGIN_RESOURCE_PW_FIELD_POSITION_Y.getSize(),
					LoginSizesEnum.SIZE_TEXT_WIDTH.getSize(), 
					LoginSizesEnum.SIZE_TEXT_HEIGHT.getSize()
		);
		this.pwField.setFont(this.textFieldFont);
		
		this.loginPanel.add(this.idField);
		this.loginPanel.add(this.pwField);
	   
	}

	
	
	public JButton getSearchIdButton() {
		return searchIdButton;
	}

	public JButton getSearchPwButton() {
		return searchPwButton;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public JButton getJoinButton() {
		return joinButton;
	}
	
	public JTextField getIdField() {
		return idField;
	}

	public JPasswordField getPwField() {
		return pwField;
	}

	public BasicFrame getBasicFrame() {
		return basicFrame;
	}
	public SearchPwdPanel getSearchPwdPanel() {
		return searchPwdPanel;
	}


}
