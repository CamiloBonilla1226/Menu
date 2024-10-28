package com.menu.demo.application.output;

import java.util.List;

import com.menu.demo.domain.Menu;
import com.menu.demo.infrastructure.input.controller.DTOResponse.MenuDTOResponse;

public interface MapperMenuInfrastructureDomainIntPort {
    public MenuDTOResponse mapModelToResponse(Menu model);
    public List<MenuDTOResponse> mapModelToResponse(List<Menu> menus);
}
