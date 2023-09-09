package com.trendyol.shipment;

import java.util.*;

public class Basket {

    private List<Product> products;
    private static final Integer THRESHOLD = 3;

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ShipmentSize getShipmentSize() {
        if(products.isEmpty())
            return null;

        if(products.size() >= THRESHOLD) {
            Map.Entry<ShipmentSize, Integer> shipment = findMostOccurredShipment();
            Integer occurrenceNumber = shipment.getValue();
            ShipmentSize size = shipment.getKey();

            if(occurrenceNumber >= THRESHOLD)
                return size.getNextBigSize();
            else
                return biggestOneInBasket();
        }
        else
            return biggestOneInBasket();
    }

    private ShipmentSize biggestOneInBasket() {
        ShipmentSize biggest = ShipmentSize.SMALL;
        for(Product product: products) {
            ShipmentSize size = product.getSize();
            if(size.ordinal() > biggest.ordinal())
                biggest = size;
        }
        return biggest;
    }

    private Map.Entry<ShipmentSize, Integer> findMostOccurredShipment() {
        Map<ShipmentSize, Integer> shipmentSizeNumbers = new HashMap<>();

        for(Product product: products) {
            shipmentSizeNumbers.putIfAbsent(product.getSize(), 0);
            Integer currentNumberOfSize = shipmentSizeNumbers.get(product.getSize());
            shipmentSizeNumbers.replace(product.getSize(), ++currentNumberOfSize);
        }

        return shipmentSizeNumbers.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get();
    }
}
