package ar.fiuba.tecnicas.framework.JTest;

import java.util.ArrayList;
import java.util.List;

import ar.fiuba.tecnicas.framework.JTest.rerunner.RerunMode;
import ar.fiuba.tecnicas.framework.JTest.rerunner.RerunStorage;

public class TestReport {
	private List<TestListener> testListeners;
	private RerunStorage rerunStorage;
	private RerunMode rerunMode;
	private TestConditions testConditions;

	public void setRerunStorage(RerunStorage rerunStorage) {
		this.rerunStorage = rerunStorage;
	}

	private int runTests;
	private int errorTest;
	private int failedTest;

	public void setFirsttimeintest(boolean firsttimeintest) {
	}

	public TestReport() {
		testListeners = new ArrayList<TestListener>();
		runTests = 0;
		errorTest = 0;
		failedTest = 0;
	}

	public void setMode(RerunMode rerunMode) {
		this.rerunMode = rerunMode;
	}

	public void addSuccess(TestCase test, double time) {
		runTests++;
		for (TestListener testListener : testListeners)
			testListener.addSuccess(test, time);

		if(this.rerunMode == RerunMode.RECORD)
			this.rerunStorage.addPassedTestName(test.getTestname());
	}

	public void addFailure(Test test, double time, Throwable throwable) {
		failedTest++;
		runTests++;
		for (TestListener testListener : testListeners)
			testListener.addFailure(test, time, throwable);
	}

	public void addError(Test test, double time, Throwable throwable) {
		errorTest++;
		runTests++;
		for (TestListener testListener : testListeners)
			testListener.addError(test, time, throwable);
	}

	public void insertHSeparator() {
		for (TestListener testListener : testListeners)
			testListener.insertHSeparator();
	}

	public void print(String messsage) {
		for (TestListener testListener : testListeners)
			testListener.print(messsage);
	}

	public void addListener(TestListener listener) {
		testListeners.add(listener);
	}

	public void run(final TestCase test) {
		Timer timer = new Timer();
		timer.start();
		if (testConditions.validateTest(test)) {
			try {
				test.runTestSequence();
				timer.calculateTime();
				addSuccess(test, timer.getTime());
			} catch (AssertionError assertionError) {
				addFailure(test, timer.getTime(), assertionError);
			} catch (TimeOutException timeOutException) {
				addFailure(test, timer.getTime(), timeOutException);
			} catch (Throwable exception) {
				addError(test, timer.getTime(), exception);
			}
		}
	}

	public boolean testsuiteNameMatchRegularExpression(TestSuite test) {
		return testConditions.validateTest(test);
	}

	public boolean wasSuccessful() {
		return failureCount() == 0;
	}

	public int failureCount() {
		return failedTest;
	}

	public int runCount() {
		return runTests;
	}

	public int errorCount() {
		return errorTest;
	}
	
	public void setTestConditions(TestConditions conditions) {
		this.testConditions = conditions;
	}

}
