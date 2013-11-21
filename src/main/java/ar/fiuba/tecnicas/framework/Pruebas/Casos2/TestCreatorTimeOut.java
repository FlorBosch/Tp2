package ar.fiuba.tecnicas.framework.Pruebas.Casos2;

import ar.fiuba.tecnicas.framework.JTest.Test;

import ar.fiuba.tecnicas.framework.JTest.TestCreator;
import ar.fiuba.tecnicas.framework.JTest.TestSuite;

public class TestCreatorTimeOut implements TestCreator{
    
    private TestSuite suite; 
    
    public TestCreatorTimeOut(){
	suite = new TestSuite("TS1");
    }
    
    public Test getTest() throws Exception {
	return suite;
    }
    public void addTest(Test test){
	suite.addTest(test);
    }
    
}
