/**
 * 
 */
package cc.sferalabs.sfera.drivers.marantz;

import cc.sferalabs.sfera.io.comm.CommPortException;

/**
 *
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class MainMarantzZone extends MarantzZone {

	/**
	 * @param driver
	 */
	MainMarantzZone(Marantz driver) {
		super(driver, "M");
	}
	
	@Override
	public void volumeUp() throws CommPortException {
		String cmd = "MVUP";
		driver.sendCommand(cmd);
	}
	
	@Override
	public void volumeDown() throws CommPortException {
		String cmd = "MVDOWN";
		driver.sendCommand(cmd);
	}
	
	@Override
	public void setVolume(float val) throws CommPortException {
		String cmd = "MV" + volumeToString(val);
		driver.sendCommand(cmd);
	}
	
	@Override
	public void setMute(boolean on) throws CommPortException {
		String cmd = "MU" + (on ? "ON" : "OFF");
		driver.sendCommand(cmd);
	}

	@Override
	public void setSleep(int min) throws CommPortException {
		String cmd = "SLP" + sleepMinToString(min);
		driver.sendCommand(cmd);
	}

}
