package dao;

public class DaoVilleFactory {

	private static DaoVille daoVille = null;

	private DaoVilleFactory() {
	}

	public static DaoVille getInstance() {

		if (daoVille == null) {
			daoVille = new DaoVilleJpaImpl();

		}
		return daoVille;

	}

}
