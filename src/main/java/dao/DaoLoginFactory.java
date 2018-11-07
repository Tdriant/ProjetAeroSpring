package dao;


public class DaoLoginFactory {
	private static DaoLoginJpaImpl daoLogin = null;
	
	public DaoLoginFactory() {
	}
	
	public static DaoLogin getInstance() {
		if(daoLogin == null) {
			daoLogin = new DaoLoginJpaImpl();
		}
		return daoLogin;
	}
}
