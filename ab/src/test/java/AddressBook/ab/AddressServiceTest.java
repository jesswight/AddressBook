package AddressBook.ab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

import junit.framework.TestCase;

public class AddressServiceTest extends TestCase {

	Address ad;
	List<Address> searchList;

	protected void setUp() throws Exception {
		ad = new Address("liyu", "15298376867", "nanhang");
		searchList = new ArrayList<Address>();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAdd() {

		assertEquals(AddressService.add(ad), true);

	}

	public void testSearch() throws SAXException, IOException {
		searchList.add(ad);
		searchList.add(ad);
		
		List<Address> slist=new ArrayList<Address>();
		
		slist=AddressService.search("name", "liyu");
		System.out.println(searchList);
		System.out.println(slist);
		
		slist=AddressService.search("mobile", "15298376867");
		System.out.println(searchList);
		System.out.println(slist);
		
		slist=AddressService.search("address", "nanhang");
		System.out.println(searchList);
		System.out.println(slist);

	}

	public void testDelete() {
		assertEquals(AddressService.delete("name", "liyu"), 1);
		assertEquals(AddressService.add(ad), true);
		assertEquals(AddressService.delete("mobile", "15298376867"), 1);
		assertEquals(AddressService.add(ad), true);
		assertEquals(AddressService.delete("address", "nanhang"), 1);

	}

}
