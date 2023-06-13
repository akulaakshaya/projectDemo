package eStoreProduct.DAO;

import java.util.List;

import eStoreProduct.model.stockSummaryModel;

public interface stockSummaryDAO {
	/* public List<Object[]> fetchStockSummary(); */

	public List<stockSummaryModel> getStocks();
}
