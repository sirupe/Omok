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

import actions.findIDandPW.FindIdResultAction;
import enums.frames.CorrectEnum;
import enums.frames.SearchIdEnum;


public class SearchIdResultPanel extends JPanel {
	private JLabel showUserId;
	private JLabel showUserIdResult;
	private JButton homeButton;
	
	private SearchIdFrame searchIdFrame;
	private FindIdResultAction findIdResultAction;

	public SearchIdResultPanel(SearchIdFrame searchIdFrame) throws IOException {
		this.searchIdFrame = searchIdFrame;
		this.findIdResultAction = new FindIdResultAction(this);
		
		Font defaultFont = SearchIdEnum.LABELFONT_DEFAULT.getFont();

		this.setBounds(CorrectEnum.DROPOUT_FRAME_SIZE_RECT.getRect());
		
		this.showUserId = new JLabel("ȸ���� ID");
		this.showUserId.setFont(defaultFont);
		this.showUserId.setBounds(CorrectEnum.SHOW_USER_ID_LABEL_RECT.getRect());
		
		this.showUserIdResult = new JLabel("yjyj*****");
		this.showUserIdResult.setFont(defaultFont);
		this.showUserIdResult.setBounds(CorrectEnum.SHOW_USER_ID_RESULT_RECT.getRect());
		
		this.homeButton = new JButton();
		this.homeButton.setIcon(
				new ImageIcon(ImageIO.read(
					new File("resources/forgotID/goHome.png")).getScaledInstance(
							SearchIdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().width,
							SearchIdEnum.SEARCH_CONFIRM_BUTTON.getRectangle().height,
							Image.SCALE_AREA_AVERAGING))
			);
		this.homeButton.setBounds(SearchIdEnum.GO_HOME_BUTTON.getRectangle());
		this.homeButton.setBorderPainted(false);
		this.homeButton.setFocusPainted(false);
		this.homeButton.setContentAreaFilled(false);
		this.homeButton.setName(SearchIdEnum.BUTTON_NAME_GOHOME.getButtonName());

		this.add(showUserId);
		this.add(showUserIdResult);
		this.add(homeButton);
		this.homeButton.addActionListener(this.findIdResultAction);
		this.setLayout(null);
		
	}
	//Ȩ���ι�ư�� ����
	public void doHomeButton(){	
		//�������� ��ҹ�ư�� ����, ��ҹ�ư�� ������ ���ۼ���
		this.searchIdFrame.doCancleButton();
	}
	public JButton getHomeButton() {
		return homeButton;
	}
	public SearchIdFrame getSearchIdFrame() {
		return searchIdFrame;
	}
	public FindIdResultAction getFindIdResultAction() {
		return findIdResultAction;
	}
	
	
	
	
}
