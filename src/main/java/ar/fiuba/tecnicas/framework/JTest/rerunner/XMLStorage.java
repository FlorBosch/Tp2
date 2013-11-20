package ar.fiuba.tecnicas.framework.JTest.rerunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLStorage extends RerunStorage {

    private String fileName = "store.xml";

    public XMLStorage() {
	DocumentBuilderFactory documentFactory = DocumentBuilderFactory
		.newInstance();
	DocumentBuilder documentBuilder;
	Document document;

	try {
	    documentBuilder = documentFactory.newDocumentBuilder();
	    document = documentBuilder.newDocument();
	    Element passedTests = document.createElement("passedtests");
	    document.appendChild(passedTests);

	    TransformerFactory transformerFactory = TransformerFactory
		    .newInstance();
	    Transformer transformer;

	    transformer = transformerFactory.newTransformer();
	    DOMSource source = new DOMSource(document);
	    StreamResult result = new StreamResult(new File(fileName));
	    transformer.transform(source, result);
	}

	catch (ParserConfigurationException e) {
	} catch (TransformerConfigurationException e) {
	} catch (TransformerException e) {
	}
    }

    @Override
    public void addPassedTestName(String testName) {
	DocumentBuilderFactory documentFactory = DocumentBuilderFactory
		.newInstance();

	try {
	    DocumentBuilder documentBuilder = documentFactory
		    .newDocumentBuilder();
	    Document document = documentBuilder.parse(fileName);
	    Node passedtests = document.getFirstChild();

	    Element passedtest = document.createElement("passedtest");
	    passedtest.setAttribute("name", testName);
	    passedtests.appendChild(passedtest);

	    TransformerFactory transformerFactory = TransformerFactory
		    .newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    DOMSource source = new DOMSource(document);
	    StreamResult result = new StreamResult(new File(fileName));
	    transformer.transform(source, result);
	}

	catch (ParserConfigurationException e) {
	} catch (SAXException e) {
	} catch (IOException e) {
	} catch (TransformerConfigurationException e) {
	} catch (TransformerException e) {
	}
    }

    @Override
    public List<String> getPassedTestsNames() {

	List<String> passedTestsNames = new ArrayList<String>();
	DocumentBuilderFactory documentFactory = DocumentBuilderFactory
		.newInstance();

	try {
	    DocumentBuilder documentBuilder = documentFactory
		    .newDocumentBuilder();
	    Document document = documentBuilder.parse(fileName);
	    Node passedtests = document.getFirstChild();
	    NodeList passedTestsList = passedtests.getChildNodes();

	    for (int i = 0; i < passedTestsList.getLength(); i++) {
		Node passedTest = passedTestsList.item(i);
		String testName = ((Element) passedTest).getAttribute("name");
		passedTestsNames.add(testName);
	    }
	}

	catch (ParserConfigurationException e) {
	} catch (SAXException e) {
	} catch (IOException e) {
	}

	return passedTestsNames;
    }

}
