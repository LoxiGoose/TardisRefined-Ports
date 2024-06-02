package whocraft.tardis_refined.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorCraftingRecipeSerializer;

public class TRCraftingRecipeSerializers {

    public static final DeferredRegistry<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegistry.create(TardisRefined.MODID, Registries.RECIPE_SERIALIZER);

    public static final RegistrySupplier<ManipulatorCraftingRecipeSerializer> ASTRAL_MANIPULATOR = RECIPE_SERIALIZERS.register("astral_manipulator", () -> new ManipulatorCraftingRecipeSerializer());

}
