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
public class MarantzZone {

	protected final Marantz driver;
	protected final String num;

	/**
	 * @param driver
	 * @param num
	 */
	MarantzZone(Marantz driver, String num) {
		this.driver = driver;
		this.num = num;
	}

	/**
	 * Sends an on or off command to this zone.
	 * 
	 * @param on
	 *            if {@code true} sends on, if {@code false} sends off
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setOn(boolean on) throws CommPortException {
		String cmd = "Z" + num + (on ? "ON" : "OFF");
		driver.sendCommand(cmd);
	}

	/**
	 * Sends a volume up command.
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void volumeUp() throws CommPortException {
		String cmd = "Z" + num + "UP";
		driver.sendCommand(cmd);
	}

	/**
	 * Sends a volume up command.
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void volumeDown() throws CommPortException {
		String cmd = "Z" + num + "DOWN";
		driver.sendCommand(cmd);
	}

	/**
	 * Sends a command to set the volume to the specified value.
	 * 
	 * @param val
	 *            value from 0 to 99.5 (the main zone accepts X.5 values, the
	 *            other zones only integer values)
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setVolume(float val) throws CommPortException {
		String cmd = "Z" + num + volumeToString(val);
		driver.sendCommand(cmd);
	}

	/**
	 * @param val
	 * @return
	 */
	protected String volumeToString(float val) {
		if (val < 0 || val > 99.5) {
			throw new IllegalArgumentException("0 <= val <= 99.5");
		}
		int v = (int) (val * 10);
		String vStr;
		if (v < 10) {
			vStr = "00";
		} else if (v < 100) {
			vStr = "0";
		} else {
			vStr = "";
		}
		vStr += v;
		if (v % 10 == 0) {
			vStr = vStr.substring(0, vStr.length() - 1);
		}
		return vStr;
	}

	/**
	 * Sends a mute on or mute off command to this zone.
	 * 
	 * @param on
	 *            if {@code true} sends mute on, if {@code false} sends mute off
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setMute(boolean on) throws CommPortException {
		String cmd = "Z" + num + "MU" + (on ? "ON" : "OFF");
		driver.sendCommand(cmd);
	}

	/**
	 * Sends a sleep timer command with the specified number of minutes.
	 * 
	 * @param min
	 *            number of minutes (from 1 to 120), a value of 0 sets the timer off
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setSleep(int min) throws CommPortException {
		String cmd = "Z" + num + "SLP" + sleepMinToString(min);
		driver.sendCommand(cmd);
	}

	/**
	 * @param min
	 * @return
	 */
	protected String sleepMinToString(int min) {
		String m;
		if (min == 0) {
			m = "OFF";
		} else {
			if (min < 10) {
				m = "00";
			} else if (min < 100) {
				m = "0";
			} else {
				m = "";
			}
			m += min;
		}
		return m;
	}
}
