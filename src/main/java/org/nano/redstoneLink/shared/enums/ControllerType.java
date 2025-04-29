package org.nano.redstoneLink.shared.enums;

import org.bukkit.Material;

public enum ControllerType {
    LEVER,
    BUTTON;

    public static boolean check(Material material) {
        if (material == null) return false;

        return switch (material) {
            case LEVER, STONE_BUTTON, OAK_BUTTON, SPRUCE_BUTTON, BIRCH_BUTTON, JUNGLE_BUTTON, ACACIA_BUTTON,
                 DARK_OAK_BUTTON, CRIMSON_BUTTON, WARPED_BUTTON -> true;
            default -> false;
        };
    }
}
