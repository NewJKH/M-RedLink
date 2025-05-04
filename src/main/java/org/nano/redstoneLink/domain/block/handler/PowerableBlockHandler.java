package org.nano.redstoneLink.domain.block.handler;

import org.bukkit.block.Block;
import org.bukkit.block.data.Powerable;

public class PowerableBlockHandler extends AbstractBlockHandler{

    @Override
    protected boolean isSupportedBlock(Block block) {
        return block.getBlockData() instanceof Powerable;
    }

    @Override
    protected void apply(Block block) {
        Powerable powerable = (Powerable) block.getBlockData();
        powerable.setPowered(!powerable.isPowered());
        block.setBlockData(powerable);
    }
}
