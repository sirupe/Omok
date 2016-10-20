package frames.searchFrames;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import actions.findIDandPW.FindRePwdAction;
import datasDTO.AbstractEnumsDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.ServerActionEnum;
import enums.etc.UserActionEnum;
import enums.etc.UserPositionEnum;
import enums.frames.JoinSizesEnum;
import enums.frames.LoginPanelEnum;
import enums.frames.SearchIDEnum;
import enums.frames.SearchPwdEnum;
import enums.frames.SearchRePwdEnum;
import frames.BasicFrame;

@SuppressWarnings("serial")
public class SearchRePwdPanel extends JPanel {
	private Image backGround;
	
	private JLabel searchPwdLabel;
	private JLabel searchRePwdLabel;
	private JPasswordField searchPwdText;
	private JPasswordField searchRePwdText;
	private JLabel searchRePwdErrorLabel;
	private JButton searchConfirmButton;

	private FindRePwdAction findRePwdAction;
	private SearchPwdFrame searchPwdFrame;
	
	public SearchRePwdPanel(SearchPwdFrame searchPwdFrame) throws IOException {
		this.setLayout(null);
		this.searchPwdFrame = searchPwdFrame;
		this.findRePwdAction = new FindRePwdAction(this);
		this.setsearchPwdTextLabel();
		
		this.addKeyAction(this.searchPwdText, "searchPwdText");
		this.addKeyAction(this.searchRePwdText, "searchRePwdText");
		
	} //생성자
	
	
	//패널 생성 -- 비밀번호 입력, 재비밀번호 입력 텍스트
	public void setsearchPwdTextLabel() throws IOException {
		
		// 패널의 배경이미지
		backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
				SearchPwdEnum.SEARCH_PWD_FRAME_WIDTH.getSize(),
				SearchPwdEnum.SEARCH_PWD_FRAME_HEIGHT.getSize(),
                Image.SCALE_SMOOTH);
		this.add(new JLabel(new ImageIcon(backGround)));

		this.setBounds(
				SearchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_X.getSize(),
				SearchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_Y.getSize(),
				SearchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
				SearchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize()
		);
		
		
		//비밀번호 라벨
		this.searchPwdLabel = new JLabel("PW");
		this.searchPwdLabel.setBounds(SearchRePwdEnum.SEARCH_PWD_LABEL.getRectangle());
		this.searchPwdLabel.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		
		//비밀번호 입력창
		this.searchPwdText = new JPasswordField(10);
		this.searchPwdText.setBounds(SearchRePwdEnum.SEARCH_PWD_TEXTFIELD.getRectangle());
		this.searchPwdText.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());

		// 재비밀번호 라벨
		this.searchRePwdLabel = new JLabel("PW재입력");
		this.searchRePwdLabel.setBounds(SearchRePwdEnum.SEARCH_REPWD_LABEL.getRectangle());
		this.searchRePwdLabel.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		
		// 제비밀번호 텍스트창
		this.searchRePwdText = new JPasswordField(10);
		this.searchRePwdText.setBounds(SearchRePwdEnum.SEARCH_REPWD_TEXTFIELD.getRectangle());
		this.searchRePwdText.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
		
		// 에러 메세지 라벨
		String searchCheckAnswer = "";
		this.searchRePwdErrorLabel = new JLabel(searchCheckAnswer);
		searchRePwdErrorLabel.setBounds(SearchRePwdEnum.SEARCH_ERROR_LABEL.getRectangle());

		searchRePwdErrorLabel.setFont(SearchIDEnum.LABELFONT_ERROR.getFont());
		searchRePwdErrorLabel.setForeground(SearchIDEnum.LABELCOLOR_ERROR.getColor());
		
		//확인 버튼창
		this.searchConfirmButton = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/signUp/confirm.jpg")), 
						0, 
						0, 
						SearchRePwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().width,
						SearchRePwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
				}		
			}
		};	
		
		this.searchConfirmButton.setBounds(SearchRePwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle());
		this.searchConfirmButton.setName(LoginPanelEnum.BUTTON_NAME_SEARCH_CONFIRMBUTTON.getButtonName());
		this.add(searchConfirmButton);
		this.searchConfirmButton.addActionListener(this.findRePwdAction);
			
		this.add(searchPwdLabel);
		this.add(searchRePwdLabel);
		this.add(searchRePwdErrorLabel);
		
	}

		public void pwdMsgLabel(String searchCheckAnswer){
			this.setLayout(null);
			this.searchRePwdErrorLabel.setBounds(SearchRePwdEnum.SEARCH_ERROR_LABEL.getRectangle());
			this.searchRePwdErrorLabel.setBackground(Color.red);
			this.add(this.searchRePwdErrorLabel);
			
			this.searchRePwdErrorLabel.setFont(SearchIDEnum.LABELFONT_DEFAULT.getFont());
			this.searchRePwdErrorLabel.setForeground(Color.blue);
			this.searchRePwdErrorLabel.setText(searchCheckAnswer);
		}
		
		public void pwdMsgLabelReset() {
			String init = "";
			this.searchRePwdErrorLabel.setText(init);
		}
		
		public void addKeyAction(JComponent comp, String Name) {
			comp.setName(Name);
			comp.setFont(JoinSizesEnum.JOIN_COMPFONT_DEFAULT.getFont());
			comp.addKeyListener(this.findRePwdAction);
			this.add(comp);	
		}
		
		//변경된 패스워드 확인창 
		public void doSearchChangeConfirmPanel() {
			this.searchPwdFrame.doSearchChangeConfirmPanel();
		}
		
		//재비밀번호 저장해서 서버로 보냄
		public void pwdChange() {
			BasicFrame basicFrame = this.searchPwdFrame.getBasicFrame();

			char[] pwdArr = this.searchPwdText.getPassword();
			String pwd = new String(pwdArr, 0, pwdArr.length);
			String userId =  this.getSearchPwdFrame().getSearchPwdPanel().getSearchIdTextField().getText();
			
			UserPersonalInfoDTO userPersonalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_PW);
			
			userPersonalDTO.setUserAction(UserActionEnum.USER_SEARCH_PASSWD);
			userPersonalDTO.setUserID(userId);
			userPersonalDTO.setUserPasswd(pwd);
			
			try {
				basicFrame.sendDTO(userPersonalDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		//비밀번호  DB에 보낸 후 결과 값 나오기
		public void searchPwdSuccess(AbstractEnumsDTO data) {
			if(data.getServerAction() == ServerActionEnum.SEARCH_PASSWD_SUCCESS) {
				this.pwdMsgLabel("<html>비밀번호 변경 성공");
				this.searchPwdFrame.doSearchChangeConfirmPanel();
				
			} else {
				this.pwdMsgLabel("<html>비밀번호 설정 실패" 
						+"<br>다시설정해주세요.<br></html>");
				this.getSearchPwdText().setEditable(true);
				this.getsearchRePwdText().setEditable(true);
			}
			
		}
	
	public SearchPwdFrame getSearchPwdFrame() {
		return searchPwdFrame;
	}
	
	public JTextField getSearchPwdText() {
		return searchPwdText;
	}
	
	public JTextField getsearchRePwdText() {
		return searchRePwdText;
	}
	
	public JLabel getSearchRePwdErrorLabel() {
		return searchRePwdErrorLabel;
	}
	
	public JButton getSearchConfirmButton() {
		return searchConfirmButton;
	}
	
}
