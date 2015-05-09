package components;

import java.io.Serializable;

import utilEnum.DeviceOS;
import utilEnum.DeviceTypeEnum;

public class Device extends Component implements Serializable{

	private static final long serialVersionUID = -7029820968846251459L;
	private String 			deviceIpAddress ;
    private DeviceTypeEnum 	deviceType;
    private DeviceOS 		deviceOS;
    private String	 		deviceName;

    public Device(String ipAddress, String deviceName, DeviceOS deviceOS, DeviceTypeEnum deviceType){
        this.deviceIpAddress = ipAddress;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.deviceOS = deviceOS;
    }
    
    public Device(){}
    
	public String getDeviceIpAddress() { return deviceIpAddress;}
	public void setDeviceIpAddress(String ipDeviceAddress) { this.deviceIpAddress = ipDeviceAddress; }
	
	public DeviceTypeEnum getDeviceType() { return deviceType; }
	public void setDeviceType(DeviceTypeEnum deviceType) { this.deviceType = deviceType; }
	
	public DeviceOS getDeviceOS() { return deviceOS; }
	public void setDeviceOS(DeviceOS deviceOS) {this.deviceOS = deviceOS;}
	
	public String getDeviceName() { return deviceName; }
	public void setDeviceName(String deviceName) { this.deviceName = deviceName; }
	
	@Override
	public String toString(){
		return "Device : " + deviceName + " (" + deviceIpAddress + ") -- " + deviceOS.toString() ;
	}
}
