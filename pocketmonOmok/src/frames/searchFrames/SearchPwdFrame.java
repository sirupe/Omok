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
import datasDTO.UserPersonalInfoDTO;
import enums.frames.SearchPwdEnum;
import enums.frames.SearchRePwdEnum;
import frames.BasicFrame;
import frames.LoginPanel;


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
		
		this.basicFrame = basicFrame;
		
		backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
				SearchPwdEnum.SEARCH_PWD_FRAME_WIDTH.getSize(),
				SearchPwdEnum.SEARCH_PWD_FRAME_HEIGHT.getSize(),
                Image.SCALE_SMOOTH);
		
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
		this.searchRePwdPanel = new SearchRePwdPanel() {
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
		//this.setLayout(null);
		
//		this.newSearchChangePanel();
		this.add("searchPwdPanel",this.searchPwdPanel);
		this.searchPwdPanel.setOpaque(false);
//		this.add("searchRePwdPanel", this.searchRePwdPanel);
//		this.searchRePwdPanel.setOpaque(false);
//		this.add("searchChangeConfirmPanel", this.searchChangePanel);
//		this.searchChangePanel.setOpaque(false);

		this.setLayout(this.cardLayout);
		this.setTitle("PW찾기");
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		
	}
	//비밀번호 변경 확인 프레임
	public void newSearchChangePanel() throws IOException {
		
		this.searchChangePanel = new SearchChangePanel() {	
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/signUp/backg.png")),
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
	}
	//나자신을 꺼주고 basicFrame을 켜준다.
    public void doCancelButton() {
    	this.setVisible(false);
    	this.basicFrame.setVisible(true);
    }
    
    public void getCerficartion(UserPersonalInfoDTO userPersonalInfoDTO) {
    	try {
			this.basicFrame.getClientOS().writeObject(userPersonalInfoDTO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	//	public static void main(String[] args) {
//		try {
//			new SearchPwdFrame();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
