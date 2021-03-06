package ar.fiuba.tecnicas.framework.JTest;

public class Timer {
    private long startTime;
    private double runTime;
    private static double timeOut = 1000;

    public Timer() {
	this.startTime = 0;
	this.runTime = 0;
    }

    public void start() {
	this.startTime = getSystemsTime();
    }

    public void calculateTime() throws TimeOutException {
	runTime = (double) (getSystemsTime() - startTime) / 1000000;
	if (runTime > timeOut)
	    throw new TimeOutException("Time out.");
    }

    public double getTime() {
	return runTime;
    }

    private long getSystemsTime() {
	return System.nanoTime();
    }

    static public void setTimeOut(double time){
	timeOut = time;
    }
}
