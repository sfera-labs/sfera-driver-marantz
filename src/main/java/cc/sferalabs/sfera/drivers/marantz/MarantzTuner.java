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
