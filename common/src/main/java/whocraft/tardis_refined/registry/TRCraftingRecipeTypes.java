package whocraft.tardis_refined.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import whocraft.tardis_refined.TardisRefined;

public class TRCraftingRecipeTypes {

    public static final DeferredRegistry<RecipeType<?>> RECIPE_TYPES = DeferredRegistry.create(TardisRefined.MODID, Registries.RECIPE_TYPE);


    /** Need a custom registry method as vanilla will register our entries under the minecraft namspace which creates a duplicate entry
     * We don't want that to happen so we have a custom method*/
    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final ResourceLocation name) {
        final String toString = name.toString();
        return new RecipeType<T>() {
            @Override
            public String toString() {
                return toString;
                }
        };
    }

}
