package lk.ijse.gem_management_layered.bo.custom.impl;

import lk.ijse.gem_management_layered.bo.custom.GemBO;
import lk.ijse.gem_management_layered.dao.DAOFactory;
import lk.ijse.gem_management_layered.dao.custom.GemDAO;
import lk.ijse.gem_management_layered.dto.GemDTO;
import lk.ijse.gem_management_layered.entity.Gem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GemBOImpl implements GemBO {

    private final GemDAO gemDAO = (GemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.GEM);

    @Override
    public boolean saveGem(GemDTO dto) throws SQLException, ClassNotFoundException {
        return gemDAO.save(new Gem(dto.getGemName(), dto.getType()));
    }

    @Override
    public boolean updateGem(GemDTO dto) throws SQLException, ClassNotFoundException {
        return gemDAO.update(new Gem(dto.getGemId(), dto.getGemName(), dto.getType()));
    }

    @Override
    public boolean deleteGem(String id) throws SQLException, ClassNotFoundException {
        return gemDAO.delete(Integer.parseInt(id));
    }

    @Override
    public GemDTO searchGem(String id) throws SQLException, ClassNotFoundException {
        Gem gem = gemDAO.search(Integer.parseInt(id));
        if(gem != null){
            return new GemDTO(gem.getGemId(), gem.getGemName(), gem.getType());
        }
        return null;
    }

    @Override
    public List<GemDTO> getAllGems() throws SQLException, ClassNotFoundException {
        List<GemDTO> list = new ArrayList<>();
        for(Gem gem : gemDAO.getAll()){
            list.add(new GemDTO(gem.getGemId(), gem.getGemName(), gem.getType()));
        }
        return list;
    }
}