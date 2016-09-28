package frames.searchFrames;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enums.frames.CorrectEnum;
import enums.frames.SearchIdEnum;

public class SearchIdPanel extends JPanel {
	
	private JLabel searchIdLabel;
	private JLabel searchemailLabel;
	private JLabel searchErrorMsgLabel;
	private JTextField searchIdTextField;
	private JTextField searchemailTextField;
	private String searchError;
	private JButton searchConfirmButton;
	private JButton backButton;
	private Image backGround;
	
	private SearchIdFrame searchIdFrame;
	
	
	public SearchIdPanel(SearchIdFrame searchIdFrame) throws IOException {
		
		this.searchIdFrame = searchIdFrame;
		
		Font defaultFont = SearchIdEnum.LABELFONT_DEFAULT.getFont();
		Font errorFont = SearchIdEnum.LABELFONT_ERROR.getFont();

		this.setBounds(CorrectEnum.DROPOUT_FRAME_SIZE_RECT.getRect());
		
		//이름
		this.searchIdLabel = new JLabel("이름");
		this.searchIdLabel.setFont(defaultFont);
		this.searchIdLabel.setBounds(SearchIdEnum.SEARCH_ID_LABEL.getRectangle());
		//이메일
		this.searchemailLabel = new JLabel("email");
		this.searchemailLabel.setFont(defaultFont);
		this.searchemailLabel.setBounds(SearchIdEnum.SEARCH_EMAIL_LABEL.getRectangle());
		//에러메세지
		//TODO
		System.out.println(SearchIdEnum.SEARCH_ERROR_LABEL.getRectangle().toString());
		System.out.println(CorrectEnum.DROPOUT_FRAME_SIZE_RECT.getRect());
		
		//에러메시지크기
		this.searchError = new String("ID,Pwd 입력 오류");
		this.searchErrorMsgLabel = new JLabel(searchError);
		this.searchErrorMsgLabel.setForeground(SearchIdEnum.LABELCOLOR_ERROR.getColor());
		this.searchErrorMsgLabel.setFont(errorFont);
		this.searchErrorMsgLabel.setBounds(SearchIdEnum.SEARCH_ERROR_LABEL.getRectangle());
		//아이디텍스트필드
		this.searchIdTextField = new JTextField(10);
		this.searchIdTextField.setFont(defaultFont);
		this.searchIdTextField.setBounds(SearchIdEnum.SEARCH_ID_TEXTFIELD.getRectangle());
		//이메일텍스트필드
		this.searchemailTextField = new JTextField(10);
		this.searchemailTextField.setFont(defaultFont);
		this.searchemailTextField.setBounds(SearchIdEnum.SEARCH_EMAIL_TEXTFIELD.getRectangle());
		
		//확인버튼
		this.searchConfirmButton = new JButton();
		this.searchConfirmButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/signUp/confirm.jpg")).getScaledInstance(
						SearchIdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().width,
						SearchIdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().height,
						Image.SCALE_AREA_AVERAGING))
		);
		this.searchConfirmButton.setBounds(SearchIdEnum.SEARCH_CONFIRM_BUTTON.getRectangle());
		this.searchConfirmButton.setBorderPainted(false);
		this.searchConfirmButton.setFocusPainted(false);
		this.searchConfirmButton.setContentAreaFilled(false);
		this.searchConfirmButton.setName("confirmButton");
		this.searchConfirmButton.setIconTextGap(this.searchConfirmButton.getIconTextGap() - 15);
		
    	//취소버튼
		this.backButton = new JButton();
		this.backButton.setIcon(
			new ImageIcon(ImageIO.read(
    				new File("resources/forgotID/backButton.png")).getScaledInstance(
    						SearchIdEnum.SEARCH_BACK_BUTTON.getRectangle().width,
    						SearchIdEnum.SEARCH_BACK_BUTTON.getRectangle().height,
    						Image.SCALE_AREA_AVERAGING))
    		);
		this.backButton.setBounds(SearchIdEnum.SEARCH_BACK_BUTTON.getRectangle());
		this.backButton.setBorderPainted(false);
		this.backButton.setFocusPainted(false);
		this.backButton.setContentAreaFilled(false);
		this.searchConfirmButton.setName("backButton");
		
		
		this.add(searchIdLabel);
		this.add(searchemailLabel);
		this.add(searchErrorMsgLabel);
		this.add(searchIdTextField);
    	this.add(searchemailTextField);
    	this.add(searchConfirmButton);
		this.add(backButton);
    	
        this.setLayout(null);
        this.setVisible(true); 

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
    
    public JButton getBackButton() {
    	return backButton;
    }
    
    public JTextField getEmailField() {
    	return searchemailTextField;
    }
    
    public JTextField getIdField() {
    	return searchIdTextField;
    }
    
    public SearchIdFrame getSearchIdFrame() {
    	return searchIdFrame;
    }

}
