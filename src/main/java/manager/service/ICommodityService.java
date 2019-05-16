package manager.service;

import manager.domain.Commodity;

import java.util.List;

public interface ICommodityService {

    List<Commodity> findCommoditiesByType(String id);

    boolean save(Commodity commodity);

    void deleteByTypeId(String id);

    boolean delete(String id);

    Commodity findCommodityById(String id);

    List<Commodity> findCommodities();
}
