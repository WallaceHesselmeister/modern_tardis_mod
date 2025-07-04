/* (C) TAMA Studios 2025 */
package com.code.tama.tts.server.enums;

import net.minecraft.network.chat.Component;

public enum SonicInteractionType {
    BLOCKS(Component.translatable("aseoha.sonic.mode.block")),
    ENTITY(Component.translatable("aseoha.sonic.mode.entity")),
    SCANNER(Component.translatable("aseoha.sonic.mode.scanner"));

    private final Component Name;

    SonicInteractionType(Component Name) {
        this.Name = Name;
    }

    public Component Name() {
        return this.Name;
    }
}
