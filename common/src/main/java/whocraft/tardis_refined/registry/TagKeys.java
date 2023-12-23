package whocraft.tardis_refined.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.util.RegistryHelper;

public class TagKeys {
    public static TagKey<Biome> IS_MOUNTAIN_OR_OCEAN = RegistryHelper.makeGenericBiomeTagCollection("is_mountain_or_ocean");
    public static TagKey<Biome> TARDIS_ROOT_CLUSTER = RegistryHelper.makeBiomeTagForFeature("tardis_root_cluster");

    /** Entity tag used to blacklist entities from being teleported into the Tardis via the doors or being landed on by the exterior shell*/
    public static final TagKey<EntityType<?>> TARDIS_TELEPORT_BLACKLIST = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(TardisRefined.MODID, "tardis_teleport_blacklist"));

    public static void init(){}
}