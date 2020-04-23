package service;

import model.entity.Car;
import model.service.CarServiceModel;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarsService {

    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    @Inject
    public CarServiceImpl(ModelMapper modelMapper, EntityManager entityManager) {
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<CarServiceModel> getAll() {
        return entityManager.createQuery("select c from Cars c", Car.class)
                .getResultList()
                .stream()
                .map(car -> modelMapper.map(car, CarServiceModel.class))
                .collect(Collectors.toList());

    }
}
