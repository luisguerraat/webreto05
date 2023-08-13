package com.desaextremo.retocinco;

import com.desaextremo.retocinco.entity.Gadget;
import com.desaextremo.retocinco.entity.Order;
import com.desaextremo.retocinco.entity.User;
import com.desaextremo.retocinco.repository.GadgetRepository;
import com.desaextremo.retocinco.repository.OrderRepository;
import com.desaextremo.retocinco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class RetoCincoApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GadgetRepository gadgetRepositoy;

	@Autowired
	private OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(RetoCincoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		//borrado inicial de datos
		userRepository.deleteAll();
		gadgetRepositoy.deleteAll();
		orderRepository.deleteAll();
		/*
		gadgetRepositoy.saveAll(List.of(
				new Gadget(1, "GENIUS", "MOUSE", "SUPER MOUSE", "Mouse para video Gamer, costo calidad precio economia juegos", 250000, true, 10, "https:www.avasoluciones.com/mouse1.jpg"),
				new Gadget(2, "Coredy", "ROBOT", "Coredy Robot aspirador R650", "Personaliza tu propia aspiradora robot. costo calidad", 180000, true, 10, "https:www.avasoluciones.com/mouse.jpg")
		));

		userRepository.saveAll(List.of(
				new User(3, "46669989", "BLODY MARRY", ft.parse("1987-05-15"),"11","CR 34-45", "3174565625", "stellez@gmail.com", "Demo123.", "ZONA 2", "ASE"),
				new User(6, "213456789", "PEDRO CAPAROSA", ft.parse("1966-02-15"),"02","CR 34-45", "3168965645", "pcaparosa@gmail.com", "Demo123.", "ZONA 1", "ASE")
		));

		//Instanciar las ordenes
		Order orderOne = new Order();
		orderOne.setId(1);
		Order orderTwo = new Order();
		orderTwo.setId(2);

		Order orderThree = new Order();
		orderThree.setId(3);

		//Recuperar vendedores
		Optional<User> salesManOne = userRepository.findById(3);
		Optional<User> salesManTwo = userRepository.findById(6);
		Optional<User> salesManThree = userRepository.findById(6);

		//Mapa de productos asociados a una orden
		Map<Integer, Gadget> gadgetOrderOne = new HashMap<Integer, Gadget>();
		Map<Integer, Gadget> gadgetOrderTwo = new HashMap<Integer, Gadget>();
		Map<Integer, Gadget> gadgetOrderThree = new HashMap<Integer, Gadget>();

		//Mapa de cantidades de producto asociados a una orden
		Map<Integer, Integer> quantitiesOrderOne = new HashMap<Integer, Integer>();
		Map<Integer, Integer> quantitiesOrderTwo = new HashMap<Integer, Integer>();
		Map<Integer, Integer> quantitiesOrderThree = new HashMap<Integer, Integer>();

		//lista de producto de la orden 1
		gadgetOrderOne.put(1, gadgetRepositoy.findById(1).get());
		gadgetOrderOne.put(2, gadgetRepositoy.findById(2).get());

		//Cantidades de producto de la orden 1
		quantitiesOrderOne.put(1, 1);
		quantitiesOrderOne.put(2, 1);

		//lista de producto de la orden 2
		gadgetOrderTwo.put(1, gadgetRepositoy.findById(1).get());
		gadgetOrderTwo.put(2, gadgetRepositoy.findById(2).get());

		//Cantidades de producto de la orden 2
		quantitiesOrderTwo.put(1, 1);
		quantitiesOrderTwo.put(2, 1);


		//lista de producto de la orden 3
		gadgetOrderThree.put(1, gadgetRepositoy.findById(1).get());
		gadgetOrderThree.put(2, gadgetRepositoy.findById(2).get());

		//Cantidades de producto de la orden 3
		quantitiesOrderThree.put(1, 1);
		quantitiesOrderThree.put(2, 1);

		//Configuraciòn datos orden 1
		orderOne.setRegisterDay(ft.parse("2021-09-15"));
		orderOne.setStatus(Order.PENDING);
		orderOne.setSalesMan(salesManOne.get());
		orderOne.setProducts(gadgetOrderOne);
		orderOne.setQuantities(quantitiesOrderOne);

		//Configuraciòn datos orden 2
		orderTwo.setRegisterDay(ft.parse("2021-09-15"));
		orderTwo.setStatus(Order.PENDING);
		orderTwo.setSalesMan(salesManTwo.get());
		orderTwo.setProducts(gadgetOrderTwo);
		orderTwo.setQuantities(quantitiesOrderTwo);

		//Configuraciòn datos orden 3
		orderThree.setRegisterDay(ft.parse("2021-11-15"));
		orderThree.setStatus(Order.PENDING);
		orderThree.setSalesMan(salesManThree.get());
		orderThree.setProducts(gadgetOrderThree);
		orderThree.setQuantities(quantitiesOrderThree);

		//registra las ordenes en la base de datos
		//imprime el los datos de las ordenes
		orderRepository.saveAll(List.of(orderOne, orderTwo, orderThree));
		orderRepository.findAll().forEach(System.out::println);

		System.out.println("Imprime las ordenes asociadas al vendedor 6");
		orderRepository.findAllBySalesMan_Id(6).forEach(System.out::println);

		System.out.println("Imprime las ordenes asociadas al vendedor 6 (FomratoJSON)");
		orderRepository.encontrarOrdenesIdVen(6).forEach(System.out::println);


		System.out.println("Imprime las ordenes asociadas al estado y vendedor 6");
		orderRepository.findAllByStatusAndSalesMan_Id(Order.PENDING,3).forEach(System.out::println);

		System.out.println("Imprime las ordenes asociadas al estado y vendedor 6 (FomratoJSON)");
		orderRepository.encontrarOrdenesXEstadnIdVen(Order.PENDING,3).forEach(System.out::println);

		System.out.println("Productos con cadena 'calidad (JSON EXPR)");
		gadgetRepositoy.findByDescLike("calidad").forEach(System.out::println);

		System.out.println("Productos con cadena 'calidad'");
		gadgetRepositoy.findByDescriptionContains("calidad").forEach(System.out::println);

		System.out.println("Gadgets con precio menor o igual a :");
		gadgetRepositoy.findAllByPriceLessThanEqual(200000).forEach(System.out::println);

		System.out.println("Gadgets con precio menor o igual a (JSON EXP):");
		gadgetRepositoy.gadgetsPrecioMenorIgual(200000).forEach(System.out::println);
		*/
	}
}
