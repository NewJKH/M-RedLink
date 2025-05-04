package org.nano.redstoneLink.domain.block.handler;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.nano.redstoneLink.shared.registry.VisualLampRegistry;

public class LampHandler extends AbstractBlockHandler {

    private final VisualLampRegistry lampRegistry;

    public LampHandler(VisualLampRegistry lampRegistry) {
        this.lampRegistry = lampRegistry;
    }

    @Override
    protected boolean isSupportedBlock(Block block) {
        return block.getType() == Material.REDSTONE_LAMP;
    }

    @Override
    protected void apply(Block block) {
        Location loc = block.getLocation();
        boolean isLit = lampRegistry.isLit(loc);
        boolean nextState = !isLit;

        BlockData lampData = Bukkit.createBlockData("minecraft:redstone_lamp[lit=" + nextState + "]");
        WrappedBlockData wrappedData = WrappedBlockData.createData(lampData);

        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        Bukkit.getOnlinePlayers().forEach(player -> {
            PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.BLOCK_CHANGE);
            packet.getBlockPositionModifier().write(0, new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
            packet.getBlockData().write(0, wrappedData);
            try {
                protocolManager.sendServerPacket(player, packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        lampRegistry.setLit(loc, nextState);
    }
}
