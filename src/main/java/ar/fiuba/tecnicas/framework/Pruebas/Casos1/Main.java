package ar.fiuba.tecnicas.framework.Pruebas.Casos1;

import ar.fiuba.tecnicas.framework.JTest.TestRunner;

public class Main {

    public static void main(String args[]) {
        TestRunner runner = new TestRunner();
	runner.setCreatorTest(new AllTests());
        runner.run(args);
    }
}
