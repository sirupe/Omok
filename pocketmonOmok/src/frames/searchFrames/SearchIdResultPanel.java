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

import enums.frames.CorrectEnum;
import enums.frames.SearchIdEnum;


public class SearchIdResultPanel extends JPanel {
	private JLabel showUserId;
	private JLabel showUserIdResult;
	private Image backGround;
	private JButton confirmButton;

	public SearchIdResultPanel(SearchIdFrame searchIdFrame) throws IOException {
		this.setLayout(null);
		
		Font defaultFont = SearchIdEnum.LABELFONT_DEFAULT.getFont();
		
		this.backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
				CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().width,
				CorrectEnum.CORRECT_COMPLETE_FRAME_SIZE_RECT.getRect().height,
                Image.SCALE_SMOOTH);

		this.setBounds(CorrectEnum.DROPOUT_FRAME_SIZE_RECT.getRect());
		
		this.showUserId = new JLabel("È¸¿ø´Ô ID");
		this.showUserId.setFont(defaultFont);
		this.showUserId.setBounds(CorrectEnum.SHOW_USER_ID_LABEL_RECT.getRect());
		
		this.showUserIdResult = new JLabel("yjyj*****");
		this.showUserIdResult.setFont(defaultFont);
		this.showUserIdResult.setBounds(CorrectEnum.SHOW_USER_ID_RESULT_RECT.getRect());
		
		
		this.add(showUserId);
		this.add(showUserIdResult);
		
		this.setVisible(true);
		
	}

	public JLabel getshowUserId() {
		return showUserId;
	}
	public JLabel getshowUserIdResult() {
		return showUserIdResult;
	}



}
