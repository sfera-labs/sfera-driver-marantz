/*-
 * +======================================================================+
 * Marantz
 * ---
 * Copyright (C) 2016 Sfera Labs S.r.l.
 * ---
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * -======================================================================-
 */

/**
 * 
 */
package cc.sferalabs.sfera.drivers.marantz.events;

import cc.sferalabs.sfera.drivers.marantz.Marantz;
import cc.sferalabs.sfera.drivers.marantz.MarantzZone;
import cc.sferalabs.sfera.events.NumberEvent;

/**
 *
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class ZoneVolumeMarantzEvent extends NumberEvent implements MarantzZoneEvent {

	private final MarantzZone zone;

	/**
	 * @param source
	 * @param zone
	 * @param value
	 */
	public ZoneVolumeMarantzEvent(Marantz source, int zone, String value) {
		super(source, "zone(" + zone + ").volume", toVolume(value));
		this.zone = source.zone(zone);
	}

	@Override
	public MarantzZone getZone() {
		return zone;
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
