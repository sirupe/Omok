package frames.serchFrames;


import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import actions.findIDandPW.FindIDAction;
import enums.frames.SearchIdEnum;
import frames.LoginPanel;

public class SearchIdFrame extends JFrame {
	private JLabel searchIdLabel;
	private JLabel searchemailLabel;
	private JLabel searchErrorMsgLabel;
	
	private JTextField searchIdTextField;
	private JTextField searchemailTextField;
	private String searchError;
	
	private JButton searchConfirmButton;
	private Image backGround;
	
	private FindIDAction findIDAction;

	public SearchIdFrame(LoginPanel loginPanel) throws IOException {
		this.findIDAction = new FindIDAction(this);
		
		//라벨 생성
		this.searchIdLabel        = new JLabel("이름");
		this.searchemailLabel     = new JLabel("Email"); 

		//텍스트 필드생성
		this.searchIdTextField    = new JTextField(10);
		this.searchemailTextField = new JTextField(10);
		
		//에러 메세지 레이블
		this.searchError = "ID,Pwd 입력 오류";
		this.searchErrorMsgLabel = new JLabel(searchError);
		this.searchErrorMsgLabel.setForeground(SearchIdEnum.LABELCOLOR_ERROR.getColor());
		
		//버튼 생성
		searchConfirmButton  = new JButton();
		searchConfirmButton.setBorderPainted(false);
		searchConfirmButton.setFocusPainted(false);
		searchConfirmButton.setContentAreaFilled(false);
		
		this.add(searchConfirmButton);
		
		//폰트
		Font defaultFont = SearchIdEnum.LABELFONT_DEFAULT.getFont();
		Font errorFont = SearchIdEnum.LABELFONT_ERROR.getFont();
		this.searchIdLabel.setFont(defaultFont);
		this.searchemailLabel.setFont(defaultFont);
		this.searchErrorMsgLabel.setFont(errorFont);
		
		this.searchemailTextField.setFont(defaultFont);
		this.searchIdTextField.setFont(defaultFont);
	   
	    //배경화면	
		
		backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
				SearchIdEnum.SEARCHFRAME_SIZE_WIDTH.getSize(),
				SearchIdEnum.SEARCHFRAME_SIZE_HEIGHT.getSize(),
                Image.SCALE_SMOOTH);

		this.setContentPane(new JLabel(new ImageIcon(backGround)));
				
		this.setBounds(
			   SearchIdEnum.SEARCHFRAME_POSITION_X.getSize(),
			   SearchIdEnum.SEARCHFRAME_POSITION_Y.getSize(),
			   SearchIdEnum.SEARCHFRAME_SIZE_WIDTH.getSize(),
			   SearchIdEnum.SEARCHFRAME_SIZE_HEIGHT.getSize()
		);
		
		//레이블, 텍스트, 버튼 불러오기
			    this.setLabelPosition();
			    this.setTextFieldPosition();
			    this.setButtonPosition();
				
				this.setLayout(null);
			    this.setTitle("아이디찾기");
			    this.setVisible(true);
			    this.setResizable(false);	   
			 
	}
	//이름, 이메일 , 에러 메세지 위치 및 크기
	public void setLabelPosition() {
		this.searchIdLabel.setBounds(SearchIdEnum.SEARCH_ID_LABEL.getRectangle());
		this.searchemailLabel.setBounds(SearchIdEnum.SEARCH_EMAIL_LABEL.getRectangle());
		this.searchErrorMsgLabel.setBounds(SearchIdEnum.SEARCH_ERROR_LABEL.getRectangle());
		this.add(searchIdLabel);
		this.add(searchemailLabel);
		this.add(searchErrorMsgLabel);
    }
	// 이름 이메일 위치 및 크기
    public void setTextFieldPosition() {
    	this.searchIdTextField.setBounds(SearchIdEnum.SEARCH_ID_TEXTFIELD.getRectangle());
    	this.searchemailTextField.setBounds(SearchIdEnum.SEARCH_EMAIL_TEXTFIELD.getRectangle());
    	this.add(searchIdTextField);
    	this.add(searchemailTextField);
    }
	    
    public void setButtonPosition() throws IOException {
    	
    	// 버튼이미지 짤리는걸 이미지 간격이동으로 해결해줌
    	this.searchConfirmButton.setIconTextGap(this.searchConfirmButton.getIconTextGap() - 15);
    	

    	this.searchConfirmButton.setIcon(
    			new ImageIcon(ImageIO.read(
    				new File("resources/signUp/confirm.jpg")).getScaledInstance(
    						SearchIdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().width,
    						SearchIdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().height,
    						Image.SCALE_AREA_AVERAGING))
    		);
    	this.searchConfirmButton.setBounds(SearchIdEnum.SEARCH_CONFIRM_BUTTON.getRectangle());
    	this.add(searchConfirmButton);    	 
    }
    
    public void searchFailLabelReset(){
		String init = "";
		this.searchErrorMsgLabel.setText(init);
	}
    
    
    public String getsearchError() {
    	return searchError;
    }
    public JLabel getErrorMsgLabel(){
    	return searchErrorMsgLabel;
    }
    
    public JButton getConfirmButton() {
    	return searchConfirmButton;
    }
    
    public JTextField getEmailField() {
    	return searchemailTextField;
    }
    
    public JTextField getIdField() {
    	return searchIdTextField;
    }
//    
//	public static void main(String[] args) throws IOException {
//		new SearchIdFrame();
//	}

}
