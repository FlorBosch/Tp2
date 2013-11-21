package ar.fiuba.tecnicas.framework.JTest.rerunner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class PlainFileStorage extends RerunStorage {

    private static final String fileName = "store.txt";
    private List<String> passedTests;

    public PlainFileStorage() {
	this.passedTests = new ArrayList<String>();
    }

    @Override
    public void addPassedTestName(String testName) {
	this.passedTests.add(testName);
    }

    @Override
    public boolean isTestRunnable(String testName) {
	return !this.passedTests.contains(testName);
    }

    @Override
    public void setMode(RerunMode mode) {
	if (mode == RerunMode.RERUN) {
	    getPassedTests();
	}
    }

    private void getPassedTests() {
	try {
	    BufferedReader reader = new BufferedReader(new FileReader(fileName));
	    String line = null;
	    while ((line = reader.readLine()) != null) {
		this.passedTests.add(line);
	    }
	    reader.close();
	} catch (Exception e) {
	    System.out.println("Error reading storage file:");
	    System.out.println(e.getMessage());
	}
    }

    @Override
    public void saveRunInformation() {
	FileWriter file;
	BufferedWriter writeBuffer;
	try {
	    file = new FileWriter(fileName);
	    writeBuffer = new BufferedWriter(file);

	    for (String line : this.passedTests) {
		writeBuffer.write(line);
		writeBuffer.newLine();
		writeBuffer.flush();
	    }

	    writeBuffer.close();

	} catch (Exception e) {
	    System.out.println("Error writing storage file:");
	    System.out.println(e.getMessage());
	}
    }

}
