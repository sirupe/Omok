package frames.searchFrames;

import java.awt.CardLayout;
import java.awt.Font;
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

import actions.findIDandPW.FindIDAction;
import datasDTO.AbstractEnumsDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserPositionEnum;
import enums.frames.CorrectEnum;
import enums.frames.JoinSizesEnum;
import enums.frames.SearchIDEnum;
import frames.BasicFrame;

@SuppressWarnings("serial")
public class SearchIdPanel extends JPanel {
	
	private JLabel searchNameLabel;
	private JLabel searchemailLabel;
	private JLabel searchErrorMsgLabel;
	private JTextField searchNameTextField;
	private JTextField searchEmailTextField;
	
	private JButton searchConfirmButton;
	private JButton backButton;
	
	private FindIDAction findIDAction;
	private SearchIdFrame searchIdFrame;
	private JPanel cardLayoutPanel;
	
	public SearchIdPanel(SearchIdFrame searchIdFrame) throws IOException {
		this.cardLayoutPanel = new JPanel();
		this.searchIdFrame = searchIdFrame;
		
		this.findIDAction = new FindIDAction(this);
		JoinSizesEnum.JOIN_MESSAGE.getMessageMap();

		Font defaultFont = SearchIDEnum.LABELFONT_DEFAULT.getFont();

		this.setBounds(CorrectEnum.DROPOUT_FRAME_SIZE_RECT.getRect());
		
		//이름
		this.searchNameLabel = new JLabel("이름");
		this.searchNameLabel.setFont(defaultFont);
		this.searchNameLabel.setBounds(SearchIDEnum.SEARCH_ID_LABEL.getRectangle());
		
		//이메일
		this.searchemailLabel = new JLabel("email");
		this.searchemailLabel.setFont(defaultFont);
		this.searchemailLabel.setBounds(SearchIDEnum.SEARCH_EMAIL_LABEL.getRectangle());
		
		//에러메시지
		String searchMsg = "";
		this.searchErrorMsgLabel = new JLabel(searchMsg);
		this.searchErrorMsgLabel.setBounds(SearchIDEnum.SEARCH_ERROR_LABEL.getRectangle());
		this.searchErrorMsgLabel.setForeground(SearchIDEnum.LABELCOLOR_ERROR.getColor());
		this.searchErrorMsgLabel.setFont(SearchIDEnum.LABELFONT_ERROR.getFont());

		//아이디텍스트필드
		this.searchNameTextField = new JTextField(10);

		this.searchNameTextField.setFont(defaultFont);
		this.searchNameTextField.setBounds(SearchIDEnum.SEARCH_ID_TEXTFIELD.getRectangle());
		//이메일텍스트필드
		this.searchEmailTextField = new JTextField(10);
		this.searchEmailTextField.setFont(defaultFont);
		this.searchEmailTextField.setBounds(SearchIDEnum.SEARCH_EMAIL_TEXTFIELD.getRectangle());
	
		//확인버튼
		this.searchConfirmButton = new JButton();
		this.searchConfirmButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/signUp/confirm.jpg")).getScaledInstance(
						SearchIDEnum.SEARCH_CONFIRM_BUTTON.getRectangle().width,
						SearchIDEnum.SEARCH_CONFIRM_BUTTON.getRectangle().height,
						Image.SCALE_AREA_AVERAGING))
		);
		
		this.searchConfirmButton.setBounds(SearchIDEnum.SEARCH_CONFIRM_BUTTON.getRectangle());
		this.searchConfirmButton.setBorderPainted(false);
		this.searchConfirmButton.setFocusPainted(false);
		this.searchConfirmButton.setContentAreaFilled(false);
		this.searchConfirmButton.setName(SearchIDEnum.BUTTON_NAME_CONFIRM.getButtonName());
		this.searchConfirmButton.setIconTextGap(this.searchConfirmButton.getIconTextGap() - 15);
		
    	//취소버튼
		this.backButton = new JButton();
		this.backButton.setIcon(
			new ImageIcon(ImageIO.read(
    				new File("resources/forgotID/backButton.png")).getScaledInstance(
    						SearchIDEnum.SEARCH_BACK_BUTTON.getRectangle().width,
    						SearchIDEnum.SEARCH_BACK_BUTTON.getRectangle().height,
    						Image.SCALE_AREA_AVERAGING))
    		);
		this.backButton.setBounds(SearchIDEnum.SEARCH_BACK_BUTTON.getRectangle());
		this.backButton.setBorderPainted(false);
		this.backButton.setFocusPainted(false);
		this.backButton.setContentAreaFilled(false);
		this.backButton.setName(SearchIDEnum.BUTTON_NAME_BACK.getButtonName());

		
		this.addKeyAction(this.searchNameTextField, "nameTextField");
		this.addKeyAction(this.searchEmailTextField, "emailTextField");
		this.add(this.cardLayoutPanel);
		this.cardLayoutPanel.setOpaque(false);
		this.cardLayoutPanel.add(searchNameLabel);
		this.cardLayoutPanel.add(searchemailLabel);
		this.cardLayoutPanel.add(searchErrorMsgLabel);
		this.cardLayoutPanel.add(searchNameTextField);
    	this.cardLayoutPanel.add(searchEmailTextField);
    	this.cardLayoutPanel.add(searchConfirmButton);
		this.cardLayoutPanel.add(backButton);
		this.searchConfirmButton.addActionListener(this.findIDAction);
		this.backButton.addActionListener(this.findIDAction);
        this.cardLayoutPanel.setLayout(null);
        this.setLayout(new CardLayout());
        this.setVisible(true); 
	}
	
	//findIdResult 에서는 호출만.해봐.
	public void findIdResult(AbstractEnumsDTO data) {
		
		UserPersonalInfoDTO result = (UserPersonalInfoDTO) data;
		SearchIdResultPanel searchIdResultPanel = this.searchIdFrame.getSearchIdResultPanel();
		
		//user ID가 널이 아니면 확인버튼이 실행되도록 하기
		if(result.getUserID() == null) {
			//서치아이디 패널의Label창에 떠야하
			this.errorMsg("정보입력오류:(");
		} else {
			searchIdResultPanel.showUserIdLael(result.getUserID());
			this.doConfirmButton();
		}
	}
	
	//에러메시지
	public void errorMsg(String errorMsgLabel) {
		this.setLayout(null);
		this.searchErrorMsgLabel.setBounds(SearchIDEnum.SEARCH_ERROR_LABEL.getRectangle());
		this.add(this.searchErrorMsgLabel);
		this.searchErrorMsgLabel.setText(errorMsgLabel);
	}
	
	//에러메시지 없애기
	public void errorMsgReset() {
		this.searchErrorMsgLabel.setText(null);	
	}
	
	//name, email 텍스트 필드 액션
	private void addKeyAction(JComponent comp, String name) {
		comp.setName(name);
		comp.addKeyListener(this.findIDAction);
		this.add(comp);
	}	
	
	//확인번튼시 실행
	public void doConfirmButton() {
		this.searchIdFrame.doConfirmButton();
	}
	
	//취소버튼시 실행
	public void doCancleButton(){	
		//프레임의 취소버튼을 실행
		this.searchIdFrame.doCancleButton();
	}
	
	//아이디 찾기 메소드
	public void doCheckId() {
		String name = this.searchNameTextField.getText();
		String email = this.searchEmailTextField.getText();
		
		UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_ID);
		personalDTO.setUserEmail(email);
		personalDTO.setUserName(name);

		try {
			
			BasicFrame basicFrame = this.searchIdFrame.getBasicFrame();
			basicFrame.sendDTO(personalDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

    public JLabel getErrorMsgLabel(){
    	return searchErrorMsgLabel;
    }
    
    public JButton getConfirmButton() {
    	return searchConfirmButton;
    }
    
    public JButton getBackButton() {
    	return backButton;
    }
    
    public JTextField getEmailTextField() {
    	return searchEmailTextField;
    }
    
    public JTextField getNameTextField() {
    	return searchNameTextField;
    }
 
    public SearchIdFrame getSearchIdFrame() {
    	return searchIdFrame;
    }
    
    public FindIDAction getFindIDAction() {
		return findIDAction;
	}
    
}
