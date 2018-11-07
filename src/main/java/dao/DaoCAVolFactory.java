package dao;

public class DaoCAVolFactory {
	private static DaoCAVol daoCAVol = null;

	public static DaoCAVol getInstance() {
		if (daoCAVol == null) {
			daoCAVol = new DaoCAVolJpaImpl();
		}
		return daoCAVol;
	}

}