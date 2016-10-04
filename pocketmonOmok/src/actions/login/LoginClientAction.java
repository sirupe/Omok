package actions.login;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import datasDTO.UserPersonalInfoDTO;
import enums.etc.ImageEnum;
import enums.etc.UserPositionEnum;
import enums.frames.LoginPanelEnum;
import frames.BasicFrame;
import frames.LoginPanel;
import utility.GetResources;

public class LoginClientAction extends MouseAdapter implements ActionListener{
	private LoginPanel loginPanel;
	
	public LoginClientAction(LoginPanel loginPanel){
		this.loginPanel = loginPanel;
	} 
	
	// �α��� ��ư �׼��� ������ �����
	@Override
	public void actionPerformed(ActionEvent e) {
		// id�Է�ĭ�̳� pw�Է�ĭ �� �ϳ��� ����־ �Է��϶�� �޼��� ���.
		String buttonName = e.getSource().toString(); 
		if(buttonName.contains(LoginPanelEnum.BUTTON_NAME_LOGIN.getButtonName())) {
			this.loginAction();
		}
	}
	// �̹����� ���콺�� �ø��� ��ư �̹����� �ٲ�ϴ�.
	// �ԷµǴ� ��ư�� ȸ�������� ���, IDã���� ���, PWã���� ���.
	@Override
	public void mouseEntered(MouseEvent e) {
		String buttonName = e.getComponent().toString();
		if(buttonName.contains(LoginPanelEnum.BUTTON_NAME_SIGNUP.getButtonName())) {
			this.loginPanel.getJoinButton().setIcon(this.iconButtonImage(ImageEnum.LOGINPANEL_SIGNUP_HOVER.getImageDir()));
			
		} else if(buttonName.contains(LoginPanelEnum.BUTTON_NAME_SEARCHID.getButtonName())) {
			this.loginPanel.getSearchIdButton().setIcon(this.iconButtonImage(ImageEnum.LOGINPANEL_SEARCHID_HOVER.getImageDir()));
			
		} else if(buttonName.contains(LoginPanelEnum.BUTTON_NAME_SEARCHPW.getButtonName())) {
			this.loginPanel.getSearchPwButton().setIcon(this.iconButtonImage(ImageEnum.LOGINPANEL_SEARCHPW_HOVER.getImageDir()));
			
		} else if(buttonName.contains(LoginPanelEnum.BUTTON_NAME_LOGIN.getButtonName())) {
			this.loginPanel.getLoginButton().setIcon(GetResources.getImageIcon(ImageEnum.LOGINPANEL_LOGIN_HOVER.getImageDir(),
				LoginPanelEnum.LOGIN_ICON_WIDTH.getSize(),
	            LoginPanelEnum.LOGIN_ICON_HEIGHT.getSize())
			);
		}
	}
	
	// �̹������� ���콺�� ���� ���� �̹����� �ǵ��ư��ϴ�.
	// �ԷµǴ� ��ư�� ȸ�������� ���, IDã���� ���, PWã���� ���
	@Override
	public void mouseExited(MouseEvent e) {
		String buttonName = e.getComponent().toString();
		try {
			if(buttonName.contains(LoginPanelEnum.BUTTON_NAME_SIGNUP.getButtonName())) {
				this.loginPanel.getJoinButton().setIcon(this.iconButtonImage(ImageEnum.LOGINPANEL_SIGHUP.getImageDir()));
			
			} else if(buttonName.contains(LoginPanelEnum.BUTTON_NAME_SEARCHID.getButtonName())) {
				this.loginPanel.getSearchIdButton().setIcon(this.iconButtonImage(ImageEnum.LOGINPANEL_SEARCHID.getImageDir()));
			
			} else if(buttonName.contains(LoginPanelEnum.BUTTON_NAME_SEARCHPW.getButtonName())) {
				this.loginPanel.getSearchPwButton().setIcon(this.iconButtonImage(ImageEnum.LOGINPANEL_SEARCHPW.getImageDir()));
				
			} else if(buttonName.contains(LoginPanelEnum.BUTTON_NAME_LOGIN.getButtonName())) {
				this.loginPanel.getLoginButton().setIcon(
					new ImageIcon(ImageIO.read(
						new File(ImageEnum.LOGINPANEL_LOGIN.getImageDir())).getScaledInstance(
							LoginPanelEnum.LOGIN_ICON_WIDTH.getSize(),
				            LoginPanelEnum.LOGIN_ICON_HEIGHT.getSize(),
							Image.SCALE_AREA_AVERAGING))
				);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		String buttonName = e.getComponent().toString();
		if(buttonName.contains(LoginPanelEnum.BUTTON_NAME_SIGNUP.getButtonName())) {
			this.loginPanel.getBasicFrame().setVisible(false);
			try {
				this.loginPanel.getBasicFrame().newJoinFrame();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if(buttonName.contains(LoginPanelEnum.BUTTON_NAME_SEARCHID.getButtonName())) {
			this.loginPanel.getBasicFrame().setVisible(false);
			try {
				this.loginPanel.getBasicFrame().newSearchIdFrame();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if(buttonName.contains(LoginPanelEnum.BUTTON_NAME_SEARCHPW.getButtonName())) {
				this.loginPanel.getBasicFrame().setVisible(false);
					try {
						this.loginPanel.getBasicFrame().newSearchPwdFrame();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}}

	
	public void loginAction() {
		char[] passwd = this.loginPanel.getPwField().getPassword();
		String passwdStr = new String(passwd, 0, passwd.length);
		if(this.loginPanel.getIdField().getText().isEmpty() || passwdStr.isEmpty()) {
			this.loginPanel.loginFailLabelReset();
			this.loginPanel.loginFail("���̵�� ��й�ȣ�� ��� �Է����ּ���.");
		} else {
			UserPersonalInfoDTO personalDTO = new UserPersonalInfoDTO(UserPositionEnum.POSITION_LOGIN);
			personalDTO.setUserID(this.loginPanel.getIdField().getText());
			personalDTO.setUserPasswd(passwdStr);
			try {
				this.loginPanel.getBasicFrame().getClientOS().writeObject(personalDTO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public ImageIcon iconButtonImage(String imageDir) {
		return GetResources.getImageIcon(imageDir, 
				LoginPanelEnum.ICON_SIZE_WIDTH.getSize(), 
				LoginPanelEnum.ICON_SIZE_HEIGHT.getSize());
	}
}