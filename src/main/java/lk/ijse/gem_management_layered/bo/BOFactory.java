package lk.ijse.gem_management_layered.bo;

import lk.ijse.gem_management_layered.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    // Enum for BO types
    public enum BOType {
        GEM,
        GEM_CUTTER,
        ORDERS,
        SALES,
        CUSTOMER,
        STOCK,
        SUPPLIERS
    }

    // Method to get BO object
    public Object getBO(BOType type) {
        switch (type) {
            case GEM:
                return new GemBOImpl();
            case GEM_CUTTER:
                return new Gem_CutterBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case ORDERS:
                return new OrdersBOImpl();
            case SALES:
                return new SalesBOImpl();
            case STOCK:
                return new StockBOImpl();
            case SUPPLIERS:
                return new SuppliersBOImpl();
            default:
                return null;
        }
    }
}