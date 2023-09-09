package com.trendyol.shipment;

public class Product {

    private ShipmentSize size;

    public Product(ShipmentSize shipmentSize){
        setSize(shipmentSize);
    }

    public static Product create(ShipmentSize shipmentSize) {
        return new Product(shipmentSize);
    }

    public ShipmentSize getSize() {
        return size;
    }

    public void setSize(ShipmentSize size) {
        this.size = size;
    }
}
