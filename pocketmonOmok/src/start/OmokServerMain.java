package start;

import java.io.IOException;

import omokGame.server.OmokServer;


// Exception in thread "main" java.net.BindException: Address already in use: JVM_Bind
// �̷� ���� �߻��� enums > ServerIPEnum ���� ������Ʈ �ٸ� ��ȣ�� ���� �� �ٽ� ���� �� �õ��ϼ���.

 
// ȸ������ ȭ�鿡�� java.sql.SQLSyntaxErrorException: ORA-00942: table or view does not exist
// �̷� ���� �߻��� datasDAO > DBCommectionPool ���� user�� passwd ������ ���� ���̵�� �н������ ���� �� �۾��ϼ���.
public class OmokServerMain {
	public static void main(String[] args) throws IOException {
		new OmokServer().gameServerOn();
	}
}