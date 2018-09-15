package online.potters.impl.bukkit.items;

import online.potters.impl.bukkit.utils.ColorUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;

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
public class ItemBuilder {

	private Material material;

	private HashMap<Enchantment, Integer> enchantments;

	private int amount;

	private String name;

	private String[] lore;

	public ItemBuilder() {
		material = Material.AIR;
		amount = 1;
		name = null;
		enchantments = new HashMap<>();
		lore = null;
	}

	public ItemBuilder material(Material material) {
		this.material = material;
		return this;
	}

	public ItemBuilder amount(int amount) {
		this.amount = amount;
		return this;
	}

	public ItemBuilder enchantment(Enchantment enchantment) {
		enchantments.put(enchantment, 1);
		return this;
	}

	public ItemBuilder enchantment(Enchantment enchantment, int level) {
		enchantments.put(enchantment, level);
		return this;
	}

	public ItemBuilder name(String name) {
		this.name = ColorUtils.colorize(name);
		return this;
	}

	public ItemBuilder lore(String... lore) {
		this.lore = lore;
		return this;
	}

	public ItemStack build() {
		ItemStack itemStack = new ItemStack(material, amount);
		enchantments.forEach(itemStack::addEnchantment);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setLore(Arrays.asList(lore));
		itemMeta.setDisplayName(name);
		itemStack.setItemMeta(itemMeta);

		return itemStack;
	}
}
