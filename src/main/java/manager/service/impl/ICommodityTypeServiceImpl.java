package manager.service.impl;

import manager.domain.CommodityType;
import manager.domain.mappers.CommodityTypeMapper;
import manager.service.ICommodityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ICommodityTypeServiceImpl implements ICommodityTypeService {

    @Autowired
    private CommodityTypeMapper commodityTypeMapper;

    @Override
    public List<CommodityType> findAllTypes() {
        return commodityTypeMapper.findAllCommodityTypes();
    }

    @Override
    //增加商品类
    public boolean addType(String typeName) {
        CommodityType commodityType = commodityTypeMapper.findByName(typeName);
        if(!(commodityType==null)){
            return false;
        }else {
            commodityType = new CommodityType();
            commodityType.setId(UUID.randomUUID().toString().toUpperCase());
            commodityType.setTypeName(typeName);
            commodityType.setNum(0);
            boolean flag = commodityTypeMapper.saveType(commodityType);
            return flag;
        }
    }

    @Override
    //删除商品类
    public void delete(String id) {
        commodityTypeMapper.delete(id);
    }

    @Override
    public CommodityType findTypeById(String id) {
        return commodityTypeMapper.findById(id);
    }

    @Override
    public boolean changeTypeName(String id, String typeName) {
        CommodityType type = commodityTypeMapper.findByName(typeName);
        if(type==null){
            return commodityTypeMapper.changeTypeName(id,typeName);
        }else if(type.getId().equals(id)){//未更改名字
            return true;
        }else {//该类名已存在
            return false;
        }
    }

    @Override
    public CommodityType findTypeByName(String typeName) {
        return commodityTypeMapper.findByName(typeName);
    }

    @Override
    public void numAdd(String tId) {
        CommodityType type = commodityTypeMapper.findById(tId);
        type.setNum(type.getNum()+1);
        commodityTypeMapper.updateNum(type);
    }

    @Override
    public void numReduce(String tId) {
        CommodityType type = commodityTypeMapper.findById(tId);
        type.setNum(type.getNum()-1);
        commodityTypeMapper.updateNum(type);
    }
}
