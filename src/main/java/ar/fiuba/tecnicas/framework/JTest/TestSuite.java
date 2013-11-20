/*

 */

package ar.fiuba.tecnicas.framework.JTest;

import java.util.Vector;

/*
 Responsabilidad: almacenar test y testsuite en una estructura (es el la clase "composite")
 * */
public class TestSuite extends Test {
    private Vector<Test> testlineitem;
    private boolean firsttimeinsuite;

    public void printSuiteTrace(Test test, TestReport testReport) {
	if (test instanceof TestCase) {
	    if (firsttimeinsuite) {
		testReport.print(getNameFather());
		testReport.insertHSeparator();
	    }
	    firsttimeinsuite = false;
	} else
	    firsttimeinsuite = true;
    }

    private boolean existsTest(Test newTest) {
	return testlineitem.contains(newTest);
    }

    private String insertAncestor(TestSuite father) {
	StringBuilder sb = new StringBuilder(getTestname());
	sb.insert(0, father.getNameFather() + ".");
	return sb.toString();
    }

    private String getNameFather() {
	TestSuite father = getSuiteFather();
	String retval = getTestname();
	if (father != null) {
	    retval = insertAncestor(father);
	}
	return retval;
    }

    public TestSuite(String testname) {
	super(testname);
	this.testlineitem = new Vector<Test>();
	this.firsttimeinsuite = true;
    }

    public TestSuite() {
	super(null);
	this.testlineitem = new Vector<Test>();
	this.firsttimeinsuite = true;
    }

    public void addTest(Test test) {
	if (!existsTest(test)) {
	    test.setSuiteFather(this);
	    testlineitem.add(test);
	}
    }

    @Override
    public int countTestCases() {
	int count = 0;
	for (Test test : testlineitem) {
	    count += test.countTestCases();
	}
	return count;
    }

    @Override
    public void run(TestReport testReport) throws Throwable {

	if (testReport.testsuiteNameMatchRegularExpression(this)) {
	    Throwable exception = null;
	    setUp();
	    try {
		for (Test test : testlineitem) {
		    printSuiteTrace(test, testReport);
		    test.run(testReport);
		}
	    } catch (Throwable running) {
		exception = running;
	    } finally {
		tearingDown(exception);
	    }
	    if (exception != null)
		throw exception;
	}
    }
}
