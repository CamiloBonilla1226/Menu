package com.menu.demo.infrastructure.output.mapper;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;


import com.menu.demo.application.output.MapperMenuInfrastructureDomainIntPort;
import com.menu.demo.domain.Menu;
import com.menu.demo.infrastructure.input.controller.DTOResponse.MenuDTOResponse;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapperMenuInfrastructureDomainImplAdapter implements MapperMenuInfrastructureDomainIntPort {
    private final ModelMapper mapper;



    @Override
    public MenuDTOResponse mapModelToResponse(Menu model) {
        return this.mapper.map(model, MenuDTOResponse.class);
    }


    @Override
    public List<MenuDTOResponse> mapModelToResponse(List<Menu> menus) {
        return this.mapper.map(menus, new TypeToken<List<MenuDTOResponse>>(){}.getType());
    }
}
