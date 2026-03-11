package lk.ijse.gem_management_layered.dao;

import lk.ijse.gem_management_layered.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {}

    // Singleton instance
    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }



    // Enum to identify DAO types
    public enum DAOType {
        ORDER,
        GEM,
        GEM_CUTTER,
        ORDERS,
        SALES,
        STOCKS,
        CUSTOMER,
        SUPPLIERS,
    }

    // Return DAO based on type
    public Object getDAO(DAOType type) {
        switch (type) {
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
            case STOCKS:
                return new StockDAOImpl();
            case SUPPLIERS:
                return new SuppliersDAOImpl();

            default:
                return null;
        }
    }
}
//import lk.ijse.gem_management_layered.dao.custom.impl.CustomerDAOImpl;
//
//public class DAOFactory {
//
//        private static DAOFactory daoFactory;
//        private DAOFactory() {}
//
//        public static DAOFactory getInstance() {
//            return daoFactory==null ? daoFactory=new DAOFactory() : daoFactory;
//        }
//
//    public Object getDAO(DAOType daoType) {
//    }
//
//    public enum DAOType {
//            GEM_CUTTER, GEM, CUSTOMER
//        }
//
//        public SuperDAO getDAOType(DAOType daoType) {
//            switch (daoType) {
//                case CUSTOMER:
//                    return new CustomerDAOImpl();
//                default:
//                    return null;
//            }
//        }
//
//}
