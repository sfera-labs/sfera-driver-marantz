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
public class MarantzMenu {

	private final Marantz driver;

	/**
	 * @param driver
	 */
	MarantzMenu(Marantz driver) {
		this.driver = driver;
	}

	/**
	 * Sends a command to show the menu
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void on() throws CommPortException {
		driver.sendCommand("MNMEN ON");
	}

	/**
	 * Sends a command to hide the menu
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void off() throws CommPortException {
		driver.sendCommand("MNMEN OFF");
	}

	/**
	 * Sends a cursor up command.
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void up() throws CommPortException {
		driver.sendCommand("MNCUP");
	}

	/**
	 * Sends a cursor down command.
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void down() throws CommPortException {
		driver.sendCommand("MNCDN");
	}

	/**
	 * Sends a cursor left command.
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void left() throws CommPortException {
		driver.sendCommand("MNCLT");
	}

	/**
	 * Sends a cursor right command.
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void right() throws CommPortException {
		driver.sendCommand("MNCRT");
	}

	/**
	 * Sends an enter command.
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void enter() throws CommPortException {
		driver.sendCommand("MNENT");
	}

	/**
	 * Sends a back command.
	 * 
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void back() throws CommPortException {
		driver.sendCommand("MNRTN");
	}

}
