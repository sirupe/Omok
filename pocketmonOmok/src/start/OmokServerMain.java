package start;

import java.io.IOException;

import omokGame.server.OmokServer;


// Exception in thread "main" java.net.BindException: Address already in use: JVM_Bind
// 이런 에러 발생시 enums > ServerIPEnum 에서 서버포트 다른 번호로 변경 후 다시 서버 온 시도하세요.

 
// 회원가입 화면에서 java.sql.SQLSyntaxErrorException: ORA-00942: table or view does not exist
// 이런 에러 발생시 datasDAO > DBCommectionPool 에서 user와 passwd 각자의 서버 아이디와 패스워드로 변경 후 작업하세요.
public class OmokServerMain {
	public static void main(String[] args) throws IOException {
		new OmokServer().gameServerOn();
	}
}