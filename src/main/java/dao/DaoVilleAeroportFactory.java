package dao;

public class DaoVilleAeroportFactory {
	private static DaoVilleAeroport daoVilleAeroport = null;

	private DaoVilleAeroportFactory() {

	}

	public static DaoVilleAeroport getInstance() {
		if (daoVilleAeroport == null) {
			daoVilleAeroport = new DaoVilleAeroportJpaImpl();
		}
		return daoVilleAeroport;
	}
}