package com.trendyol.shipment;

public enum ShipmentSize {
    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    public ShipmentSize getNextBigSize() {
        if (this == X_LARGE) {
            return X_LARGE;
        }
        return values()[this.ordinal() + 1];
    }

}

