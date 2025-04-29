package org.nano.redstoneLink.domain.repository;

import org.nano.redstoneLink.domain.block.BlockLink;
import org.nano.redstoneLink.domain.remoter.Remoter;

import java.util.List;

public class LinkedBlockRepository {
    private List<BlockLink> blockLinks;

    public LinkedBlockRepository(Remoter remoter) {
        this.blockLinks = remoter.getLinkedBlocks();
    }
}
