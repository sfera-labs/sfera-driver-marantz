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
public class ZoneSleepMarantzEvent extends NumberEvent implements MarantzEvent {

	/**
	 * @param source
	 * @param zone
	 * @param value
	 */
	public ZoneSleepMarantzEvent(Marantz source, int zone, String value) {
		super(source, "zone(" + zone + ").sleep",
				value.equals("OFF") ? 0 : Integer.parseInt(value));
	}

}
