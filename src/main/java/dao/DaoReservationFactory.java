package dao;

public class DaoReservationFactory {
	private static DaoReservation daoReservation = null;

	public static DaoReservation getInstance() {
		if (daoReservation == null) {
			daoReservation = new DaoReservationJpaImpl();
		}
		return daoReservation;
	}

}