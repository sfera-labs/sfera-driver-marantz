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
public class OnMarantzEvent extends BooleanEvent implements MarantzEvent {

	/**
	 * @param source
	 * @param id
	 * @param value
	 */
	public OnMarantzEvent(Marantz source, String value) {
		super(source, "on", value.equals("ON"));
	}

}
