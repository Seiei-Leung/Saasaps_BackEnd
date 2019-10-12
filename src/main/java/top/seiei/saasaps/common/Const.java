package top.seiei.saasaps.common;

public class Const {
	
	public final static String CURRENT_USER = "currentUser";

	// 内部定义接口的好处：具体可以理解成一个类中进一步的逻辑细分
	public interface Role {
		int ROLE_CUSTOMER = 0; // 普通用户
		int ROLE_ADMIN = 1; // 管理员
	}

	public interface ProductionLineStatus {
		boolean WORKING = false;
		boolean NOT_WORKING = true;
	}
}
