package org.nano.redstoneLink.domain.block.handler;

import org.bukkit.block.Block;
import org.bukkit.block.data.Openable;

public class OpenableHandler extends AbstractBlockHandler{

    @Override
    protected boolean isSupportedBlock(Block block) {
        return block.getBlockData() instanceof Openable;
    }

    @Override
    protected void apply(Block block) {
        Openable openable = (Openable) block.getBlockData();
        openable.setOpen(!openable.isOpen());
        block.setBlockData(openable);
    }
}
