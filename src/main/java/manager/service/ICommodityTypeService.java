package manager.service;

import manager.domain.CommodityType;

import java.util.List;

public interface ICommodityTypeService {

    List<CommodityType> findAllTypes();

    boolean addType(String typeName);

    void delete(String id);

    CommodityType findTypeById(String id);

    boolean changeTypeName(String id, String typeName);

    CommodityType findTypeByName(String typeName);

    void numAdd(String tId);

    void numReduce(String tId);
}
