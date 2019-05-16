package manager.service.impl;

import manager.domain.Commodity;
import manager.domain.mappers.CommodityMapper;
import manager.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICommodityServiceImpl implements ICommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public List<Commodity> findCommoditiesByType(String typeId) {
        return commodityMapper.findByType(typeId);
    }

    @Override
    public boolean save(Commodity commodity) {
        String name = commodity.getName();
        Commodity cc = commodityMapper.findByName(name);
        if(cc==null){
            return commodityMapper.save(commodity);
        }else {
            return false;
        }

    }

    @Override
    public void deleteByTypeId(String typeId) {
        commodityMapper.deleteByTypeId(typeId);
    }

    @Override
    public boolean delete(String id) {
        return commodityMapper.delete(id);
    }

    @Override
    public Commodity findCommodityById(String id) {
        return commodityMapper.findById(id);
    }

    @Override
    public List<Commodity> findCommodities() {
        return commodityMapper.findAll();
    }
}
