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

package cc.sferalabs.sfera.drivers.marantz;

import org.slf4j.Logger;

import cc.sferalabs.sfera.core.Configuration;
import cc.sferalabs.sfera.drivers.Driver;
import cc.sferalabs.sfera.io.comm.CommPort;
import cc.sferalabs.sfera.io.comm.CommPortException;

public class Marantz extends Driver {

	private final static long POLL_INTERVAL = 5 * 60000;

	private static final MarantzCommand[] POLLING_CMDS = { new MarantzCommand("PW?"),
			new MarantzCommand("ZM?"), new MarantzCommand("MV?"), new MarantzCommand("MU?"),
			new MarantzCommand("SI?"), new MarantzCommand("SD?"), new MarantzCommand("DC?"),
			new MarantzCommand("SV?"), new MarantzCommand("SLP?"), new MarantzCommand("MS?"),
			new MarantzCommand("PSSWR ?"), new MarantzCommand("TFAN?"), new MarantzCommand("TPAN?"),
			new MarantzCommand("SSSMG ?"), new MarantzCommand("Z2?"), new MarantzCommand("Z2MU?"),
			new MarantzCommand("Z2SLP?"), new MarantzCommand("Z3?"), new MarantzCommand("Z3MU?"),
			new MarantzCommand("Z3SLP?") };

	private final MarantzZone[] zones = { new MainMarantzZone(this), new MarantzZone(this, "2"),
			new MarantzZone(this, "3") };

	private final MarantzMenu menu = new MarantzMenu(this);
	private final MarantzTuner tuner = new MarantzTuner(this);

	private CommPort commPort;
	boolean gotUpdate;
	private int errCount = 0;

	/**
	 * @param id
	 */
	public Marantz(String id) {
		super(id);
	}

	/**
	 * @return
	 */
	Logger getLogger() {
		return log;
	}

	@Override
	protected boolean onInit(Configuration config) throws InterruptedException {
		String port = config.get("port", null);
		if (port == null) {
			port = config.get("host", null);
			if (port == null) {
				log.error("Specify parameter 'port' or 'host' in the driver configuration");
				return false;
			}
			port += ":23";
		}

		try {
			commPort = CommPort.open(port);
			commPort.setParams(9600, 8, CommPort.STOPBITS_1, CommPort.PARITY_NONE,
					CommPort.FLOWCONTROL_NONE);
			commPort.setListener(new MarantzCommPortListener(this));
		} catch (CommPortException e) {
			log.error("Error initializing communication", e);
			return false;
		}

		return true;
	}

	@Override
	protected boolean loop() throws InterruptedException {
		try {
			for (MarantzCommand cmd : POLLING_CMDS) {
				writeAndSleep(cmd);
			}

			if (gotUpdate) {
				Thread.sleep(POLL_INTERVAL);
			} else {
				throw new Exception("No updates");
			}
		} catch (Exception e) {
			log.debug("Loop error", e);
			if (++errCount > 3) {
				log.error("Too many communication errors");
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param cmd
	 * @throws CommPortException
	 * @throws InterruptedException
	 */
	private void writeAndSleep(MarantzCommand cmd) throws CommPortException, InterruptedException {
		write(cmd);
		Thread.sleep(100);
	}

	/**
	 * @param cmd
	 * @throws CommPortException
	 */
	private synchronized void write(MarantzCommand cmd) throws CommPortException {
		log.debug("Writing: {}", cmd.text);
		commPort.writeBytes(cmd.bytes);
		commPort.writeByte(0x0D);
	}

	@Override
	protected void onQuit() {
		if (commPort != null) {
			try {
				commPort.close();
			} catch (CommPortException e) {
				log.warn("Error closing comm port");
			}
		}
	}

	/**
	 * Sends the specified command.
	 * 
	 * @param cmd
	 *            the command to send
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void sendCommand(String cmd) throws CommPortException {
		write(new MarantzCommand(cmd));
	}

	/**
	 * Sends an on or off command.
	 * 
	 * @param on
	 *            if {@code true} sends on, if {@code false} sends off
	 * @throws CommPortException
	 *             if an error occurs
	 */
	public void setOn(boolean on) throws CommPortException {
		String cmd = on ? "PWON" : "PWSTANDBY";
		sendCommand(cmd);
	}

	/**
	 * Returns the zone with the specified number.
	 * 
	 * @param num
	 *            the zone number: 1 for the main zone, 2 or 3 for the secondary
	 *            zones
	 * @return the requested zone
	 */
	public MarantzZone zone(int num) {
		return zones[num - 1];
	}

	/**
	 * Returns the menu object.
	 * 
	 * @return the menu object
	 */
	public MarantzMenu getMenu() {
		return menu;
	}

	/**
	 * Returns the tuner object.
	 * 
	 * @return the tuner object
	 */
	public MarantzTuner getTuner() {
		return tuner;
	}
}
