package ar.fiuba.tecnicas.framework.JTest;

/**
 * Special exception of this framework thrown by tests that fail.
 */

public class TimeOutException extends Exception {

    private static final long serialVersionUID = 1L;

    public TimeOutException(String message) {
	super(message);
    }

}
