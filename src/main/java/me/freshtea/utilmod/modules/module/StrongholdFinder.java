package me.freshtea.utilmod.modules.module;

import com.google.common.collect.Sets;
import me.freshtea.utilmod.modules.Module;
import me.freshtea.utilmod.util.MathUtil;
import me.freshtea.utilmod.util.MsgUtil;
import me.freshtea.utilmod.util.Pair;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;

public class StrongholdFinder extends Module {

    private Map<Entity, Pair<Vec3d>> eyeData = new HashMap<>();

    public StrongholdFinder() {
        super(Sets.newHashSet("strongholdfinder", "eyespy"), 0, false);

        ClientEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (isActive() && entity instanceof EyeOfEnderEntity)
                eyeData.put(entity, new Pair<>(entity.getPos(), null));
        });

        ClientEntityEvents.ENTITY_UNLOAD.register(((entity, world) -> {
            if (isActive() && entity instanceof EyeOfEnderEntity) {
                Pair<Vec3d> pair = eyeData.get(entity);

                if (pair != null)
                    pair.setSecond(entity.getPos());

                Object[] measurements = eyeData.values().stream().filter(p -> p.getFirst() != null && p.getSecond() != null).toArray();

                if (measurements.length > 1) {
                    Pair<Vec3d> firstMeasurement = (Pair<Vec3d>) measurements[0];
                    Pair<Vec3d> secondMeasurement = (Pair<Vec3d>) measurements[1];

                    Pair<Double> point = MathUtil.intersection(
                            firstMeasurement.getFirst().x,
                            firstMeasurement.getFirst().z,
                            firstMeasurement.getSecond().x,
                            firstMeasurement.getSecond().z,
                            secondMeasurement.getFirst().x,
                            secondMeasurement.getFirst().z,
                            secondMeasurement.getSecond().x,
                            secondMeasurement.getSecond().z
                    );

                    if (point == null) {
                        MsgUtil.sendMessage("Failed to intersect, please try another combination of angles.");
                    } else {
                        MsgUtil.sendMessage(String.format("Coordinates: (x=%f, z=%f)", point.getFirst(), point.getSecond()));
                    }

                    eyeData.clear();
                }
            }
        }));
    }

    @Override
    public void onEnable() { }

    @Override
    public void onDisable() {}

}
