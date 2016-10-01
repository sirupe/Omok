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

import actions.findIDandPW.FindIDResultAction;
import enums.frames.CorrectEnum;
import enums.frames.SearchIDEnum;


public class SearchIdResultPanel extends JPanel {
	private JLabel showUserIdLabel;
	private JButton homeButton;
	
	private SearchIdFrame searchIdFrame;
	private FindIDResultAction findIdResultAction;

	public SearchIdResultPanel(SearchIdFrame searchIdFrame) throws IOException {
		this.searchIdFrame = searchIdFrame;
		this.findIdResultAction = new FindIDResultAction(this);
		
		Font defaultFont = SearchIDEnum.LABELFONT_DEFAULT.getFont();

		this.setBounds(CorrectEnum.DROPOUT_FRAME_SIZE_RECT.getRect());
		
		String IdMsg = "";
		this.showUserIdLabel = new JLabel(IdMsg);//������ ������ ���̵� // ������ �Է����� ����:(
		this.showUserIdLabel.setFont(defaultFont);
		this.showUserIdLabel.setBounds(CorrectEnum.SHOW_USER_ID_RESULT_RECT.getRect());
		
		this.homeButton = new JButton();
		this.homeButton.setIcon(
				new ImageIcon(ImageIO.read(
					new File("resources/forgotID/goHome.png")).getScaledInstance(
							SearchIDEnum.SEARCH_CONFIRM_BUTTON.getRectangle().width,
							SearchIDEnum.SEARCH_CONFIRM_BUTTON.getRectangle().height,
							Image.SCALE_AREA_AVERAGING))
			);
		this.homeButton.setBounds(SearchIDEnum.GO_HOME_BUTTON.getRectangle());
		this.homeButton.setBorderPainted(false);
		this.homeButton.setFocusPainted(false);
		this.homeButton.setContentAreaFilled(false);
		this.homeButton.setName(SearchIDEnum.BUTTON_NAME_GOHOME.getButtonName());

		this.add(showUserIdLabel);
		this.add(homeButton);
		this.homeButton.addActionListener(this.findIdResultAction);
		this.setLayout(null);
		
	}
	//Ȩ���ι�ư�� ����
	public void doHomeButton(){	
		//�������� ��ҹ�ư�� ����, ��ҹ�ư�� ������ ���ۼ���
		this.searchIdFrame.doCancleButton();
	}
	//�󺧰��� �ٲ��ִ� �޼ҵ带�����
	public void showUserIdLael(String IdMsg) {
		
		this.setLayout(null);
		this.showUserIdLabel.setBounds(CorrectEnum.SHOW_USER_ID_RESULT_RECT.getRect());
		this.add(this.showUserIdLabel);
		this.showUserIdLabel.setText(IdMsg);
	
	}

	

	//DB���� ������ userID�� ��
	public JLabel getSearchIdLabel (){
		return showUserIdLabel;	
	}
	public JButton getHomeButton() {
		return homeButton;
	}
	public SearchIdFrame getSearchIdFrame() {
		return searchIdFrame;
	}
	public FindIDResultAction getFindIdResultAction() {
		return findIdResultAction;
	}
	
	
	
	
	
}
