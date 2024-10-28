package com.menu.demo.infrastructure.output.formatter;

import org.springframework.stereotype.Service;
import com.menu.demo.application.output.ExceptionFormatterIntPort;
import com.menu.demo.infrastructure.output.exceptionHandler.ownException.BusinessRuleException;
import com.menu.demo.infrastructure.output.exceptionHandler.ownException.NoDataException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ExceptionFormatterImplAdapter implements ExceptionFormatterIntPort {
    @Override
    public void responseBusinessRuleViolates(String message) {
        throw new BusinessRuleException(message);
    }

    @Override
    public void responseEntityNotFound(String message) {
        throw new EntityNotFoundException(message);
    }

    @Override
    public void responseEntityExists(String message) {
        throw new EntityExistsException(message);
    }

    @Override
    public void responseNoData(String message) {
        throw new NoDataException(message);
    }
}
