package cc.sferalabs.sfera.drivers.marantz.events;

import cc.sferalabs.sfera.drivers.marantz.MarantzZone;

public interface MarantzZoneEvent extends MarantzEvent {

	public MarantzZone getZone();

}
