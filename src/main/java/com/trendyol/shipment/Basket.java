package com.trendyol.shipment;

import java.util.*;

public class Basket {

    private List<Product> products;

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ShipmentSize getShipmentSize() {
        if(products.size() >= 3) {
            Map.Entry<ShipmentSize, Integer> shipment = findMostOccurredShipment();
            Integer occurrenceNumber = shipment.getValue();
            ShipmentSize size = shipment.getKey();

            if(occurrenceNumber >= 3)
                return nextBiggerSizeOf(size);
            else
                return biggestOneInBasket();
        }
        else return biggestOneInBasket();
    }

    private ShipmentSize nextBiggerSizeOf(ShipmentSize size) {
        switch (size) {
            case SMALL:
                return ShipmentSize.MEDIUM;
            case MEDIUM:
                return ShipmentSize.LARGE;
            case LARGE:
            case X_LARGE:
                return ShipmentSize.X_LARGE;
            default:
                return null;
        }
    }

    private ShipmentSize biggestOneInBasket() {
        List<ShipmentSize> sizes = new ArrayList<>(products.size());
        for (int i = 0; i < products.size(); i++) {
            sizes.add(products.get(i).getSize());
        }

        return sizes.contains(ShipmentSize.X_LARGE) ? ShipmentSize.X_LARGE :
                sizes.contains(ShipmentSize.LARGE) ? ShipmentSize.LARGE :
                        sizes.contains(ShipmentSize.MEDIUM) ? ShipmentSize.MEDIUM :
                                sizes.contains(ShipmentSize.SMALL) ? ShipmentSize.SMALL : null;

    }

    private Map.Entry<ShipmentSize, Integer> findMostOccurredShipment() {
        Map<ShipmentSize, Integer> shipmentSizeNumbers = new HashMap<>();
        shipmentSizeNumbers.put(ShipmentSize.SMALL, 0);
        shipmentSizeNumbers.put(ShipmentSize.MEDIUM, 0);
        shipmentSizeNumbers.put(ShipmentSize.LARGE, 0);
        shipmentSizeNumbers.put(ShipmentSize.X_LARGE, 0);

        for(Product product: products) {
            Integer currentNumberOfSize = shipmentSizeNumbers.get(product.getSize());
            shipmentSizeNumbers.replace(product.getSize(), ++currentNumberOfSize);
        }

        int maxNum = -1;
        Map.Entry<ShipmentSize, Integer> mostOccurred = null;
        for(Map.Entry<ShipmentSize, Integer> entry: shipmentSizeNumbers.entrySet()) {
            if(entry.getValue() > maxNum) {
                maxNum = entry.getValue();
                mostOccurred = entry;
            }
        }
        return mostOccurred;
    }
}
