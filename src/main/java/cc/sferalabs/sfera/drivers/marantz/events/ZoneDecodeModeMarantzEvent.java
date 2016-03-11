/**
 * 
 */
package cc.sferalabs.sfera.drivers.marantz.events;

import cc.sferalabs.sfera.drivers.marantz.Marantz;
import cc.sferalabs.sfera.events.StringEvent;

/**
 *
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class ZoneDecodeModeMarantzEvent extends StringEvent implements MarantzEvent {

	/**
	 * @param source
	 * @param zone
	 * @param value
	 */
	public ZoneDecodeModeMarantzEvent(Marantz source, int zone, String value) {
		super(source, "zone(" + zone + ").decodeMode", value.toLowerCase());
	}

}
