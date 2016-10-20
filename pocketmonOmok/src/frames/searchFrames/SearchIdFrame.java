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
		
		//������ ȭ�� ��� ��ġ ����
		this.setBounds(
				SearchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_X.getSize(),
				SearchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_Y.getSize(),
				SearchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
				SearchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize()
		);
		
		//���̵� ã�� �г�
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
		
		//���̵� ã�� ���â �г�
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
		this.setTitle("IDã��");
		this.setVisible(true);
		this.setResizable(false);

	}
	
	
	
	//��ҹ�ư�� ������ //Ȩ��ư�� ������
	public void doCancleButton(){
		this.setVisible(false);
		this.basicFrame.setVisible(true);
	}
	
	//ID�˻���� �гη� �ٲ��ִ� �żҵ�
	public void doConfirmButton(){	
		this.cardLayout.show(this.getContentPane(), "searchIdResultPanel");
	}
	
	//ID�˻� �гη� �ٲ��ִ� �żҵ�
	public void searchIdPanel() {
		this.cardLayout.show(this.getContentPane(), "searchIdPanel");
	}
	
	//ID�˻�â �гη� ��ȯ�ϴ� �żҵ�
	public SearchIdPanel getSearchIdPanel() {
		return searchIdPanel;
	}
	
	//ID�˻� ���â �гη� ��ȯ�ϴ� �żҵ�
	public SearchIdResultPanel getSearchIdResultPanel() {
		return searchIdResultPanel;
	}
	
	//�������������� ��ȯ�ϴ� �żҵ�
	public BasicFrame getBasicFrame() {
		return basicFrame;
	}

}