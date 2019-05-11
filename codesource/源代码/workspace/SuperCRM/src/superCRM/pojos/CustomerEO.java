package superCRM.pojos;

public class CustomerEO {

	/** 客户对象的属性 */
	private int id;

	private String displayName;

	private String webSite;

	private String category;

	private String numberEmployee;

	private String activeStatus = "Y";

	/** 获得客户属性的getter和setter方法 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getNumberEmployee() {
		return numberEmployee;
	}

	public void setNumberEmployee(String numberEmployee) {
		this.numberEmployee = numberEmployee;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

}
