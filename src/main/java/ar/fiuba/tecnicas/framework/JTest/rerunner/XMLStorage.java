package ar.fiuba.tecnicas.framework.JTest.rerunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLStorage extends RerunStorage {

    private static final String fileName = "store.xml";
    private List<String> passedTests;

    public XMLStorage() {
	this.passedTests = new ArrayList<String>();
    }

    @Override
    public void addPassedTestName(String testName) {
	this.passedTests.add(testName);
    }

    @Override
    public boolean isTestRunnable(String testName) {
	return !this.passedTests.contains(testName);
    }

    @Override
    public void setMode(RerunMode mode) {
	if (mode == RerunMode.RERUN) {
	    getPassedTests();
	}
    }

    @Override
    public void saveRunInformation() {
	try {
	    Document document = DocumentBuilderFactory.newInstance()
		    .newDocumentBuilder().newDocument();
	    Element passedTests = document.createElement("passedtests");
	    document.appendChild(passedTests);

	    for (String testName : this.passedTests) {
		Element passedtest = document.createElement("passedtest");
		passedtest.setAttribute("name", testName);
		passedTests.appendChild(passedtest);
	    }

	    Transformer transformer = TransformerFactory.newInstance()
		    .newTransformer();
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	    transformer.setOutputProperty(
		    "{http://xml.apache.org/xslt}indent-amount", "4");
	    DOMSource source = new DOMSource(document);
	    StreamResult result = new StreamResult(new File(fileName));
	    transformer.transform(source, result);
	}

	catch (Exception e) {
	    System.out.println("Error writing storage file:");
	    System.out.println(e.getMessage());
	}
    }

    private void getPassedTests() {
	this.passedTests = new ArrayList<String>();
	try {
	    Document document = DocumentBuilderFactory.newInstance()
		    .newDocumentBuilder().parse(fileName);
	    NodeList passedTestsList = document
		    .getElementsByTagName("passedtest");

	    for (int i = 0; i < passedTestsList.getLength(); i++) {
		Node passedTest = passedTestsList.item(i);
		Element passedTestElement = (Element) passedTest;
		String testName = passedTestElement.getAttribute("name");
		this.passedTests.add(testName);
	    }
	}

	catch (Exception e) {
	    System.out.println("Error reading storage file:");
	    System.out.println(e.getMessage());
	}
    }

}
