package ru.betterend.world.generator;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.TheEndBiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import ru.betterend.BetterEnd;
import ru.betterend.registry.BiomeRegistry;
import ru.betterend.registry.BlockTagRegistry;
import ru.betterend.util.FeaturesHelper;
import ru.betterend.world.biome.EndBiome;

public class BetterEndBiomeSource extends BiomeSource {
	public static final Codec<BetterEndBiomeSource> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(RegistryLookupCodec.of(Registry.BIOME_KEY).forGetter((theEndBiomeSource) -> {
			return theEndBiomeSource.biomeRegistry;
		}), Codec.LONG.fieldOf("seed").stable().forGetter((theEndBiomeSource) -> {
			return theEndBiomeSource.seed;
		})).apply(instance, instance.stable(BetterEndBiomeSource::new));
	});
	private final Registry<Biome> biomeRegistry;
	private final SimplexNoiseSampler noise;
	private final Biome centerBiome;
	private BiomeMap mapLand;
	private BiomeMap mapVoid;
	private final long seed;

	public BetterEndBiomeSource(Registry<Biome> biomeRegistry, long seed) {
		super(getBiomes(biomeRegistry));
		
		this.mapLand = new BiomeMap(seed, 256, BiomeRegistry.LAND_BIOMES);
		this.mapVoid = new BiomeMap(seed, 256, BiomeRegistry.VOID_BIOMES);
		this.centerBiome = biomeRegistry.getOrThrow(BiomeKeys.THE_END);
		this.biomeRegistry = biomeRegistry;
		this.seed = seed;
		
		ChunkRandom chunkRandom = new ChunkRandom(seed);
		chunkRandom.consume(17292);
		this.noise = new SimplexNoiseSampler(chunkRandom);

		BiomeRegistry.mutateRegistry(biomeRegistry);
		BlockTagRegistry.addTerrainTags(biomeRegistry);
		FeaturesHelper.addFeatures(biomeRegistry);
	}
	
	private static List<Biome> getBiomes(Registry<Biome> biomeRegistry) {
		List<Biome> list = Lists.newArrayList();
		biomeRegistry.forEach((biome) -> {
			if (biome.getCategory() == Category.THEEND) {
				list.add(biome);
			}
		});
		return list;
	}

	@Override
	public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) {
		long i = biomeX >> 2;
		long j = biomeZ >> 2;
		if (i * i + j * j <= 4096L) return this.centerBiome;
		
		float height = TheEndBiomeSource.getNoiseAt(noise, (int) i * 2 + 1, (int) j * 2 + 1);
	         
		EndBiome netherBiome = height < -2.0F ? mapVoid.getBiome(biomeX << 2, biomeZ << 2) : mapLand.getBiome(biomeX << 2, biomeZ << 2);
		if (biomeX == 0 && biomeZ == 0) {
			mapLand.clearCache();
			mapVoid.clearCache();
		}
		return biomeRegistry.getOrThrow(BiomeRegistry.getBiomeKey(netherBiome));
	}

	@Override
	public BiomeSource withSeed(long seed) {
		return new BetterEndBiomeSource(biomeRegistry, seed);
	}

	@Override
	protected Codec<? extends BiomeSource> getCodec() {
		return CODEC;
	}

	public static void register() {
		Registry.register(Registry.BIOME_SOURCE, BetterEnd.makeID("better_end_biome_source"), CODEC);
	}
}
