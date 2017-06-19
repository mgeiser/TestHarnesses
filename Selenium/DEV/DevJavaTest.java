import junit.framework.Test;
import junit.framework.TestSuite;

public class DevJavaTest {

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(Login.class);
    suite.addTestSuite(EnrollFlow.class);
    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
