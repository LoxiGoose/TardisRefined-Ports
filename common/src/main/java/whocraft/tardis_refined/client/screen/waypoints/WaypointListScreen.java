package whocraft.tardis_refined.client.screen.waypoints;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.screen.ScreenHelper;
import whocraft.tardis_refined.client.screen.components.CommonTRWidgets;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.client.screen.components.SpriteIconButton;
import whocraft.tardis_refined.client.screen.selections.SelectionScreen;
import whocraft.tardis_refined.common.network.messages.waypoints.C2SOpenCoordinatesDisplayMessage;
import whocraft.tardis_refined.common.network.messages.waypoints.C2SOpenEditCoordinatesDisplayMessage;
import whocraft.tardis_refined.common.network.messages.waypoints.RemoveWaypointEntryMessage;
import whocraft.tardis_refined.common.network.messages.waypoints.TravelToWaypointMessage;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.TardisWaypoint;
import whocraft.tardis_refined.constants.ModMessages;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;


public class WaypointListScreen extends SelectionScreen {

    protected int imageWidth = 256;
    protected int imageHeight = 173;
    private int leftPos, topPos;

    private SpriteIconButton loadButton;
    private SpriteIconButton editButton;
    private SpriteIconButton trashButton;

    public static ResourceLocation MONITOR_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/gui/monitor.png");
    private Collection<TardisWaypoint> WAYPOINTS = new ArrayList<>();
    private TardisWaypoint waypoint = null;
    public static final ResourceLocation TRASH_LOCATION = new ResourceLocation(TardisRefined.MODID, "trash");
    public static final ResourceLocation OKAY_TEXTURE = new ResourceLocation(TardisRefined.MODID, "okay");
    public static final ResourceLocation EDIT_TEXTURE = new ResourceLocation(TardisRefined.MODID, "edit");

    private final Component noWaypointsLabel = Component.translatable(ModMessages.UI_MONITOR_NO_WAYPOINTS);

    public WaypointListScreen(Collection<TardisWaypoint> waypoints) {
        super(Component.translatable(ModMessages.UI_MONITOR_MAIN_TITLE));
        this.WAYPOINTS = waypoints;
    }


    @Override
    public boolean isPauseScreen() {
        return false;
    }



    @Override
    protected void init() {
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        //Super method already creates the list, we don't need to create it a second time.
        super.init();

        setEvents(() -> {
            if(waypoint != null) {
                new TravelToWaypointMessage(waypoint.getId()).send();
                Minecraft.getInstance().setScreen(null);
            }
            Minecraft.getInstance().setScreen(null);
        }, new SelectionScreenRun() {
            @Override
            public void onPress() {
                if (waypoint != null) {
                    new RemoveWaypointEntryMessage(waypoint.getId()).send();
                }
            }
        });


        SpriteIconButton newWaypointButton = this.addRenderableWidget(CommonTRWidgets.imageButton(20, Component.translatable("Submit"), (arg) -> {
            new C2SOpenCoordinatesDisplayMessage(CoordInputType.WAYPOINT).send();
        }, true, BUTTON_LOCATION));

        newWaypointButton.setTooltip(Tooltip.create(Component.translatable(ModMessages.UI_MONITOR_WAYPOINT_CREATE)));
        newWaypointButton.setPosition(width / 2 + 85, (height) / 2 - 60);




        this.loadButton = this.addRenderableWidget(CommonTRWidgets.imageButton(20, Component.translatable("Submit"), (arg) -> {
            if(waypoint != null) {

                new TravelToWaypointMessage(waypoint.getId()).send();
                Minecraft.getInstance().setScreen(null);
            }
        }, true, OKAY_TEXTURE));
        this.loadButton.setTooltip(Tooltip.create(Component.translatable(ModMessages.UI_MONITOR_WAYPOINT_LOAD)));
        this.loadButton.setPosition(width / 2 + 85, (height) / 2 );

        this.loadButton.active = false;


        this.editButton = this.addRenderableWidget(CommonTRWidgets.imageButton(20, Component.translatable("Edit"), (arg) -> {
            if(waypoint != null) {
                new C2SOpenEditCoordinatesDisplayMessage(waypoint.getId()).send();
                Minecraft.getInstance().setScreen(null);
            }
        }, true, EDIT_TEXTURE));
        this.editButton.setTooltip(Tooltip.create(Component.translatable(ModMessages.UI_MONITOR_WAYPOINT_EDIT)));
        this.editButton.setPosition(width / 2 + 85, (height) / 2 - 40);

        this.editButton.active = false;

        this.trashButton = this.addRenderableWidget(CommonTRWidgets.imageButton(20, Component.translatable("Submit"), (arg) -> {
            new RemoveWaypointEntryMessage(waypoint.getId()).send();

        }, true, TRASH_LOCATION));

        this.trashButton.setPosition(width / 2 + 85, (height) / 2 - 20);
        this.trashButton.setTooltip(Tooltip.create(Component.translatable(ModMessages.UI_MONITOR_WAYPOINT_DELETE)));
        this.trashButton.active = false;
    }


    @Override
    public GenericMonitorSelectionList createSelectionList() {
        int leftPos = this.width / 2 - 100;
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 250, 80, leftPos - 70, this.topPos + 45, this.topPos + this.imageHeight - 45 , 12);
        selectionList.setRenderBackground(false);

        for (TardisWaypoint waypointEntry : WAYPOINTS) {
            selectionList.children().add(new SelectionListEntry(Component.literal(waypointEntry.getLocation().getName()), entry -> {
                entry.setChecked(true);
                this.waypoint = waypointEntry;
                this.loadButton.active = true;
                this.editButton.active = true;
                this.trashButton.active = true;

                for (SelectionListEntry child : selectionList.children()) {
                    if(child != entry) {
                        child.setChecked(false);
                    }
                }

            }, leftPos));
        }

        return selectionList;
    }


//    @Override
//    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
//        // super.renderBackground(guiGraphics, i, j, f);
//    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {

        int textOffset = height / 2 - 60;
        int textScale = 40;

        guiGraphics.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);

        guiGraphics.blit(MONITOR_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        ScreenHelper.renderWidthScaledText(Component.translatable(ModMessages.UI_MONITOR_WAYPOINTS).getString(), guiGraphics, Minecraft.getInstance().font, width / 2 - 96, textOffset, Color.LIGHT_GRAY.getRGB(), textScale * 2, 1F, false);

        if (WAYPOINTS.isEmpty()) {
            ScreenHelper.renderWidthScaledText(noWaypointsLabel.getString(), guiGraphics, Minecraft.getInstance().font, width / 2 - 96, textOffset + 15, Color.LIGHT_GRAY.getRGB(), textScale * 2, 1F, false);
        }

        super.render(guiGraphics, i, j, f);

    }
}
