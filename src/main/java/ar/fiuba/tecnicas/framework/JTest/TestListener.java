package ar.fiuba.tecnicas.framework.JTest;

public interface TestListener {
    public void addFailure(Test test, double time, Throwable throwable);

    public void addError(Test test, double time, Throwable throwable);

    public void addSuccess(TestCase test, double time);

    public void print(String messsage);

    public void insertHSeparator();
}
