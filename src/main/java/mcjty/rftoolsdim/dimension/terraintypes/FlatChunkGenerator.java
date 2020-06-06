package mcjty.rftoolsdim.dimension.terraintypes;

import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.jigsaw.JigsawJunction;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;

import java.util.List;

public class FlatChunkGenerator extends BaseChunkGenerator<FlatChunkGenerator.Config> {

    public FlatChunkGenerator(IWorld world, BiomeProvider biomeProvider) {
        super(world, biomeProvider, 4, 8, 256, new Config(), true);  // @todo configurable settings?
        this.randomSeed.skip(2620);
    }

    @Override
    public int getGroundHeight() {
        return world.getSeaLevel()+1;
    }

    @Override
    protected void makeBaseInternal(IWorld world, int seaLevel,
                                    ObjectList<AbstractVillagePiece> villagePieces, ObjectList<JigsawJunction> jigsawJunctions,
                                    int chunkX, int chunkZ, int x, int z,
                                    List<BlockState> baseBlocks, ChunkPrimer primer,
                                    Heightmap heightmapOceanFloor, Heightmap heightmapWorldSurface) {
        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
//                int realx = chunkpos.x * 16 + x;
//                int realz = chunkpos.z * 16 + z;
                int height = seaLevel+1;
                for (int y = 1 ; y < height ; y++) {
                    primer.setBlockState(pos.setPos(x, y, z), baseBlocks.get(world.getRandom().nextInt(baseBlocks.size())), false);
                }
            }
        }

    }

    @Override
    protected double[] getBiomeNoiseColumn(int noiseX, int noiseZ) {
        return new double[0];
    }

    @Override
    protected void fillNoiseColumn(double[] noiseColumn, int noiseX, int noiseZ) {

    }

    @Override
    public int func_222529_a(int p_222529_1_, int p_222529_2_, Heightmap.Type heightmapType) {
        // @todo
//        DimensionInformation info = DimensionManager.get(world.getWorld()).getDimensionInformation(world);
//        List<BlockState> baseBlocks = info.getBaseBlocks();
//
//        for(int i = ablockstate.length - 1; i >= 0; --i) {
//            BlockState blockstate = ablockstate[i];
//            if (blockstate != null && heightmapType.getHeightLimitPredicate().test(blockstate)) {
//                return i + 1;
//            }
//        }
        return 0;
    }

    public static class Config extends GenerationSettings {

        public static Config createDefault() {
            Config config = new Config();
            config.setDefaultBlock(Blocks.DIAMOND_BLOCK.getDefaultState());
            config.setDefaultFluid(Blocks.LAVA.getDefaultState());
            return config;
        }

    }

}