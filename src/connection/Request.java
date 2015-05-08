package connection;

import java.io.Serializable;
import java.util.Map;

import utilEnum.ParamEnum;
import utilEnum.RequestEnum;

/**
 * Created by Romain on 03/05/2015.
 */
public class Request implements Serializable{

	private static final long serialVersionUID = 821116012528203242L;
	private RequestEnum requestType;
    private Map<ParamEnum, Object> parameters = null;

    public Request(RequestEnum requestType, Map<ParamEnum, Object> parameters){
        this.requestType = requestType;
        this.parameters = parameters;
    }

    public Request(RequestEnum requestType){
        this(requestType, null);
    }
    
    public Request(){}

    public Map<ParamEnum, Object> getParameters() { return parameters; }
    public void setParameters(Map<ParamEnum, Object> parameters) { this.parameters = parameters; }
    public RequestEnum getRequestType() { return requestType; }
    public void setRequestType(RequestEnum requestType) { this.requestType = requestType; }


}
