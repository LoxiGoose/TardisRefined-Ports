package whocraft.tardis_refined.common.world.chunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import whocraft.tardis_refined.common.world.ChunkGenerators;
import whocraft.tardis_refined.constants.TardisGeneration;
import whocraft.tardis_refined.registry.ARSStructurePieceRegistry;
import whocraft.tardis_refined.registry.BlockRegistry;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class TardisChunkGenerator extends ChunkGenerator {
    public static final Codec<TardisChunkGenerator> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(RegistryOps.retrieveElement(ChunkGenerators.TARDIS_BIOME)).apply(instance,
                    instance.stable(TardisChunkGenerator::new)));


    public final Random random;

    // Some parameter values.
    public final int distanceBetweenRooms = 18;
    public final int arsChunkSize = ARSStructurePiece.LOCKED_PIECE_CHUNK_SIZE;
    private final int chunkSize = 16;



    public TardisChunkGenerator(Holder<Biome> holder) {
        super(new FixedBiomeSource(holder));
        this.random = new Random();
    }

    @Override
    public void applyBiomeDecoration(WorldGenLevel pLevel, ChunkAccess pChunk, StructureManager pStructureManager) {

        if (pChunk.getPos().x > -25 && pChunk.getPos().x < 25 && pChunk.getPos().z > -25 && pChunk.getPos().z < 25 ) {
            return;
        }


        if(pChunk.getPos().x % arsChunkSize == 0 && pChunk.getPos().z % arsChunkSize == 0){

           if ( random.nextBoolean()) {
               ResourceLocation pieceToPlace = getRandomRoomPiece().getResourceLocation();
               placePieceInWorld(pLevel, pieceToPlace, pChunk, false);
           } else {
               ResourceLocation pieceToPlace = getRandomCorridorPiece().getResourceLocation();
               placePieceInWorld(pLevel, pieceToPlace, pChunk, false);
           }


            if ( random.nextBoolean()) {
                ResourceLocation pieceToPlace = getRandomRoomPiece().getResourceLocation();
                placePieceInWorld(pLevel, pieceToPlace, pChunk, true);
            } else {
                ResourceLocation pieceToPlace = getRandomCorridorPiece().getResourceLocation();
                placePieceInWorld(pLevel, pieceToPlace, pChunk, true);
            }

        }
    }

    @Override
    public void createStructures(RegistryAccess registryAccess, ChunkGeneratorStructureState chunkGeneratorStructureState, StructureManager structureManager, ChunkAccess chunkAccess, StructureTemplateManager structureTemplateManager) {
        // super.createStructures(registryAccess, chunkGeneratorStructureState, structureManager, chunkAccess, structureTemplateManager);
    }

    @Override
    public void createReferences(WorldGenLevel pLevel, StructureManager pStructureManager, ChunkAccess pChunk) {
        //super.createReferences(pLevel, pStructureManager, pChunk);
    }

    @Override
    public Codec<TardisChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void applyCarvers(WorldGenRegion p_223043_, long p_223044_, RandomState p_223045_, BiomeManager p_223046_, StructureManager p_223047_, ChunkAccess p_223048_, GenerationStep.Carving p_223049_) {
    }


    @Override
    public void buildSurface(WorldGenRegion level, StructureManager structureManager, RandomState random, ChunkAccess chunk) {

    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion p_62167_) {}

    @Override
    public int getGenDepth() {
        return 384;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender p_223210_, RandomState p_223211_, StructureManager p_223212_, ChunkAccess access) {

        // Flatworlds appear to use this function instead of the surface.
        BlockPos cornerPos = new BlockPos(access.getPos().getMinBlockX(), TardisGeneration.TARDIS_ROOT_GENERATION_MIN_HEIGHT -5, access.getPos().getMinBlockZ());
        BlockPos lastCornerPos = new BlockPos(access.getPos().getMaxBlockX(), TardisGeneration.TARDIS_ROOT_GENERATION_MAX_HEIGHT + 5, access.getPos().getMaxBlockZ());
        for (BlockPos pos : BlockPos.betweenClosed(cornerPos, lastCornerPos)) {

            if (pos.getY() <= TardisGeneration.TARDIS_ROOT_GENERATION_MIN_HEIGHT || pos.getY() > TardisGeneration.TARDIS_ROOT_GENERATION_MAX_HEIGHT ) {
                access.setBlockState(pos, Blocks.BEDROCK.defaultBlockState(), false);
            } else {

                access.setBlockState(pos, BlockRegistry.FOOLS_STONE.get().defaultBlockState(), false);
            }
        }



        return CompletableFuture.completedFuture(access);
    }

    @Override
    public int getSeaLevel() {
        return -63;
    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getBaseHeight(int p_223032_, int p_223033_, Heightmap.Types p_223034_, LevelHeightAccessor p_223035_, RandomState p_223036_) {
        return 0;
    }

    @Override
    public NoiseColumn getBaseColumn(int p_223028_, int p_223029_, LevelHeightAccessor level, RandomState p_223031_) {

        BlockState[] states = new BlockState[level.getHeight()];
        for(int i = 0; i < states.length; ++i){
            states[i] = Blocks.STONE.defaultBlockState();
        }

        return new NoiseColumn(0, states);
    }

    /**
     * Determines if the chunk is a room chunk
     * @param pos the position of the chunk
     * @return is the chunk a room chunk
     */
    private boolean isChunkAtRoomInterval(ChunkPos pos) {

        System.out.println(pos.x % distanceBetweenRooms);

        return (pos.x % distanceBetweenRooms == 0 || pos.x % distanceBetweenRooms == 3 || pos.x % distanceBetweenRooms == 15 )  && (pos.z % distanceBetweenRooms == 0 || pos.z % distanceBetweenRooms == 3 || pos.z % distanceBetweenRooms == 15 );
    }

    /**
     * Fetch a random corridor piece to populate a chunk
     * @return random corridor ARS piece from the registry.
     */
    private ARSStructurePiece getRandomCorridorPiece() {

        return ARSStructurePieceRegistry.CORRIDORS.get(this.random.nextInt(ARSStructurePieceRegistry.CORRIDORS.size()));
    }


    /**
     * Fetch a random room piece to populate a chunk
     * @return random room ARS piece from the registry.
     */
    private ARSStructurePiece getRandomRoomPiece() {
        return ARSStructurePieceRegistry.ROOMS.get(this.random.nextInt(ARSStructurePieceRegistry.ROOMS.size()));
    }


    private void placePieceInWorld(WorldGenLevel level, ResourceLocation pieceToPlace, ChunkAccess pChunk, boolean isSecondFloor) {
        // Place the desired piece.

        level.getLevel().getStructureManager().get(pieceToPlace).ifPresent(structure -> {

            int height = isSecondFloor ? 125 : 97;

            BlockPos pos = pChunk.getPos().getBlockAt(0, height,0).north(chunkSize).west(chunkSize); // Must be offset to utilize all 3x3 chunks.
            StructurePlaceSettings settings = new StructurePlaceSettings();
            structure.placeInWorld(level, pos, pos, settings, level.getRandom(), 1);
        });
    }



    @Override
    public void addDebugScreenInfo(List<String> p_223175_, RandomState p_223176_, BlockPos p_223177_) {}
}
