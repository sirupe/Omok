package actions.findIDandPW;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import datasDTO.UserPersonalInfoDTO;
import enums.etc.UserPositionEnum;
import enums.frames.SearchIdEnum;
import frames.LoginPanel;
import frames.serchFrames.SearchIdFrame;

public class FindIDAction extends MouseAdapter implements ActionListener {
	private LoginPanel loginPanel;
	private SearchIdFrame searchIdFrame;
	
	public FindIDAction(SearchIdFrame searchIdFrame){
		this.searchIdFrame = searchIdFrame;	
	}
	
	//id, email 입력란 중 하나만 비어있어도 에러 메세지 출력
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	//확인버튼을 눌렀을때
	@Override
	public void mouseClicked(MouseEvent e){
		
	}
	
	//ForgotID 버튼 눌렀을시 
	public void findIDAction() {
		
		try {
			this.loginPanel.getBasicFrame().getClientOS().defaultWriteObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
		
}
