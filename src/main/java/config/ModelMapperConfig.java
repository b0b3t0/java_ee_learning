package config;

import javax.enterprise.inject.Produces;

public class ModelMapper {
    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
