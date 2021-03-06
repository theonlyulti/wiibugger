package wiibugger.pc.wiimote.wiiusej;

import wiibugger.pc.DeviceList;
import wiibugger.pc.Wiibugger;
import wiibugger.pc.wiimote.WiimoteDevice;
import wiibugger.pc.wiimote.WiimoteScanner;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;

public class WiiuseJScanner extends WiimoteScanner {

	protected WiiuseJScanner(DeviceList<WiimoteDevice> wiimoteList, int numberOfWiimotes, Runnable callback) {
		super(wiimoteList, numberOfWiimotes, callback);
	}
	
	public static WiiuseJScanner getScanner(DeviceList<WiimoteDevice> wiimoteList, int numberOfWiimotes, Runnable callback) {		
		return new WiiuseJScanner(wiimoteList, numberOfWiimotes, callback);
	}
	
	@Override
	public void run() {
		System.out.println("Scanning for Wiimotes using WiiuseJ...");
		Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(numberOfWiimotes, true);
		
		if(wiimotes.length == 0) {
			System.out.println("No Wiimote Found");
		} else {
			for(int i = 0; i < wiimotes.length; i++) {
			//	wiimotes[i].setLeds(true, true, true, true);
				Wiibugger.getWiimoteList().add(new WiiuseJDevice(wiimotes[i]));;
				System.out.println("Found wiimote " + wiimotes[i].getId());
			}
		}
		System.out.println("Finished scanning");
		callback.run();
	}

}
