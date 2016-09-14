package frames;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import datas.UserPersonalInfoDTO;
import actions.login.LoginAction;
import enums.LoginFrameSizesEnum;
import enums.UserPositionEnum;

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
	private Font loginFailFont;
	
	private JTextArea loginFailText;
	
	//이미지 화면 비율에 맞춰서 바뀌게 하기 위해 이미지 사용
	private Image ID, IDreimage, PW, PWreimage;
	private Image join, joinreimage;
	private Image login, loginreimage;
	private Image searchid, searchidreimage;
	private Image searchpw, searchpwreimage;
	
	//테스트용 스트링선언
	private String dataId;
	private String dataPw;
	private String putId;
	private String putPw;
	
	private JPanel loginPanel;
	private BasicFrame basicFrame;
	
	private LoginAction loginAction;
	
	public LoginPanel(BasicFrame basicFrame) throws IOException {
		this.loginAction = new LoginAction(this);
		
		this.loginPanel	= new JPanel();
		this.dataId     = new String("1234");
		this.dataPw     = new String("1234");   
		
		this.loginPanel.setLayout(null);
		this.loginPanel.setOpaque(false);
		this.basicFrame = basicFrame;
		//프레임 화면 출력 위치 설정
		
		
		UserPersonalInfoDTO dto = new UserPersonalInfoDTO(UserPositionEnum.POSITION_LOGIN);
		dto.setUserID("test");
		dto.setUserPasswd("1234");
		this.basicFrame.getClientSender().getClientOS().writeObject(dto);
		
		this.setBounds(   
		      LoginFrameSizesEnum.LOGIN_FRAME_POSITION_X.getSize(),
		      LoginFrameSizesEnum.LOGIN_FRAME_POSITION_Y.getSize(),
		      LoginFrameSizesEnum.LOGIN_FRAME_SIZE_WIDTH.getSize(), 
		      LoginFrameSizesEnum.LOGIN_FRAME_SIZE_HEIGHT.getSize()
		);
		
		//아이디, 비밀번호 입력하는 텍스트필드 설정
		this.setTextFieldPosition();
		
		//로그인, 회원가입, 아이디찾기, 비밀번호 찾기 버튼 위치 설정
		this.setButtonPosition();
		
		//아이디, 비밀번호 입력 알려주는 이미지 설정
		this.setIdPwImagePositon();
		
		this.add(this.loginPanel);
		this.setLayout(new CardLayout());

	}
	
	//아이디 또는 비밀번호 오류라서 알려주는 텍스트
	public void loginFail(){
		this.setLayout(null);
		this.loginFailText = new JTextArea();
		this.textFieldFont = new Font("", Font.BOLD, 20);      
		this.loginFailText.setBounds(
					LoginFrameSizesEnum.LOGIN_FAIL_TEXT_POSITION_X.getSize(),
					LoginFrameSizesEnum.LOGIN_FAIL_TEXT_POSITION_Y.getSize(),
					LoginFrameSizesEnum.LOGIN_FAIL_TEXT_WIDTH.getSize(), 
					LoginFrameSizesEnum.LOGIN_FAIL_TEXT_HEIGTH.getSize()
		);
		this.add(this.loginFailText);
		
		this.loginFailFont = new Font("", Font.PLAIN, 13);
		this.loginFailText.setFont(loginFailFont);
		this.loginFailText.setForeground(Color.RED);
		this.loginFailText.setBackground(Color.BLACK);
		this.loginFailText.setText("아이디 또는 비밀번호 오류");
	}
	
	public void loginFailTextReset(){
		this.loginFailText.setText("");
	}
	
	public void setIdPwImagePositon() throws IOException{
		this.ID        = ImageIO.read(new File("resources/login/ID.png"));
		this.IDreimage = this.ID.getScaledInstance(
			               LoginFrameSizesEnum.SIZE_LABEL_WIDTH.getSize(),
			               LoginFrameSizesEnum.SIZE_LABEL_HEIGHT.getSize(),
			               Image.SCALE_SMOOTH);
		
		this.idImage   = new JButton(new ImageIcon(this.IDreimage));
		
		//아이디 이미지 위치 설정
		this.idImage.setBounds(
					LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_X.getSize(),
					LoginFrameSizesEnum.LOGIN_RESOURCE_ID_POSITION_Y.getSize(),
					LoginFrameSizesEnum.SIZE_LABEL_WIDTH.getSize(), 
					LoginFrameSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.idImage.setBorderPainted(false);
		this.idImage.setContentAreaFilled(false);
		this.idImage.setFocusPainted(false);
		
		this.PW        = ImageIO.read(new File("resources/login/PW.png"));
		this.PWreimage = PW.getScaledInstance(
					LoginFrameSizesEnum.SIZE_LABEL_WIDTH.getSize(), 
					LoginFrameSizesEnum.SIZE_LABEL_HEIGHT.getSize(),
					Image.SCALE_SMOOTH);
		
		this.passwordImage = new JButton(new ImageIcon(this.PWreimage));  
		
		//비밀번호 이미지 위치 설정
		this.passwordImage.setBounds(
					LoginFrameSizesEnum.LOGIN_RESOURCE_PASSWORD_POSITION_X.getSize(),
					LoginFrameSizesEnum.LOGIN_RESOURCE_PASSWORD_POSITION_Y.getSize(), 
					LoginFrameSizesEnum.SIZE_LABEL_WIDTH.getSize(), 
					LoginFrameSizesEnum.SIZE_LABEL_HEIGHT.getSize()
		);
		
		this.passwordImage.setBorderPainted(false);
		this.passwordImage.setContentAreaFilled(false);
		this.passwordImage.setFocusPainted(false);
		
		this.loginPanel.add(this.idImage);
		this.loginPanel.add(this.passwordImage);
	}
	
	public void setButtonPosition() throws IOException{
	   //이미지 비율 변환
		this.login  	  = ImageIO.read(new File("resources/login/login.jpg"));
		this.loginreimage = login.getScaledInstance(
		               LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize(),
		               LoginFrameSizesEnum.LOGIN_ICON_HEIGHT.getSize(),
		               Image.SCALE_AREA_AVERAGING);
		this.loginButton = new JButton(new ImageIcon(this.loginreimage));
	  
		this.join 		 = ImageIO.read(new File("resources/login/signup.png"));
		this.joinreimage = join.getScaledInstance(
		               LoginFrameSizesEnum.ICON_SIZE_WIDTH.getSize(),
		               LoginFrameSizesEnum.ICON_SIZE_HEIGHT.getSize(),
		               Image.SCALE_AREA_AVERAGING);
		this.joinButton = new JButton(new ImageIcon(this.joinreimage));
		
		this.searchid 		 = ImageIO.read(new File("resources/login/forgotID.png"));
		this.searchidreimage = searchid.getScaledInstance(
		               LoginFrameSizesEnum.ICON_SIZE_WIDTH.getSize(),
		               LoginFrameSizesEnum.ICON_SIZE_HEIGHT.getSize(),
		               Image.SCALE_AREA_AVERAGING);
		this.searchIdButton = new JButton(new ImageIcon(this.searchidreimage));
		
		this.searchpw  		 = ImageIO.read(new File("resources/login/forgotPW.png"));
		this.searchpwreimage = searchpw.getScaledInstance(
		               LoginFrameSizesEnum.ICON_SIZE_WIDTH.getSize(),
		               LoginFrameSizesEnum.ICON_SIZE_HEIGHT.getSize(),
		               Image.SCALE_AREA_AVERAGING);
		this.searchPwButton = new JButton(new ImageIcon(this.searchpwreimage));
		
		//로그인 버튼 위치 설정
		this.loginButton.setBounds(
					LoginFrameSizesEnum.LOGIN_RESOURCE_LOGIN_BUTTON_POSITION_X.getSize(),
					LoginFrameSizesEnum.LOGIN_RESOURCE_LOGIN_BUTTON_POSITION_Y.getSize(),
					LoginFrameSizesEnum.LOGIN_ICON_WIDTH.getSize(), 
					LoginFrameSizesEnum.LOGIN_ICON_HEIGHT.getSize()
		);
			
		this.loginButton.setBorderPainted(false);
		this.loginButton.setContentAreaFilled(false);
		this.loginButton.setFocusPainted(false);
		this.loginButton.setToolTipText("로그인");
		
		//나중에 액션 등록 할때 this에 넣으면 됨
		this.loginButton.addActionListener(this.loginAction);
		
		//회원가입 버튼 위치 설정
		this.joinButton.setBounds(
					LoginFrameSizesEnum.LOGIN_RESOURCE_JOIN_BUTTON_POSITION_X.getSize(),
					LoginFrameSizesEnum.LOGIN_RESOURCE_JOIN_BUTTON_POSITION_Y.getSize(),
					LoginFrameSizesEnum.ICON_SIZE_WIDTH.getSize(), 
					LoginFrameSizesEnum.ICON_SIZE_HEIGHT.getSize()
		);
		
		this.joinButton.setBorderPainted(false);
		this.joinButton.setContentAreaFilled(false);
		this.joinButton.setFocusPainted(false);
		this.joinButton.setToolTipText("회원가입");
		
		this.joinButton.addActionListener(this.loginAction);
		
		//아이디찾기 버튼 위치 설정
		this.searchIdButton.setBounds(
					LoginFrameSizesEnum.LOGIN_RESOURCE_SEARCHID_BUTTON_POSITION_X.getSize(),
					LoginFrameSizesEnum.LOGIN_RESOURCE_SEARCHID_BUTTON_POSITION_Y.getSize(),
					LoginFrameSizesEnum.ICON_SIZE_WIDTH.getSize(), 
					LoginFrameSizesEnum.ICON_SIZE_HEIGHT.getSize()
		);
		
		this.searchIdButton.setBorderPainted(false);
		this.searchIdButton.setContentAreaFilled(false);
		this.searchIdButton.setFocusPainted(false);
		this.searchIdButton.setToolTipText("아이디 찾기");
		
		this.searchIdButton.addActionListener(this.loginAction);
		
		//비밀번호찾기 버튼 위치 설정
		this.searchPwButton.setBounds(
					LoginFrameSizesEnum.LOGIN_RESOURCE_SEARCHPW_BUTTON_POSITION_X.getSize(),
					LoginFrameSizesEnum.LOGIN_RESOURCE_SEARCHPW_BUTTON_POSITION_Y.getSize(),
					LoginFrameSizesEnum.ICON_SIZE_WIDTH.getSize(), 
					LoginFrameSizesEnum.ICON_SIZE_HEIGHT.getSize()
		);
		
		this.searchPwButton.setBorderPainted(false);
		this.searchPwButton.setContentAreaFilled(false);
		this.searchPwButton.setFocusPainted(false);
		this.searchPwButton.setToolTipText("비밀번호 찾기");
		
		this.searchPwButton.addActionListener(this.loginAction);     
		
		this.loginPanel.add(this.loginButton);
		this.loginPanel.add(this.joinButton);
		this.loginPanel.add(this.searchIdButton);
		this.loginPanel.add(this.searchPwButton);
	}
	
	public void setTextFieldPosition(){		
		this.idField = new JTextField("", 10);
		this.pwField = new JPasswordField("", 10);
		
		//아이디 입력창
		this.idField.setBounds(
					LoginFrameSizesEnum.LOGIN_RESOURCE_ID_FIELD_POSITION_X.getSize(),
					LoginFrameSizesEnum.LOGIN_RESOURCE_ID_FIELD_POSITION_Y.getSize(),
					LoginFrameSizesEnum.SIZE_TEXT_WIDTH.getSize(), 
					LoginFrameSizesEnum.SIZE_TEXT_HEIGHT.getSize()
		);
		this.idField.setFont(this.textFieldFont);
		
		//비밀번호 입력창
		this.pwField.setBounds(
					LoginFrameSizesEnum.LOGIN_RESOURCE_PW_FIELD_POSITION_X.getSize(),
					LoginFrameSizesEnum.LOGIN_RESOURCE_PW_FIELD_POSITION_Y.getSize(),
					LoginFrameSizesEnum.SIZE_TEXT_WIDTH.getSize(), 
					LoginFrameSizesEnum.SIZE_TEXT_HEIGHT.getSize()
		);
		this.pwField.setFont(this.textFieldFont);
		
		this.loginPanel.add(this.idField);
		this.loginPanel.add(this.pwField);
	   
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public JTextField getIdField() {
		return idField;
	}

	public JPasswordField getPwField() {
		return pwField;
	}

	public String getDataId() {
		return dataId;
	}

	public String getDataPw() {
		return dataPw;
	}

	public BasicFrame getBasicFrame() {
		return basicFrame;
	}
}
