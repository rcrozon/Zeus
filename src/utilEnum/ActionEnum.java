package utilEnum;

public enum ActionEnum {

	PLAY("PLAY"), 
	STOP("STOP"), 
	RESUME("RESUME");

	private String name = "";

	ActionEnum(String name) {
		this.name = name;
	}

	public ActionEnum getName(){
		return ActionEnum.valueOf(name);
	}
	
	public String toString() {
		return name;
	}
}
