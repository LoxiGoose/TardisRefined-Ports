package whocraft.tardis_refined.common.tardis.themes;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.CustomRegistry;
import whocraft.tardis_refined.registry.DeferredRegistry;
import whocraft.tardis_refined.registry.RegistrySupplier;

public class ShellTheme implements Theme {


    /** Tardis Refined instance of the ShellTheme registry. Addon Mods: DO NOT USE THIS, it is only for Tardis Refined use only*/
    public static final CustomRegistry<ShellTheme> SHELL_THEMES = CustomRegistry.create(ShellTheme.class, new ResourceLocation(TardisRefined.MODID, "shell_theme"));

    /** Global instance of the Shell Theme custom registry created by Tardis Refined*/
    public static final DeferredRegistry<ShellTheme> SHELL_THEME_REGISTRY = DeferredRegistry.create(TardisRefined.MODID, SHELL_THEMES);

    public static final RegistrySupplier<ShellTheme> HALF_BAKED = registerShellTheme("half_baked"); // The default shell. Do not remove.

    public static final RegistrySupplier<ShellTheme> FACTORY = registerShellTheme("factory");
    public static final RegistrySupplier<ShellTheme> POLICE_BOX = registerShellTheme("police_box", true);
    public static final RegistrySupplier<ShellTheme> PHONE_BOOTH = registerShellTheme("phone_booth", true);
    public static final RegistrySupplier<ShellTheme> MYSTIC = registerShellTheme("mystic", true);
    public static final RegistrySupplier<ShellTheme> PRESENT = registerShellTheme("present");
    public static final RegistrySupplier<ShellTheme> DRIFTER = registerShellTheme("drifter");
    public static final RegistrySupplier<ShellTheme> VENDING = registerShellTheme("vending", true);
    public static final RegistrySupplier<ShellTheme> BRIEFCASE = registerShellTheme("briefcase");
    public static final RegistrySupplier<ShellTheme> GROENING = registerShellTheme("groening", true);
    public static final RegistrySupplier<ShellTheme> BIG_BEN = registerShellTheme("big_ben", true);
    public static final RegistrySupplier<ShellTheme> NUKA = registerShellTheme("nuka", true);
    public static final RegistrySupplier<ShellTheme> GROWTH = registerShellTheme("growth");
    public static final RegistrySupplier<ShellTheme> PORTALOO = registerShellTheme("portaloo");
    public static final RegistrySupplier<ShellTheme> PAGODA = registerShellTheme("pagoda");
    public static final RegistrySupplier<ShellTheme> LIFT = registerShellTheme("lift", true);
    public static final RegistrySupplier<ShellTheme> HIEROGLYPH = registerShellTheme("hieroglyph");
    public static final RegistrySupplier<ShellTheme> CASTLE = registerShellTheme("castle");
    public static final RegistrySupplier<ShellTheme> PATHFINDER = registerShellTheme("pathfinder");


    public static ShellTheme getShellTheme(ResourceLocation resourceLocation){
        ShellTheme potentialTheme = SHELL_THEMES.get(resourceLocation);
        if(potentialTheme != null){
            return potentialTheme;
        }
        return HALF_BAKED.get();
    }

    public static ResourceLocation getKey(ShellTheme shellTheme){
        return SHELL_THEMES.getKey(shellTheme);
    }

    private ResourceLocation translationKey;
    private boolean producesLight;

    public ShellTheme(ResourceLocation translationKey) {
        this.translationKey = translationKey;
    }
    public ShellTheme(ResourceLocation translationKey, boolean producesLight) {
        this.translationKey = translationKey;
        this.producesLight = producesLight;
    }

    private static RegistrySupplier<ShellTheme> registerShellTheme(String id){
        return SHELL_THEME_REGISTRY.register(id,  () -> new ShellTheme(new ResourceLocation(TardisRefined.MODID, id)));
    }

    private static RegistrySupplier<ShellTheme> registerShellTheme(String id, boolean producesLight){
        return SHELL_THEME_REGISTRY.register(id,  () -> new ShellTheme(new ResourceLocation(TardisRefined.MODID, id), producesLight));
    }

    @Override
    public String getTranslationKey() {
        return Util.makeDescriptionId("shell", this.translationKey);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getTranslationKey());
    }

    public boolean producesLight() {
        return producesLight;
    }

}
