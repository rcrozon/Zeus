package configuration;

import tools.UtilsDevices;
import components.Device;

/**
 * Created by Romain on 26/04/2015.
 */
public class Configuration {
	
    private Device device;
    
    public static Configuration configuration;

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
}
