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

import java.util.Locale;

import cc.sferalabs.sfera.drivers.marantz.Marantz;
import cc.sferalabs.sfera.drivers.marantz.MarantzZone;
import cc.sferalabs.sfera.events.StringEvent;

/**
 *
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class ZoneSoundModeMarantzEvent extends StringEvent implements MarantzZoneEvent {

	private final MarantzZone zone;

	/**
	 * @param source
	 * @param zone
	 * @param value
	 */
	public ZoneSoundModeMarantzEvent(Marantz source, int zone, String value) {
		super(source, "zone(" + zone + ").soundMode", value.toLowerCase(Locale.ENGLISH));
		this.zone = source.zone(zone);
	}

	@Override
	public MarantzZone getZone() {
		return zone;
	}

}
