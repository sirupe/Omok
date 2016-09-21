package frames;

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

import enums.frames.LoginSizesEnum;
import enums.frames.searchPwdEnum;
import enums.frames.searchRePwdEnum;

@SuppressWarnings("serial")
public class SearchChangePanel extends JPanel {
	private JPanel searchChangePanel;
	private Image backGround;
	
	public SearchChangePanel() throws IOException {
		this.setLayout(null);
		
		//패널 생성 후 불러오기
		this.searchChangePanel  = new JPanel(); 	
		this.setchangePanel();
	}
	
	//변경했다는 패널 생성
	public void setchangePanel() throws IOException {
		this.searchChangePanel.setLayout(null);
		this.searchChangePanel.setOpaque(false);
		
		// 패널 배경화면
		backGround = ImageIO.read(new File("resources/signUp/backg.png")).getScaledInstance(
				searchPwdEnum.SEARCH_PWD_FRAME_WIDTH.getSize(),
				searchPwdEnum.SEARCH_PWD_FRAME_HEIGHT.getSize(),
                Image.SCALE_SMOOTH);
		this.add(new JLabel(new ImageIcon(backGround)));
		//this.setContentPane(new JLabel(new ImageIcon(backGround))); 
		
		this.setBounds(
				searchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_X.getSize(),
				searchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_Y.getSize(),
				searchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
				searchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize()
				);
		// 변경 완료창 라벨
		JLabel changeConfirmMsgLabel = new JLabel("<html>비밀번호 변경이 완료 되었습니다."
				+ "<br>다시 로그인해주세요!<br></html>");
		changeConfirmMsgLabel.setBounds(searchRePwdEnum.SEARCH_CONFIRM_CHANGE_LABEL.getRectangle());
		

		// 확인 버튼 창
		JButton changeConfirmButton  = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/signUp/confirm.jpg")), 
						0, 
						0, 
						searchRePwdEnum.SEARCH__CHANGE_CONFIRM_BUTTON.getRectangle().width,
						searchRePwdEnum.SEARCH__CHANGE_CONFIRM_BUTTON.getRectangle().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		this.searchChangePanel.add(changeConfirmMsgLabel);
		this.searchChangePanel.add(changeConfirmButton);
		}
		
	}