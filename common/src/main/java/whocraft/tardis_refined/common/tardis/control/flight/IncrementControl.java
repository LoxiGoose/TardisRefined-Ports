package whocraft.tardis_refined.common.tardis.control.flight;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.capability.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.control.IControl;

public class IncrementControl implements IControl {

    @Override
    public void onRightClick(TardisLevelOperator operator, ControlEntity controlEntity, Player player) {
        operator.getControlManager().cycleCordIncrement(1);
        int incrm = operator.getControlManager().getCordIncrement();
        player.displayClientMessage(Component.translatable("x" + incrm), true);
    }

    @Override
    public void onLeftClick(TardisLevelOperator operator, ControlEntity controlEntity, Player player) {
        operator.getControlManager().cycleCordIncrement(-1);
        int incrm = operator.getControlManager().getCordIncrement();
        player.displayClientMessage(Component.translatable("x" + incrm), true);
    }
}
