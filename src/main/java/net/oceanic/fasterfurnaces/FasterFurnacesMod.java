package net.oceanic.fasterfurnaces;

import com.oroarmor.config.Config;
import com.oroarmor.config.command.ConfigCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.rule.DoubleRule;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FasterFurnacesMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("fasterfurnace");
	public static final GameRules.Key<DoubleRule> furnaceSpeed =
			register("furnaceSpeedMultiplier", GameRules.Category.UPDATES, GameRuleFactory.createDoubleRule(20.0, 0, 1000.0));
	private static  GameRules.Key register(String name, GameRules.Category category, GameRules.Type type) {
		return GameRuleRegistry.register(name, category, type);
	}

	private static  GameRules.Key register(String name, CustomGameRuleCategory category, GameRules.Type type) {
		return GameRuleRegistry.register(name, category, type);
	}
	@Override
	public void onInitialize() {

		}
}
