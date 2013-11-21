package ar.fiuba.tecnicas.framework.JTest;

import java.util.List;

import ar.fiuba.tecnicas.framework.JTest.rerunner.RerunMode;
import ar.fiuba.tecnicas.framework.JTest.rerunner.RerunStorage;

public class TestConditions {
	private PatternRecognizer recognizerExpressionsTestcase;
	private PatternRecognizer recognizerExpressionsTestsuite;
	private RecognizerTag recognizerTags;
	private RerunStorage storage;
	private RerunMode mode;

	public void initialize(String testCaseRegExp, String testSuiteRegExp,
			List<String> tags, RerunStorage storage, RerunMode mode) {
		recognizerExpressionsTestcase = new PatternRecognizer(testCaseRegExp);
		recognizerExpressionsTestsuite = new PatternRecognizer(testSuiteRegExp);
		recognizerTags = new RecognizerTag(tags);
		this.storage = storage;
		this.mode = mode;
	}

	public boolean validateTest(TestCase test) {
		boolean storageValidation = true;
		if (mode == RerunMode.RERUN)
			storageValidation = storage.isTestRunnable(test.getTestname());

		return testNameMatchRegularExpression(test)
				&& validateTagTestCase(test) && !test.isSkip()
				&& storageValidation;
	}

	public boolean validateTest(TestSuite test) {
		return testsuiteNameMatchRegularExpression(test);
	}

	private boolean validateTagTestCase(TestCase test) {
		return recognizerTags.validate(test.getTags());
	}

	private boolean testNameMatchRegularExpression(Test test) {
		return recognizerExpressionsTestcase == null
				|| recognizerExpressionsTestcase.validate(test.toString());
	}

	private boolean testsuiteNameMatchRegularExpression(Test test) {
		return recognizerExpressionsTestsuite == null
				|| recognizerExpressionsTestsuite.validate(test.getTestname());
	}

}
