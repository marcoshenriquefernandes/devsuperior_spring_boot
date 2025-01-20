package com.devsuperior.components;

import com.devsuperior.components.entities.Order;
import com.devsuperior.components.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan({"com.devsuperior"})
public class ComponentsApplication implements CommandLineRunner {

	@Autowired
	private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(ComponentsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Map<Integer, Double> results = new LinkedHashMap<>();

		for (int i = 1; i <= 3; i++) {
			System.out.println("\nExemplo: " + i);
			System.out.print("Código do Pedido: ");
			int code = sc.nextInt();

			System.out.print("Valor básico: ");
			double basic = sc.nextDouble();

			System.out.print("Porcentagem de desconto: ");
			double discount = sc.nextDouble();

			Order order = new Order(code, basic, discount);
			results.put(order.getCode(), orderService.total(order));
		}

		System.out.println("\nSaída");
		results.forEach((code, total) -> {
			System.out.println("Pedido código: " + code +
					           "\nValor total: R$ " + String.format("%.2f", total) +
			                   "\n");
		});

		sc.close();
	}
}
