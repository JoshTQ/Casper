package online.potters.api.bukkit.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

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
public interface GUIItem {

	/**
	 * @return The current ItemStack associated with this GUIItem.
	 */
	ItemStack getItemStack();

	/**
	 * @return Permission required to click on this item.
	 */
	default String getPermission() {
		return null;
	}

	/**
	 * @param itemStack Set the ItemStack in the Slot (Refreshing)
	 */
	void setItemStack(ItemStack itemStack);

	/**
	 * @param player The Player who's trying to click the GUIItem.
	 * @return Whether or not they can click the item.
	 */
	default boolean canClick(Player player) {
		return true;
	}

	/**
	 * Method ran when the player has clicked on the item and canClick.
	 *
	 * @param player Player who's clicking the item.
	 * @param clickType Type of Click this player used.
	 */
	void onClick(Player player, ClickType clickType);


}
