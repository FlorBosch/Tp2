package ar.fiuba.tecnicas.framework.JTest;

public class Timer {
    private long startTime;
    private double runTime;
    static double timeOut = 3;

    public Timer(long startTime) {
	this.startTime = startTime;
	this.runTime = 0;
    }

    public void calculateTime() throws TimeOutException {
	long endTime = System.nanoTime();
	runTime = (double) (endTime - startTime) / 1000000;
	if (runTime > timeOut)
	    throw new TimeOutException("Time out.");
    }

    public double getTime() {
	return runTime;
    }
}
