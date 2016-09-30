package frames.searchFrames;


import java.awt.Color;


import java.awt.Font;
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
import javax.swing.JTextField;

import actions.findIDandPW.FindRePwdAction;
import enums.frames.JoinSizesEnum;
import enums.frames.LoginSizesEnum;
import enums.frames.SearchIdEnum;
import enums.frames.SearchPwdEnum;
import enums.frames.SearchRePwdEnum;

@SuppressWarnings("serial")
public class SearchRePwdPanel extends JPanel {
	private JPanel searchRePwdPanel;
	private Image backGround;
	
	private JLabel searchPwdLabel;
	private JLabel searchRePwdLabel;
	private JTextField searchPwdText;
	private JTextField searchRePwdText;
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
	// 패널 생성 -- 비밀번호 입력, 재비밀번호 입력 텍스트
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
		this.searchPwdLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		
		//비밀번호 입력창
		this.searchPwdText = new JTextField();
		this.searchPwdText.setBounds(SearchRePwdEnum.SEARCH_PWD_TEXTFIELD.getRectangle());
		this.searchPwdText.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());

		// 재비밀번호 라벨
		this.searchRePwdLabel = new JLabel("PW재입력");
		this.searchRePwdLabel.setBounds(SearchRePwdEnum.SEARCH_REPWD_LABEL.getRectangle());
		this.searchRePwdLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		
		// 제비밀번호 텍스트창
		this.searchRePwdText = new JTextField();
		this.searchRePwdText.setBounds(SearchRePwdEnum.SEARCH_REPWD_TEXTFIELD.getRectangle());
		this.searchRePwdText.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
		
		// 에러 메세지 라벨
		
		String searchCheckAnswer = "";
		this.searchRePwdErrorLabel = new JLabel(searchCheckAnswer);
		searchRePwdErrorLabel.setBounds(SearchRePwdEnum.SEARCH_ERROR_LABEL.getRectangle());
		searchRePwdErrorLabel.setFont(SearchIdEnum.LABELFONT_ERROR.getFont());
		searchRePwdErrorLabel.setForeground(SearchIdEnum.LABELCOLOR_ERROR.getColor());
		
		//확인 버튼창
		
		this.searchConfirmButton = new JButton(LoginSizesEnum.BUTTON_NAME_SEARCH_CONFIRMBUTTON.getButtonName()) {
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
		this.add(searchConfirmButton);
		this.searchConfirmButton.addActionListener(this.findRePwdAction);
//				
		this.add(searchPwdLabel);
		//this.add(searchRePwdText);
		//this.add(searchPwdText);
		this.add(searchRePwdLabel);
		this.add(searchRePwdErrorLabel);
		
	}

	
		public void pwdMsgLabel(String searchCheckAnswer){
			this.setLayout(null);
			this.searchRePwdErrorLabel.setBounds(SearchRePwdEnum.SEARCH_ERROR_LABEL.getRectangle());
//			this.searchRePwdErrorLabel.setOpaque(false);
			this.searchRePwdErrorLabel.setBackground(Color.red);
			this.add(this.searchRePwdErrorLabel);
			
			this.searchRePwdErrorLabel.setFont(SearchIdEnum.LABELFONT_DEFAULT.getFont());
			this.searchRePwdErrorLabel.setForeground(Color.blue);
			this.searchRePwdErrorLabel.setText(searchCheckAnswer);
		}
		
		public void pwdMsgLabelReset() {
			String init = "";
			this.searchRePwdErrorLabel.setText(init);
		}
		
		public void addKeyAction(JComponent comp, String Name) {
//			EmptyBorder border = JoinSizesEnum.LABEL_DEFAULT_BORDER.getBorder();
			comp.setName(Name);
//			comp.setBorder(border);
			comp.setFont(JoinSizesEnum.JOIN_COMPFONT_DEFAULT.getFont());
			comp.addKeyListener(this.findRePwdAction);
			this.add(comp);	
		}
		public void doSearchChangeConfirmPanel() {
			this.searchPwdFrame.doCheckButton();
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
