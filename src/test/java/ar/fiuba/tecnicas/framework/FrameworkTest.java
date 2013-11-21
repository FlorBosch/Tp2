package ar.fiuba.tecnicas.framework;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.framework.JTest.TestCase;
import ar.fiuba.tecnicas.framework.JTest.TestCreator;
import ar.fiuba.tecnicas.framework.JTest.TestRunner;
import ar.fiuba.tecnicas.framework.JTest.rerunner.PlainFileStorage;
import ar.fiuba.tecnicas.framework.JTest.rerunner.RerunMode;
import ar.fiuba.tecnicas.framework.Pruebas.Casos2.MyTestCase;
import ar.fiuba.tecnicas.framework.Pruebas.Casos2.TestCaseTimeOut;
import ar.fiuba.tecnicas.framework.Pruebas.Casos2.TestCreatorSuccessTest;
import ar.fiuba.tecnicas.framework.Pruebas.Casos2.TestCreatorTimeOut;

public class FrameworkTest {
    
    TestCreatorTimeOut creatorTest; 
    TestRunner runner;
    
    @Before
    public void setUp(){
	creatorTest = new TestCreatorTimeOut();
	runner = new TestRunner();
	TestCase test1 = new MyTestCase("T1");
	TestCase test2 = new MyTestCase("T2");
	TestCase testTimeOut1 = new TestCaseTimeOut("TestTimeOut1");
	creatorTest.addTest(test1);
	creatorTest.addTest(test2);
	creatorTest.addTest(testTimeOut1);
	runner.setCreatorTest(creatorTest);
    }
 
    @Test
    public void testFailedTestWithTimeOut() {
	String args[] = {};
	runner.run(args);
	assertTrue(runner.getTestReport().failureCount() == 1);
    }

    @Test
    public void testSuccessTestWithTimeOut() {
	String args[] = {};
	runner.run(args);
	assertTrue(runner.getTestReport().successTestCount() == 2);
    }

    @Test
    public void testStorageFailedTests() {
	String args[] = {};
	runner.setRerunStorage(new PlainFileStorage());
	runner.setRerunMode(RerunMode.RECORD);
	runner.setCreatorTest(creatorTest);
	runner.run(args);
	TestCase test4 = new MyTestCase("test4");
	creatorTest.addTest(test4);
	runner.setRerunMode(RerunMode.RERUN);
	runner.run(args);
	assertTrue(runner.getTestReport().runCount() == 2);
    }

    @Test
    public void testStorageSuccessTests() {
	String args[] = {};
	TestCreator creatorTest = new TestCreatorSuccessTest();
	runner.setCreatorTest(creatorTest);
	runner.setRerunStorage(new PlainFileStorage());
	runner.setRerunMode(RerunMode.RECORD);
	runner.setCreatorTest(creatorTest);
	runner.run(args);
	runner.setRerunMode(RerunMode.RERUN);
	runner.run(args);
	assertTrue(runner.getTestReport().runCount() == 0);
    }

}
