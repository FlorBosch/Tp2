package ar.fiuba.tecnicas.framework.JTest.rerunner;

//import java.util.ArrayList;
import java.util.List;

public abstract class RerunStorage {
	
	/* hay que decidir como el usuario va a especificar que storage quiere usar.
	private static List<RerunStorage> storages = new ArrayList<RerunStorage>();
	
	public static void registerStorage(RerunStorage storage) {
		storages.add(storage);
	} */
	
	public abstract void addPassedTestName(String testName);
	
	public abstract List<String> getPassedTestsNames();

}