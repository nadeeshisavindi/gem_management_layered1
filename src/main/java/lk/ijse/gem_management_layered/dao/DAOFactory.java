package lk.ijse.gem_management_layered.dao;

import lk.ijse.gem_management_layered.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    // Enum for DAO types
    public enum DAOType {
        GEM,
        GEM_CUTTER,
        CUSTOMER,
        ORDERS,
        SALES,
        STOCK,
        SUPPLIERS,
        USER
    }

    // Return DAO instance based on type
    public Object getDAO(DAOType type){
        switch (type){
            case GEM:
                return new GemDAOImpl();
            case GEM_CUTTER:
                return new Gem_CutterDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ORDERS:
                return new OrdersDAOImpl();
            case SALES:
                return new SalesDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case SUPPLIERS:
                return new SuppliersDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}