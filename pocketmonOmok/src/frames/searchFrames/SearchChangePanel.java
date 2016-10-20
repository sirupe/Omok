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

import actions.findIDandPW.findChangePwdConfirmAction;
import enums.frames.LoginPanelEnum;
import enums.frames.SearchRePwdEnum;

@SuppressWarnings("serial")
public class SearchChangePanel extends JPanel {
	@SuppressWarnings("unused")
	private JPanel searchChangePanel;
	private SearchPwdFrame searchPwdFrame;
	private findChangePwdConfirmAction findchangepwdconfirmAction;

	private JLabel changeConfirmMsgLabel;
	private JButton changeConfirmButton;
	
	public SearchChangePanel(SearchPwdFrame searchPwdFrame) throws IOException {
		this.setLayout(null);
		this.setVisible(true);
		
		this.searchPwdFrame = searchPwdFrame;
		this.findchangepwdconfirmAction = new findChangePwdConfirmAction(this);
		
		
		//패널 생성 후 불러오기
		this.searchChangePanel  = new JPanel(); 
		
		
		// 변경 완료창 라벨
		this.changeConfirmMsgLabel = new JLabel("<html>비밀번호 변경이 완료 되었습니다."
				+ "<br>다시 로그인해주세요!<br></html>");
		this.changeConfirmMsgLabel.setBounds(SearchRePwdEnum.SEARCH_CONFIRM_CHANGE_LABEL.getRectangle());
		this.changeConfirmMsgLabel.setFont(new Font("consolas", Font.BOLD, 25));
		this.changeConfirmMsgLabel.setForeground(SearchRePwdEnum.LABELCOLOR_DEFAULT.getColor());

		// 확인 버튼 창
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
			this.changeConfirmButton.setName(LoginPanelEnum.BUTTON_NAME_CHANGE_CONFIRM.getButtonName());
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