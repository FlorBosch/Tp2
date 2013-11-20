package ar.fiuba.tecnicas.framework.JTest;

import java.util.HashMap;

/*
 Responsabilidad: Definir una interfaz para los elementos de la composicion
 */
public abstract class Test {
    private String testname;
    private HashMap<String, Object> context;
    private TestSuite suiteFather;

    public Test(String testname) {
	this.testname = testname;
	context = new HashMap<String, Object>();
	suiteFather = null;
    }

    public void setSuiteFather(TestSuite suiteFather) {
	this.suiteFather = suiteFather;
    }

    public TestSuite getSuiteFather() {
	return suiteFather;
    }

    public String getTestname() {
	return testname;
    }

    public HashMap<String, Object> getContext() {
	return context;
    }

    public void tearingDown(Throwable exception) {
	try {
	    tearDown();
	} catch (Throwable tearingDown) {
	    if (exception == null)
		exception = tearingDown;
	}
    }

    public abstract void run(TestReport testReport) throws Throwable;

    public abstract int countTestCases();

    public void setUp() {
    }

    public void tearDown() {
    }
}
