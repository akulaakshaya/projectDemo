package eStoreProduct.DAO;

import java.util.List;

import eStoreProduct.model.Category;

public interface CategoryDAO {

	public List<String> getAllCategories();

	public boolean addNewCategory(Category catg);
}