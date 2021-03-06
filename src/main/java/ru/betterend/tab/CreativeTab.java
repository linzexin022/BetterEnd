package ru.betterend.tab;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import ru.betterend.BetterEnd;
import ru.betterend.registry.BlockRegistry;
import ru.betterend.registry.ItemRegistry;

public class CreativeTab {
	public static final ItemGroup END_TAB = FabricItemGroupBuilder.create(BetterEnd.makeID("items"))
			.icon(() -> new ItemStack(BlockRegistry.END_MYCELIUM)).appendItems(stacks -> {
				for (Item i : ItemRegistry.getModBlocks()) {
					stacks.add(new ItemStack(i));
				}
				for (Item i : ItemRegistry.getModItems()) {
					stacks.add(new ItemStack(i));
				}
			}).build();
}
