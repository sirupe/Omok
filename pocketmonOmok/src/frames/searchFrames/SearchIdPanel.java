package frames.searchFrames;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
import omokGame.client.ClientAccept;

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
	private String errMsg;
	
	public SearchIdPanel(SearchIdFrame searchIdFrame) throws IOException {
		this.cardLayoutPanel = new JPanel();
		this.searchIdFrame = searchIdFrame;
		
		this.findIDAction = new FindIDAction(this);
		JoinSizesEnum.JOIN_MESSAGE.getMessageMap();

		Font defaultFont = SearchIDEnum.LABELFONT_DEFAULT.getFont();

		this.setBounds(CorrectEnum.DROPOUT_FRAME_SIZE_RECT.getRect());
		
		//�̸�
		this.searchNameLabel = new JLabel("�̸�");
		this.searchNameLabel.setFont(defaultFont);
		this.searchNameLabel.setBounds(SearchIDEnum.SEARCH_ID_LABEL.getRectangle());
		//�̸���
		this.searchemailLabel = new JLabel("email");
		this.searchemailLabel.setFont(defaultFont);
		this.searchemailLabel.setBounds(SearchIDEnum.SEARCH_EMAIL_LABEL.getRectangle());
		
		//�����޽���
		String searchMsg = "";
		this.searchErrorMsgLabel = new JLabel(searchMsg);
		this.searchErrorMsgLabel.setBounds(SearchIDEnum.SEARCH_ERROR_LABEL.getRectangle());
		this.searchErrorMsgLabel.setForeground(SearchIDEnum.LABELCOLOR_ERROR.getColor());
		this.searchErrorMsgLabel.setFont(SearchIDEnum.LABELFONT_ERROR.getFont());

		//���̵��ؽ�Ʈ�ʵ�
		this.searchNameTextField = new JTextField(10);

		this.searchNameTextField.setFont(defaultFont);
		this.searchNameTextField.setBounds(SearchIDEnum.SEARCH_ID_TEXTFIELD.getRectangle());
		//�̸����ؽ�Ʈ�ʵ�
		this.searchEmailTextField = new JTextField(10);
		this.searchEmailTextField.setFont(defaultFont);
		this.searchEmailTextField.setBounds(SearchIDEnum.SEARCH_EMAIL_TEXTFIELD.getRectangle());
	
		//Ȯ�ι�ư
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
		
    	//��ҹ�ư
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
	
	//findIdResult ������ ȣ�⸸.�غ�.
	public void findIdResult(AbstractEnumsDTO data) {
		
		UserPersonalInfoDTO result = (UserPersonalInfoDTO) data;
		SearchIdResultPanel searchIdResultPanel = this.searchIdFrame.getSearchIdResultPanel();
		
		//user ID�� ���� �ƴϸ� Ȯ�ι�ư�� ����ǵ��� �ϱ�
		if(result.getUserID() == null) {
			//��ġ���̵� �г���Lableâ�� ������
			this.errorMsg("�����Է¿���:(");
		} else {
			searchIdResultPanel.showUserIdLael(result.getUserID());
			this.doConfirmButton();
		}
	}
	
	//�����޽���
	public void errorMsg(String errorMsgLabel) {
		this.setLayout(null);
		this.searchErrorMsgLabel.setBounds(SearchIDEnum.SEARCH_ERROR_LABEL.getRectangle());
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
	//Ȯ�ι�ư�� ����
	public void doConfirmButton() {
		this.searchIdFrame.doConfirmButton();
	}
	
	//��ҹ�ư�� ����
	public void doCancleButton(){	
		//�������� ��ҹ�ư�� ����
		this.searchIdFrame.doCancleButton();
	}
	
	//���̵� ã�� �޼ҵ�
	public void doCheckId() {
		String name = this.searchNameTextField.getText();
		String email = this.searchEmailTextField.getText();
		
		UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_FIND_ID);
		personalDTO.setUserEmail(email);
		personalDTO.setUserName(name);

		System.out.println("client data -> " + personalDTO.toString());
		
		try {
			
			BasicFrame basicFrame = this.searchIdFrame.getBasicFrame();
			ClientAccept clientAccept = basicFrame.getClientAccept();
			ObjectOutputStream oos = clientAccept.getClientOS();
			
			oos.writeObject(personalDTO);
			
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
