import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoAeroport;
import dao.DaoAeroportFactory;
import dao.DaoEscale;
import dao.DaoEscaleFactory;
import dao.DaoVol;
import dao.DaoVolFactory;
import model.Aeroport;
import model.Escale;
import model.EscaleKey;
import model.Vol;
import util.Context;

public class TestEscale {
	private static DaoVol daoVol;
	private static DaoAeroport daoAeroport;
	private static DaoEscale daoEscale;
	 private Vol v1=null;
	 private Vol v2=null;
	 private Aeroport a1=null;
	 private Aeroport a2=null;
	 private Escale e1=null;
	 private Escale e2=null;
	 
	@BeforeClass
	public static void init() {
		daoVol = DaoVolFactory.getInstance();
		daoAeroport = DaoAeroportFactory.getInstance();
		daoEscale = DaoEscaleFactory.getInstance();
		 }
	
	@Test
	public void test() {

		 SimpleDateFormat date = new SimpleDateFormat("DD/MM/YYYY");
		 SimpleDateFormat heure = new SimpleDateFormat("HH-mm-ss");
		 
		 try {
			 a1= new Aeroport("Paris");
			 a2=new Aeroport("Madrid");
				v1 = new Vol(date.parse("26/10/2018"), date.parse("27/10/2018"), heure.parse("11-50-00"), heure.parse("06-45-00"));
				v2 = new Vol(date.parse("15/05/2009"), date.parse("15/05/2009"), heure.parse("06-05-00"), heure.parse("12-30-00"));
		 } catch (ParseException e) {
				e.printStackTrace();
			}
		 
		 daoAeroport.create(a1);
		 daoAeroport.create(a2);
		 daoVol.create(v1);
		 daoVol.create(v2);

		 e1=new Escale(new EscaleKey(v1, a1));
		 e2=new Escale(new EscaleKey(v2,a2));
		 daoEscale.create(e1);
		 daoEscale.create(e2);
		 daoEscale.delete(e2);
		 e1= new Escale(new EscaleKey(v1, a2));
		 daoEscale.update(e1);
		 assertNotNull(daoEscale.findByKey(e1.getId()));
		 assertNotNull(daoEscale.findAll());
	}

	@AfterClass
	public static void fermeturJpa() {

		Context.destroy();

	}
}
