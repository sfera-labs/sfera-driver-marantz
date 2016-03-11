/**
 * 
 */
package cc.sferalabs.sfera.drivers.marantz;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author Giampiero Baggiani
 *
 * @version 1.0.0
 *
 */
public class MarantzCommand {

	final String text;
	final byte[] bytes;

	/**
	 * 
	 */
	public MarantzCommand(String cmd) {
		this.text = cmd;
		this.bytes = cmd.getBytes(StandardCharsets.US_ASCII);
	}

}
