package AddressBook.ab;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class AddressPersistence {

	private Address ad;
	static Document doc;
	// 实例化一个文档构建器工厂
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	// 通过文档构建器工厂获取一个文档构建器
	static DocumentBuilder db;

	// 构造函数
	public AddressPersistence() throws SAXException, IOException,
			ParserConfigurationException {
		db = dbf.newDocumentBuilder();
		// 通过文档通过文档构建器构建一个文档实例
		doc = db.newDocument();		
	}

	public static boolean doc2XmlFile(Document document, String filename) {
		boolean flag = true;
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	public List<Address> getAddress() throws SAXException, IOException {
		List<Address> list = new ArrayList<Address>();
		doc = db.parse("AddressBook.xml");
		Element element = doc.getDocumentElement();
		NodeList Nodes = element.getElementsByTagName("Address");

		for (int i = 0; i < Nodes.getLength(); i++) {
			Element addressElement = (Element) Nodes.item(i);
			Address address = new Address();
			NodeList childNodes = addressElement.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					if ("name".equals(childNodes.item(j).getNodeName())) {
						address.setName(childNodes.item(j).getFirstChild()
								.getNodeValue());
					} else if ("mobile"
							.equals(childNodes.item(j).getNodeName())) {
						address.setMobile(childNodes.item(j).getFirstChild()
								.getNodeValue());
					} else if ("address".equals(childNodes.item(j)
							.getNodeName())) {
						address.setAddress(childNodes.item(j).getFirstChild()
								.getNodeValue());
					}
				}

			}
			list.add(address);
		}
		return list;
	}

	public boolean add(Address ad) throws SAXException, IOException {

		doc = db.parse("AddressBook.xml");
		Element root = (Element) doc.getFirstChild();

		Element address = doc.createElement("Address");
		Element name = doc.createElement("name");
		Element mobilenumber = doc.createElement("mobile");
		Element homeaddress = doc.createElement("address");
		Text nameText = doc.createTextNode(ad.getName());
		Text mobilenumberText = doc.createTextNode(ad.getMobile());
		Text homeaddressText = doc.createTextNode(ad.getAddress());

		name.appendChild(nameText);
		mobilenumber.appendChild(mobilenumberText);
		homeaddress.appendChild(homeaddressText);
		address.appendChild(name);
		address.appendChild(mobilenumber);
		address.appendChild(homeaddress);
		root.appendChild(address);

		return doc2XmlFile(doc, "AddressBook.xml");

	}

	public List<Address> searchbyName(String text) throws SAXException,
			IOException {

		List<Address> adlist = getAddress();
		List<Address> adSearchList = new ArrayList<Address>();

		for (int i = 0; i < adlist.size(); i++) {
			Address adi = adlist.get(i);
			if (adi.getName().equals(text)) {
				adSearchList.add(adi);
			}
		}
		return adSearchList;

	}

	public List<Address> searchbymobile(String text) throws SAXException,
			IOException {

		List<Address> adlist = getAddress();
		List<Address> adSearchList = new ArrayList<Address>();
		for (int i = 0; i < adlist.size(); i++) {
			Address adi = adlist.get(i);
			if (adi.getMobile().equals(text)) {
				adSearchList.add(adi);
			}
		}
		return adSearchList;
	}

	public List<Address> searchbyaddress(String text) throws SAXException,
			IOException {
		List<Address> adlist = getAddress();
		List<Address> adSearchList = new ArrayList<Address>();
		for (int i = 0; i < adlist.size(); i++) {
			Address adi = adlist.get(i);
			if (adi.getAddress().equals(text)) {
				adSearchList.add(adi);
			}
		}
		return adSearchList;
	}

	public int deletebyName(String text) throws SAXException, IOException {

		doc = db.parse("AddressBook.xml");

		Element element = doc.getDocumentElement();
		NodeList Nodes = element.getElementsByTagName("Address");
		int cnt = 0;
		for (int i = 0; i < Nodes.getLength(); i++) {
			Element addressElement = (Element) Nodes.item(i);
			NodeList childNodes = addressElement.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					if ("name".equals(childNodes.item(j).getNodeName())) {

						if (childNodes.item(j).getFirstChild().getNodeValue()
								.equals(text)) {
							doc.adoptNode(addressElement);

							cnt++;
						}
					}
				}
			}
		}
		doc2XmlFile(doc, "AddressBook.xml");

		return cnt;

	}

	public int deletebyMobile(String text) throws SAXException, IOException {
		doc = db.parse("AddressBook.xml");

		Element element = doc.getDocumentElement();
		NodeList Nodes = element.getElementsByTagName("Address");
		int cnt = 0;
		for (int i = 0; i < Nodes.getLength(); i++) {
			Element addressElement = (Element) Nodes.item(i);
			NodeList childNodes = addressElement.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					if ("mobile".equals(childNodes.item(j).getNodeName())) {

						if (childNodes.item(j).getFirstChild().getNodeValue()
								.equals(text)) {
							doc.adoptNode(addressElement);

							cnt++;
						}
					}
				}
			}
		}
		doc2XmlFile(doc, "AddressBook.xml");
		
		return cnt;
	}

	public int deletebyAddress(String text) throws SAXException, IOException {

		doc = db.parse("AddressBook.xml");

		Element element = doc.getDocumentElement();
		NodeList Nodes = element.getElementsByTagName("Address");
		int cnt = 0;
		for (int i = 0; i < Nodes.getLength(); i++) {
			Element addressElement = (Element) Nodes.item(i);
			NodeList childNodes = addressElement.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					if ("address".equals(childNodes.item(j).getNodeName())) {

						if (childNodes.item(j).getFirstChild().getNodeValue()
								.equals(text)) {
							doc.adoptNode(addressElement);

							cnt++;
						}
					}
				}
			}
		}
		doc2XmlFile(doc, "AddressBook.xml");
		
		return cnt;
	}

	public Address getAd() {
		return ad;
	}

	public void setAd(Address ad) {
		this.ad = ad;
	}

}
