package org.nano.redstoneLink.shared.registry;

import org.bukkit.block.Block;
import org.nano.redstoneLink.domain.block.handler.*;

import java.util.ArrayList;
import java.util.List;

public class BlockHandlerRegistry {
    private final List<AbstractBlockHandler> handlers = new ArrayList<>();

    public BlockHandlerRegistry() {
        handlers.add(new PowerableBlockHandler());
        handlers.add(new LampHandler(new VisualLampRegistry()));
        handlers.add(new DispenserHandler());
        handlers.add(new OpenableHandler());
    }

    public void handle(Block block) {
        for (AbstractBlockHandler handler : handlers) {
            if (handler.supports(block)) {
                handler.handle(block);
                return;
            }
        }
    }
}
