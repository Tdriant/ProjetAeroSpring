package JpaTesting;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoCompagnieAerienne;
import dao.DaoCompagnieAerienneFactory;
import model.CompagnieAerienne;
import util.Context;

public class TestVolCA {
		private static DaoCompagnieAerienne daoCompagnieAerienne;

		@BeforeClass
		public static void initDaoCompagnieAerienne() {
			daoCompagnieAerienne = DaoCompagnieAerienneFactory.getInstance();
		}

		@AfterClass
		public static void fermeturejpa() {
			Context.destroy();
		}

		@Test
		public void insert() {
			CompagnieAerienne bouwa = new CompagnieAerienne("airlines");
			assertNull(bouwa.getId());
			daoCompagnieAerienne.create(bouwa);
			assertNotNull(bouwa.getId());
		}

		@Test
		public void findByKey() {
			CompagnieAerienne p = new CompagnieAerienne("flytime");
			daoCompagnieAerienne.create(p);
			assertNotNull(daoCompagnieAerienne.findByKey(p.getId()));
		}

		@Test
		public void update() {
			CompagnieAerienne p = new CompagnieAerienne("caribouAir");
			daoCompagnieAerienne.create(p);
			p.setNom("nom update");
			daoCompagnieAerienne.update(p);
			assertEquals("nom update", p.getNom());
		}

		@Test
		public void findAll() {
			assertNotNull(daoCompagnieAerienne.findAll());
		}

		@Test
		public void delete() {
			CompagnieAerienne p = new CompagnieAerienne("DeleterLine");
			daoCompagnieAerienne.create(p);
			daoCompagnieAerienne.delete(p);
			assertNull(daoCompagnieAerienne.findByKey(p.getId()));
		}

		@Test
		public void deleteByKey() {
			CompagnieAerienne p = new CompagnieAerienne("Deleter");
			daoCompagnieAerienne.create(p);
			daoCompagnieAerienne.deleteByKey(p.getId());
			assertNull(daoCompagnieAerienne.findByKey(p.getId()));
		}

	}

