/**
 * 
 */
package cc.sferalabs.sfera.drivers.marantz;

import cc.sferalabs.sfera.drivers.marantz.events.MarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.OnMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.TunerFrequencyMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.TunerModeMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.TunerPresetMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.ZoneAudioInputMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.ZoneDecodeModeMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.ZoneInputMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.ZoneMuteMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.ZoneOnMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.ZoneSleepMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.ZoneSoundModeMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.ZoneSubwooferMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.ZoneSurroundModeMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.ZoneVideoSignalMarantzEvent;
import cc.sferalabs.sfera.drivers.marantz.events.ZoneVolumeMarantzEvent;
import cc.sferalabs.sfera.events.Bus;
import cc.sferalabs.sfera.io.comm.CommPortListener;

/**
 *
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class MarantzCommPortListener implements CommPortListener {

	private final Marantz driver;
	private StringBuilder message = new StringBuilder();;

	/**
	 * 
	 */
	MarantzCommPortListener(Marantz driver) {
		this.driver = driver;
	}

	@Override
	public void onRead(byte[] bytes) {
		for (byte b : bytes) {
			if (b == (byte) 0x0D) {
				processMessage(message.toString());
				message = new StringBuilder();
			} else {
				char c = (char) (b & 0xFF);
				message.append(c);
			}
		}
	}

	@Override
	public void onError(Throwable t) {
		driver.getLogger().warn("Communication error", t);
	}

	/**
	 * @param message
	 */
	private void processMessage(String message) {
		driver.getLogger().debug("Processing message: {}", message);
		String cmd = message.substring(0, 2);
		String prm = message.substring(2);
		MarantzEvent e;
		switch (cmd) {
		case "PW":
			e = new OnMarantzEvent(driver, prm);
			break;

		case "ZM":
			e = new ZoneOnMarantzEvent(driver, 1, prm);
			break;

		case "MV":
			try {
				e = new ZoneVolumeMarantzEvent(driver, 1, prm);
			} catch (Exception ex) {
				// case MVMAX
				return;
			}
			break;

		case "MU":
			e = new ZoneMuteMarantzEvent(driver, 1, prm);
			break;

		case "SI":
			e = new ZoneInputMarantzEvent(driver, 1, prm);
			break;

		case "SD":
			e = new ZoneAudioInputMarantzEvent(driver, 1, prm);
			break;

		case "DC":
			e = new ZoneDecodeModeMarantzEvent(driver, 1, prm);
			break;

		case "SV":
			e = new ZoneVideoSignalMarantzEvent(driver, 1, prm);
			break;

		case "SL": // SLP
			e = new ZoneSleepMarantzEvent(driver, 1, prm.substring(1));
			break;

		case "MS":
			e = new ZoneSurroundModeMarantzEvent(driver, 1, prm);
			break;

		case "PS":
			if (prm.startsWith("SWR ")) {
				e = new ZoneSubwooferMarantzEvent(driver, 1, prm.substring(4));
			} else {
				e = null;
			}
			break;

		case "TF":
			if (prm.startsWith("AN")) {
				try {
					e = new TunerFrequencyMarantzEvent(driver, prm.substring(2));
					String mode;
					if (((TunerFrequencyMarantzEvent) e).getValue().floatValue() > 500) {
						mode = "am";
					} else {
						mode = "fm";
					}
					Bus.postIfChanged(new TunerModeMarantzEvent(driver, mode));
				} catch (Exception ex) {
					// there are other TFAN messages not documented
					e = null;
				}
			} else {
				e = null;
			}
			break;

		case "TP":
			if (prm.startsWith("AN")) {
				e = new TunerPresetMarantzEvent(driver, prm.substring(2));
			} else {
				e = null;
			}
			break;

		case "SS":
			if (prm.startsWith("SMG ")) {
				// not documented
				e = new ZoneSoundModeMarantzEvent(driver, 1, prm.substring(4));
			} else {
				e = null;
			}
			break;

		case "Z3":
		case "Z2":
			int zone = Integer.parseInt(cmd.substring(1));
			if (prm.equals("ON") || prm.equals("OFF")) {
				e = new ZoneOnMarantzEvent(driver, zone, prm);
			} else if (prm.startsWith("MU")) {
				e = new ZoneMuteMarantzEvent(driver, zone, prm.substring(2));
			} else if (prm.startsWith("SLP")) {
				e = new ZoneSleepMarantzEvent(driver, zone, prm.substring(3));
			} else {
				try {
					e = new ZoneVolumeMarantzEvent(driver, zone, prm);
				} catch (NumberFormatException ex) {
					e = new ZoneInputMarantzEvent(driver, zone, prm);
				}
			}
			break;

		default:
			e = null;
		}

		if (e != null) {
			Bus.postIfChanged(e);
			driver.gotUpdate = true;
		}
	}

}
