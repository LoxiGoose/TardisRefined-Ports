package whocraft.tardis_refined.common.tardis.themes.console;

import net.minecraft.world.entity.EntityDimensions;
import org.joml.Vector3f;
import whocraft.tardis_refined.common.tardis.control.ControlSpecification;
import whocraft.tardis_refined.registry.TRControlRegistry;

public class MystConsoleTheme extends ConsoleThemeDetails {

    @Override
    public ControlSpecification[] getControlSpecification() {
        return new ControlSpecification[]{
                new ControlSpecification(TRControlRegistry.THROTTLE, new Vector3f(-0.39f, 0.59f, 1.04f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.X, new Vector3f(-0.86f, 0.63f, -0.59f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.Y, new Vector3f(-0.97f, 0.63f, -0.56f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.Z, new Vector3f(-0.96f, 0.63f, -0.44f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.INCREMENT, new Vector3f(-0.39f, 0.50f, -1.07f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.ROTATE, new Vector3f(-0.02f, 0.69f, -1.10f), EntityDimensions.scalable(0.12f, 0.13f)),
                new ControlSpecification(TRControlRegistry.RANDOM, new Vector3f(1.14f, 0.56f, 0.17f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.DOOR_TOGGLE, new Vector3f(1.09f, 0.50f, 0.67f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.MONITOR, new Vector3f(0.71f, 0.56f, 0.38f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.DIMENSION, new Vector3f(0.47f, 0.58f, 1.08f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.FAST_RETURN, new Vector3f(0.33f, 0.63f, 0.95f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.63f, 0.79f, 0.35f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.88f, 0.69f, 0.81f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.69f, 0.88f, -0.44f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.94f, 0.69f, -0.29f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-1.14f, 0.59f, -0.37f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.71f, 0.66f, -0.81f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.93f, 0.60f, -0.56f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.FUEL, new Vector3f(0.76f, 0.51f, 0.90f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.01f, 0.58f, 1.06f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.94f, 0.74f, 0.53f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-1.01f, 0.53f, 0.57f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.MONITOR, new Vector3f(-0.08f, 0.85f, -0.77f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.ROTATE, new Vector3f(0.78f, 0.63f, -0.87f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.ROTATE, new Vector3f(0.59f, 0.80f, -0.38f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.HANDBRAKE, new Vector3f(-1.01f, 0.56f, 0.09f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.READOUT, new Vector3f(-0.02f, 0.69f, -1.09f), EntityDimensions.scalable(0.12f, 0.13f))
        };
    }

}
