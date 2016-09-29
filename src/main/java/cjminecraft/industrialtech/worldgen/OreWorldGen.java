package cjminecraft.industrialtech.worldgen;

import java.util.Random;

import cjminecraft.industrialtech.blocks.BlockOre;
import cjminecraft.industrialtech.init.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreWorldGen implements IWorldGenerator{
	
	//World Gens
	private WorldGenerator gen_ore_copper;
	private WorldGenerator gen_ore_silver;
	private WorldGenerator gen_ore_tin;
	private WorldGenerator gen_ore_zinc;
	private WorldGenerator gen_ore_lead;
	private WorldGenerator gen_ore_nickel;
	
	public OreWorldGen() {
		this.gen_ore_copper = new WorldGenMinable(ModBlocks.ore.getDefaultState().withProperty(BlockOre.TYPE, BlockOre.OreType.COPPER), 8);
		this.gen_ore_silver = new WorldGenMinable(ModBlocks.ore.getDefaultState().withProperty(BlockOre.TYPE, BlockOre.OreType.SILVER), 8);
		this.gen_ore_tin = new WorldGenMinable(ModBlocks.ore.getDefaultState().withProperty(BlockOre.TYPE, BlockOre.OreType.TIN), 8);
		this.gen_ore_zinc = new WorldGenMinable(ModBlocks.ore.getDefaultState().withProperty(BlockOre.TYPE, BlockOre.OreType.ZINC), 8);
		this.gen_ore_lead = new WorldGenMinable(ModBlocks.ore.getDefaultState().withProperty(BlockOre.TYPE, BlockOre.OreType.LEAD), 8);
		this.gen_ore_nickel = new WorldGenMinable(ModBlocks.ore.getDefaultState().withProperty(BlockOre.TYPE, BlockOre.OreType.NICKEL), 8);
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
	    if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
	        throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

	    int heightDiff = maxHeight - minHeight + 1;
	    for (int i = 0; i < chancesToSpawn; i ++) {
	        int x = chunk_X * 16 + rand.nextInt(16);
	        int y = minHeight + rand.nextInt(heightDiff);
	        int z = chunk_Z * 16 + rand.nextInt(16);
	        generator.generate(world, rand, new BlockPos(x, y, z));
	    }
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
	    switch (world.provider.getDimension()) {
	    case 0: //Overworld
	    	this.runGenerator(this.gen_ore_copper, world, random, chunkX, chunkZ, 20, 0, 64);
	    	this.runGenerator(this.gen_ore_silver, world, random, chunkX, chunkZ, 20, 0, 64);
	    	this.runGenerator(this.gen_ore_tin, world, random, chunkX, chunkZ, 20, 0, 64);
	    	this.runGenerator(this.gen_ore_zinc, world, random, chunkX, chunkZ, 20, 0, 64);
	    	this.runGenerator(this.gen_ore_lead, world, random, chunkX, chunkZ, 20, 0, 64);
	    	this.runGenerator(this.gen_ore_nickel, world, random, chunkX, chunkZ, 20, 0, 64);
	        break;
	    case -1: //Nether

	        break;
	    case 1: //End

	        break;
	    }
	}

}
