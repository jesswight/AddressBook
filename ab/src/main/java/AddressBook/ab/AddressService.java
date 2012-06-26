package AddressBook.ab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class AddressService {

	private static AddressPersistence addressPersistence;
	private static final Object lockObj = new Object();
	static {
		try {
			addressPersistence = new AddressPersistence();			

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

	public AddressService() throws SAXException, IOException, ParserConfigurationException {
		addressPersistence = new AddressPersistence();
		
	}
	

	public static boolean add(Address address) {
		
			synchronized (lockObj) {
				try {	
					
					if(addressPersistence.add(address)){
						return true;
					}else{
						System.out.println("Address:" + address.getName()
								+ " add error!a");
						return false;
					}
					
				} catch (Exception ex) {
					System.out.println("Exception:Address:" + address.getName()
							+ " add error!");
					return false;
				}
			}
		
	}

	@SuppressWarnings("unchecked")
	public static List<Address> search(String byID,String text) throws SAXException, IOException {
		List<Address> searchList= new ArrayList<Address>();
		if(byID.equals("name")){
			searchList=addressPersistence.searchbyName(text);
		}else if(byID.equals("mobile")){
			searchList=addressPersistence.searchbymobile(text);
		}else if(byID.equals("address")){
			searchList=addressPersistence.searchbyaddress(text);
		}		
		return searchList;
		
	}
	public static int delete(String byID,String text) {
		int cnt=0;
		synchronized (lockObj) {
			try {
				if(byID.equals("name")){
					
					cnt=addressPersistence.deletebyName(text);
					
				}else if(byID.equals("mobile")){
					cnt=addressPersistence.deletebyMobile(text);
					
				}else if(byID.equals("address")){
					cnt=addressPersistence.deletebyAddress(text);					
				}
			} catch (Exception ex) {
				System.out.println("Address:" + text
						+ " delete error!");
							}
		}
		return cnt;
	} 


}
