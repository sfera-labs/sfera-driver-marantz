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
public class ZoneVolumeMarantzEvent extends NumberEvent implements MarantzEvent {

	/**
	 * @param source
	 * @param zone
	 * @param value
	 */
	public ZoneVolumeMarantzEvent(Marantz source, int zone, String value) {
		super(source, "zone(" + zone + ").volume", toVolume(value));
	}

	/**
	 * @param value
	 * @return
	 */
	private static float toVolume(String value) {
		int i = Integer.parseInt(value.substring(0, 2));
		int d;
		try {
			d = Integer.parseInt(value.substring(2));
		} catch (Exception ex) {
			d = 0;
		}
		return (float) (i + d / 10.0);
	}

}
