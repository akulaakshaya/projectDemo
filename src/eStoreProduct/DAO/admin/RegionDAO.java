package eStoreProduct.DAO.admin;

import java.util.List;

import eStoreProduct.model.admin.output.Regions;

public interface RegionDAO {
	List<Regions> getRegions();

	void addRegion(Regions reg);

	void removeRegion(int id);
}
