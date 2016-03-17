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
public class TunerFrequencyMarantzEvent extends NumberEvent implements MarantzEvent {

	/**
	 * 
	 * @param source
	 * @param value
	 */
	public TunerFrequencyMarantzEvent(Marantz source, String value) {
		super(source, "tuner.frequency", toFrequency(value));
	}

	/**
	 * @param value
	 * @return
	 */
	private static float toFrequency(String value) {
		return Integer.parseInt(value) / 100.0f;
	}

}
