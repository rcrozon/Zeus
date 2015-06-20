package configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import threads.ConnectionThread;
import threads.DevicesLoaderThread;
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
		Device localDevice = Configuration.getInstance().getDevice();
		for(int i = 0; i < ConstantsConnection.MAX_IP; i++){
			Request request = new Request();
			request.setRequestType(RequestEnum.GET_DEVICE);
			request.setSenderDevice(localDevice);
			
			Map<ParamEnum, Object> parameters = new HashMap<>();
			parameters.put(ParamEnum.RECIPIENT_IP_ADDRESS, ConstantsConnection.IP_BASE + i);
			request.setParameters(parameters);
			ConnectionThread loadDevicesThread = new DevicesLoaderThread(request);
			loadDevicesThread.start();
		}
	}
}
