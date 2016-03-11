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
public class ZoneToneControlMarantzEvent extends BooleanEvent implements MarantzEvent {

	/**
	 * @param source
	 * @param id
	 * @param value
	 */
	public ZoneToneControlMarantzEvent(Marantz source, int zone, String value) {
		super(source, "zone(" + zone + ").toneControl", value.equals("ON"));
	}

}
