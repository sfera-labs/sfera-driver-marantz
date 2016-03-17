/**
 * 
 */
package cc.sferalabs.sfera.drivers.marantz.events;

import java.util.Locale;

import cc.sferalabs.sfera.drivers.marantz.Marantz;
import cc.sferalabs.sfera.events.StringEvent;

/**
 *
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class ZoneAudioInputMarantzEvent extends StringEvent implements MarantzEvent {

	/**
	 * @param source
	 * @param zone
	 * @param value
	 */
	public ZoneAudioInputMarantzEvent(Marantz source, int zone, String value) {
		super(source, "zone(" + zone + ").audioInput", value.toLowerCase(Locale.ENGLISH));
	}

}
