package lk.ijse.gem_management_layered.dao.custom;

import lk.ijse.gem_management_layered.dao.CrudDAO;
import lk.ijse.gem_management_layered.entity.Gem;

public interface GemDAO extends CrudDAO<Gem, Number> {
    boolean update();

    boolean exits();
}

