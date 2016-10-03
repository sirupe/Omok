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
import datasDTO.AbstractEnumsDTO;
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
      
      this.backGround = ImageIO.read(new File("resources/background/popup.png")).getScaledInstance(
            SearchPwdEnum.SEARCH_PWD_FRAME_WIDTH.getSize(),
            SearchPwdEnum.SEARCH_PWD_FRAME_HEIGHT.getSize(),
           	Image.SCALE_SMOOTH
      );	
      
      this.setContentPane(new JLabel(new ImageIcon(backGround))); 
      
      //������ ȭ�� ��� ��ġ ����
      this.setBounds(
	      SearchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_X.getSize(),
	      SearchRePwdEnum.SEARCH_REPWD_FRAME_POSITION_Y.getSize(),
	      SearchRePwdEnum.SEARCH_REPWD_FRAME_WIDTH.getSize(),
	      SearchRePwdEnum.SEARCH_REPWD_FRAME_HEIGHT.getSize()
      );
      
      //��й�ȣ ã�� ������
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
      
      
      //��й�ȣ ���Է� ������
      this.searchRePwdPanel = new SearchRePwdPanel(this) {
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
         
       //��й�ȣ ���� Ȯ�� ������
         this.searchChangePanel = new SearchChangePanel(this) {   
             @Override
             protected void paintComponent(Graphics g) {
                super.paintComponent(g);
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
          
          
          
        this.setLayout(this.cardLayout);
         
         
          
      	this.add("searchPwdPanel",this.searchPwdPanel);
      	this.add("searchRePwdPanel", this.searchRePwdPanel);
      	this.add("searchChangeConfirmPanel", this.searchChangePanel);
  
      	this.setTitle("PWã��");
      	this.setVisible(true);
      	this.setResizable(false);
      
   	}

   //���ڽ��� ���ְ� basicFrame�� ���ش�.
	public void doCancelButton() {
		this.setVisible(false);
		this.basicFrame.setVisible(true);
	}
	
	public void doSearchChangeConfirmPanel() {
		this.cardLayout.show(this.getContentPane(), "searchChangeConfirmPanel");
	}
	
    public void doCheckButton() {
	 	this.cardLayout.show(this.getContentPane(), "searchRePwdPanel");
	 	
    }

    public void getCerficartion(UserPersonalInfoDTO userPersonalInfoDTO) {
      try {
         this.basicFrame.getClientOS().writeObject(userPersonalInfoDTO);
     } catch (IOException e) {
    	 e.printStackTrace();
     }
    }
    
    public void receiverSuccess(AbstractEnumsDTO userPosition) {
    	
    	switch (userPosition.getUserAction()) {
		case USER_SEARCH_CERTIFICATION_CHECK :
			this.searchPwdPanel.emailSuccess(userPosition);
			break;
		case USER_SEARCH_PASSWORD_CERTIFICATION_NUMBER :
			this.searchPwdPanel.confirmNumberSuccess(userPosition);
			break;
		case USER_SEARCH_ID_EMAIL_CHECK:
			this.searchPwdPanel.getChangePanel(userPosition);
			break;
		case USER_SEARCH_PASSWD :
			this.searchRePwdPanel.searchPwdSuccess(userPosition);
			break;
		default:
			break;
		}
    }
   
    public void inSearchRePwdFrame() {
  	  	System.out.println("");
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
    public BasicFrame getBasicFrame() {
    	return basicFrame;
    }


 
}