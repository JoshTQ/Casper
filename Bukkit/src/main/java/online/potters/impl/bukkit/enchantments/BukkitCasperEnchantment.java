package online.potters.impl.bukkit.enchantments;

import com.google.common.collect.ImmutableList;
import online.potters.api.bukkit.enchantments.CasperEnchantment;
import online.potters.api.bukkit.enchantments.EnchantmentType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BukkitCasperEnchantment extends Enchantment implements CasperEnchantment, Listener {

	private JavaPlugin javaPlugin;
	private String enchantName;

	public BukkitCasperEnchantment(JavaPlugin javaPlugin, int id, String name) {
		super(id);

		this.javaPlugin = javaPlugin;
		this.enchantName = name;
	}

	/**
	 * @return The name of the Enchantment.
	 */
	public String getName() {
		return null;
	}

	/**
	 * @return Minimum Level of this Enchantment.
	 */
	public int getStartLevel() {
		return 1;
	}

	/**
	 * @return Maximum Level of this Enchantment.
	 */
	public abstract int getMaxLevel();

	/**
	 * @return The Materials that this enchantment can be applied to.
	 */
	public abstract ImmutableList<Material> getEnchantableMaterials();

	/**
	 * @param itemStack ItemStack that is trying to enchant.
	 * @return Whether or not this ItemStack can be enchanted.
	 */
	public abstract boolean canEnchant(ItemStack itemStack);

	/**
	 * @param itemStack Enchant the Item.
	 */
	public abstract void enchantItem(ItemStack itemStack);

	/**
	 * @param itemStack The ItemStack to reference.
	 * @return The current level the enchant has (if it doesn't, return 0)
	 */
	public int getLevel(ItemStack itemStack) {
		if (itemStack == null) {
			return 0;
		}

		if (!itemStack.hasItemMeta()) {
			return 0;
		}

		if (!itemStack.getItemMeta().hasLore()) {
			return 0;
		}

		ItemMeta itemMeta = itemStack.getItemMeta();

		if (itemMeta.getEnchantLevel(this) != 0) {
			return itemMeta.getEnchantLevel(this);
		}


		return 0;
	}

	/**
	 * @param itemStack Check whether or not this ItemStack has this enchantment.
	 * @return Whether or not it does have the enchantment.
	 */
	public boolean hasEnchantment(ItemStack itemStack) {
		return false;
	}

	/**
	 * @return The Type of Enchantment, and it's general purpose.
	 */
	public EnchantmentType getEnchantmentType() {
		return null;
	}

	public EnchantmentTarget getItemTarget() {
		return null;
	}

	public boolean conflictsWith(Enchantment enchantment) {
		return false;
	}

	public boolean canEnchantItem(ItemStack itemStack) {
		return false;
	}
}
