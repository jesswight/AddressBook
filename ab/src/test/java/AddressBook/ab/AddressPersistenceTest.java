package AddressBook.ab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import junit.framework.TestCase;

public class AddressPersistenceTest extends TestCase {

	static AddressPersistence ap;
	static {
		try {
			ap = new AddressPersistence();			

		} catch (IOException ex) {
			System.out.println("Can¡¯t read the file:AddressBook.xml");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDoc2XmlFile() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();
		Document doc=db.newDocument();
		doc = db.parse("AddressBook.xml");
		assertEquals(AddressPersistence.doc2XmlFile(doc, "test.xml"), true);
	}

	public void testGetAddress() throws SAXException, IOException {
		List<Address> list = new ArrayList<Address>();
		list=ap.getAddress();
		System.out.println(list);
	}

}
