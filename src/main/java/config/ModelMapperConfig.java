package config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;

public class ModelMapperConfig {
    @Produces
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        return mapper;
    }
}
