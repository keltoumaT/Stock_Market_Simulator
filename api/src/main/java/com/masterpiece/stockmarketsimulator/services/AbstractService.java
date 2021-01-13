package com.masterpiece.stockmarketsimulator.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class  AbstractService {

    @Autowired
    ModelMapper modelMapper;

    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
