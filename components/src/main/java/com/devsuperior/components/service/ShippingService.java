package com.devsuperior.components.service;

import com.devsuperior.components.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public double shipment(Order order) {
        double value = order.getBasic();

        if (value < 100.00) {
            return 20.00;
        } else if (value > 100.00 && value <= 200.00) {
            return 12.00;
        } else {
            return 0.0;
        }
    }
}
