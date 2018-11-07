package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Context {
	private EntityManagerFactory emf = null;
	private static Context ctx = null;

	private Context() {
		emf = Persistence.createEntityManagerFactory("dac");
	}

	public static Context getInstance() {
		if (ctx == null) {
			ctx = new Context();
		}
		return ctx;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	public static void destroy() {
		if (ctx != null) {
			ctx.emf.close();
			ctx = null;
		}
	}
}
