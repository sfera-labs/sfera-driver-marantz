/**
 * 
 */
package cc.sferalabs.sfera.drivers.marantz.events;

import cc.sferalabs.sfera.drivers.marantz.Marantz;
import cc.sferalabs.sfera.events.BooleanEvent;

/**
 *
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class ZoneSubwooferMarantzEvent extends BooleanEvent implements MarantzEvent {

	/**
	 * @param source
	 * @param id
	 * @param value
	 */
	public ZoneSubwooferMarantzEvent(Marantz source, int zone, String value) {
		super(source, "zone(" + zone + ").subwoofer", value.equals("ON"));
	}

}
