package vocalAnalysis;

import java.io.File;

import tools.XMLUtils;
import utilEnum.ParamEnum;

import commands.Command;

import connection.Request;
import constants.ConstantsVocalRecognition;

public class VocalAnalyser {

	public static void analyseVocalRequest(Request vocalRequest){
		String vocalCommand = (String)vocalRequest.getParameters().get(ParamEnum.VOCAL_COMMAND);
		
		if (vocalCommand != null && !vocalCommand.trim().equals("")){
			Command command = XMLUtils.getCommand(new File(ConstantsVocalRecognition.VOCAL_CONFIG), vocalCommand.trim());
			if (command.getActionType() != null && command.getsCommand() != null){
				switch (command.getActionType()){
					case PLAY:
						System.out.println("PLAY");
					break;
					case STOP:
						System.out.println("STOP");
					break;
					case RESUME:
						System.out.println("RESUME");
					break;
				}
			}
		}
	}
}
 