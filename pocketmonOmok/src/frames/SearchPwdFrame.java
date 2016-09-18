package frames;

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

import enums.LoginFrameSizesEnum;
import enums.searchPwdEnum;
import enums.searchRePwdEnum;

public class SearchPwdFrame extends JFrame implements Serializable{
	
	private CardLayout cardLayout;
	private searchPwdPanel searchPwdPanel;
	private SearchRePwdPanel searchRePwdPanel;
	private SearchChangePanel searchChangePanel;
	private Image backGround;
	
	//
	public SearchPwdFrame() throws IOException {
		backGround = ImageIO.read(new File("resources/signUp/backg.png")).getScaledInstance(
				searchPwdEnum.SEARCH_PWD_FRAME_WIDTH.getSize(),
				searchPwdEnum.SEARCH_PWD_FRAME_HEIGHT.getSize(),
                Image.SCALE_SMOOTH);
		
		this.setContentPane(new JLabel(new ImageIcon(backGround))); 
		
		//프레임 화면 출력 위치 설정
		this.setBounds(
		searchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_X.getSize(),
		searchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_Y.getSize(),
		searchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
		searchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize()
		);
		
		//비밀번호 찾기 프레임
		this.searchPwdPanel = new searchPwdPanel(this);
		this.searchPwdPanel.setOpaque(false);
		this.cardLayout = new CardLayout();
		
		//비밀번호 재입력 프레임
		this.searchRePwdPanel = new SearchRePwdPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponents(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/signUp/backg.png")),
							searchPwdEnum.SEARCH_PWD_FRAME_POSITION_X.getSize(),
							searchPwdEnum.SEARCH_PWD_FRAME_POSITION_Y.getSize(),
							searchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
							searchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize(),
							this);		
				}catch (IOException e) {
					e.printStackTrace();
				}
			}};
		this.setLayout(null);
		this.newSearchChangePanel();
		this.add("searchPwdPanel",this.searchPwdPanel);
		this.add("searchRePwdPanel", this.searchRePwdPanel);
		this.add("searchChangeConfirmPanel", this.searchChangePanel);
		this.setLayout(this.cardLayout);
		this.setTitle("PW찾기");
		this.setVisible(true);
		this.setResizable(false);
	}
	
	//비밀번호 변경 확인 프레임
	public void newSearchChangePanel() {
		this.searchChangePanel = new SearchChangePanel() {	
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/signUp/backg.png")),
							0,
							0 ,
							searchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
							searchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize(),
							this);		
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	}
	public void insearchRePwdPanel() {
		this.cardLayout.show(this.getContentPane(), "searchRePwdPanel");
	}
	
	public searchPwdPanel getsearchPwdPanel() {
		return searchPwdPanel;
	}
	
		
		
	public static void main(String[] args) {
		try {
			new SearchPwdFrame();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	}
