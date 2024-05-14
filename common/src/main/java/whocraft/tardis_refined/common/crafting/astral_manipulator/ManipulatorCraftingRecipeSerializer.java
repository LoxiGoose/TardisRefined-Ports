package whocraft.tardis_refined.common.crafting.astral_manipulator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import whocraft.tardis_refined.TardisRefined;

import java.util.List;

/** The recipe serialiser implementation.
 * <br> Making this allows vanilla to automatically add our recipe types onto its recipe packet entry and reload listener
 * */
public class ManipulatorCraftingRecipeSerializer implements RecipeSerializer<ManipulatorCraftingRecipe> {

    public static ResourceLocation SERIALIZER_ID = new ResourceLocation(TardisRefined.MODID, "astral_manipulator");

    public ManipulatorCraftingRecipeSerializer(){

    }

    @Override
    public ManipulatorCraftingRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
        return ManipulatorCraftingRecipe.CODEC.parse(NbtOps.INSTANCE, friendlyByteBuf.readNbt()).resultOrPartial(TardisRefined.LOGGER::error).get();
    }

    @Override
    public ManipulatorCraftingRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
        ManipulatorCraftingResult result = Util.getOrThrow(ManipulatorCraftingResult.RESULT_CODEC
                .decode(new Dynamic<>(JsonOps.INSTANCE, jsonObject.get("result"))), JsonParseException::new).getFirst();
        List<ManipulatorCraftingIngredient> ingredients =
                Util.getOrThrow(ManipulatorCraftingIngredient.CODEC.listOf()
                        .decode(new Dynamic<>(JsonOps.INSTANCE, jsonObject.get("ingredients"))), JsonParseException::new)
                        .getFirst();

        return new ManipulatorCraftingRecipe(ingredients, result);
    }

    @Override
    public void toNetwork(FriendlyByteBuf friendlyByteBuf, ManipulatorCraftingRecipe recipe) {
        friendlyByteBuf.writeNbt((CompoundTag) ManipulatorCraftingRecipe.CODEC.encodeStart(NbtOps.INSTANCE, recipe).result().orElse(new CompoundTag()));
    }
}
