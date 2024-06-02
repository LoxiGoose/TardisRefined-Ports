package whocraft.tardis_refined.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.constants.NbtConstants;
import whocraft.tardis_refined.registry.TRControlRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyItem extends Item {

    public KeyItem(Properties properties) {
        super(properties);
    }

    public static ItemStack addTardis(ItemStack itemStack, ResourceKey<Level> levelResourceKey) {
        // Get the tag of the itemStack object
        CompoundTag itemtag = itemStack.getOrCreateTag();

        StringTag dim = StringTag.valueOf(levelResourceKey.location().toString());

        ListTag keychain;
        if (!itemtag.contains(NbtConstants.KEYCHAIN, CompoundTag.TAG_LIST)) {
            // Create a new keychain tag and add it to the itemtag object
            keychain = new ListTag();
            itemtag.put(NbtConstants.KEYCHAIN, keychain);
        } else {
            // Get the existing keychain tag from the itemtag object
            keychain = itemtag.getList(NbtConstants.KEYCHAIN, Tag.TAG_STRING);
        }

        // Add dim to the keychain, whether it already exists in the keychain
        keychain.add(dim);

        itemStack.setTag(itemtag);
        return itemStack;
    }

    public static void setKeychain(ItemStack itemStack, ArrayList<ResourceKey<Level>> levels) {
        CompoundTag nbt = itemStack.getOrCreateTag();
        ListTag keychain;
        if (!nbt.contains(NbtConstants.KEYCHAIN, CompoundTag.TAG_LIST)) {
            // Create a new keychain tag and add it to the itemtag object
            keychain = new ListTag();
            nbt.put(NbtConstants.KEYCHAIN, keychain);
        } else {
            // Get the existing keychain tag from the itemtag object
            keychain = nbt.getList(NbtConstants.KEYCHAIN, Tag.TAG_STRING);
        }

        keychain.clear();

        levels.forEach(level -> {
            keychain.add(StringTag.valueOf(level.location().toString()));
        });

        itemStack.setTag(nbt);
    }

    public static ArrayList<ResourceKey<Level>> getKeychain(ItemStack itemStack) {
        CompoundTag nbt = itemStack.getOrCreateTag();


        if (!nbt.contains(NbtConstants.KEYCHAIN)) {
            return new ArrayList<>();
        }

        ListTag keychain = nbt.getList(NbtConstants.KEYCHAIN, Tag.TAG_STRING);

        ArrayList<ResourceKey<Level>> levels = new ArrayList<>();

        for (Tag tag : keychain) {
            String string = tag.getAsString();
            ResourceKey<Level> key = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(string));
            levels.add(key);
        }

        // Return the list of ResourceKeys
        return levels;
    }

    public static boolean keychainContains(ItemStack itemStack, ResourceKey<Level> levelResourceKey) {
        ArrayList<ResourceKey<Level>> keychain = getKeychain(itemStack);
        return keychain.contains(levelResourceKey);
    }

    @Override
    public Component getName(ItemStack itemStack) {

        if (getKeychain(itemStack).size() >= 2) {
            return Component.translatable(ModMessages.ITEM_KEYCHAIN);
        }

        return super.getName(itemStack);
    }

    public boolean interactMonitor(ItemStack itemStack, Player player, ControlEntity control, InteractionHand interactionHand) {

        if (control.level() instanceof ServerLevel serverLevel) {
            ResourceKey<Level> tardis = serverLevel.dimension();
            if (control.controlSpecification().control() != null) {
                if (control.controlSpecification().control() == TRControlRegistry.MONITOR.get()) {

                    setKeychain(itemStack, new ArrayList<>(List.of(serverLevel.dimension())));


                    if (keychainContains(itemStack, tardis)) {
                        return false;
                    }

                    player.setItemInHand(interactionHand, addTardis(itemStack, tardis));
                    PlayerUtil.sendMessage(player, Component.translatable(ModMessages.MSG_KEY_BOUND, tardis.location().getPath()), true);
                    player.playSound(SoundEvents.PLAYER_LEVELUP, 1, 0.5F);


                    return true;
                }
            }
        }

        return false;
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {

        if (context.getLevel() instanceof ServerLevel) {
            if (context.getPlayer().isShiftKeyDown()) {
                var keychain = getKeychain(context.getItemInHand());
                if (!keychain.isEmpty()) {
                    Collections.rotate(keychain.subList(0, keychain.size()), -1);
                    setKeychain(context.getItemInHand(), keychain);
                    context.getPlayer().displayClientMessage(Component.translatable(ModMessages.MSG_KEY_CYCLED, keychain.get(0).location().getPath()), true);
                    context.getLevel().playSound(null, context.getPlayer().blockPosition(), SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.BLOCKS, 1, 2);
                }
            }
        }


        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);

        ArrayList<ResourceKey<Level>> keychain = KeyItem.getKeychain(itemStack);

        if (!keychain.isEmpty()) {

            ResourceKey<Level> mainTardisLevel = keychain.get(0);

            if (TardisClientData.getInstance(mainTardisLevel).isFlying()) {
                list.add(Component.translatable("* " + ModMessages.TOOLTIP_IN_FLIGHT + " *"));
            }


            list.add(Component.translatable(ModMessages.TOOLTIP_TARDIS_LIST_TITLE));

            for (int i = 0; i < keychain.size(); i++) {
                MutableComponent hyphen = Component.literal((i == 0) ? ChatFormatting.YELLOW + "> " : "- ");
                list.add(hyphen.append(Component.literal(keychain.get(i).location().getPath().substring(0, 5))));
            }


        }
    }
}
