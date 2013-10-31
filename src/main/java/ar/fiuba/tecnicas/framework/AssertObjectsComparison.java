/*
    Se encarga de la comparacion de dos objetos, se usa el patron strategy,
    y la validez depende del ObjectComparisonBehavior que reciba como parámetro.
 */

package ar.fiuba.tecnicas.framework;

public class AssertObjectsComparison<T> extends Test {
    private T expected;
    private T actual;
    private String message;
    private ObjectComparisonBehavior comparisonbehavior;
    private void assignExpectedAndActual(T expected, T actual, ObjectComparisonBehavior comparisonbehavior){
        this.expected=expected;
        this.actual=actual;
        this.comparisonbehavior=comparisonbehavior;
    }
    public AssertObjectsComparison(T expected,T actual, ObjectComparisonBehavior comparisonbehavior){
        assignExpectedAndActual(expected,actual,comparisonbehavior);
    }
    public AssertObjectsComparison(T expected,T actual,
                                   ObjectComparisonBehavior comparisonbehavior,
                                   String message){
        assignExpectedAndActual(expected,actual,comparisonbehavior);
        this.message=message;
    }
    @Override
    public void excecute(TestReport testreport)throws AssertionError{
        if(!comparisonbehavior.compare(expected,actual)) throw new AssertionError(message);
        testreport.start(comparisonbehavior.getClass()+": expected: "+ expected.toString()+"\tactual: "+actual.toString());
    }
}
