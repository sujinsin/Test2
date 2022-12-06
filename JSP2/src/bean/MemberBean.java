package bean;

public class MemberBean {
	private String id;
	private String pass1;
	private String email;
	private String tel;
	private String address;
	
	public MemberBean() {};
	
	public MemberBean(String id, String pass1, String email, String tel, String address) {
		super();
		this.id = id;
		this.pass1 = pass1;
		this.email = email;
		this.tel = tel;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass1() {
		return pass1;
	}
	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "MemberBean [id=" + id + ", pass1=" + pass1 + ", email=" + email + ", tel=" + tel + ", address="
				+ address + "]";
	}
	
	
}
