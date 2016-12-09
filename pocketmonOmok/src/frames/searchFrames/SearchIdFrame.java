package frames.searchFrames;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import enums.frames.SearchPwdEnum;
import enums.frames.SearchRePwdEnum;
import frames.BasicFrame;


@SuppressWarnings("serial")
public class SearchIdFrame extends JFrame  {
	private SearchIdPanel searchIdPanel;
	private SearchIdResultPanel searchIdResultPanel;
	private Image backGround;
	
	private CardLayout cardLayout;
	
	private BasicFrame basicFrame;
	
	public SearchIdFrame(BasicFrame basicFrame) throws IOException {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.basicFrame = basicFrame;
		
		this.backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
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
		
		//아이디 찾기 패널
		this.searchIdPanel = new SearchIdPanel(this) {
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
		
		this.searchIdPanel.setOpaque(false);
		
		//아이디 찾기 결과창 패널
		this.searchIdResultPanel = new SearchIdResultPanel(this) {
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

		this.cardLayout = new CardLayout();
		this.setLayout(this.cardLayout);
		this.add("searchIdPanel",this.searchIdPanel);
		this.add("searchIdResultPanel",this.searchIdResultPanel);
		this.setTitle("ID찾기");
		this.setVisible(true);
		this.setResizable(false);

	}
	
	
	
	//취소버튼을 누르면 //홈버튼을 누르면
	public void doCancleButton(){
		this.setVisible(false);
		this.basicFrame.setVisible(true);
	}
	
	//ID검색결과 패널로 바꿔주는 매소드
	public void doConfirmButton(){	
		this.cardLayout.show(this.getContentPane(), "searchIdResultPanel");
	}
	
	//ID검색 패널로 바꿔주는 매소드
	public void searchIdPanel() {
		this.cardLayout.show(this.getContentPane(), "searchIdPanel");
	}
	
	//ID검색창 패널로 변환하는 매소드
	public SearchIdPanel getSearchIdPanel() {
		return searchIdPanel;
	}
	
	//ID검색 결과창 패널로 변환하는 매소드
	public SearchIdResultPanel getSearchIdResultPanel() {
		return searchIdResultPanel;
	}
	
	//베이직프래임을 변환하는 매소드
	public BasicFrame getBasicFrame() {
		return basicFrame;
	}

}