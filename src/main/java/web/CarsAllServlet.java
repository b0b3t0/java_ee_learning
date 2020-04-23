package web;

import model.service.CarViewModel;
import org.modelmapper.ModelMapper;
import service.CarsService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/cars/all")
public class CarsAllServlet extends HttpServlet {

    private final CarsService carsService;
    private final ModelMapper modelMapper;

    @Inject
    public CarsAllServlet(CarsService carsService, ModelMapper modelMapper) {
        this.carsService = carsService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CarViewModel> cars = carsService.getAll()
                .stream()
                .map(car -> modelMapper.map(car, CarViewModel.class))
                .collect(Collectors.toList());

        req.setAttribute("viewModel", cars);

        req.getRequestDispatcher("/cars-all.jsp")
                .forward(req, resp);
    }
}
