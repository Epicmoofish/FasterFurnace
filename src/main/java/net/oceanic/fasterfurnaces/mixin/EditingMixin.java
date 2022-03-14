package net.oceanic.fasterfurnaces.mixin;

import com.oroarmor.config.ConfigItemGroup;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.item.Item;
import net.minecraft.recipe.Recipe;
import net.minecraft.server.MinecraftServer;
import net.oceanic.fasterfurnaces.FasterFurnacesMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.entity.AbstractFurnaceBlockEntity.createFuelTimeMap;


@Mixin(AbstractFurnaceBlockEntity.class)
public class EditingMixin {
	@Inject(method = "tick", at = @At("HEAD"))
		private static void tick(World world, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity, CallbackInfo ci) {

		((GettingMixin)blockEntity ).setTotalCookTime(Math.max((int)((double)(world.getRecipeManager().getFirstMatch(((GettingMixin)blockEntity ).getRecipeType(), blockEntity, world).map(AbstractCookingRecipe::getCookTime).orElse(200)) / blockEntity.getWorld().getGameRules().get(FasterFurnacesMod.furnaceSpeed).get()),1));
	}
	@Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/block/entity/AbstractFurnaceBlockEntity;burnTime:I", opcode = Opcodes.PUTFIELD))
	private static void injectedBurnTime(AbstractFurnaceBlockEntity blockEntity, int burnTime) {
		if (((GettingMixin) blockEntity).getBurnTime() <= 0) {
			((GettingMixin) blockEntity).setBurnTime(Math.max((int) ((double) burnTime / blockEntity.getWorld().getGameRules().get(FasterFurnacesMod.furnaceSpeed).get()),1));
		} else{
			((GettingMixin) blockEntity).setBurnTime((int) ((double) burnTime));
		}
	}
	@Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/block/entity/AbstractFurnaceBlockEntity;cookTimeTotal:I", opcode = Opcodes.PUTFIELD))
	private static void injectedCookTime(AbstractFurnaceBlockEntity blockEntity, int cookTime) {
			((GettingMixin) blockEntity).setTotalCookTime(Math.max((int) ((double) cookTime / blockEntity.getWorld().getGameRules().get(FasterFurnacesMod.furnaceSpeed).get()),1));
	}
}

