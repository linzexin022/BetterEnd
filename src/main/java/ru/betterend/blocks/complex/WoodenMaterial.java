package ru.betterend.blocks.complex;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.Items;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import ru.betterend.blocks.basis.BlockBark;
import ru.betterend.blocks.basis.BlockBarkStripable;
import ru.betterend.blocks.basis.BlockBarrel;
import ru.betterend.blocks.basis.BlockBase;
import ru.betterend.blocks.basis.BlockChest;
import ru.betterend.blocks.basis.BlockCraftingTable;
import ru.betterend.blocks.basis.BlockDoor;
import ru.betterend.blocks.basis.BlockFence;
import ru.betterend.blocks.basis.BlockGate;
import ru.betterend.blocks.basis.BlockLadder;
import ru.betterend.blocks.basis.BlockLogStripable;
import ru.betterend.blocks.basis.BlockPillar;
import ru.betterend.blocks.basis.BlockPressurePlate;
import ru.betterend.blocks.basis.BlockSign;
import ru.betterend.blocks.basis.BlockSlab;
import ru.betterend.blocks.basis.BlockStairs;
import ru.betterend.blocks.basis.BlockTrapdoor;
import ru.betterend.blocks.basis.BlockWoodenButton;
import ru.betterend.recipe.builders.RecipeBuilder;
import ru.betterend.registry.BlockRegistry;
import ru.betterend.util.TagHelper;

public class WoodenMaterial {
	public final Block log;
	public final Block bark;

	public final Block log_stripped;
	public final Block bark_stripped;

	public final Block planks;

	public final Block stairs;
	public final Block slab;
	public final Block fence;
	public final Block gate;
	public final Block button;
	public final Block pressure_plate;
	public final Block trapdoor;
	public final Block door;
	
	public final Block crafting_table;
	public final Block ladder;
	public final Block sign;
	
	public final Block chest;
	public final Block barrel;
	
	public WoodenMaterial(String name, MaterialColor woodColor, MaterialColor planksColor) {
		FabricBlockSettings materialPlanks = FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).materialColor(planksColor);
		
		log_stripped = BlockRegistry.registerBlock(name + "_stripped_log", new BlockPillar(materialPlanks));
		bark_stripped = BlockRegistry.registerBlock(name + "_stripped_bark", new BlockBark(materialPlanks));
		
		log = BlockRegistry.registerBlock(name + "_log", new BlockLogStripable(woodColor, log_stripped));
		bark = BlockRegistry.registerBlock(name + "_bark", new BlockBarkStripable(woodColor, bark_stripped));
		
		planks = BlockRegistry.registerBlock(name + "_planks", new BlockBase(materialPlanks));
		stairs = BlockRegistry.registerBlock(name + "_stairs", new BlockStairs(planks));
		slab = BlockRegistry.registerBlock(name + "_slab", new BlockSlab(planks));
		fence = BlockRegistry.registerBlock(name + "_fence", new BlockFence(planks));
		gate = BlockRegistry.registerBlock(name + "_gate", new BlockGate(planks));
		button = BlockRegistry.registerBlock(name + "_button", new BlockWoodenButton(planks));
		pressure_plate = BlockRegistry.registerBlock(name + "_plate", new BlockPressurePlate(planks));
		trapdoor = BlockRegistry.registerBlock(name + "_trapdoor", new BlockTrapdoor(planks));
		door = BlockRegistry.registerBlock(name + "_door", new BlockDoor(planks));
		
		crafting_table = BlockRegistry.registerBlock(name + "_crafting_table", new BlockCraftingTable(planks));
		ladder = BlockRegistry.registerBlock(name + "_ladder", new BlockLadder(planks));
		sign = BlockRegistry.registerBlock(name + "_sign", new BlockSign(planks));
		
		chest = BlockRegistry.registerBlock(name + "_chest", new BlockChest(planks));
		barrel = BlockRegistry.registerBlock(name + "_barrel", new BlockBarrel(planks));
		
		// Recipes //
		RecipeBuilder.make(name + "_planks", planks).setOutputCount(4).setList("#").addMaterial('#', log, bark, log_stripped, bark_stripped).setGroup("end_planks").build();
		RecipeBuilder.make(name + "_stairs", stairs).setOutputCount(4).setShape("#  ", "## ", "###").addMaterial('#', planks).setGroup("end_planks_stairs").build();
		RecipeBuilder.make(name + "_slab", slab).setOutputCount(6).setShape("###").addMaterial('#', planks).setGroup("end_planks_slabs").build();
		RecipeBuilder.make(name + "_fence", fence).setOutputCount(3).setShape("#I#", "#I#").addMaterial('#', planks).addMaterial('I', Items.STICK).setGroup("end_planks_fences").build();
		RecipeBuilder.make(name + "_gate", gate).setShape("I#I", "I#I").addMaterial('#', planks).addMaterial('I', Items.STICK).setGroup("end_planks_gates").build();
		RecipeBuilder.make(name + "_button", button).setList("#").addMaterial('#', planks).setGroup("end_planks_buttons").build();
		RecipeBuilder.make(name + "_pressure_plate", pressure_plate).setShape("##").addMaterial('#', planks).setGroup("end_planks_plates").build();
		RecipeBuilder.make(name + "_trapdoor", trapdoor).setOutputCount(2).setShape("###", "###").addMaterial('#', planks).setGroup("end_trapdoors").build();
		RecipeBuilder.make(name + "_door", door).setOutputCount(3).setShape("##", "##", "##").addMaterial('#', planks).setGroup("end_doors").build();
		RecipeBuilder.make(name + "_crafting_table", crafting_table).setShape("##", "##").addMaterial('#', planks).setGroup("end_tables").build();
		RecipeBuilder.make(name + "_ladder", ladder).setOutputCount(3).setShape("I I", "I#I", "I I").addMaterial('#', planks).addMaterial('I', Items.STICK).setGroup("end_ladders").build();
		RecipeBuilder.make(name + "_sign", sign).setOutputCount(3).setShape("###", "###", " I ").addMaterial('#', planks).addMaterial('I', Items.STICK).setGroup("end_signs").build();
		RecipeBuilder.make(name + "_chest", chest).setShape("###", "# #", "###").addMaterial('#', planks).setGroup("end_chests").build();
		RecipeBuilder.make(name + "_barrel", barrel).setShape("#S#", "# #", "#S#").addMaterial('#', planks).addMaterial('S', slab).setGroup("end_barrels").build();
		
		// Item Tags //
		TagHelper.addTag(ItemTags.PLANKS, planks);
		TagHelper.addTag(ItemTags.WOODEN_PRESSURE_PLATES, pressure_plate);
		TagHelper.addTag(ItemTags.LOGS, log, bark, log_stripped, bark_stripped);
		TagHelper.addTag(ItemTags.LOGS_THAT_BURN, log, bark, log_stripped, bark_stripped);
		
		TagHelper.addTags(button, ItemTags.WOODEN_BUTTONS, ItemTags.BUTTONS);
		TagHelper.addTags(door, ItemTags.WOODEN_DOORS, ItemTags.DOORS);
		TagHelper.addTags(fence, ItemTags.WOODEN_FENCES, ItemTags.FENCES);
		TagHelper.addTags(slab, ItemTags.WOODEN_SLABS, ItemTags.SLABS);
		TagHelper.addTags(stairs, ItemTags.WOODEN_STAIRS, ItemTags.STAIRS);
		TagHelper.addTags(trapdoor, ItemTags.WOODEN_TRAPDOORS, ItemTags.TRAPDOORS);
		
		// Block Tags //
		TagHelper.addTag(BlockTags.PLANKS, planks);
		TagHelper.addTag(BlockTags.CLIMBABLE, ladder);
		TagHelper.addTag(BlockTags.LOGS, log, bark, log_stripped, bark_stripped);
		TagHelper.addTag(BlockTags.LOGS_THAT_BURN, log, bark, log_stripped, bark_stripped);
		
		TagHelper.addTags(button, BlockTags.WOODEN_BUTTONS, BlockTags.BUTTONS);
		TagHelper.addTags(door, BlockTags.WOODEN_DOORS, BlockTags.DOORS);
		TagHelper.addTags(fence, BlockTags.WOODEN_FENCES, BlockTags.FENCES);
		TagHelper.addTags(slab, BlockTags.WOODEN_SLABS, BlockTags.SLABS);
		TagHelper.addTags(stairs, BlockTags.WOODEN_STAIRS, BlockTags.STAIRS);
		TagHelper.addTags(trapdoor, BlockTags.WOODEN_TRAPDOORS, BlockTags.TRAPDOORS);
	}
	
	public boolean isTreeLog(Block block) {
		return block == log || block == bark;
	}
	
	public boolean isTreeLog(BlockState state) {
		return isTreeLog(state.getBlock());
	}
}