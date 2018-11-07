import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AeroportTestSimple.class, TestAdresse.class, TestEscale.class, TestInit.class, TestReservation.class,
		TestsCaVol.class, TestVol.class, villeTestSimple.class })
public class AllTests {

}
