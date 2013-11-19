package ar.fiuba.tecnicas.framework.JTest;

import java.io.PrintStream;

public class ResultPrinter implements TestListener{
    private PrintStream printStream;

    public ResultPrinter(PrintStream writer) {
        printStream = writer;
    }
    public void printFooter(TestReport result) {
      insertHSeparator();
      printSummary(result);
    }
    private void printSummary(TestReport result) {
        printStream.println("SUMMARY");
        printStream.println("Run: " + result.runCount());
        printStream.println("Failures: " + result.failureCount());
        printStream.println("Errors: " + result.errorCount());
    }
    @Override
    public void addSuccess(TestCase test, String time) {
        printStream.println("[Ok]\t\t" + test + "\t\t\t\t\t" + time);
    }
    @Override
    public void insertHSeparator() {
        printStream.println();
        printStream.println("==================");
    }

    @Override
    public void addFailure(Test test, String time, Throwable throwable) {
        printStream.println("[Failure]\t" + test + "\t\t" + time);
    }
    @Override
    public void addError(Test test, String time, Throwable throwable) {
        printStream.println("[Error]\t\t" + test + "\t\t" + time);
    }

    @Override
    public void print(String messsage) {
        printStream.print(messsage);
    }
}
