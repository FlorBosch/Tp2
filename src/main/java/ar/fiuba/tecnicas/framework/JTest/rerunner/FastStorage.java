package ar.fiuba.tecnicas.framework.JTest.rerunner;

import java.util.ArrayList;
import java.util.List;

public class FastStorage extends RerunStorage {

	List<String> passedTestsNames;

	public FastStorage() {
		this.passedTestsNames = new ArrayList<String>();
	}

	@Override
	public void addPassedTestName(String testName) {
		this.passedTestsNames.add(testName);
	}

	@Override
	public boolean isTestRunnable(String testName) {
		return !this.passedTestsNames.contains(testName);
	}

}
