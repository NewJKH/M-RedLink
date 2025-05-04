package org.nano.redstoneLink.domain.block.handler;

import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Dropper;

public class DispenserHandler extends AbstractBlockHandler {

    @Override
    protected boolean isSupportedBlock(Block block) {
        return block.getState() instanceof Dispenser || block.getState() instanceof Dropper;
    }

    @Override
    protected void apply(Block block) {
        if ( block.getState() instanceof Dispenser dispenser ) {
            dispenser.dispense();
        }
        else if ( block.getState() instanceof Dropper dropper ) {
            dropper.drop();
        }
    }
}
