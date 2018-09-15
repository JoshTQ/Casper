package online.potters.api.locale;

import java.util.Arrays;

/**
 * .-----.
 * .' -   - '.
 * /  .-. .-.  \  	   .--.      .---.      .--.       .-..     .--.    ___ .-.
 * |  | | | |  |  	  /    \    / .-, \   /  _  \     /    \   /    \  (   )   \
 * \ \o/ \o/ /   	 |  .-. ;  (__) ; |  . .' `. ;   ' .-,  ; |  .-. ;  | ' .-. ;
 * _/    ^    \_  	 |  |(___)   .'`  |  | '   | |   | |  . | |  | | |  |  / (___)
 * | \  '---'  / |  	 |  |       / .'| |  _\_`.(___)  | |  | | |  |/  |  | |
 * / /`--. .--`\ \  	 |  | ___  | /  | | (   ). '.    | |  | | |  ' _.'  | |
 * / /'---` `---'\ \	 |  '(   ) ; |  ; |  | |  `\ |   | |  ' | |  .'.-.  | |
 * '.__.       .__.'	 '  `-' |  ' `-'  |  ; '._,' '   | `-'  ' '  `-' /  | |
 * `|     |`    	  `.__,'   `.__.'_.   '.___.'    | \__.'   `.__.'  (___)
 * |     \     	                                 | |
 * \      '--.   	                                (___)
 * '.        `\
 * `'---.   |
 * ,__) /
 * `..'
 *
 * @author PottersMC (2018)
 */
public interface Message {

	/**
	 * @return
	 */
	String getIdentifier();

	default String get() {
		return get(true);
	}

	default String get(boolean shouldFormat) {
		StringBuilder stringBuilder = new StringBuilder();

		Arrays.stream(getList(shouldFormat)).forEach(message -> {
					stringBuilder.append(message);
					stringBuilder.append("\n");
				}
		);

		return stringBuilder.toString();
	}

	default String[] getList() {
		return getList(true);
	}

	String[] getList(boolean shouldFormat);

	Message replace(String valueToReplace, String replacedBy);

	void sendMessage(Object sendTo);

	void sendMessage(Object sendTo, String permission);

	void shout();

}
