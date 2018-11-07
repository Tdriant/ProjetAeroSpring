package dao;


public class DaoPassagerFactory {
	private static DaoPassagerJpaImpl daoPassager = null;
	
	public DaoPassagerFactory() {
	}
	
	public static DaoPassager getInstance() {
		if(daoPassager == null) {
			daoPassager = new DaoPassagerJpaImpl();
		}
		return daoPassager;
	}
}
