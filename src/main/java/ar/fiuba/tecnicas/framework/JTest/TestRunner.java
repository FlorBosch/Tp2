package ar.fiuba.tecnicas.framework.JTest;

import ar.fiuba.tecnicas.framework.ArgumentValidator;
import ar.fiuba.tecnicas.framework.Usage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

/*
 Responsabilidad: Crear y correr test ya definidos o un grupo de test definidios filtrado con expresiones regulares
 */
public class TestRunner {
    public static final int SUCCESS_EXIT = 0;
    public static final int FAILURE_EXIT = 1;
    public static final int EXCEPTION_EXIT = 2;

    private ResultPrinter resultPrinter;
    private String regExpTestcase;
    private String regExpTestsuite;
    private List<String> argtags;
    private boolean reRunMode;
    private TestReport testReport; 
    private static TestCreator testCreator;

    public TestRunner() {
	this.resultPrinter = new ResultPrinter(System.out);
	regExpTestcase = "";
	regExpTestsuite = "";
	argtags = new ArrayList<String>();

    }

    public void setListener(TestListener listener) {

    }

    public void setRegExpTestCase(String regexpTestCase) {
	this.regExpTestcase = regexpTestCase;
    }

    public void setRegExpTestSuite(String regexpTestSuite) {
	this.regExpTestsuite = regexpTestSuite;
    }

    public void setArgtags(List<String> argtags) {
	this.argtags = argtags;
    }

    public void setCreatorTest(TestCreator testCreator) {
	TestRunner.testCreator = testCreator;
    }

    public  void run(String args[]) {
	try {
	     ArgumentValidator argvalidate = new ArgumentValidator(this,
		    args);
	    argvalidate.start();
	    testReport = start();
	    if (!testReport.wasSuccessful()) {
		System.exit(FAILURE_EXIT);
	    }
	    System.exit(SUCCESS_EXIT);
	}

	catch (PatternSyntaxException patternexcp) {
	    System.err.println("Invalid regular expression 's syntax");
	    System.exit(EXCEPTION_EXIT);
	}

	catch (IllegalArgumentException badarg) {
	    Usage usage = new Usage();
	    System.err.println(usage);
	    System.exit(EXCEPTION_EXIT);
	}

	catch (Throwable e) {
	    System.err.println(e.getMessage());
	    System.exit(EXCEPTION_EXIT);
	}
    }

    private Test getTest() throws Exception {
	return testCreator.getTest();
    }

    private TestReport start() throws Throwable {
	Test test = getTest();
	return doRun(test);
    }

    private TestReport doRun(Test suite) throws Throwable {
	TestReport result = createTestReport();
	suite.run(result);
	resultPrinter.printFooter(result);
	return result;
    }

    private TestReport createTestReport() {
	TestReport result = new TestReport();
	result.initializeConditions(regExpTestcase, regExpTestsuite, argtags);
	result.addListener(resultPrinter);
	result.addListener(new XmlPrinter());
	return result;
    }

    public void setReRunMode() {
	this.reRunMode = true;
    }
}
