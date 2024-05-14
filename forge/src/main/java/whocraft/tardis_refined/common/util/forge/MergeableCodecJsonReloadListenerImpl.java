package whocraft.tardis_refined.common.util.forge;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.OnDatapackSyncEvent;
import whocraft.tardis_refined.common.network.MessageS2C;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.util.MergeableCodecJsonReloadListener;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class MergeableCodecJsonReloadListenerImpl {

    public static <RAW, PROCESSED> MergeableCodecJsonReloadListener<RAW, PROCESSED> create(String folderName, Codec<RAW> codec, final Function<List<RAW>, PROCESSED> merger) {
        return new Impl<RAW, PROCESSED>(folderName, codec, merger);
    }

    public static class Impl<RAW, PROCESSED> extends MergeableCodecJsonReloadListener<RAW, PROCESSED> {
        public Impl(String folderName, Codec<RAW> codec, final Function<List<RAW>, PROCESSED> merger) {
            super(folderName, codec, merger);
        }

        @Override
        public MergeableCodecJsonReloadListener setSyncPacket(NetworkManager networkManager, Function packetFactory) {
            MinecraftForge.EVENT_BUS.addListener(this.getDatapackSyncListener(networkManager, packetFactory));
            return this;
        }

        /**
         * Generate an event listener function for Forge's dedicated on-datapack-sync event which is timed at the correct point when datapack registries are synced.
         * The event is fired when a player logs in or if server resources were reloaded successfully, so there is no need to add it in the login event
         **/
        private Consumer<OnDatapackSyncEvent> getDatapackSyncListener(final NetworkManager networkManager, final Function<Map<ResourceLocation, PROCESSED>, MessageS2C> packetFactory) {
            return event -> {
                this.handleSyncPacket(event.getPlayer(), networkManager, packetFactory);
            };
        }
    }

}