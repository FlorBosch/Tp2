package ar.fiuba.tecnicas.framework.Pruebas.Casos1;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.fiuba.tecnicas.framework.JTest.TestCase;
import ar.fiuba.tecnicas.framework.JTest.TestCreator;
import ar.fiuba.tecnicas.framework.JTest.TestRunner;
import ar.fiuba.tecnicas.framework.Pruebas.Casos2.CaseUse1;
import ar.fiuba.tecnicas.framework.Pruebas.Casos2.TestCaseTimeOut;

public class FrameworkTest {

    @Test
    public void testFailedTestWithTimeOut() {
	TestCreator creatorTest = new CaseUse1();
        TestRunner.setCreatorTest(creatorTest);
//        TestRunner.main();
   
        
    }

}
