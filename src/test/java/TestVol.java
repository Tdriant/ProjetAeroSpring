import static org.junit.Assert.*;

import org.junit.Test;

	import static org.junit.Assert.*;

	import org.junit.AfterClass;
	import org.junit.BeforeClass;
	import org.junit.Test;

	import dao.DaoVol;
	import dao.DaoVolFactory;
	import model.Vol;
	import util.Context;

	public class TestVol {

		private static DaoVol daoVol;

		@BeforeClass
		public static void initDaoVol() {
			daoVol = DaoVolFactory.getInstance();
		}

		@AfterClass
		public static void fermeturejpa() {
			Context.destroy();
		}

		@Test
		public void insert() {
			Vol bouwa = new Vol();
			assertNull(bouwa.getId());
			daoVol.create(bouwa);
			assertNotNull(bouwa.getId());
		}

		@Test
		public void findByKey() {
			Vol p = new Vol();
			daoVol.create(p);
			assertNotNull(daoVol.findByKey(p.getId()));
		}

		@Test
		public void update() {
			Vol p = new Vol();
			daoVol.create(p);
			daoVol.update(p);
		}

		@Test
		public void findAll() {
			assertNotNull(daoVol.findAll());
		}

		@Test
		public void delete() {
			Vol p = new Vol();
			daoVol.create(p);
			daoVol.delete(p);
			assertNull(daoVol.findByKey(p.getId()));
		}

		@Test
		public void deleteByKey() {
			Vol p = new Vol();
			daoVol.create(p);
			daoVol.deleteByKey(p.getId());
			assertNull(daoVol.findByKey(p.getId()));
		}



	}
