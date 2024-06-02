package whocraft.tardis_refined.common.tardis.themes.console;

import net.minecraft.world.entity.EntityDimensions;
import org.joml.Vector3f;
import whocraft.tardis_refined.common.tardis.control.ControlSpecification;
import whocraft.tardis_refined.registry.TRControlRegistry;

public class ToyotaConsoleTheme extends ConsoleThemeDetails {

    @Override
    public ControlSpecification[] getControlSpecification() {
        return new ControlSpecification[]{
                new ControlSpecification(TRControlRegistry.THROTTLE, new Vector3f(0.27f, 0.50f, 0.94f), EntityDimensions.scalable(0.19f, 0.19f)),
                new ControlSpecification(TRControlRegistry.X, new Vector3f(-0.07f, 0.63f, 0.80f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.Y, new Vector3f(0.02f, 0.63f, 0.80f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.Z, new Vector3f(0.11f, 0.63f, 0.80f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.INCREMENT, new Vector3f(-0.72f, 0.71f, 0.14f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.ROTATE, new Vector3f(-1.06f, 0.53f, -0.19f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.RANDOM, new Vector3f(0.58f, 0.57f, -0.74f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.DOOR_TOGGLE, new Vector3f(-0.01f, 0.69f, -0.69f), EntityDimensions.scalable(0.12f, 0.13f)),
                new ControlSpecification(TRControlRegistry.MONITOR, new Vector3f(-0.43f, 1.07f, -0.74f), EntityDimensions.scalable(0.19f, 0.19f)),
                new ControlSpecification(TRControlRegistry.MONITOR, new Vector3f(0.34f, 1.07f, 0.63f), EntityDimensions.scalable(0.19f, 0.19f)),
                new ControlSpecification(TRControlRegistry.DIMENSION, new Vector3f(0.64f, 0.62f, -0.48f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.FAST_RETURN, new Vector3f(-0.65f, 0.53f, -0.86f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.12f, 0.75f, 0.69f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.01f, 0.75f, 0.69f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.13f, 0.75f, 0.69f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.03f, 0.51f, 0.98f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.94f, 0.61f, 0.19f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-1.02f, 0.54f, 0.24f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.FUEL, new Vector3f(1.04f, 0.49f, -0.19f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.73f, 0.49f, -0.82f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.18f, 0.54f, -0.98f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.08f, 0.54f, -0.98f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.05f, 0.54f, -0.98f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.14f, 0.54f, -0.98f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.02f, 0.64f, -0.79f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.13f, 0.64f, -0.79f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.08f, 0.64f, -0.79f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.28f, 0.49f, -1.00f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.27f, 0.49f, -1.00f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.88f, 0.54f, 0.37f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.61f, 0.69f, 0.31f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.61f, 0.69f, 0.57f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.77f, 0.53f, 0.60f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.71f, 0.53f, 0.78f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.READOUT, new Vector3f(0.88f, 0.56f, -0.17f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.HANDBRAKE, new Vector3f(-0.42f, 0.49f, 0.91f), EntityDimensions.scalable(0.25f, 0.25f))
        };
    }

}
