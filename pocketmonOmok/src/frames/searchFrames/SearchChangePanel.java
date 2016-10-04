package frames.searchFrames;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import actions.findIDandPW.findChangePwdConfirmAction;
import enums.frames.LoginSizesEnum;
import enums.frames.SearchIDEnum;
import enums.frames.SearchPwdEnum;
import enums.frames.SearchRePwdEnum;

@SuppressWarnings("serial")
public class SearchChangePanel extends JPanel {
	private JPanel searchChangePanel;
	private Image backGround;
	private SearchPwdFrame searchPwdFrame;
	private findChangePwdConfirmAction findchangepwdconfirmAction;

	private JLabel changeConfirmMsgLabel;
	private JButton changeConfirmButton;
	
	public SearchChangePanel(SearchPwdFrame searchPwdFrame) throws IOException {
		this.setLayout(null);
		this.setVisible(true);
		
		this.searchPwdFrame = searchPwdFrame;
		this.findchangepwdconfirmAction = new findChangePwdConfirmAction(this);
		
		
		//�г� ���� �� �ҷ�����
		this.searchChangePanel  = new JPanel(); 
		
		
		// ���� �Ϸ�â ��
		this.changeConfirmMsgLabel = new JLabel("<html>��й�ȣ ������ �Ϸ� �Ǿ����ϴ�."
				+ "<br>�ٽ� �α������ּ���!<br></html>");
		this.changeConfirmMsgLabel.setBounds(SearchRePwdEnum.SEARCH_CONFIRM_CHANGE_LABEL.getRectangle());
		this.changeConfirmMsgLabel.setFont(SearchRePwdEnum.LABELFONT_DEFAULT.getFont());
		this.changeConfirmMsgLabel.setForeground(SearchRePwdEnum.LABELCOLOR_DEFAULT.getColor());

		// Ȯ�� ��ư â
		this.changeConfirmButton  = new JButton();
			this.changeConfirmButton.setIcon(
					new ImageIcon(ImageIO.read(
						new File("resources/signUp/confirm.jpg")).getScaledInstance(
								SearchRePwdEnum.SEARCH_CHANGE_CONFIRM_BUTTON.getRectangle().width,
								SearchRePwdEnum.SEARCH_CHANGE_CONFIRM_BUTTON.getRectangle().height,
								Image.SCALE_AREA_AVERAGING))
				);
			this.changeConfirmButton.setIconTextGap(this.changeConfirmButton.getIconTextGap() - 15);
			this.changeConfirmButton.setBounds(SearchRePwdEnum.SEARCH_CHANGE_CONFIRM_BUTTON.getRectangle());
			this.changeConfirmButton.setBorderPainted(false);
			this.changeConfirmButton.setFocusPainted(false);
			this.changeConfirmButton.setContentAreaFilled(false);
			this.changeConfirmButton.setName(LoginSizesEnum.BUTTON_NAME_CHANGE_CONFIRM.getButtonName());
			this.changeConfirmButton.addActionListener(this.findchangepwdconfirmAction);
			
			this.add(changeConfirmMsgLabel);
			this.add(changeConfirmButton);

			}
	
	public void goHome() {
		this.searchPwdFrame.doCancelButton();
	}

	public JButton getChangeConfirmButton() {
		return changeConfirmButton;
	}
	
	public SearchPwdFrame getSearchPwdFrame() {
		return searchPwdFrame;
	}

}