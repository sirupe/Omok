package enums.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

public enum ModifyEnum {
   
   
   SCREEN_SIZE(Toolkit.getDefaultToolkit().getScreenSize()),
   DISTANCE((int)(SCREEN_SIZE.getDimension().height * 0.065)),
   
   MODIFY_JOINFRAME_SIZE_WIDTH((int)(SCREEN_SIZE.getDimension().getWidth() * 0.3)),
   MODIFY_JOINFRMAE_SIZE_HEIGHT((int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 1.4)),   
   MODIFY_JOINFRMAE_POSITION_X((int)((SCREEN_SIZE.getDimension().getWidth() / 2) - (MODIFY_JOINFRAME_SIZE_WIDTH.getSize() / 2 ))),
   MODIFY_JOINFRMAE_POSITION_Y((int)((SCREEN_SIZE.getDimension().getHeight() / 2) - (MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() / 2))),
   
//======================================라벨=====================================================================
   //아이디 라벨
   MODIFY_ID_LABEL(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.03),
         (int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 0.2),
         (int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.25),
         (int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.03)
   )),
   
   //비밀번호라벨
   MODIFY_PWD_LABEL(new Rectangle(
         MODIFY_ID_LABEL.getRectangle().x,
         MODIFY_ID_LABEL.getRectangle().y + DISTANCE.getSize(),
         MODIFY_ID_LABEL.getRectangle().width,
         MODIFY_ID_LABEL.getRectangle().height
   )),
   
   //재비밀번호라벨
   MODIFY_REPWD_LABEL(new Rectangle(
         MODIFY_ID_LABEL.getRectangle().x,
         MODIFY_PWD_LABEL.getRectangle().y + DISTANCE.getSize(),
         (int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.5),
         MODIFY_ID_LABEL.getRectangle().height
   )),
   
   //비밀번호 재비밀번호 일치 하지 않을시 나타나는 에러 메세지
   MODIFY_REPWDERROR_LABEL(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.30),
         (int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 1.3),
         MODIFY_REPWD_LABEL.getRectangle().width,
         MODIFY_ID_LABEL.getRectangle().height
   )),
   
   //이름
   MODIFY_NAME_LABEL(new Rectangle(
         MODIFY_ID_LABEL.getRectangle().x,
         MODIFY_REPWD_LABEL.getRectangle().y + DISTANCE.getSize(),
         MODIFY_REPWD_LABEL.getRectangle().width,
         MODIFY_ID_LABEL.getRectangle().height
   )),
   
   //성별 라벨
   MODIFY_GENDER_LABEL(new Rectangle(
         MODIFY_ID_LABEL.getRectangle().x,
         MODIFY_NAME_LABEL.getRectangle().y + DISTANCE.getSize(),
         MODIFY_REPWD_LABEL.getRectangle().width,
         MODIFY_ID_LABEL.getRectangle().height
   )),
   
   //생일 라벨
   MODIFY_BIRTH_LABEL(new Rectangle(
         MODIFY_ID_LABEL.getRectangle().x,
         MODIFY_GENDER_LABEL.getRectangle().y + DISTANCE.getSize(),
         MODIFY_REPWD_LABEL.getRectangle().width,
         MODIFY_ID_LABEL.getRectangle().height
   )),
   
   //년도 라벨
   MODIFY_YEAR_LABEL(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.42),
         MODIFY_BIRTH_LABEL.getRectangle().y,
         MODIFY_REPWD_LABEL.getRectangle().width,
         MODIFY_ID_LABEL.getRectangle().height
   )),
   
   //월 라벨
   MODIFY_MONTH_LABEL(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.61),
         MODIFY_BIRTH_LABEL.getRectangle().y,
         MODIFY_REPWD_LABEL.getRectangle().width,
         MODIFY_ID_LABEL.getRectangle().height
   )),
   
   //일라벨
   MODIFY_DATE_LABEL(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.8),
         MODIFY_BIRTH_LABEL.getRectangle().y,
         MODIFY_REPWD_LABEL.getRectangle().width,
         MODIFY_ID_LABEL.getRectangle().height
   )),
   
   //이메일 라벨
   MODIFY_EMAIL_LABEL(new Rectangle(
         MODIFY_ID_LABEL.getRectangle().x,
         (int)(MODIFY_BIRTH_LABEL.getRectangle().y * 1.19),
         MODIFY_REPWD_LABEL.getRectangle().width,
         MODIFY_ID_LABEL.getRectangle().height
   )),
   
   //이메일 에러 메세지
   MODIFY_EMAIL_ERR_LABEL(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.3),
         (int)(MODIFY_JOINFRMAE_POSITION_Y.getSize() * 2.95),
         (int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.8),
         (int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.03)
   )),
   
   //이메일 @ 라벨
   MODIFY_AT_LABEL(new Rectangle(
         MODIFY_YEAR_LABEL.getRectangle().x,
         MODIFY_EMAIL_LABEL.getRectangle().y,
         (int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.135),
         (int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.05)
   )),
   
   //전화번호 수정
   MODIFY_TELNAME_LABELMID(new Rectangle(
         MODIFY_ID_LABEL.getRectangle().x,
         (int)(MODIFY_AT_LABEL.getRectangle().y * 1.16),
         MODIFY_REPWD_LABEL.getRectangle().width,
         MODIFY_ID_LABEL.getRectangle().height
   )),
   
   //- 라벨
   MODIFY_TELHYPHEN1_LABEL(new Rectangle(
         MODIFY_YEAR_LABEL.getRectangle().x,
         MODIFY_TELNAME_LABELMID.getRectangle().y,
         (int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.4),
         MODIFY_AT_LABEL.getRectangle().height
   )),
   
   MODIFY_TELHYPHEN2_LABEL(new Rectangle(
         MODIFY_MONTH_LABEL.getRectangle().x,
         MODIFY_TELHYPHEN1_LABEL.getRectangle().y,
         MODIFY_TELHYPHEN1_LABEL.getRectangle().width,
         MODIFY_AT_LABEL.getRectangle().height
   )),
//=================================gender라디오박스=================================
   MODIFY_GENDERMAN_RADIOBUTTON(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.26),
         MODIFY_GENDER_LABEL.getRectangle().y,
         MODIFY_REPWD_LABEL.getRectangle().width,
         MODIFY_ID_LABEL.getRectangle().height
   )),
   
   MODIFY_GENDERWOMAN_RADIOBUTTON(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.45),
         MODIFY_GENDER_LABEL.getRectangle().y,
         MODIFY_REPWD_LABEL.getRectangle().width,
         MODIFY_ID_LABEL.getRectangle().height
   )),

//=====================================텍스트====================================================================TODO
   //아이디 텍스트필드
   MODIFY_ID_TEXT(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.32),
         MODIFY_ID_LABEL.getRectangle().y,
         MODIFY_TELHYPHEN1_LABEL.getRectangle().width,
         MODIFY_AT_LABEL.getRectangle().height
   )),
   
   //비밀번호 텍스트 필드
   MODIFY_PWD_TEXT(new Rectangle(
         MODIFY_ID_TEXT.getRectangle().x,
         MODIFY_PWD_LABEL.getRectangle().y,
         MODIFY_TELHYPHEN1_LABEL.getRectangle().width,
         MODIFY_AT_LABEL.getRectangle().height
   )),

   //수정 패스워드 재입력 텍스트
   MODIFY_REPWD_TEXT(new Rectangle(
         MODIFY_ID_TEXT.getRectangle().x,
         MODIFY_REPWD_LABEL.getRectangle().y,
         MODIFY_TELHYPHEN1_LABEL.getRectangle().width,
         MODIFY_AT_LABEL.getRectangle().height
   )),
   
   //수정 이름 텍스트
   MODIFY_NAME_TEXT(new Rectangle(
         MODIFY_ID_TEXT.getRectangle().x,
         MODIFY_NAME_LABEL.getRectangle().y,
         MODIFY_TELHYPHEN1_LABEL.getRectangle().width,
         MODIFY_AT_LABEL.getRectangle().height
   )),
   
   //수정 이메일 아이디 텍스트
   MODIFY_EAMILID_TEXT(new Rectangle(
         MODIFY_GENDERMAN_RADIOBUTTON.getRectangle().x,
         (int)(MODIFY_AT_LABEL.getRectangle().y * 0.99),
         (int)(MODIFY_AT_LABEL.getRectangle().width * 1.2),
         MODIFY_AT_LABEL.getRectangle().height
   )),
   
   //수정 이메일 주소 텍스트
   MODIFY_EAMILADDR_TEXT(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.48),
         (int)(MODIFY_AT_LABEL.getRectangle().y * 0.99),
         (int)(MODIFY_AT_LABEL.getRectangle().width * 1.2),
         MODIFY_AT_LABEL.getRectangle().height
   )),
   
   //수정 전화번호 가운데 텍스트
   MODIFY_TELMID_TEXT(new Rectangle(
         (int)(MODIFY_EAMILADDR_TEXT.getRectangle().x * 0.98),
         (int)(MODIFY_TELHYPHEN1_LABEL.getRectangle().y * 0.99),
         (int)(MODIFY_AT_LABEL.getRectangle().width * 1.2),
         MODIFY_AT_LABEL.getRectangle().height
   )),
   
   //수정 전화번호 끝자리 텍스트
   MODIFY_TELEND_TEXT(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.65),
         (int)(MODIFY_TELHYPHEN1_LABEL.getRectangle().y * 0.99),
         (int)(MODIFY_AT_LABEL.getRectangle().width * 1.2),
         MODIFY_AT_LABEL.getRectangle().height
   )),
   
   //===================================초이스박스===================================
   //년도
   MODIFY_YEAR_COMBOBOX(new Rectangle(
         MODIFY_GENDERMAN_RADIOBUTTON.getRectangle().x,
         (int)(MODIFY_BIRTH_LABEL.getRectangle().y * 0.98),
         (int)(MODIFY_AT_LABEL.getRectangle().width * 1.2),
         MODIFY_AT_LABEL.getRectangle().height
   )),
   
   //월
   MODIFY_MONTH_COMBOBOX(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.45),
         MODIFY_YEAR_COMBOBOX.getRectangle().y,
         (int)(MODIFY_AT_LABEL.getRectangle().width * 1.2),
         MODIFY_AT_LABEL.getRectangle().height
   )),
   
   //도
   MODIFY_DATE_COMBOBOX(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.64),
         MODIFY_YEAR_COMBOBOX.getRectangle().y,
         (int)(MODIFY_AT_LABEL.getRectangle().width * 1.2),
         MODIFY_AT_LABEL.getRectangle().height
   )),
   
   //전화번호 앞자리
   MODIFY_TELFRONTNUM_COMBO(new Rectangle(
         MODIFY_GENDERMAN_RADIOBUTTON.getRectangle().x,
         (int)(MODIFY_TELHYPHEN1_LABEL.getRectangle().y * 0.99),
         (int)(MODIFY_AT_LABEL.getRectangle().width * 1.2),
         MODIFY_AT_LABEL.getRectangle().height
   )),
   //이메일주소
   MODIFY_EMAILADDR_COMBO(new Rectangle(
         MODIFY_TELEND_TEXT.getRectangle().x,
         (int)(MODIFY_AT_LABEL.getRectangle().y * 0.99),
         (int)(MODIFY_JOINFRAME_SIZE_WIDTH.getSize() * 0.2),
         MODIFY_AT_LABEL.getRectangle().height
   )),
   
   //=======================================버튼=====================================
   //수정버튼
   MODIFY_MODIFY_BUTTON(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.25),
         MODIFY_TELFRONTNUM_COMBO.getRectangle().y + DISTANCE.getSize(),
         MODIFY_AT_LABEL.getRectangle().width,
         (int)(MODIFY_JOINFRMAE_SIZE_HEIGHT.getSize() * 0.07)
   )),
   
   //취소
   MODIFY_CANCEL_BUTTON(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.4),
         MODIFY_MODIFY_BUTTON.getRectangle().y,
         MODIFY_AT_LABEL.getRectangle().width,
         MODIFY_MODIFY_BUTTON.getRectangle().height
   )),
   
   //탈퇴버튼
   MODIFY_DROPOUT_BUTTON(new Rectangle(
         (int)(MODIFY_JOINFRMAE_POSITION_X.getSize() * 0.55),
         MODIFY_MODIFY_BUTTON.getRectangle().y,
         MODIFY_AT_LABEL.getRectangle().width,
         MODIFY_MODIFY_BUTTON.getRectangle().height
   )),
   
   //이메일 및 전화번호 정보
   MODIFY_EMAIL_COMBO_ADDRESS(new String[] {
         "직접입력",
         "naver.com",
         "hanmail.net",
         "nate.com",
         "gmail.com"
   }),
   
   MODIFY_TEL_FRONT_NUM_COMBO(new String[] {
         "선택", "010", "011", "016", "019", "017"
   }),
   
   LABELFONT_DEFAULT(new Font("a으라차차", Font.BOLD, ModifyEnum.SCREEN_SIZE.getDimension().width / 100)),
   
   //컴포넌트 폰트
   JOIN_COMPFONT_DEFAULT(new Font("a으라차차", Font.PLAIN, ModifyEnum.SCREEN_SIZE.getDimension().width / 120)),

   //정합성검사 라벨 폰트
   JOIN_CHECKLABEL_FONT_DEFAULT(new Font("a으라차차", Font.BOLD, ModifyEnum.SCREEN_SIZE.getDimension().width / 150)),
   
   //콤보박스 배경화면
   CHOICEBACKGROUND(Color.black),
   //콤보박스 이메일 배경화면
   MODIFY_EMAIL_COMBOBOX_BACKGROUND(Color.white),

   ERROR_MESSAGE_COLOR(Color.red),
   DEFAULT_MESSAGE_COLOR(Color.green);
   
   private Dimension dimension;
   private int size;
   private Color color;
   private Font font;
   private EmptyBorder border;
   private Rectangle rec;
   private String[] strArr;
   
   private ModifyEnum(Color color) {
      this.color = color;
   }
   
   private ModifyEnum(Font font) {
      this.font = font;
   }
   
   private ModifyEnum(EmptyBorder border) {
      this.border = border;
   }
   
   private ModifyEnum(int size) {
      this.size = size;
   }
   
   private ModifyEnum(Dimension dimension) {
      this.dimension = dimension;
   }
   
   private ModifyEnum(Rectangle rec) {
      this.rec = rec;
   }
   
   public Rectangle getRectangle() {
      return rec;
   }
   
   private ModifyEnum(String[] strArr) {
      this.strArr = strArr;
   }
   
   public Dimension getDimension() {
      return dimension;
   }

   public int getSize() {
      return size;
   }
   
   public Color getColor() {
      return color;
   }
   
   public Font getFont() {
      return font;
   }
   
   public EmptyBorder getBorder() {
      return border;
   }
   
   public String[] getStrArr() {
      return strArr;
   }

}