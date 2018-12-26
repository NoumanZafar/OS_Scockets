package gmit.ie;

public class Bug {

	/**
	 * Bug Attributes
	 */
	private String id;
	private String name;
	private String dateAndTime;
	private String platform;
	private String problemDesc;
	private String status;

	public Bug() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param name
	 * @param dateAndTime
	 * @param platform
	 * @param problemDesc
	 * @param status
	 */
	public Bug(String id, String name, String dateAndTime, String platform, String problemDesc, String status) {
		super();
		this.id = id;
		this.name = name;
		this.dateAndTime = dateAndTime;
		this.platform = platform;
		this.problemDesc = problemDesc;
		this.status = status;
	}

	/**
	 * Getters and setters.
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getProblemDesc() {
		return problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
