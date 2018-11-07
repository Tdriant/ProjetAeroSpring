package dao;


public class DaoClientFactory {
	private static DaoClientJpaImpl daoClient = null;
	
	public DaoClientFactory() {
	}
	
	public static DaoClient getInstance() {
		if(daoClient == null) {
			daoClient = new DaoClientJpaImpl();
		}
		return daoClient;
	}
}
