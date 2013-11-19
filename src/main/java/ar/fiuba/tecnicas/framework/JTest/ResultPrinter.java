package ar.fiuba.tecnicas.framework.JTest;

import java.io.PrintStream;
import java.text.NumberFormat;

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
    private String elapsedTimeAsString(double runTime) {
        return NumberFormat.getInstance().format((double) runTime / 1000);
    }
    @Override
    public void addSuccess(TestCase test, double time) {
        printStream.println("[Ok]\t\t" + test + "\t\t\t\t\t" + time + " [miliseg]");
    }
    @Override
    public void insertHSeparator() {
        printStream.println();
        printStream.println("==================");
    }

    @Override
    public void addFailure(Test test, double time, Throwable throwable) {
        printStream.println("[Failure]\t" + test + "\t\t\t\t" + time + " [miliseg]");
    }
    @Override
    public void addError(Test test, double time, Throwable throwable) {
        printStream.println("[Error]\t\t" + test + "\t\t\t\t" + time + " [miliseg]");
    }

    @Override
    public void print(String messsage) {
        printStream.print(messsage);
    }
}
