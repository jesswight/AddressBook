package AddressBook.ab;


public class Address {
	String name;
	String mobile;
	String address;
	public Address(){
		
	}
	public Address(String name,String mobilenumber,String homeaddress){
		this.name=name;
		this.mobile=mobilenumber;
		this.address=homeaddress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "Address [name=" + name + ", mobile=" + mobile + ", address="
				+ address + "]";
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	

}
