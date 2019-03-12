package xyz.dreeks.weirdadditions.tileentities;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TEPartyRender extends TileEntity implements ITickable {

    public float yaw = 0.0f;

    @Override
    public void update() {

        this.yaw += .01f;

        if (yaw > 1.0f) yaw = .0f;

    }
}
