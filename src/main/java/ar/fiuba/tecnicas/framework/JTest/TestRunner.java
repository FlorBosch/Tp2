package ar.fiuba.tecnicas.framework.JTest;

import ar.fiuba.tecnicas.framework.ArgumentValidator;
import ar.fiuba.tecnicas.framework.Usage;
import ar.fiuba.tecnicas.framework.JTest.rerunner.RerunMode;
import ar.fiuba.tecnicas.framework.JTest.rerunner.RerunStorage;

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
    private TestReport testReport;
    private RerunStorage storage;
    private RerunMode rerunMode;
    private static TestCreator testCreator;

    public TestRunner() {
	this.testReport = new TestReport();
	this.resultPrinter = new ResultPrinter(System.out);
	this.regExpTestcase = "";
	this.regExpTestsuite = "";
	this.argtags = new ArrayList<String>();
	this.rerunMode = RerunMode.NONE;
    }

    public void setRerunStorage(RerunStorage storage) {
	this.storage = storage;
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

    public int run(String args[]) {
	TestConditions conditions = new TestConditions();
	try {
	    ArgumentValidator argvalidate = new ArgumentValidator(this, args);
	    argvalidate.start();
	    conditions.initialize(regExpTestcase, regExpTestsuite, argtags,
		    storage, rerunMode);
	    testReport = start(conditions);
	    if (this.rerunMode != RerunMode.NONE)
		storage.saveRunInformation();
	}

	catch (PatternSyntaxException patternexcp) {
	    System.err.println("Invalid regular expression 's syntax");
	    return EXCEPTION_EXIT;
	}

	catch (IllegalArgumentException badarg) {
	    Usage usage = new Usage();
	    System.err.println(usage);
	    return EXCEPTION_EXIT;
	}

	catch (Throwable e) {
	    System.err.println(e.getMessage());
	    return EXCEPTION_EXIT;
	}

	return testReport.wasSuccessful() ? SUCCESS_EXIT : FAILURE_EXIT;
    }

    private Test getTest() throws Exception {
	return testCreator.getTest();
    }

    private TestReport start(TestConditions conditions) throws Throwable {
	Test test = getTest();
	TestReport result = createTestReport();
	result.setTestConditions(conditions);
	result.setMode(rerunMode);
	test.run(result);
	resultPrinter.printFooter(result);
	return result;
    }

    private TestReport createTestReport() {
	TestReport result = new TestReport();
	result.addListener(resultPrinter);
	result.addListener(new XmlPrinter());
	return result;
    }

    public void setRerunMode(RerunMode mode) {
	this.rerunMode = mode;
    }

    public TestReport getTestReport() {
	return testReport;
    }
}
