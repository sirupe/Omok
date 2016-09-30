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

import actions.findIDandPW.FindIdAction;
import enums.frames.CorrectEnum;
import enums.frames.JoinSizesEnum;
import enums.frames.SearchIdEnum;

public class SearchIdPanel extends JPanel {
	
	private JLabel searchNameLabel;
	private JLabel searchemailLabel;
	private JLabel searchErrorMsgLabel;
	private JTextField searchNameTextField;
	private JTextField searchEmailTextField;
	
	private JButton searchConfirmButton;
	private JButton backButton;
	
	private FindIdAction findIDAction;
	private SearchIdFrame searchIdFrame;
	private JPanel cardLayoutPanel;
	
	public SearchIdPanel(SearchIdFrame searchIdFrame) throws IOException {
		this.cardLayoutPanel = new JPanel();
		this.searchIdFrame = searchIdFrame;
		
		this.findIDAction = new FindIdAction(this);
		JoinSizesEnum.JOIN_MESSAGE.getMessageMap();
		Font defaultFont = SearchIdEnum.LABELFONT_DEFAULT.getFont();

		this.setBounds(CorrectEnum.DROPOUT_FRAME_SIZE_RECT.getRect());
		
		//�̸�
		this.searchNameLabel = new JLabel("�̸�");
		this.searchNameLabel.setFont(defaultFont);
		this.searchNameLabel.setBounds(SearchIdEnum.SEARCH_ID_LABEL.getRectangle());
		//�̸���
		this.searchemailLabel = new JLabel("email");
		this.searchemailLabel.setFont(defaultFont);
		this.searchemailLabel.setBounds(SearchIdEnum.SEARCH_EMAIL_LABEL.getRectangle());
		
		//�����޽���
		String searchMsg = "";
		this.searchErrorMsgLabel = new JLabel(searchMsg);
		this.searchErrorMsgLabel.setBounds(SearchIdEnum.SEARCH_ERROR_LABEL.getRectangle());
		this.searchErrorMsgLabel.setForeground(SearchIdEnum.LABELCOLOR_ERROR.getColor());
		this.searchErrorMsgLabel.setFont(SearchIdEnum.LABELFONT_ERROR.getFont());

		//���̵��ؽ�Ʈ�ʵ�
		this.searchNameTextField = new JTextField(10);
		this.searchNameTextField.setFont(defaultFont);
		this.searchNameTextField.setBounds(SearchIdEnum.SEARCH_ID_TEXTFIELD.getRectangle());
		//�̸����ؽ�Ʈ�ʵ�
		this.searchEmailTextField = new JTextField(10);
		this.searchEmailTextField.setFont(defaultFont);
		this.searchEmailTextField.setBounds(SearchIdEnum.SEARCH_EMAIL_TEXTFIELD.getRectangle());
		
		//Ȯ�ι�ư
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
		this.searchConfirmButton.setName(SearchIdEnum.BUTTON_NAME_CONFIRM.getButtonName());
		this.searchConfirmButton.setIconTextGap(this.searchConfirmButton.getIconTextGap() - 15);
		
    	//��ҹ�ư
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
		this.backButton.setName(SearchIdEnum.BUTTON_NAME_BACK.getButtonName());

		
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
	
	//�����޽���
	public void errorMsg(String errorMsgLabel) {
		this.setLayout(null);
		this.searchErrorMsgLabel.setBounds(SearchIdEnum.SEARCH_ERROR_LABEL.getRectangle());
		this.add(this.searchErrorMsgLabel);
		this.searchErrorMsgLabel.setText(errorMsgLabel);
	}
	
	//�����޽��� ���ֱ�
	public void errorMsgReset() {
		this.searchErrorMsgLabel.setText(null);	
	}
	
	//name, email �ؽ�Ʈ �ʵ� �׼�
	private void addKeyAction(JComponent comp, String name) {
		comp.setName(name);
		comp.addKeyListener(this.findIDAction);
		this.add(comp);
	}	
	

	//��ҹ�ư�� ����
	public void doCancleButton(){	
		//�������� ��ҹ�ư�� ����
		this.searchIdFrame.doCancleButton();
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
    
    public FindIdAction getFindIDAction() {
		return findIDAction;
	}
}
