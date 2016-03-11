package cc.sferalabs.sfera.drivers.marantz;

import org.slf4j.Logger;

import cc.sferalabs.sfera.core.Configuration;
import cc.sferalabs.sfera.drivers.Driver;
import cc.sferalabs.sfera.io.comm.CommPort;
import cc.sferalabs.sfera.io.comm.CommPortException;

public class Marantz extends Driver {

	private static final String PARAM_ADDR = "addr";
	private final static long POLL_INTERVAL = 5 * 60000;

	private static final MarantzCommand[] POLLING_CMDS = { new MarantzCommand("PW?"),
			new MarantzCommand("ZM?"), new MarantzCommand("MV?"), new MarantzCommand("MU?"),
			new MarantzCommand("SI?"), new MarantzCommand("SD?"), new MarantzCommand("DC?"),
			new MarantzCommand("SV?"), new MarantzCommand("SLP?"), new MarantzCommand("MS?"),
			new MarantzCommand("PSSWR ?"), new MarantzCommand("TFAN?"),
			new MarantzCommand("SSSMG ?"), new MarantzCommand("Z2?"), new MarantzCommand("Z2MU?"),
			new MarantzCommand("Z2SLP?"), new MarantzCommand("Z3?"), new MarantzCommand("Z3MU?"),
			new MarantzCommand("Z3SLP?") };

	private CommPort commPort;
	boolean gotUpdate;
	private int errCount = 0;
	private MarantzZone[] zone = { new MainMarantzZone(this), new MarantzZone(this, "2"),
			new MarantzZone(this, "3") };

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
		String addr = config.get(PARAM_ADDR, null);
		if (addr == null) {
			log.error("Param {} not specified in configuration", PARAM_ADDR);
			return false;
		}

		try {
			commPort = CommPort.open(addr);
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
		// TODO Auto-generated method stub
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
		return zone[num - 1];
	}
}
