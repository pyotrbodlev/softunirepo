package alararestaurant.service;

import alararestaurant.common.Texts;
import alararestaurant.domain.dtos.xml.OrderImportXmlDto;
import alararestaurant.domain.dtos.xml.OrderItemImportDto;
import alararestaurant.domain.dtos.xml.OrdersImportXml;
import alararestaurant.domain.entities.*;
import alararestaurant.repository.*;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import alararestaurant.util.parsers.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private ItemRepository itemRepository;
    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;
    private FileUtil fileUtil;
    private XmlParser xmlParser;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ItemRepository itemRepository, EmployeeRepository employeeRepository, PositionRepository positionRepository, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemRepository = itemRepository;
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() {
        return this.fileUtil.readFile(getClass().getClassLoader().getResource("files/orders.xml").getFile());
    }

    @Override
    public String importOrders() throws JAXBException {
        OrdersImportXml ordersImportXml = this.xmlParser.parseXml(OrdersImportXml.class, getClass().getClassLoader().getResource("files/orders.xml").getPath());
        StringBuilder sb = new StringBuilder();

        for (OrderImportXmlDto orderDto : ordersImportXml.getOrders()) {
            if (this.validationUtil.isValid(orderDto)) {
                Employee employee = this.employeeRepository.findByName(orderDto.getEmployee()).orElse(null);
                LocalDateTime dateTime = LocalDateTime.parse(orderDto.getDateTime(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

                if (employee == null) {
                    sb.append(Texts.INVALID_DATA).append(System.lineSeparator());
                    continue;
                }

                Order order = this.modelMapper.map(orderDto, Order.class);

                order.setEmployee(employee);
                order.setDateTime(dateTime);

                if (orderDto.getType() != null) {
                    try {
                        order.setType(OrderType.valueOf(orderDto.getType()));
                    } catch (IllegalArgumentException ier) {
                        sb.append(Texts.INVALID_DATA).append(System.lineSeparator());
                        continue;
                    }
                }

                this.orderRepository.saveAndFlush(order);

                List<OrderItem> orderItems = new ArrayList<>();

                for (OrderItemImportDto orderItemImportDto : orderDto.getItems()) {
                    Item item = this.itemRepository.findByName(orderItemImportDto.getItem()).orElse(null);

                    if (this.validationUtil.isValid(orderItemImportDto)
                            && item != null) {
                        OrderItem orderItem = this.modelMapper.map(orderItemImportDto, OrderItem.class);
                        orderItem.setOrder(order);
                        orderItem.setItem(item);

                        orderItems.add(orderItem);

                        this.orderItemRepository.saveAndFlush(orderItem);
                    } else {
                        sb.append(Texts.INVALID_DATA).append(System.lineSeparator());
                    }
                }

                order.setOrderItems(orderItems);

                this.orderRepository.saveAndFlush(order);

                sb.append(String.format(Texts.ORDER_ADDED, orderDto.getCustomer(), orderDto.getDateTime())).append(System.lineSeparator());

            } else {
                sb.append(Texts.INVALID_DATA).append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        Position burgerFilpper = this.positionRepository.findByName("Burger Flipper").get();

        List<Employee> employees = this.employeeRepository
                .findAllByPositionOrderByName(burgerFilpper)
                .stream()
                .filter(e -> !e.getOrders().isEmpty())
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (Employee employee : employees) {

            sb.append(String.format("Employee: %s", employee.getName())).append(System.lineSeparator())
                    .append("Orders: ").append(System.lineSeparator());

            for (Order order : employee.getOrders()) {
                sb.append(String.format("   Customer: %s", order.getCustomer())).append(System.lineSeparator())
                        .append("   Items: ").append(System.lineSeparator());

                for (OrderItem orderItem : order.getOrderItems()) {
                    sb.append(String.format("      Name: %s", orderItem.getItem().getName())).append(System.lineSeparator())
                            .append(String.format("      Price: %.2f", orderItem.getItem().getPrice())).append(System.lineSeparator())
                            .append(String.format("      Quantity: %d", orderItem.getQuantity())).append(System.lineSeparator());

                    sb.append(System.lineSeparator());
                }
            }
        }

        return sb.toString().trim();
    }
}
