package ar.fiuba.tecnicas.framework.JTest;

import java.util.List;

public class TestConditions {
    private PatternRecognizer recognizerExpressionsTestcase;
    private PatternRecognizer recognizerExpressionsTestsuite;
    private RecognizerTag recognizerTags;

    public void initialize(String testCaseRegExp, String testSuiteRegExp,
	    List<String> tags) {
	recognizerExpressionsTestcase = new PatternRecognizer(testCaseRegExp);
	recognizerExpressionsTestsuite = new PatternRecognizer(testSuiteRegExp);
	recognizerTags = new RecognizerTag(tags);
    }

    public boolean validateTest(TestCase test) {
	return (testNameMatchRegularExpression(test))
		&& validateTagTestCase(test) && !test.isSkip();
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
