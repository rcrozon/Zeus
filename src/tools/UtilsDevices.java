package tools;

import java.net.InetAddress;
import java.net.UnknownHostException;

import utilEnum.DeviceOS;

public class UtilsDevices {

	public static DeviceOS getDeviceOS(String OS) {
		if (OS.indexOf("win") >= 0){
			return DeviceOS.WINDOWS;
		}else if (OS.indexOf("mac") >= 0){
			return DeviceOS.MAC;
		}else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 ){
			return DeviceOS.UNIX;
		}else if (OS.indexOf("sunos") >= 0){
			return DeviceOS.SOLARIS;
		}
		return DeviceOS.WINDOWS;
	}

	public static String getLocalDeviceName(){
		String hostname = "Unknown";
		try{
		    InetAddress addr;
		    addr = InetAddress.getLocalHost();
		    hostname = addr.getHostName();
		}catch (UnknownHostException ex) {
		    System.out.println("Hostname can not be resolved");
		}
		return hostname; 
	}

	public static String getLocalDeviceIpAddress(){
		String ipAddress = "";
		try{
			InetAddress addr = InetAddress.getLocalHost();
			ipAddress = addr.getHostAddress();
		}catch (UnknownHostException ex) {
		    System.out.println("Hostname can not be resolved");
		}
	    return ipAddress;
	}
}
