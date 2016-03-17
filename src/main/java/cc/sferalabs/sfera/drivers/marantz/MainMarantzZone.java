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
	public void setInput(String input) throws CommPortException {
		String cmd = "SI" + input.toUpperCase(Locale.ENGLISH);
		driver.sendCommand(cmd);
	}

	@Override
	public void setSleep(int min) throws CommPortException {
		String cmd = "SLP" + sleepMinToString(min);
		driver.sendCommand(cmd);
	}

	/**
	 * Sends a command to set the specified audio input signal.
	 * 
	 * @param input
	 *            the audio input signal to set
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setAudioInput(String input) throws CommPortException {
		String cmd = "SD" + input.toUpperCase(Locale.ENGLISH);
		driver.sendCommand(cmd);
	}

	/**
	 * Sends a command to set the specified decode mode.
	 * 
	 * @param mode
	 *            the decode mode to set
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setDecodeMode(String mode) throws CommPortException {
		String cmd = "DC" + mode.toUpperCase(Locale.ENGLISH);
		driver.sendCommand(cmd);
	}

	/**
	 * Sends a command to set the specified video signal.
	 * 
	 * @param value
	 *            the video signal to set
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setVideoSignal(String value) throws CommPortException {
		String cmd = "SV" + value.toUpperCase(Locale.ENGLISH);
		driver.sendCommand(cmd);
	}

	/**
	 * Sends a command to set the specified surround mode.
	 * 
	 * @param mode
	 *            the surround mode to set
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setSurroundMode(String mode) throws CommPortException {
		String cmd = "MS" + mode.toUpperCase(Locale.ENGLISH);
		driver.sendCommand(cmd);
	}

	/**
	 * Sends a subwoofer on or subwoofer off command.
	 * 
	 * @param on
	 *            if {@code true} sends subwoofer on, if {@code false} sends
	 *            subwoofer off
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setSubwoofer(boolean on) throws CommPortException {
		String cmd = "PSSWR " + (on ? "ON" : "OFF");
		driver.sendCommand(cmd);
	}

	/**
	 * Sends a command to set the specified sound mode.
	 * 
	 * @param mode
	 *            the sound mode to set
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setSoundMode(String mode) throws CommPortException {
		String cmd = "SSSMG " + mode.toUpperCase(Locale.ENGLISH);
		driver.sendCommand(cmd);
	}
}
