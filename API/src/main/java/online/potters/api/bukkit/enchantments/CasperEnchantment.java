package online.potters.api.bukkit.enchantments;

import com.google.common.collect.ImmutableList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 *
 *		     .-----.
 *		   .' -   - '.
 *		  /  .-. .-.  \  	   .--.      .---.      .--.       .-..     .--.    ___ .-.
 *		  |  | | | |  |  	  /    \    / .-, \   /  _  \     /    \   /    \  (   )   \
 *		   \ \o/ \o/ /   	 |  .-. ;  (__) ; |  . .' `. ;   ' .-,  ; |  .-. ;  | ' .-. ;
 *		  _/    ^    \_  	 |  |(___)   .'`  |  | '   | |   | |  . | |  | | |  |  / (___)
 * 		| \  '---'  / |  	 |  |       / .'| |  _\_`.(___)  | |  | | |  |/  |  | |
 * 		/ /`--. .--`\ \  	 |  | ___  | /  | | (   ). '.    | |  | | |  ' _.'  | |
 *		/ /'---` `---'\ \	 |  '(   ) ; |  ; |  | |  `\ |   | |  ' | |  .'.-.  | |
 *		'.__.       .__.'	 '  `-' |  ' `-'  |  ; '._,' '   | `-'  ' '  `-' /  | |
 *		    `|     |`    	  `.__,'   `.__.'_.   '.___.'    | \__.'   `.__.'  (___)
 *		     |     \     	                                 | |
 *		    \      '--.   	                                (___)
 *  		    '.        `\
 * 	 	      `'---.   |
 *  	 	        ,__) /
 *  	  	        `..'
 *
 * @author PottersMC (2018)
 */
public interface CasperEnchantment {

	/**
	 * @return The name of the Enchantment.
	 */
	String getName();

	/**
	 * @return Minimum Level of this Enchantment.
	 */
	int getStartLevel();

	/**
	 * @return Maximum Level of this Enchantment.
	 */
	int getMaxLevel();

	/**
	 * @return The Materials that this enchantment can be applied to.
	 */
	ImmutableList<Material> getEnchantableMaterials();

	/**
	 * @param itemStack ItemStack that is trying to enchant.
	 * @return Whether or not this ItemStack can be enchanted.
	 */
	boolean canEnchant(ItemStack itemStack);

	/**
	 * @param itemStack Enchant the Item.
	 */
	void enchantItem(ItemStack itemStack);

	/**
	 * @param itemStack The ItemStack to reference.
	 * @return The current level the enchant has (if it doesn't, return 0)
	 */
	int getLevel(ItemStack itemStack);

	/**
	 * @param itemStack Check whether or not this ItemStack has this enchantment.
	 * @return Whether or not it does have the enchantment.
	 */
	boolean hasEnchantment(ItemStack itemStack);

	/**
	 * @return The Type of Enchantment, and it's general purpose.
	 */
	EnchantmentType getEnchantmentType();


}
