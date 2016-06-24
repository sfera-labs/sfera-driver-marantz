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
