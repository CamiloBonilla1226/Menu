package com.menu.demo.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.menu.demo.application.output.ExceptionFormatterIntPort;
import com.menu.demo.application.output.ManageMenuGatewayIntPort;
import com.menu.demo.domain.service.MenuDomainService;
import com.menu.demo.domain.useCases.ManageMenuUCImplAdapter;
import com.menu.demo.infrastructure.input.ErrorCatcher;



@Configuration
public class BeanConfigurations {

    @Bean
    public MenuDomainService createMenuDomainService(){
        return new MenuDomainService();
    }
    
    @Bean
    public ManageMenuUCImplAdapter createMenuCU(MenuDomainService domainService,
                                                ManageMenuGatewayIntPort gateway,
                                                ExceptionFormatterIntPort formatter){
        return new ManageMenuUCImplAdapter(domainService, gateway, formatter);
    }

    @Bean
    public ErrorCatcher createErrorCatcher(){
        return new ErrorCatcher();
    }
}
