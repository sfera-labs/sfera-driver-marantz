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
	 *            value from 0 to 99.5 (the main zone accepts X.5 decimal
	 *            values, other zones only integer values). Illegal values will
	 *            be rounded to the closest valid one.
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
		if (val < 0) {
			val = 0;
		} else if (val > 99.5) {
			val = 99.5f;
		}

		int integer = (int) val;
		int decimal = ((int) (val * 10)) % 10;

		if (this instanceof MainMarantzZone) {
			if (decimal != 5) {
				if (decimal <= 2) {
					decimal = 0;
				} else if (decimal >= 8) {
					decimal = 0;
					if (integer < 99) {
						integer++;
					}
				} else {
					decimal = 5;
				}
			}
		} else {
			if (decimal < 5) {
				decimal = 0;
			} else if (decimal >= 5) {
				decimal = 0;
				if (integer < 99) {
					integer++;
				}
			}
		}

		String vStr;
		if (integer < 10) {
			vStr = "0";
		} else {
			vStr = "";
		}
		vStr += integer;
		if (decimal == 5) {
			vStr += "5";
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
	 * Sends a command to set the specified input source.
	 * 
	 * @param input
	 *            the input source to set
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setInput(String input) throws CommPortException {
		String cmd = "Z" + num + input.toUpperCase(Locale.ENGLISH);
		driver.sendCommand(cmd);
	}

	/**
	 * Sends a sleep timer command with the specified number of minutes.
	 * 
	 * @param min
	 *            number of minutes (from 1 to 120), a value of 0 sets the timer
	 *            off
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
