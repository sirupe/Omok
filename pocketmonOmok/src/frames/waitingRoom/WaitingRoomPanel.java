package frames.waitingRoom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import actions.waitingRoom.WaitingRoomActionListeners;
import actions.waitingRoom.WaitingRoomActions;
import datasDTO.AbstractEnumsDTO;
import datasDTO.GameRoomInfoVO;
import datasDTO.RoomAndUserListDTO;
import datasDTO.UserGamedataInfoDTO;
import datasDTO.UserPersonalInfoDTO;
import enums.etc.ImageEnum;
import enums.frames.WaitingRoomEnum;
import frames.BasicFrame;
import utility.GetResources;

public class WaitingRoomPanel extends JPanel {	
	private static final long serialVersionUID = 6433378L;
	
	private JScrollPane waitingRoomListScroll;
	private JTable waitingRoomTable;
	private JPanel waitingRoomListBackground;
	
	private JButton modifyInfoButton;
	private JButton createRoomButton;
	
	private JTextArea chattingOutput;
	private JTextField chattingInputTextField;
	private JTextField noticeTextField;
	private JButton sendMessageButton;
	private JScrollPane chattingScroll;
	
	private JList<String> playerList;
	private JPanel playerListBackground;
	private JScrollPane playerListScroll;
	
	private JPanel myInfo;
	private JPanel myInfoImageBorder;
	private JLabel userInfoImageLabel;
	private JLabel userIDTitleLabel;
	private JLabel userIDTextLabel;
	private JLabel scoreTitleLabel;
	private JLabel scoreTextLabel;
	private JLabel winningRateTitleLabel;
	private JLabel winningRateTextLabel;
	private JLabel pointTitleLabel;
	private JLabel pointTextLabel;
	private JLabel levelTitleLabel;
	private JLabel levelImageLabel;
	
	private WaitingRoomActionListeners waitingRoomActionListener;
	private WaitingRoomActions waitingRoomActions;
	private BasicFrame basicFrame;
	
	private Map<String,ImageIcon> imageMap;
	private Vector<String> players;
	
	private CreateGameRoomFrame createGameRoomFrame;
	
	public WaitingRoomPanel(BasicFrame basicFrame) throws IOException {
		this.waitingRoomActions = new WaitingRoomActions(this);
		this.waitingRoomActionListener = new WaitingRoomActionListeners(this.waitingRoomActions);
		this.playerListScroll   = new JScrollPane();
		//==========================ä�ù�&������==========================
		
		this.chattingOutput 		 = new JTextArea();
		this.chattingScroll			 = new JScrollPane(this.chattingOutput);
		this.chattingInputTextField  = new JTextField();
		this.noticeTextField		 = new JTextField();
		this.sendMessageButton    	 = new JButton();
						
		this.modifyInfoButton  		 = new JButton();
		this.createRoomButton 		 = new JButton();
			
		this.userIDTitleLabel 	 	 = new JLabel("ID");
		this.scoreTitleLabel 		 = new JLabel("����");
		this.winningRateTitleLabel 	 = new JLabel("�·�");
		this.pointTitleLabel 		 = new JLabel("����");
		this.levelTitleLabel 		 = new JLabel(".LV");
		
		this.userIDTextLabel 	  = new JLabel("���̵�");
		this.scoreTextLabel   	  = new JLabel("����");
		this.winningRateTextLabel = new JLabel("�·�");
		this.pointTextLabel       = new JLabel("����");
		this.levelImageLabel 	  = new JLabel();
		this.userInfoImageLabel   = new JLabel();
		
		this.basicFrame 	 = basicFrame;

	}
	//==========================���� ����Ʈ==========================

	public void roomListSetting(WaitingRoomListTable roomListModel) throws IOException {
		DefaultTableModel defaultTableModel = new DefaultTableModel(roomListModel.getWaitingRoomListData(), roomListModel.getWaitingRoomListColumn());
		this.waitingRoomTable = new JTable(defaultTableModel) {

			private static final long serialVersionUID = 747424234L;

			
			@SuppressWarnings("unchecked")
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
			
			@Override
			//���̺� ���� ����
			public boolean isCellEditable(int row, int column){
			    return false;
			}
		};
		
		this.waitingRoomTable.getTableHeader().setFont(WaitingRoomEnum.LABELFONT_SIZE80.getfont());//��Ÿ��Ʋ�۲�
		this.waitingRoomTable.setFont(WaitingRoomEnum.LABELFONT_SIZE90.getfont());
		this.waitingRoomTable.setForeground(Color.WHITE);
		this.waitingRoomTable.setShowVerticalLines(false);                               //�������� �׸����ΰ�
		this.waitingRoomTable.getTableHeader().setReorderingAllowed(false);              //�̵��Ұ�
		this.waitingRoomTable.getTableHeader().setResizingAllowed(false);                //ũ�� ���� �Ұ�
		this.waitingRoomTable.setOpaque(false);
		this.waitingRoomTable.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.waitingRoomTable.setBackground(new Color(0, 0, 0, 0));
		
		this.waitingRoomTable.getColumn("OX").setPreferredWidth(1);
		this.waitingRoomTable.getColumn("NO").setPreferredWidth(1);
		this.waitingRoomTable.getColumn("TITLE").setPreferredWidth(300);
		this.waitingRoomTable.getColumn("MASTER").setPreferredWidth(140);
		this.waitingRoomTable.getColumn("NUM").setPreferredWidth(30);
		this.waitingRoomTable.setRowHeight(50);
		this.waitingRoomTable.addMouseListener(this.waitingRoomActionListener);
		
		this.roomListPosition();
	}

	// �� ����Ʈ�� ���� �߰�
	public void addGameRoom(GameRoomInfoVO roomInfoVo) {
		DefaultTableModel tableModel = (DefaultTableModel) this.waitingRoomTable.getModel();
		
		tableModel.addRow(new Object[] {
				roomInfoVo.getEnterImage(),
				roomInfoVo.getRoomNumber(),
				roomInfoVo.getRoomName(),
				roomInfoVo.getOwner(),
				roomInfoVo.getPersons()
		});
	}
	// �渮��Ʈ �������� TODO
	public void modGameRoom(RoomAndUserListDTO roomListVo) {
		DefaultTableModel tableModel = (DefaultTableModel) this.waitingRoomTable.getModel();
		// ���� �����Ǿ��ִ� ���̺� ��ü�� �˻��Ͽ� ���� (rowCount ��ŭ)
		for(int i = 0, size = tableModel.getRowCount(); i < size; i++) {
			tableModel.removeRow(i);
		}
		for(int i = 0, size = roomListVo.getGameRoomList().size(); i < size; i++) {
			GameRoomInfoVO roomInfoVo = roomListVo.getGameRoomList().get(i);
			
			tableModel.addRow(new Object[] {
					roomInfoVo.getEnterImage(),
					roomInfoVo.getRoomNumber(),
					roomInfoVo.getRoomName(),
					roomInfoVo.getOwner(),
					roomInfoVo.getPersons()
			});
		}
	}
	
	// �� ����Ʈ���� ���� ����
	public void deleteGameRoom(GameRoomInfoVO roomInfoVo) {
		DefaultTableModel tableModel = (DefaultTableModel) this.waitingRoomTable.getModel();
		
		tableModel.getRowCount();
		
		for(int i = 0, size = tableModel.getRowCount(); i < size; i++) {
			if(roomInfoVo.getRoomNumber() == (int)tableModel.getValueAt(i, 1)) {
				tableModel.removeRow(i);
				break;
			}
		}
	}
	
	//==========================������ ����Ʈ==========================
	public void userListSetting(List<UserGamedataInfoDTO> list) throws IOException {
		ArrayList<String> usersGrade = new ArrayList<String>();
		this.players = new Vector<String>();
		for(UserGamedataInfoDTO gameData : list) {
			this.players.add(gameData.getUserID());
			usersGrade.add(gameData.getUserGrade());
		}

		
		this.imageMap = createImage(this.players, usersGrade);
		this.playerList = new JList<String>(this.players);
		this.playerList.setCellRenderer(new PlayerRenderer());
		this.addMouseAction(this.playerList, "playerList");
		this.playerListScroll.setViewportView(this.playerList);

		this.playerListScroll.setOpaque(false);
		this.playerListScroll.getViewport().setOpaque(false);
		this.playerList.setOpaque(false);
			
	}
//TODO
	public void userListAddSetting(UserGamedataInfoDTO newUser) throws IOException {
		
		this.players.add(newUser.getUserID());
		this.playerList.setListData(players);
		this.addNewUserImage(newUser.getUserID(), newUser.getUserGrade());

		this.playerList.setCellRenderer(new PlayerRenderer());
		
		this.playerListScroll.setViewportView(this.playerList);
	}
	
	public void deleteUserSetting(UserPersonalInfoDTO delUser) {
		this.players.remove(delUser.getUserID());
		this.imageMap.remove(delUser.getUserID());
		this.playerList.setCellRenderer(new PlayerRenderer());
		this.playerListScroll.setViewportView(this.playerList);
	}
	
	/***************************������ ����Ʈ Ŭ����***************************/
	public class PlayerRenderer extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList player, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(player, value, index, isSelected, cellHasFocus);
            label.setIcon(imageMap.get((String) value));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setOpaque(false);
            label.setFont(WaitingRoomEnum.LABELFONT_SIZE90.getfont());
            return label;
		}
	}
	
	/***************************������ ����Ʈ ��***************************/
	private Map<String, ImageIcon> createImage(Vector<String> player, ArrayList<String> grade) throws IOException {
		Map<String, ImageIcon> map = new HashMap<>();
	    try{
    		for(int i = 0, size = player.size(); i < size; i++) {
		    	map.put(player.get(i), new ImageIcon(ImageIO.read(
            		new File(ImageEnum.WAITINGROOM_USER_GRADE_IMAGE_MAP.getMap().get(grade.get(i)))).getScaledInstance(
    					WaitingRoomEnum.LEVEL_ICON_SIZE_WIDTH.getSize(),
    					WaitingRoomEnum.LEVEL_ICON_SIZE_HEIGHT.getSize(),
    					Image.SCALE_AREA_AVERAGING))
		    	);
    		}
	    } catch (Exception e){
	    	e.printStackTrace();
	    }    
	    return map;
	}
	
	private void addNewUserImage(String player, String grade) throws IOException {
		try{
			this.imageMap.put(player, new ImageIcon(ImageIO.read(
				new File(ImageEnum.WAITINGROOM_USER_GRADE_IMAGE_MAP.getMap().get(grade))).getScaledInstance(
					WaitingRoomEnum.LEVEL_ICON_SIZE_WIDTH.getSize(),
					WaitingRoomEnum.LEVEL_ICON_SIZE_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
			);
		
		} catch (Exception e){
			e.printStackTrace();
		}    
	}
	

	/***************************���� ��ġ***************************/
	public void roomListPosition() throws IOException {
		//�� ����Ʈ�� ��� �̹����� �ҷ��� JPanel ����
		this.waitingRoomListBackground = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/waitingroom/waitingRoomList.png")),
							0,
							0,
							WaitingRoomEnum.WAITING_ROOM_LIST_SIZE_BACKGROUND_WIDTH.getSize(), 
							WaitingRoomEnum.WAITING_ROOM_LIST_SIZE_BACKGROUND_HEIGHT.getSize(), 
							this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		
		//�渮��Ʈ ��� �̹����� ������
		this.waitingRoomListBackground.setBounds(
				WaitingRoomEnum.WAITING_ROOM_LIST_BACKGROUND_POSITION_X.getSize(), 
				WaitingRoomEnum.WAITING_ROOM_LIST_BACKGROUND_POSITION_Y.getSize(), 
				WaitingRoomEnum.WAITING_ROOM_LIST_SIZE_BACKGROUND_WIDTH.getSize(), 
				WaitingRoomEnum.WAITING_ROOM_LIST_SIZE_BACKGROUND_HEIGHT.getSize()
		);
		
		this.waitingRoomListScroll = new JScrollPane(this.waitingRoomTable);
		this.waitingRoomListScroll.setViewportView(this.waitingRoomTable);
		
		//�渮��Ʈ ��ġ�� ũ�⸦ ������
				this.waitingRoomListScroll.setBounds(
						0,0,
						WaitingRoomEnum.WAITING_ROOM_LIST_SIZE_WIDTH.getSize(),
						WaitingRoomEnum.WAITING_ROOM_LIST_SIZE_HEIGHT.getSize()
				);	
	
		this.waitingRoomListBackground.setLayout(null);
		this.waitingRoomListBackground.setOpaque(false);

		this.waitingRoomListScroll.setOpaque(false);
		this.waitingRoomListScroll.getViewport().setOpaque(false);
		
		/******************************************************************************/
		//ä�� �Է�â�� ��ġ�� ũ�⸦ ������
		this.chattingOutput.setBounds(
				0,
				0,
				WaitingRoomEnum.CHATTING_OUTPUT_SIZE_WIDTH.getSize() - 20,
				WaitingRoomEnum.CHATTING_OUTPUT_SIZE_HEIGHT.getSize()
		);
		this.chattingScroll.setBounds(
				WaitingRoomEnum.CHATTING_OUTPUT_POSITION_X.getSize(),
				WaitingRoomEnum.CHATTING_OUTPUT_POSITION_Y.getSize(),
				WaitingRoomEnum.CHATTING_OUTPUT_SIZE_WIDTH.getSize(),
				WaitingRoomEnum.CHATTING_OUTPUT_SIZE_HEIGHT.getSize()
		);
		this.chattingScroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				JScrollBar src = (JScrollBar)e.getSource();
				src.setValue(src.getMaximum());
			}
		});
		
		this.chattingOutput.setEditable(false);
		//ä�� ���â�� ��ġ�� ũ�⸦ ������
		this.chattingInputTextField.setBounds(
				WaitingRoomEnum.CHATTING_INPUT_POSITION_X.getSize(),
				WaitingRoomEnum.CHATTING_INPUT_POSITION_Y.getSize(),
				WaitingRoomEnum.CHATTING_INPUT_SIZE_WIDTH.getSize(),
				WaitingRoomEnum.CHATTING_INPUT_SIZE_HEIGHT.getSize()	
		);
		//��Ƽ���ؽ�Ʈ�ʵ�
		this.noticeTextField.setBounds(
				WaitingRoomEnum.NOTICE_TEXTFIELD_POSITION_X.getSize(),
				WaitingRoomEnum.CHATTING_INPUT_POSITION_Y.getSize(),
				WaitingRoomEnum.NOTICE_TEXTFIELD_WIDTH.getSize(),
				WaitingRoomEnum.CHATTING_INPUT_SIZE_HEIGHT.getSize()
		);
		this.noticeTextField.setEditable(false);
		this.noticeTextField.setText("��üä��");
		
		//�޼��� ��ư ��ġ�� ũ�⸦ ������
		this.sendMessageButton.setBounds(
				WaitingRoomEnum.SEND_MESSAGE_BUTTON_POSITION_X.getSize(),
				WaitingRoomEnum.SEND_MESSAGE_BUTTON_POSITION_Y.getSize(),
				WaitingRoomEnum.SEND_MESSAGE_BUTTON_WIDTH.getSize(),
				WaitingRoomEnum.SEND_MESSAGE_BUTTON_HEIGHT.getSize()
		);
		//�޽��� ��ư�� �̹����� �ҷ���
		this.sendMessageButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/waitingroom/send.png")).getScaledInstance(
					WaitingRoomEnum.SEND_MESSAGE_BUTTON_WIDTH.getSize(),
					WaitingRoomEnum.SEND_MESSAGE_BUTTON_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
		);
		
		/******************************************************************************/
		//���������� ��ư ��ġ�� ũ�⸦ ������
		this.modifyInfoButton.setBounds(
				WaitingRoomEnum.GAMESTART_JBUTTON_POSITION_X.getSize(), 
				WaitingRoomEnum.GAMESTART_JBUTTON_POSITION_Y.getSize(), 
				WaitingRoomEnum.GAMESTART_JBUTTON_WIDTH.getSize(),
				WaitingRoomEnum.GAMESTART_JBUTTON_HEIGHT.getSize()
		);
		//���������� ��ư�� �̹����� �ҷ���
		this.modifyInfoButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File(ImageEnum.WAITINGROOM_MYINFO_MODIFY.getImageDir())).getScaledInstance(
					WaitingRoomEnum.GAMESTART_JBUTTON_WIDTH.getSize(),
					WaitingRoomEnum.GAMESTART_JBUTTON_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
		);
		/******************************************************************************/
		//����� ��ư ��ġ�� ũ�⸦ ������
		this.createRoomButton.setBounds(
				WaitingRoomEnum.MODIFYINFO_JBUTTON_POSITION_X.getSize(), 
				WaitingRoomEnum.MODIFYINFO_JBUTTON_POSITION_Y.getSize(), 
				WaitingRoomEnum.MODIFYINFO_JBUTTON_WIDTH.getSize(),
				WaitingRoomEnum.MODIFYINFO_JBUTTON_HEIGHT.getSize()
		);
		//����� ��ư�� �̹����� �ҷ���
		this.createRoomButton.setIcon(
			new ImageIcon(ImageIO.read(
				new File("resources/waitingroom/_createRoom.jpg")).getScaledInstance(
					WaitingRoomEnum.MODIFYINFO_JBUTTON_WIDTH.getSize() ,
					WaitingRoomEnum.MODIFYINFO_JBUTTON_HEIGHT.getSize(),
					Image.SCALE_AREA_AVERAGING))
		);
		/******************************************************************************/
		
		this.playerListScroll.setBounds(
				WaitingRoomEnum.PLAYERS_LIST_POSITION_X.getSize(),
				WaitingRoomEnum.PLAYERS_LIST_POSITION_Y.getSize(),				
				WaitingRoomEnum.PLAYERS_LIST_WIDTH.getSize(),
				WaitingRoomEnum.PLAYERS_LIST_HEIGHT.getSize()		
		);
				
		//���� �������� �÷��̾� ��� �̹����� �ҷ��� JPanel ����
		this.playerListBackground = new JPanel() {	
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/waitingroom/waitingRoomInfo.png")),
							0,
							0,
							WaitingRoomEnum.PLAYERS_LIST_BACKGROUND_WIDTH.getSize(), 
							WaitingRoomEnum.PLAYERS_LIST_BACKGROUND_HEIGHT.getSize(), 
							this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		this.playerListBackground.setLayout(null);

		
		//���� �������� �÷��̾� ��� �̹��� ũ��� ��ġ
		this.playerListBackground.setBounds(
				WaitingRoomEnum.PLAYERS_LIST_BACKGROUND_POSITION_X.getSize(),
				WaitingRoomEnum.PLAYERS_LIST_BACKGROUND_POSITION_Y.getSize(), 
				WaitingRoomEnum.PLAYERS_LIST_BACKGROUND_WIDTH.getSize(),
				WaitingRoomEnum.PLAYERS_LIST_BACKGROUND_HEIGHT.getSize()
		);
		
		this.playerListBackground.setOpaque(false);
		
		/******************************************************************************/

		//���̵�, ���̵� �ؽ�Ʈ
		this.userIDTitleLabel.setBounds(
				WaitingRoomEnum.MY_INFO_USERID_POSITION_X.getSize(),
				WaitingRoomEnum.MY_INFO_USERID_POSITION_Y.getSize(), 
				WaitingRoomEnum.MY_INFO_USERID_WIDTH.getSize(),
				WaitingRoomEnum.MY_INFO_USERID_HEIGHT.getSize()
		);
		this.userIDTextLabel.setBounds(
				WaitingRoomEnum.MY_INFO_USERID_TEXT_POSITION_X.getSize(),
				WaitingRoomEnum.MY_INFO_USERID_TEXT_POSITION_Y.getSize(), 
				WaitingRoomEnum.MY_INFO_USERID_TEXT_WIDTH.getSize(),
				WaitingRoomEnum.MY_INFO_USERID_TEXT_HEIGHT.getSize()
		);
		//����, ���� �ؽ�Ʈ
		this.scoreTitleLabel.setBounds(
				WaitingRoomEnum.MY_INFO_SCORE_POSITION_X.getSize(),
				WaitingRoomEnum.MY_INFO_SCORE_POSITION_Y.getSize(), 
				WaitingRoomEnum.MY_INFO_SCORE_WIDTH.getSize(),
				WaitingRoomEnum.MY_INFO_SCORE_HEIGHT.getSize()
		);
		this.scoreTextLabel.setBounds(
				WaitingRoomEnum.MY_INFO_SCORE_TEXT_POSITION_X.getSize(),
				WaitingRoomEnum.MY_INFO_SCORE_TEXT_POSITION_Y.getSize(), 
				WaitingRoomEnum.MY_INFO_SCORE_TEXT_WIDTH.getSize(),
				WaitingRoomEnum.MY_INFO_SCORE_TEXT_HEIGHT.getSize()
		);
		
		//�·�, �·� �ؽ�Ʈ
		this.winningRateTitleLabel.setBounds(
				WaitingRoomEnum.MY_INFO_WINNINGRATE_POSITION_X.getSize(),
				WaitingRoomEnum.MY_INFO_WINNINGRATE_POSITION_Y.getSize(), 
				WaitingRoomEnum.MY_INFO_WINNINGRATE_WIDTH.getSize(),
				WaitingRoomEnum.MY_INFO_WINNINGRATE_HEIGHT.getSize()
		);
		this.winningRateTextLabel.setBounds(
				WaitingRoomEnum.MY_INFO_WINNINGRATE_TEXT_POSITION_X.getSize(),
				WaitingRoomEnum.MY_INFO_WINNINGRATE_TEXT_POSITION_Y.getSize(), 
				WaitingRoomEnum.MY_INFO_WINNINGRATE_TEXT_WIDTH.getSize(),
				WaitingRoomEnum.MY_INFO_WINNINGRATE_TEXT_HEIGHT.getSize()
		);
		
		
		//����, ���� �ؽ�Ʈ
		this.pointTitleLabel.setBounds(
				WaitingRoomEnum.MY_INFO_POINT_POSITION_X.getSize(),
				WaitingRoomEnum.MY_INFO_POINT_POSITION_Y.getSize(), 
				WaitingRoomEnum.MY_INFO_POINT_WIDTH.getSize(),
				WaitingRoomEnum.MY_INFO_POINT_HEIGHT.getSize()
		);
		this.pointTextLabel.setBounds(
				WaitingRoomEnum.MY_INFO_POINT_TEXT_POSITION_X.getSize(),
				WaitingRoomEnum.MY_INFO_POINT_TEXT_POSITION_Y.getSize(), 
				WaitingRoomEnum.MY_INFO_POINT_TEXT_WIDTH.getSize(),
				WaitingRoomEnum.MY_INFO_POINT_TEXT_HEIGHT.getSize()
		);
		
		
		//���, ��� �ؽ�Ʈ �̹���
		this.levelTitleLabel.setBounds(
				WaitingRoomEnum.MY_INFO_LEVEL_POSITION_X.getSize(),
				WaitingRoomEnum.MY_INFO_LEVEL_POSITION_Y.getSize(), 
				WaitingRoomEnum.MY_INFO_LEVEL_WIDTH.getSize(),
				WaitingRoomEnum.MY_INFO_LEVEL_HEIGHT.getSize()
		);
		this.levelImageLabel.setBounds(
				WaitingRoomEnum.MY_INFO_LEVEL_TEXT_POSITION_X.getSize(),
				WaitingRoomEnum.MY_INFO_LEVEL_TEXT_POSITION_Y.getSize(), 
				WaitingRoomEnum.MY_INFO_LEVEL_TEXT_WIDTH.getSize(),
				WaitingRoomEnum.MY_INFO_LEVEL_TEXT_HEIGHT.getSize()
		);

		//������ �̹��� Ʋ
		this.myInfoImageBorder = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/waitingroom/imageframe.png")),
							0,
							0,
							WaitingRoomEnum.MY_INFO_IMAGE_WIDTH.getSize(), 
							WaitingRoomEnum.MY_INFO_IMAGE_HEIGHT.getSize(), 
							this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		this.myInfoImageBorder.setLayout(null);
		this.userInfoImageLabel.setBounds(
				WaitingRoomEnum.USER_INFO_VIEW_SIZE_X.getSize(),
				WaitingRoomEnum.USER_INFO_VIEW_SIZE_Y.getSize(),
				WaitingRoomEnum.USER_INFO_VIEW_SIZE_WIDTH.getSize(),
				WaitingRoomEnum.USER_INFO_VIEW_SIZE_HEIGHT.getSize()
		);

		this.myInfoImageBorder.add(userInfoImageLabel);
		
		this.myInfoImageBorder.setBounds(
				WaitingRoomEnum.MY_INFO_IMAGE_POSITION_X.getSize(), 
				WaitingRoomEnum.MY_INFO_IMAGE_POSITION_Y.getSize(), 
				WaitingRoomEnum.MY_INFO_IMAGE_WIDTH.getSize(), 
				WaitingRoomEnum.MY_INFO_IMAGE_HEIGHT.getSize()
		);
		myInfoImageBorder.setOpaque(false);
		
		//���� ����â�� ��� �̹����� �ҷ��� JPanel ����
		this.myInfo = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(
						new File("resources/waitingroom/waitingRoomInfo.png")),
							0,
							0,
							WaitingRoomEnum.MY_INFO_WIDTH.getSize(), 
							WaitingRoomEnum.MY_INFO_HEIGHT.getSize(), 
							this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		//���� ����â�� ��ġ�� ũ�⸦ �ҷ���
		this.myInfo.setBounds(
				WaitingRoomEnum.MY_INFO_POSITION_X.getSize(), 
				WaitingRoomEnum.MY_INFO_POSITION_Y.getSize(), 
				WaitingRoomEnum.MY_INFO_WIDTH.getSize(), 
				WaitingRoomEnum.MY_INFO_HEIGHT.getSize()
		);
		
		this.myInfo.setOpaque(false);
		
		/******************************************************************************/
	
		//���ӽ��� ��ư�׵θ� ȿ���� ������
		this.modifyInfoButton.setBorderPainted(false);
		this.modifyInfoButton.setContentAreaFilled(false);
		this.modifyInfoButton.setFocusPainted(false);
		//���ӽ��� ��ư�̹��� ©���°� �̹��� �����̵����� �ذ�����
		this.modifyInfoButton.setIconTextGap(this.createRoomButton.getIconTextGap() - 15);
		//����� ��ư�׵θ� ȿ���� ������
		this.createRoomButton.setBorderPainted(false);
		this.createRoomButton.setContentAreaFilled(false);
		this.createRoomButton.setFocusPainted(false);
		//����� ��ư�̹��� ©���°� �̹��� �����̵����� �ذ�����
		this.createRoomButton.setIconTextGap(this.createRoomButton.getIconTextGap() - 15);
		
		//�޽��� ������ ��ư�׵θ� ȿ���� ������
		this.sendMessageButton.setBorderPainted(false);
		this.sendMessageButton.setContentAreaFilled(false);
		this.sendMessageButton.setFocusPainted(false);
		
		//������ ��Ʈ
		this.userIDTitleLabel.setFont(WaitingRoomEnum.LABELFONT_SIZE80.getfont());
		
		this.scoreTitleLabel.setFont(WaitingRoomEnum.LABELFONT_SIZE100.getfont());
		
		this.winningRateTitleLabel.setFont(WaitingRoomEnum.LABELFONT_SIZE100.getfont());
		
		this.pointTitleLabel.setFont(WaitingRoomEnum.LABELFONT_SIZE100.getfont());
		
		this.levelTitleLabel.setFont(WaitingRoomEnum.LABELFONT_SIZE90.getfont());
		
		this.chattingInputTextField.setFont(WaitingRoomEnum.LABELFONT_SIZE90.getfont());
		
		this.chattingOutput.setFont(WaitingRoomEnum.LABELFONT_SIZE90.getfont());
		
		
		this.setLayout(null);
		
		this.addAction(this.createRoomButton, "createRoomButton");
		this.addAction(this.chattingInputTextField, "chattingInputTextField");
		this.addAction(this.modifyInfoButton, "modifyInfoButton");
		this.addAction(this.sendMessageButton, "sendMessageButton");
		this.addMouseAction(this.noticeTextField, "noticeTextField");
		this.chattingInputTextField.addActionListener(this.waitingRoomActionListener);
		
		this.waitingRoomListBackground.add(waitingRoomListScroll);
		this.add(waitingRoomListBackground);
		this.add(this.chattingScroll);
		this.add(chattingInputTextField);
		this.add(sendMessageButton);
		this.add(modifyInfoButton);
		this.add(createRoomButton);
		this.playerListBackground.add(playerListScroll);
		this.add(playerListBackground);
		this.add(myInfoImageBorder);
		this.add(userIDTitleLabel);
		this.add(userIDTextLabel);
		this.add(scoreTitleLabel);
		this.add(scoreTextLabel);
		this.add(winningRateTitleLabel);
		this.add(winningRateTextLabel);
		this.add(pointTitleLabel);
		this.add(pointTextLabel);
		this.add(levelTitleLabel);
		this.add(levelImageLabel);
		this.add(myInfo);
		this.add(this.noticeTextField);
		
		
	}
	
// ���� �߰� �޼ҵ� ----------------------------------------------------------------------------------------
	
	public void addAction(JButton comp, String name) {
		comp.setName(name);
		comp.addActionListener(this.waitingRoomActionListener);
	}
	
	public void addAction(JTextField comp, String name) {
		comp.setName(name);
		comp.addActionListener(this.waitingRoomActionListener);
	}
	
	public void addItemAction(JRadioButton comp, String name) {
		comp.setName(name);
		comp.addItemListener(this.waitingRoomActionListener);
	}
	
	public void addMouseAction(JComponent comp, String name) {
		comp.setName(name);
		comp.addMouseListener(this.waitingRoomActionListener);
	}
	
	public void updateAddRoom() {
		
	}
	
	public int getRoomNumber() {
		DefaultTableModel tableModel = (DefaultTableModel) this.waitingRoomTable.getModel();
		Object o;
		int roomNum = 1;
		
		// ���ȣ ���ϱ�. 1 ~ 20 �� ���� ���� �������� ���� ���ȣ�� ���´�.
		if(tableModel.getRowCount() > 0) {			
			for(int i = 1, j, size; i <= 20; i++) {
				for(j = 0, size = tableModel.getRowCount(); j < size; j++) {
					o = tableModel.getValueAt(j, 1);
					if(Integer.parseInt(o.toString()) == i) {
						break;
					}
				}
				
				if(j == size) {
					roomNum = i;
					break;
				}	
			}
		}
		return roomNum;
	}
	
	public CreateGameRoomFrame newCreateGameRoomFrame() throws IOException {
		this.createGameRoomFrame = new CreateGameRoomFrame(this.waitingRoomActionListener, this);
		return this.createGameRoomFrame;
	}
	
	// ������ ��������Ʈ���� ���� Ŭ���� ���ӵ����͸� �ش� ���� ������ �ٲپ���
	public void setUserInfo(UserGamedataInfoDTO userGameData) throws IOException {
		int allCount = userGameData.getUserGameCount();
		int winCount = userGameData.getUserWinCount();
		double winRate = winCount == 0 ? 0 : ((double)winCount / allCount) * 100;
		
		StringBuffer setScore = new StringBuffer();
		setScore.append(allCount);
		setScore.append("��");
		setScore.append(winCount);
		setScore.append("��");
				
		this.pointTextLabel.setText(String.valueOf(userGameData.getUserScore()));
		this.scoreTextLabel.setText(setScore.toString());
		this.userIDTextLabel.setText(userGameData.getUserID());
		this.winningRateTextLabel.setText(String.valueOf(winRate));
		this.userInfoImageLabel.setIcon(userGameData.getUserWaitingRoomImage());
		
		this.userIDTextLabel.setFont(WaitingRoomEnum.LABELFONT_SIZE80.getfont());
		this.pointTextLabel.setFont(WaitingRoomEnum.LABELFONT_SIZE100.getfont());
		this.scoreTextLabel.setFont(WaitingRoomEnum.LABELFONT_SIZE100.getfont());
		this.winningRateTextLabel.setFont(WaitingRoomEnum.LABELFONT_SIZE100.getfont());
		
		String dir = ImageEnum.WAITINGROOM_USER_GRADE_IMAGE_MAP.getMap().get(userGameData.getUserGrade());
		this.levelImageLabel.setIcon(GetResources.getImageIcon(dir, 
					WaitingRoomEnum.MY_INFO_LEVEL_TEXT_WIDTH.getSize(),
					WaitingRoomEnum.MY_INFO_LEVEL_TEXT_HEIGHT.getSize()));
	}
	
	public void updateDeleteRoom() {
		
	}
	
	public void sendDTO(AbstractEnumsDTO dto) {
		this.basicFrame.sendDTO(dto);
	}
	
	public BasicFrame getBasicFrame() {
		return basicFrame;
	}
	
	public CreateGameRoomFrame getCreateGameRoomFrame() {
		return createGameRoomFrame;
	}
	
	public JList<String> getPlayerList() {
		return playerList;
	}
	
	public JTextArea getChattingOutput() {
		return chattingOutput;
	}
	
	public JTextField getChattingInputTextField() {
		return chattingInputTextField;
	}
	
	public JTextField getNoticeTextField() {
		return noticeTextField;
	}
	
	public JTable getWaitingRoomTable() {
		return waitingRoomTable;
	}
}