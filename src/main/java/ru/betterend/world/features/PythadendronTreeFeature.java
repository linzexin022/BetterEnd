package ru.betterend.world.features;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import ru.betterend.noise.OpenSimplexNoise;
import ru.betterend.registry.BlockRegistry;
import ru.betterend.registry.BlockTagRegistry;
import ru.betterend.util.MHelper;
import ru.betterend.util.SplineHelper;
import ru.betterend.util.sdf.SDF;
import ru.betterend.util.sdf.operator.SDFDisplacement;
import ru.betterend.util.sdf.operator.SDFScale3D;
import ru.betterend.util.sdf.operator.SDFSubtraction;
import ru.betterend.util.sdf.operator.SDFTranslate;
import ru.betterend.util.sdf.primitive.SDFSphere;

public class PythadendronTreeFeature extends DefaultFeature {
	private static final Function<BlockState, Boolean> REPLACE;
	
	@Override
	public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		if (world.getBlockState(pos.down()).getBlock() != BlockRegistry.CHORUS_NYLIUM) return false;
		
		float size = MHelper.randRange(10, 20, random);
		List<Vector3f> spline = SplineHelper.makeSpline(0, 0, 0, 0, size, 0, 4);
		SplineHelper.offsetParts(spline, random, 0.7F, 0, 0.7F);
		Vector3f last = spline.get(spline.size() - 1);
		
		int depth = MHelper.floor((size - 10F) * 3F / 10F + 1F);
		float bsize = (10F - (size - 10F)) / 10F + 1.5F;
		branch(last.getX(), last.getY(), last.getZ(), size * bsize, MHelper.randRange(0, MHelper.PI2, random), random, depth, world, pos);
		
		SDF function = SplineHelper.buildSDF(spline, 1.7F, 1.1F, (bpos) -> {
			return BlockRegistry.PYTHADENDRON.bark.getDefaultState();
		});
		
		function.setReplaceFunction(REPLACE);
		function.fillRecursive(world, pos);
		
		return true;
	}
	
	private void branch(float x, float y, float z, float size, float angle, Random random, int depth, StructureWorldAccess world, BlockPos pos) {
		if (depth == 0) return;
		
		float dx = (float) Math.cos(angle) * size * 0.15F;
		float dz = (float) Math.sin(angle) * size * 0.15F;
		
		float x1 = x + dx;
		float z1 = z + dz;
		float x2 = x - dx;
		float z2 = z - dz;
		
		List<Vector3f> spline = SplineHelper.makeSpline(x, y, z, x1, y, z1, 5);
		SplineHelper.powerOffset(spline, size * MHelper.randRange(1.0F, 2.0F, random), 4);
		SplineHelper.offsetParts(spline, random, 0.3F, 0, 0.3F);
		Vector3f pos1 = spline.get(spline.size() - 1);
		
		boolean s1 = SplineHelper.fillSpline(spline, world, BlockRegistry.PYTHADENDRON.bark.getDefaultState(), pos, REPLACE);
		
		spline = SplineHelper.makeSpline(x, y, z, x2, y, z2, 5);
		SplineHelper.powerOffset(spline, size * MHelper.randRange(1.0F, 2.0F, random), 4);
		SplineHelper.offsetParts(spline, random, 0.3F, 0, 0.3F);
		Vector3f pos2 = spline.get(spline.size() - 1);
		
		boolean s2 = SplineHelper.fillSpline(spline, world, BlockRegistry.PYTHADENDRON.bark.getDefaultState(), pos, REPLACE);
		
		OpenSimplexNoise noise = new OpenSimplexNoise(random.nextInt());
		if (depth < 3) {
			if (s1) {
				float radius = MHelper.randRange(4.5F, 6.5F, random);
				SDF sphere = new SDFSphere().setRadius(radius).setBlock(BlockRegistry.PYTHADENDRON_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 1));
				sphere = new SDFScale3D().setScale(1, 0.6F, 1).setSource(sphere);
				sphere = new SDFDisplacement().setFunction((vec) -> { return (float) noise.eval(vec.getX() * 0.2, vec.getY() * 0.2, vec.getZ() * 0.2) * 3; }).setSource(sphere);
				sphere = new SDFDisplacement().setFunction((vec) -> { return random.nextFloat() * 3F - 1.5F; }).setSource(sphere);
				sphere = new SDFSubtraction().setSourceA(sphere).setSourceB(new SDFTranslate().setTranslate(0, -radius, 0).setSource(sphere));
				sphere.fillRecursive(world, new BlockPos(pos1.getX() + pos.getX(), pos1.getY() + pos.getY() + 1, pos1.getZ() + pos.getZ()));
			}
			if (s2) {
				float radius = MHelper.randRange(4.5F, 6.5F, random);
				SDF sphere = new SDFSphere().setRadius(radius).setBlock(BlockRegistry.PYTHADENDRON_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 1));
				sphere = new SDFScale3D().setScale(1, 0.6F, 1).setSource(sphere);
				sphere = new SDFDisplacement().setFunction((vec) -> { return (float) noise.eval(vec.getX() * 0.2, vec.getY() * 0.2, vec.getZ() * 0.2) * 3; }).setSource(sphere);
				sphere = new SDFDisplacement().setFunction((vec) -> { return random.nextFloat() * 3F - 1.5F; }).setSource(sphere);
				sphere = new SDFSubtraction().setSourceA(sphere).setSourceB(new SDFTranslate().setTranslate(0, -radius, 0).setSource(sphere));
				sphere.fillRecursive(world, new BlockPos(pos2.getX() + pos.getX(), pos2.getY() + pos.getY() + 1, pos2.getZ() + pos.getZ()));
			}
		}
		
		float size1 = size * MHelper.randRange(0.75F, 0.95F, random);
		float size2 = size * MHelper.randRange(0.75F, 0.95F, random);
		float angle1 = angle + (float) Math.PI * 0.5F + MHelper.randRange(-0.1F, 0.1F, random);
		float angle2 = angle + (float) Math.PI * 0.5F + MHelper.randRange(-0.1F, 0.1F, random);
		
		if (s1) {
			branch(pos1.getX(), pos1.getY(), pos1.getZ(), size1, angle1, random, depth - 1, world, pos);
		}
		if (s2) {
			branch(pos2.getX(), pos2.getY(), pos2.getZ(), size2, angle2, random, depth - 1, world, pos);
		}
	}
	
	static {
		REPLACE = (state) -> {
			if (state.isIn(BlockTagRegistry.END_GROUND)) {
				return true;
			}
			if (state.getBlock() == BlockRegistry.PYTHADENDRON_LEAVES) {
				return true;
			}
			if (state.getMaterial().equals(Material.PLANT)) {
				return true;
			}
			return state.getMaterial().isReplaceable();
		};
	}
}
