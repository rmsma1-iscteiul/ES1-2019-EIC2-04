import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({BackendTest.class, DataContainerTest.class,IO_ManagerTest.class, MetricsRuleTest.class})
public class AllTests {

}
