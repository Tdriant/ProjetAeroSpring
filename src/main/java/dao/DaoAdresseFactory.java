package dao;

public class DaoAdresseFactory {
	private static DaoAdresse daoAdresse = null;

	public static DaoAdresse getInstance() {
		if (daoAdresse == null) {
			daoAdresse = new DaoAdresseJpaImpl();
		}
		return daoAdresse;
	}

}