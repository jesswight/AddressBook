package AddressBook.ab;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class ab implements Runnable {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		AddressService addressService = null;
		try {
			addressService = new AddressService();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String operate = null;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.print("ab>");
			operate = scan.nextLine();
			if (operate.equals("add")) {
				System.out.print("name:");
				String name = scan.nextLine();
				String moblie;
				do {
					System.out.print("moblie:");
					moblie = scan.nextLine();
				} while (!isInteger(moblie));
				System.out.print("address:");
				String haddress = scan.nextLine();
				Address address = new Address(name, moblie, haddress);

				if (AddressService.add(address)) {
					System.out.println("address entry added");
				}
			} else if (operate.equals("search")) {
				System.out.print("by (name|mobile|address): ");
				String byID = scan.nextLine();
				String text;
				if (byID.equals("name") || byID.equals("mobile")
						|| byID.equals("address")) {
					System.out.print(byID + ": ");
					text = scan.nextLine();
				} else {
					System.out.print("please input the right word. ");
					System.out.print("by (name|mobile|address): ");
					byID = scan.nextLine();
					System.out.print(byID + ": ");
					text = scan.nextLine();
				}
				List<Address> slist = null;
				try {
					slist = addressService.search(byID, text);
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (slist.size() == 0) {
					System.out.println("the address doesn't exist");
				}

				for (int i = 0; i < slist.size(); i++) {

					System.out.println(slist.get(i).toString());
				}
			} else if (operate.equals("delete")) {
				int cnt;
				System.out.print("by (name|mobile|address): ");
				String byID = scan.nextLine();
				String text;
				if (byID.equals("name") || byID.equals("mobile")
						|| byID.equals("address")) {
					System.out.print(byID + ": ");
					text = scan.nextLine();
				} else {
					System.out.print("please input the right word. ");
					System.out.print("by (name|mobile|address): ");
					byID = scan.nextLine();
					System.out.print(byID + ": ");
					text = scan.nextLine();
				}
				cnt = addressService.delete(byID, text);
				System.out.println(cnt + " address entries deleted");

			} else if (operate.equals("!help")) {
				System.out.println("��add�����address����;�������룬�������绰����ַ��");
				System.out
						.println("��search����ѯaddress����;�ɰ��ա��������绰����ַ�����ַ�ʽ��ѯ����ѯ�����������ݵ�����Address");
				System.out
						.println("��delete��ɾ��address����;�ɰ��ա��������绰����ַ�����ַ�ʽɾ����ɾ�������������ݵ�����Address");
				System.out.println("��!help���鿴�������ݡ�");
			}

		} while (!operate.equals("!quit"));
		System.exit(0);
	}

}
