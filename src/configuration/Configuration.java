package configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.UtilsDevices;
import utilEnum.ParamEnum;
import utilEnum.RequestEnum;
import components.Device;
import connection.Connection;
import connection.Request;
import constants.ConstantsConnection;

/**
 * Created by Romain on 26/04/2015.
 */
public class Configuration {
	
    private Device device;
    
    public static Configuration configuration;
    public static List<Device> availableDevices ;

    public static Configuration getInstance(){
        if (configuration == null){
            configuration = new Configuration();
        }
        return configuration;
    }

    public Device getDevice() { return device; }
    public void setDevice(Device device) { this.device = device; }
    
    public static void initConfiguration(){
    	Device localDevice = new Device();
    	localDevice.setDeviceOS(UtilsDevices.getDeviceOS(System.getProperty("os.name").toLowerCase()));
    	localDevice.setDeviceName(UtilsDevices.getLocalDeviceName());
    	localDevice.setDeviceIpAddress(UtilsDevices.getLocalDeviceIpAddress());
    	Configuration.getInstance().setDevice(localDevice);
    }

	public static List<Device> getAvailableDevices(){
		if (availableDevices == null){
			availableDevices = new ArrayList<>();
		}
		return availableDevices;
	}
	
	public static void loadAvailableDevices(){
		Request request = new Request();
		request.setRequestType(RequestEnum.GET_DEVICE);
		request.setSenderDevice(Configuration.getInstance().getDevice());
		
		Map<ParamEnum, Object> parameters = new HashMap<>();
		request.setParameters(parameters);
		
		for(int i = 0; i < ConstantsConnection.MAX_IP; i++){
			parameters.put(ParamEnum.RECIPIENT_IP_ADDRESS, ConstantsConnection.IP_BASE + i);
			Connection.getClientInstance().sendSerializedObject(request);
		}
	}
}
