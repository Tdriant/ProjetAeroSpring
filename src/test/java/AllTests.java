import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AeroportTestSimple.class, DaoVilleAeroportTest.class, TestAdresse.class, TestAdressePassager.class,
		testAvionJClient.class, testAvionJLogin.class, testAvionJPassager.class, TestClientLogin.class,
		TestEscale.class, TestInit.class, TestResaClient.class, TestResaPassager.class, TestResaVol.class,
		TestReservation.class, TestsCaVol.class, TestVol.class, VilleAeroportTestSimple.class, villeTestSimple.class })
public class AllTests {

}
