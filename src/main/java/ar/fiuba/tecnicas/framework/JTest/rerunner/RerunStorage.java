package ar.fiuba.tecnicas.framework.JTest.rerunner;

public abstract class RerunStorage {
	
	public void setMode(RerunMode mode) { }
	
	public abstract void addPassedTestName(String testName);
		
	public abstract boolean isTestRunnable(String testName);
	
	public void saveRunInformation() { }
	
}
