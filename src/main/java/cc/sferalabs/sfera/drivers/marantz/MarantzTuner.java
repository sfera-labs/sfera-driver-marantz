/**
 * 
 */
package cc.sferalabs.sfera.drivers.marantz;

import java.util.Locale;

import cc.sferalabs.sfera.io.comm.CommPortException;

/**
 *
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class MarantzTuner {

	private final Marantz driver;

	/**
	 * @param driver
	 */
	MarantzTuner(Marantz driver) {
		this.driver = driver;
	}

	/**
	 * Sends a frequency up command.
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void frequencyUp() throws CommPortException {
		driver.sendCommand("TFANUP");
	}

	/**
	 * Sends a frequency down command.
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void frequencyDown() throws CommPortException {
		driver.sendCommand("TFANDOWN");
	}

	/**
	 * Sends a command to set the frequency to the specified value.
	 * 
	 * @param val
	 *            the frequency to set
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setFrequency(float val) throws CommPortException {
		String cmd = "TFAN" + frequencyToString(val);
		driver.sendCommand(cmd);
	}

	/**
	 * @param val
	 * @return
	 */
	private String frequencyToString(float val) {
		String v = Integer.toString((int) (val * 100));
		int zeros = 6 - v.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < zeros; i++) {
			sb.append("0");
		}
		sb.append(v);
		return sb.toString();
	}

	/**
	 * Sends a preset up command.
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void presetUp() throws CommPortException {
		driver.sendCommand("TPANUP");
	}

	/**
	 * Sends a preset down command.
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void presetDown() throws CommPortException {
		driver.sendCommand("TPANDOWN");
	}

	/**
	 * Sends a command to set the specified preset.
	 * 
	 * @param preset
	 *            the preset to set
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setPreset(String preset) throws CommPortException {
		String cmd = "TPAN" + preset;
		driver.sendCommand(cmd);
	}

	/**
	 * Sends a command to set the band to AM or FM.
	 * 
	 * @param mode
	 *            "AM" or "FM"
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setMode(String mode) throws CommPortException {
		driver.sendCommand("TMAN" + mode.toUpperCase(Locale.ENGLISH));
	}

}
