package net.oceanic.fasterfurnaces.mixin;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractFurnaceBlockEntity.class)
public interface GettingMixin {
	@Accessor("cookTimeTotal")
	public void setTotalCookTime(int cookTimeTotal);
	@Accessor("burnTime")
	public void setBurnTime(int burnTime);
	@Accessor()
	public int getCookTimeTotal();
	@Accessor()
	public int getBurnTime();
	@Accessor()
	public RecipeType<? extends AbstractCookingRecipe> getRecipeType();
	@Accessor
	public DefaultedList<ItemStack> getInventory();
}


