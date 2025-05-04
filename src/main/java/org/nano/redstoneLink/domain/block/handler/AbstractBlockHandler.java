package org.nano.redstoneLink.domain.block.handler;

import org.bukkit.block.Block;

public abstract class AbstractBlockHandler {
    public boolean supports(Block block) {
        return isSupportedBlock(block);
    }

    public final void handle(Block block) {
        if (!supports(block)) return;

        apply(block);
    }

    protected abstract void apply(Block block);

    protected boolean isSupportedBlock(Block block) {
        return true;
    }
}
