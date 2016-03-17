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
public class TunerPresetMarantzEvent extends StringEvent implements MarantzEvent {

	/**
	 * 
	 * @param source
	 * @param preset
	 */
	public TunerPresetMarantzEvent(Marantz source, String preset) {
		super(source, "tuner.preset", preset);
	}

}
