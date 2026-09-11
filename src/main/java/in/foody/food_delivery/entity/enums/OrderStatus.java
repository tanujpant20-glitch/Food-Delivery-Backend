package in.foody.food_delivery.entity.enums;

import jakarta.persistence.Entity;

public enum OrderStatus {
    PAYMENT_PENDING,
    CONFIRMED,
    OUTFORDELIVERY,
    ACCEPTED,
    CANCELLED

}
