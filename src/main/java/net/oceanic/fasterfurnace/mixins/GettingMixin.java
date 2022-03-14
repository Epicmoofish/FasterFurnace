package net.oceanic.fasterfurnace.mixins;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractFurnaceBlockEntity.class)
public interface GettingMixin {
	@Accessor("cookingTotalTime")
	public void setTotalCookTime(int cookTimeTotal);
	@Accessor("litTime")
	public void setBurnTime(int burnTime);
	@Accessor()
	public int getCookingTotalTime();
	@Accessor()
	public int getLitTime();
	@Accessor()
	public RecipeType<? extends AbstractCookingRecipe> getRecipeType();
	@Accessor
	public NonNullList<ItemStack> getItems();
}


