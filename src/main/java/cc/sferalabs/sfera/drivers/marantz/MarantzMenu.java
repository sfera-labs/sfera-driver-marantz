/*-
 * +======================================================================+
 * Marantz
 * ---
 * Copyright (C) 2016 Sfera Labs S.r.l.
 * ---
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * -======================================================================-
 */

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
