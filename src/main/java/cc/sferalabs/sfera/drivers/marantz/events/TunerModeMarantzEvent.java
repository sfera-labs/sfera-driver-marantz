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
public class TunerModeMarantzEvent extends StringEvent implements MarantzEvent {

	/**
	 * 
	 * @param source
	 * @param value
	 */
	public TunerModeMarantzEvent(Marantz source, String value) {
		super(source, "tuner.mode", value);
	}

}
