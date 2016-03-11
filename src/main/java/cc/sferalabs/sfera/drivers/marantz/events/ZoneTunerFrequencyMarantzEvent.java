/**
 * 
 */
package cc.sferalabs.sfera.drivers.marantz.events;

import cc.sferalabs.sfera.drivers.marantz.Marantz;
import cc.sferalabs.sfera.events.NumberEvent;

/**
 *
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class ZoneTunerFrequencyMarantzEvent extends NumberEvent implements MarantzEvent {

	/**
	 * @param source
	 * @param zone
	 * @param value
	 */
	public ZoneTunerFrequencyMarantzEvent(Marantz source, int zone, String value) {
		super(source, "zone(" + zone + ").tunerFrequency", toNumber(value));
	}

	/**
	 * @param value
	 * @return
	 */
	private static float toNumber(String value) {
		int i = Integer.parseInt(value.substring(0, 4));
		int d = Integer.parseInt(value.substring(4));
		return (float) (i + d / 100.0);
	}

}
