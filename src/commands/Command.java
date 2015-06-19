package commands;

import utilEnum.ActionEnum;

public class Command {
	
	private ActionEnum actionType;
	private String sCommand;
	
	public Command(){}
	
	public Command(ActionEnum actionType, String sCommand){
		this.actionType = actionType;
		this.sCommand = sCommand;
	}

	public ActionEnum getActionType() {
		return actionType;
	}

	public void setActionType(ActionEnum actionType) {
		this.actionType = actionType;
	}

	public String getsCommand() {
		return sCommand;
	}

	public void setsCommand(String sCommand) {
		this.sCommand = sCommand;
	}
}
