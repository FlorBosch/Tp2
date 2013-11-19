package ar.fiuba.tecnicas.framework.JTest;

import java.util.ArrayList;
import java.util.List;

public class TestReport {
    private List<TestListener> testListeners;
    private int runTests;
    private int oktest;
    private int errortest;
    private int failedtest;
    private boolean firsttimeintest;
    private PatternRecognizer recognizerExpressionsTestcase;
    private PatternRecognizer recognizerExpressionsTestsuite;
    private RecognizerTag recognizerTags;

    public void setFirsttimeintest(boolean firsttimeintest) {
	this.firsttimeintest = firsttimeintest;
    }

    public TestReport() {
	testListeners = new ArrayList<TestListener>();
	runTests = 0;
	oktest = 0;
	errortest = 0;
	failedtest = 0;
	firsttimeintest = true;
	recognizerExpressionsTestcase = null;
	recognizerExpressionsTestsuite = null;
	recognizerTags = null;
    }

    public void initializeRecognizerExpression(String testcaseregexp,
	    String testsuiteregexp) {
	recognizerExpressionsTestcase = new PatternRecognizer(testcaseregexp);
	recognizerExpressionsTestsuite = new PatternRecognizer(testsuiteregexp);
    }

    public void setRecognizerExpressionsTestcase(PatternRecognizer recognizer) {
	recognizerExpressionsTestcase = recognizer;
    }

    public void setRecognizerExpressionsTestsuite(PatternRecognizer recognizer) {
	recognizerExpressionsTestsuite = recognizer;
    }

    public void setRecognizerTag(RecognizerTag recognizerTags) {
	this.recognizerTags = recognizerTags;
    }

    public void addSuccess(TestCase test, double time) {
	runTests++;
	for (TestListener testListener : testListeners) {
	    testListener.addSuccess(test, time);
	}
    }

    public void addFailure(Test test, double time, Throwable throwable) {
	failedtest++;
	runTests++;
	for (TestListener testListener : testListeners) {
	    testListener.addFailure(test, time , throwable);
	}
    }

    public void addError(Test test, double time, Throwable throwable) {
	errortest++;
	runTests++;
	for (TestListener testListener : testListeners) {
	    testListener.addError(test, time , throwable);
	}
    }

    public void insertHSeparator() {
	for (TestListener testListener : testListeners) {
	    testListener.insertHSeparator();
	}
    }

    public void print(String messsage) {
	for (TestListener testListener : testListeners) {
	    testListener.print(messsage);
	}
    }

    public void addListener(TestListener listener) {
	testListeners.add(listener);
    }

    public void run(final TestCase test) {
	Timer timer = new Timer(System.nanoTime());
	if (validateTestCase(test)) {
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
	    };
	}
    }

    private boolean validateTestCase(TestCase test) {
	return (testNameMatchRegularExpression(test))
		&& validateTagTestCase(test) && !test.isSkype();
    }

    private boolean validateTagTestCase(TestCase test) {
	return recognizerTags.validate(test.getTags());
    }

    private boolean testNameMatchRegularExpression(Test test) {
	return recognizerExpressionsTestcase == null
		|| recognizerExpressionsTestcase.validate(test.toString());
    }

    public boolean testsuiteNameMatchRegularExpression(Test test) {
	return recognizerExpressionsTestsuite == null
		|| recognizerExpressionsTestsuite.validate(test.getTestname());
    }

    public boolean wasSuccessful() {
	return (failureCount() == 0);
    }

    public int failureCount() {
	return failedtest;
    }

    public int runCount() {
	return runTests;
    }

    public int errorCount() {
	return errortest;
    }
}
