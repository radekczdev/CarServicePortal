package com.czajor.carserviceportal.repairorder;

public enum RepairOrderType {
    MECHANICAL("mechanical"),
    ELECTRICAL("electrical"),
    CLEANING("cleaning"),
    DAILY_SERVICE("daily service");

    private String typeName;

    RepairOrderType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static RepairOrderType getType(String orderType) {
        switch(orderType) {
            case "mechanical":
                return MECHANICAL;
            case "electrical":
                return ELECTRICAL;
            case "cleaning":
                return CLEANING;
            case "daily service":
                return DAILY_SERVICE;
            default:
                throw new IllegalArgumentException("Unknown order type: " + orderType);
        }
    }

    @Override
    public String toString() {
        return typeName;
    }
}
