package net.oceanic.fasterfurnace.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.oceanic.fasterfurnace.FasterFurnaceMod;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(AbstractFurnaceBlockEntity.class)
public class EditingMixin {
	@Inject(method = "serverTick", at = @At("HEAD"))
		private static void tick(Level item, BlockPos recipe, BlockState i, AbstractFurnaceBlockEntity p_155014_, CallbackInfo ci) {
		((GettingMixin)p_155014_ ).setTotalCookTime(Math.max((int)((double)(item.getRecipeManager().getRecipeFor(((GettingMixin)p_155014_ ).getRecipeType(), p_155014_, item).map(AbstractCookingRecipe::getCookingTime).orElse(200)) / p_155014_.getLevel().getGameRules().getRule(FasterFurnaceMod.furnaceSpeedMultiplier).get()),1));

	}
	@Redirect(method = "serverTick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;litTime:I", opcode = Opcodes.PUTFIELD))
	private static void injectedBurnTime(AbstractFurnaceBlockEntity blockEntity, int burnTime) {
		if (((GettingMixin) blockEntity).getLitTime() <= 0) {
			((GettingMixin) blockEntity).setBurnTime(Math.max((int) ((double) burnTime / blockEntity.getLevel().getGameRules().getRule(FasterFurnaceMod.furnaceSpeedMultiplier).get()),1));
		} else{
			((GettingMixin) blockEntity).setBurnTime((int) ((double) burnTime));
		}
	}
	@Redirect(method = "serverTick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;cookingTotalTime:I", opcode = Opcodes.PUTFIELD))
	private static void injectedCookTime(AbstractFurnaceBlockEntity blockEntity, int cookTime) {
			((GettingMixin) blockEntity).setTotalCookTime(Math.max((int) ((double) cookTime / blockEntity.getLevel().getGameRules().getRule(FasterFurnaceMod.furnaceSpeedMultiplier).get()),1));
	}
}

