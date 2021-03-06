package ru.betterend.registry;

import net.minecraft.block.Block;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import ru.betterend.BetterEnd;
import ru.betterend.blocks.AeterniumBlock;
import ru.betterend.blocks.AuroraCrystalBlock;
import ru.betterend.blocks.BlockBlueVine;
import ru.betterend.blocks.BlockBlueVineLantern;
import ru.betterend.blocks.BlockBlueVineSeed;
import ru.betterend.blocks.BlockBubbleCoral;
import ru.betterend.blocks.BlockChorusGrass;
import ru.betterend.blocks.BlockEndLily;
import ru.betterend.blocks.BlockEndLilySeed;
import ru.betterend.blocks.BlockEndLotusFlower;
import ru.betterend.blocks.BlockEndLotusLeaf;
import ru.betterend.blocks.BlockEndLotusSeed;
import ru.betterend.blocks.BlockEndLotusStem;
import ru.betterend.blocks.BlockEndstoneDust;
import ru.betterend.blocks.BlockGlowingMoss;
import ru.betterend.blocks.BlockMossyGlowshroomCap;
import ru.betterend.blocks.BlockMossyGlowshroomHymenophore;
import ru.betterend.blocks.BlockMossyGlowshroomSapling;
import ru.betterend.blocks.BlockMossyPythadendronSapling;
import ru.betterend.blocks.BlockOre;
import ru.betterend.blocks.BlockPath;
import ru.betterend.blocks.BlockTerrain;
import ru.betterend.blocks.BlockTerrainPlant;
import ru.betterend.blocks.BlockUmbrellaMoss;
import ru.betterend.blocks.BlockUmbrellaMossTall;
import ru.betterend.blocks.EndPortalBlock;
import ru.betterend.blocks.EndStoneSmelter;
import ru.betterend.blocks.EnderBlock;
import ru.betterend.blocks.EternalRunedFlavolite;
import ru.betterend.blocks.RunedFlavolite;
import ru.betterend.blocks.TerminiteBlock;
import ru.betterend.blocks.basis.BlockGlowingFur;
import ru.betterend.blocks.basis.BlockLeaves;
import ru.betterend.blocks.basis.BlockSimpleLeaves;
import ru.betterend.blocks.basis.BlockVine;
import ru.betterend.blocks.complex.StoneMaterial;
import ru.betterend.blocks.complex.WoodenMaterial;
import ru.betterend.tab.CreativeTab;

public class BlockRegistry {
	// Terrain //
	public static final Block ENDSTONE_DUST = registerBlock("endstone_dust", new BlockEndstoneDust());
	public static final Block END_MYCELIUM = registerBlock("end_mycelium", new BlockTerrain(MaterialColor.LIGHT_BLUE));
	public static final Block END_MOSS = registerBlock("end_moss", new BlockTerrain(MaterialColor.CYAN));
	public static final Block CHORUS_NYLIUM = registerBlock("chorus_nylium", new BlockTerrain(MaterialColor.MAGENTA));
	public static final Block CAVE_MOSS = registerBlock("cave_moss", new BlockTerrain(MaterialColor.PURPLE));
	
	// Roads //
	public static final Block END_MYCELIUM_PATH = registerBlock("end_mycelium_path", new BlockPath(END_MYCELIUM));
	public static final Block END_MOSS_PATH = registerBlock("end_moss_path", new BlockPath(END_MOSS));
	public static final Block CHORUS_NYLIUM_PATH = registerBlock("chorus_nylium_path", new BlockPath(CHORUS_NYLIUM));
	public static final Block CAVE_MOSS_PATH = registerBlock("cave_moss_path", new BlockPath(CAVE_MOSS));
	
	// Rocks //
	public static final StoneMaterial FLAVOLITE = new StoneMaterial("flavolite", MaterialColor.SAND);
	public static final StoneMaterial VIOLECITE = new StoneMaterial("violecite", MaterialColor.PURPLE);
	public static final Block FLAVOLITE_RUNED = registerBlock("flavolite_runed", new RunedFlavolite());
	public static final Block FLAVOLITE_RUNED_ETERNAL = registerBlock("flavolite_runed_eternal", new EternalRunedFlavolite());
	
	// Wooden Materials //
	public static final Block MOSSY_GLOWSHROOM_SAPLING = registerBlock("mossy_glowshroom_sapling", new BlockMossyGlowshroomSapling());
	public static final Block MOSSY_GLOWSHROOM_CAP = registerBlock("mossy_glowshroom_cap", new BlockMossyGlowshroomCap());
	public static final Block MOSSY_GLOWSHROOM_HYMENOPHORE = registerBlock("mossy_glowshroom_hymenophore", new BlockMossyGlowshroomHymenophore());
	public static final Block MOSSY_GLOWSHROOM_FUR = registerBlock("mossy_glowshroom_fur", new BlockGlowingFur(MOSSY_GLOWSHROOM_SAPLING, 16));
	public static final WoodenMaterial MOSSY_GLOWSHROOM = new WoodenMaterial("mossy_glowshroom", MaterialColor.GRAY, MaterialColor.WOOD);
	
	public static final Block PYTHADENDRON_SAPLING = registerBlock("pythadendron_sapling", new BlockMossyPythadendronSapling());
	public static final Block PYTHADENDRON_LEAVES = registerBlock("pythadendron_leaves", new BlockLeaves(MaterialColor.MAGENTA));
	public static final WoodenMaterial PYTHADENDRON = new WoodenMaterial("pythadendron", MaterialColor.MAGENTA, MaterialColor.PURPLE);
	
	public static final Block END_LOTUS_SEED = registerBlock("end_lotus_seed", new BlockEndLotusSeed());
	public static final Block END_LOTUS_STEM = registerBlock("end_lotus_stem", new BlockEndLotusStem());
	public static final Block END_LOTUS_LEAF = registerBlockNI("end_lotus_leaf", new BlockEndLotusLeaf());
	public static final Block END_LOTUS_FLOWER = registerBlockNI("end_lotus_flower", new BlockEndLotusFlower());
	public static final WoodenMaterial END_LOTUS = new WoodenMaterial("end_lotus", MaterialColor.LIGHT_BLUE, MaterialColor.CYAN);
	
	// Small Plants //
	public static final Block UMBRELLA_MOSS = registerBlock("umbrella_moss", new BlockUmbrellaMoss());
	public static final Block UMBRELLA_MOSS_TALL = registerBlock("umbrella_moss_tall", new BlockUmbrellaMossTall());
	public static final Block CREEPING_MOSS = registerBlock("creeping_moss", new BlockGlowingMoss(11));
	public static final Block CHORUS_GRASS = registerBlock("chorus_grass", new BlockChorusGrass());
	public static final Block CAVE_GRASS = registerBlock("cave_grass", new BlockTerrainPlant(CAVE_MOSS));
	
	public static final Block BLUE_VINE_SEED = registerBlock("blue_vine_seed", new BlockBlueVineSeed());
	public static final Block BLUE_VINE = registerBlockNI("blue_vine", new BlockBlueVine());
	public static final Block BLUE_VINE_LANTERN = registerBlock("blue_vine_lantern", new BlockBlueVineLantern());
	public static final Block BLUE_VINE_FUR = registerBlock("blue_vine_fur", new BlockGlowingFur(BLUE_VINE_SEED, 3));
	
	public static final Block BUBBLE_CORAL = registerBlock("bubble_coral", new BlockBubbleCoral());
	public static final Block END_LILY = registerBlockNI("end_lily", new BlockEndLily());
	public static final Block END_LILY_SEED = registerBlock("end_lily_seed", new BlockEndLilySeed());
	
	public static final Block CAVE_BUSH = registerBlock("cave_bush", new BlockSimpleLeaves(MaterialColor.MAGENTA));
	
	// Vines //
	public static final Block DENSE_VINE = registerBlock("dense_vine", new BlockVine(15, true));
	
	// Ores //
	public static final Block ENDER_ORE = registerBlock("ender_ore", new BlockOre(ItemRegistry.ENDER_DUST, 1, 3));
	
	// Materials //
	public static final Block TERMINITE_BLOCK = registerBlock("terminite_block", new TerminiteBlock());
	public static final Block AETERNIUM_BLOCK = registerBlock("aeternium_block", new AeterniumBlock());
	public static final Block ENDER_BLOCK = registerBlock("ender_block", new EnderBlock());
	public static final Block AURORA_CRYSTAL = registerBlock("aurora_crystal", new AuroraCrystalBlock());
	
	// Blocks With Entity //
	public static final Block END_STONE_SMELTER = registerBlock("end_stone_smelter", new EndStoneSmelter());
	
	//Technical
	public static final Block END_PORTAL_BLOCK = registerBlock("end_portal_block", new EndPortalBlock());
	
	public static void register() {}
	
	public static Block registerBlock(String name, Block block) {
		Registry.register(Registry.BLOCK, BetterEnd.makeID(name), block);
		ItemRegistry.registerItem(name, new BlockItem(block, new Item.Settings().group(CreativeTab.END_TAB)));
		return block;
	}
	
	public static Block registerBlockNI(String name, Block block) {
		return Registry.register(Registry.BLOCK, BetterEnd.makeID(name), block);
	}
}
