package com.fiap.tech.challenge.domain.menuitem.usecase;

import com.fiap.tech.challenge.domain.menuitem.dto.MenuItemMessageDTO;
import com.fiap.tech.challenge.domain.menuitem.entity.MenuItem;
import lombok.NonNull;

public class MenuItemCreateUseCase {
    private final MenuItem menuItem;

    public MenuItemCreateUseCase(@NonNull MenuItemMessageDTO menuItemMessageDTO) {
        this.menuItem = buildMenuItem(menuItemMessageDTO);
    }

    private MenuItem buildMenuItem(MenuItemMessageDTO menuItemMessageDTO) {
        return new MenuItem(
                menuItemMessageDTO.getHashId(),
                menuItemMessageDTO.getName(),
                menuItemMessageDTO.getDescription(),
                menuItemMessageDTO.getPrice()
        );
    }

    public MenuItem getBuiltedMenuItem() {
        return this.menuItem;
    }
}
