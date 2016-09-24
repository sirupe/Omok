package frames.serchFrames;


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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import enums.frames.GameRoomEnum;
import enums.frames.searchIdEnum;
import enums.frames.searchPwdEnum;
import enums.frames.searchRePwdEnum;

@SuppressWarnings("serial")
public class SearchRePwdPanel extends JPanel {
	private JPanel searchRePwdPanel;
	private Image backGround;
	
	public SearchRePwdPanel() throws IOException {
		this.setLayout(null);
		
//		this.searchRePwdPanel   = new JPanel();
		
		this.setsearchPwdPanel();
	} //생성자
	
	
	
	// 패널 생성 -- 비밀번호 입력, 재비밀번호 입력 텍스트
	public void setsearchPwdPanel() throws IOException {

//		this.searchRePwdPanel.setLayout(null);
//		this.searchRePwdPanel.setOpaque(false);
		
		// 패널의 배경이미지
		backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
				searchPwdEnum.SEARCH_PWD_FRAME_WIDTH.getSize(),
				searchPwdEnum.SEARCH_PWD_FRAME_HEIGHT.getSize(),
                Image.SCALE_SMOOTH);
		this.add(new JLabel(new ImageIcon(backGround)));

		this.setBounds(
				searchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_X.getSize(),
				searchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_Y.getSize(),
				searchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
				searchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize()
				);
		//비밀번호 라벨
		JLabel searchPwdLabel = new JLabel("PW");
		searchPwdLabel.setBounds(searchRePwdEnum.SEARCH_PWD_LABEL.getRectangle());
		searchPwdLabel.setFont(searchIdEnum.LABELFONT_DEFAULT.getFont());
		
		//비밀번호 입력창
		JTextField searchPwdText = new JTextField();
		searchPwdText.setBounds(searchRePwdEnum.SEARCH_PWD_TEXTFIELD.getRectangle());
		searchPwdText.setBorder(searchRePwdEnum.LABEL_DEFAULT.getBorder());
		searchPwdText.setFont(searchIdEnum.LABELFONT_DEFAULT.getFont());

		// 재비밀번호 라벨
		JLabel searchRePwdLabel = new JLabel("PW재입력");
		searchRePwdLabel.setBounds(searchRePwdEnum.SEARCH_REPWD_LABEL.getRectangle());
		searchRePwdLabel.setFont(searchIdEnum.LABELFONT_DEFAULT.getFont());
		
		// 제비밀번호 텍스트창
		JTextField searchRePwdText = new JTextField();
		searchRePwdText.setBounds(searchRePwdEnum.SEARCH_REPWD_TEXTFIELD.getRectangle());
		searchRePwdText.setBorder(searchRePwdEnum.LABEL_DEFAULT.getBorder());
		searchRePwdText.setFont(searchIdEnum.LABELFONT_DEFAULT.getFont());
		
		// 에러 메세지 라벨
		JLabel searchRePwdErrorLabel = new JLabel
				("<html>비밀번호가 일치하지 않습니다. "
				+ "<br>다시 입력해주세요<br></html>");
		searchRePwdErrorLabel.setBounds(searchRePwdEnum.SEARCH_ERROR_LABEL.getRectangle());
		searchRePwdErrorLabel.setBorder(searchRePwdEnum.LABEL_DEFAULT.getBorder());
		searchRePwdErrorLabel.setFont(searchIdEnum.LABELFONT_ERROR.getFont());
		
		//확인 버튼창
		
		JButton searchConfirmButton = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/signUp/confirm.jpg")), 
						0, 
						0, 
						searchRePwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().width,
						searchRePwdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().height,
						this);
				} catch (IOException e) {
					e.printStackTrace();
				}		
			}
		};	
		this.add(searchPwdLabel);
		this.add(searchRePwdText);
		this.add(searchPwdText);
		this.add(searchRePwdLabel);
		this.add(searchRePwdErrorLabel);
		this.add(searchConfirmButton);
		
//		this.searchRePwdPanel.add(searchPwdLabel);
//		this.searchRePwdPanel.add(searchRePwdText);
//		this.searchRePwdPanel.add(searchPwdText);
//		this.searchRePwdPanel.add(searchRePwdLabel);
//		this.searchRePwdPanel.add(searchRePwdErrorLabel);
//		this.searchRePwdPanel.add(searchConfirmButton);

	
	}
}
