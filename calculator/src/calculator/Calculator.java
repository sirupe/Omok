package calculator;

import java.awt.*;
import java.awt.event.*;

class Calculator extends Frame implements ActionListener {
	private Label lb1,lb2;
	private Button back,c;
	private Panel p1,p2,p3;
	private String str2[] = {"Backspace","C"};
	private String str[] = {"1" ,"2" ,"3" ,"+",
					 "4", "5" , "6", "-",
					 "7", "8", "9", "*" ,
					 ".",  "0", "=", "/"};
	private Button bt[] = new Button[str.length];
	private Button bt2[] = new Button[str2.length];
	private String result="";  
  	private String result1=""; //연산화면-lb1
  	private String result2=""; //현재화면-lb2
	private String number[] = {" ", " ", " "};	


	public Calculator(){
		this.setLayout(null);
		lb1 = new Label("",Label.RIGHT);
		lb2 = new Label("0",Label.RIGHT);
		
		//lb1+lb2
		p1 = new Panel();
		p1.setLayout(new GridLayout(2,1));
		p1.setBounds(10,20,300,80);
		p1.setBackground(Color.WHITE);
		p1.add(lb1);
		p1.add(lb2);

		//back + c
		p3 = new Panel();
		p3.setLayout(new GridLayout(1,2,4,4));
		p3.setBounds(10,100,300,50);
		for(int i=0; i<bt2.length;i++)
        {
           bt2[i] = new Button(str2[i]);
           bt2[i].setBackground(new Color(171,152,118));
		   bt2[i].addActionListener(this);
           p3.add(bt2[i]);
        }

		//숫자+연산
        p2 = new Panel();
		p2.setLayout(new GridLayout(4,4,4,4));
		p2.setBounds(10,150,300,150);
        for(int i=0; i<bt.length;i++)
        {
           bt[i] = new Button(str[i]);
           bt[i].setBackground(Color.ORANGE);
		   bt[i].addActionListener(this);
           p2.add(bt[i]);
        }
		//배치
		add(p1);
		add(p3);
		add(p2);
		
		//틀

		setTitle("미니계산기");
		setBounds(200,300,330,310);
		setBackground(Color.WHITE);
		setResizable(false);//크기변경 x
		setVisible(true);

		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
				}
			});
		}//public calc()



		public void actionPerformed(ActionEvent e) {
				String accom = e.getActionCommand();

				if(accom .equals("1") || accom .equals("2") ||
					accom .equals("3") || accom .equals("4") ||
					accom .equals("5") || accom .equals("6")|| 
					accom .equals("7") || accom .equals("8") || 
					accom .equals("9")) {
					if(result2.equals("0")) {
						result2="";
					}
					result2 += accom ;
					lb2.setText(result2);
				}//숫자-0~9

				else if(accom .equals("Backspace")) {
					int len = lb2.getText().length();
					if(len==1) {
						result2 = "";
						lb2.setText("0");
					} else {
						if(!result2.equals("")) {
							result2 = result2.substring(0, len-1);
							lb2.setText(result2);
						}
					}
				}//Backspace

				else if(accom .equals("C")) {
					result="";
					result1="";
					result2="";
					lb1.setText("");
					lb2.setText("0");
					number[0] = " ";
					number[1] = " ";
					number[2] = " ";
				}//C
				 else if(accom.equals("0")){
					if(result2.equals("0")){
						result2 = "0";
					}
					else{
						result2 = result2 + accom;
					}
					lb2.setText(result2);
				}//"0"


				else if(accom .equals(".")) {
					if(result2.equals("")) {
						result2="0"+accom ;
					} else {
						if(result2.indexOf(".")==-1) {
							result2 += accom ;
						}
					}
					lb2.setText(result2);
				} //.

				else if(accom .equals("=")) {
					if(!result2.equals("")) {
						number[2] = result2;
					}
					if(result1.equals("")) {
						if(!number[1].equals("")) {
							result = Cal();
							if(result.equals("0으로 나눌 수 없습니다.")) {
								result="";
								result1="";
								result2 = "";
								number[0] = " ";
								number[1] = " ";
								number[2] = " ";
							} else {
								number[0] = result;
								lb2.setText(result);
							}
						}
						lb1.setText(result1);
					} else {
						if(number[2].equals("")) {
							number[2] = result2;
						}
						result = number[0];

						if(result.equals("0으로 나눌 수 없습니다.")) {
							lb2.setText(result);
							result="";
							result1="";
							result2 = "";
							number[0] = " ";
							number[1] = " ";
							number[2] = " ";
						} else {
							result = Cal();
							
							if(!result.equals("0으로 나눌 수 없습니다.")) {
								result1 = "";
								number[0] = result;
								lb2.setText(result);
								lb1.setText(result1);
								result2 = "";
							} else {
								lb2.setText("0으로 나눌 수 없습니다.");
								lb1.setText("");
								result="";
								result1="";
								result2 = "";
								number[0] = " ";
								number[1] = " ";
								number[2] = " ";
							}
						}
					}
				}//"="
				
				else if(accom .equals("/") || accom .equals("*") 
					|| accom .equals("-") || accom .equals("+")) 
					{
					if(result1.equals("")) {
						if(result2.equals("")) {
							if(result.equals("")) {
								result1 = "0" + accom ;
								number[0] = "0";
							} else {
								result1 = result + accom ;
							}
						} else {
							if(!result.equals("")) {
								result1 = result + accom ; 
							}
							result1 = result2 + accom ;
							number[0] = result2;
						}
						number[1] = accom ;
					} else {
						if(result2.equals("")) {
							result1 = result1.substring(0, result1.length()-1) + accom ;
							number[1] = accom ;
						} else {
							result1 += result2 + accom ;
							number[2] = result2;
							result = Cal();
							number[1] = accom ;
							lb2.setText(result);
							number[0] = result;
						}
					}
					if(number[1].equals("/") && (number[2].equals(" 0") || number[2].equals("0"))) {

					} else {
						result2 = "";	
						lb1.setText(result1);
					}

				}//+_*/

			}//actionPerformed()

			public String Cal() {
				String num = "";
				String testNum1 = "";
				String testNum2 = "";

				if(number[0].indexOf(".")!=-1) {
					int index1 = number[0].indexOf(".");

					testNum1 = number[0].substring(index1, number[0].length());

					if(testNum1.replaceAll("0", "").equals(".")){
						number[0] = number[0].substring(0, index1);
					}
				}

				if(number[2].indexOf(".")!=-1) {
					int index2 = number[2].indexOf(".");

					testNum2 = number[2].substring(index2, number[2].length());

					if(testNum2.replaceAll("0", "").equals(".")){
						number[2] = number[2].substring(0, index2);
					}
				}
				if(number[1].equals("*")) {
					if(number[0].indexOf(".")!=-1 || number[2].indexOf(".")!=-1) {
						num = (Double.parseDouble(number[0]) * Double.parseDouble(number[2])) + "";
					} else {
						if(number[2].equals("")){
							num = result2;
						} else {
							if(!result.equals("")) {
								number[0] = result;
							}
							num = (Long.parseLong(number[0]) * Long.parseLong(number[2])) + "";
							result = num;
							number[0] = result;
						}
					}
				} else if(number[1].equals("+")) {
					if(number[0].indexOf(".")!=-1 || number[2].indexOf(".")!=-1) {
						num = (Double.parseDouble(number[0]) + Double.parseDouble(number[2])) + "";
					} else {
						if(number[2].equals("")){
							num = result2;
						} else {
							if(!result.equals("")) {
								number[0] = result;
							}
							num = (Long.parseLong(number[0]) + Long.parseLong(number[2])) + "";
							result = num;
							number[0] = result;
						}
					}
				} else if(number[1].equals("-")) {
					if(number[0].indexOf(".")!=-1 || number[2].indexOf(".")!=-1) {
						num = (Double.parseDouble(number[0]) - Double.parseDouble(number[2])) + "";
					} else {
						if(number[2].equals("")){
							num = result2;
						} else {
							if(!result.equals("")) {
								number[0] = result;
							}
							num = (Long.parseLong(number[0]) - Long.parseLong(number[2])) + "";
							result = num;
							number[0] = result;
						}
					}
				} else if(number[1].equals("/")) {
					number[2].trim();
					if(number[0].indexOf(".")!=-1 || number[2].indexOf(".")!=-1) {
						num = (Double.parseDouble(number[0]) / Double.parseDouble(number[2])) + "";
					} else {
						if(number[2].equals("0")) {
							num = "0으로 나눌 수 없습니다.";
							result2 = "";
						} else {
							if(number[2].equals("")){
								num = result2;
							} else {
								if(!result.equals("")) {
									number[0] = result;
								}
								num = (Double.parseDouble(number[0]) / Double.parseDouble(number[2])) + "";

								if(num.indexOf(".")!=-1) {
									int index3 = num.indexOf(".");
									String testNum3 = num.substring(index3, num.length());
									if(testNum3.replaceAll("0", "").equals(".")){
										num = num.substring(0, index3);
									}
								}
								result = num;
								number[0] = result;
							}
						}
					}
				}
				return num;
			} //cal()


		public static void main(String[] args) {
			new Calculator();
		}
	}//class calc