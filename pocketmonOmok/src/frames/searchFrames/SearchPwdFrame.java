package frames.searchFrames;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import actions.findIDandPW.FindPWAction;
import datasDTO.AbstractEnumsDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.frames.SearchPwdEnum;
import enums.frames.SearchRePwdEnum;
import frames.BasicFrame;


@SuppressWarnings("serial")
public class SearchPwdFrame extends JFrame implements Serializable {
	
	private CardLayout cardLayout;
	private SearchPwdPanel searchPwdPanel;
	private SearchRePwdPanel searchRePwdPanel;
	private SearchChangePanel searchChangePanel;
	
	private Image backGround;
	
	private FindPWAction findPwAction;
	
	private BasicFrame basicFrame;
	
	public SearchPwdFrame(BasicFrame basicFrame) throws IOException {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		   
		this.basicFrame = basicFrame;
		
		this.backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
				SearchPwdEnum.SEARCH_PWD_FRAME_WIDTH.getSize(),
				SearchPwdEnum.SEARCH_PWD_FRAME_HEIGHT.getSize(),
				Image.SCALE_SMOOTH
		);	
		
		this.setContentPane(new JLabel(new ImageIcon(backGround))); 
		
		//프레임 화면 출력 위치 설정
		this.setBounds(
			SearchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_X.getSize(),
			SearchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_Y.getSize(),
			SearchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
			SearchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize()
		);
		
		//비밀번호 찾기 프레임
		this.searchPwdPanel = new SearchPwdPanel(this) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponents(g); 
				try {
					g.drawImage(ImageIO.read(
						new File("resources/background/popup.png")),
							0,
							0,
							SearchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
							SearchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize(),
							this);      
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		this.searchPwdPanel.setOpaque(false);	
		this.cardLayout = new CardLayout();
		
		
		//비밀번호 재입력 프레임
		this.searchRePwdPanel = new SearchRePwdPanel(this) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponents(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/background/popup.png")),
							0,
							0,
							SearchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
							SearchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize(),
							this);      
				}catch (IOException e) {
				   e.printStackTrace();
				}
			}};
			
		 //비밀번호 변경 확인 프레임
		this.searchChangePanel = new SearchChangePanel(this) {   
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/background/popup.png")),
							0,
							0,
							SearchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
							SearchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize(),
							this);      
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		};  
		 
		 
		 
		this.setLayout(this.cardLayout);
		this.add("searchPwdPanel",this.searchPwdPanel);
		this.add("searchRePwdPanel", this.searchRePwdPanel);
		this.add("searchChangeConfirmPanel", this.searchChangePanel);
		
		this.setTitle("PW찾기");
		this.setVisible(true);
		this.setResizable(false);
	
	}
		
	//나자신을 꺼주고 basicFrame을 켜준다.
	public void doCancelButton() {
		this.setVisible(false);
		this.basicFrame.setVisible(true);
	}
	
	public void doSearchChangeConfirmPanel() {
		this.cardLayout.show(this.getContentPane(), "searchChangeConfirmPanel");
	}

	public void doCheckButton() {
	 	this.cardLayout.show(this.getContentPane(), "searchRePwdPanel");
	 	
	}
	
	public void getCerficartion(UserPersonalInfoDTO userPersonalInfoDTO) {
		try {
			this.basicFrame.getClientOS().writeObject(userPersonalInfoDTO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void receiverSuccess(AbstractEnumsDTO userPosition) {
	 	
	 	switch (userPosition.getUserAction()) {
		case USER_SEARCH_CERTIFICATION_CHECK :
			this.searchPwdPanel.emailSuccess(userPosition);
			break;
		case USER_SEARCH_PASSWORD_CERTIFICATION_NUMBER :
			this.searchPwdPanel.confirmNumberSuccess(userPosition);
			break;
		case USER_SEARCH_ID_EMAIL_CHECK:
			this.searchPwdPanel.getChangePanel(userPosition);
			break;
		case USER_SEARCH_PASSWD :
			this.searchRePwdPanel.searchPwdSuccess(userPosition);
			break;
		case USER_SEARCHPW_SERCHFAIL :
			this.searchPwdPanel.userInfoSearchFail();
			break;
		default:
			break;
		}
	}
	
	public SearchPwdPanel getSearchPwdPanel() {
	  return searchPwdPanel;
	}
	
	public FindPWAction getFindPwdAction() {
	  return findPwAction;
	}
	
	public SearchRePwdPanel getSearchRePanel() {
	  return searchRePwdPanel;
	}
	
	public BasicFrame getBasicFrame() {
		return basicFrame;
	}
}