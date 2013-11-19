package ar.fiuba.tecnicas.framework.JTest.rerunner;

import java.util.ArrayList;
import java.util.List;

public class FastStorage implements RerunStorage {
	
	List<String> passedTestsNames;
	
	public FastStorage() {
		this.passedTestsNames = new ArrayList<String>();
	}

	@Override
	public void addPassedTestName(String testName) {
		this.passedTestsNames.add(testName);
	}

	@Override
	public List<String> getPassedTestsNames() {
		return this.passedTestsNames;
	}

}
