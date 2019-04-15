package com.joole.service;

import java.util.List;

import com.joole.domain.Product;

public interface ProductServiceDAO {
	
	// product list view
		public List<Product> getProductBySubcategory(int sub_id);
		
		public List<Product> getAllProducts();
		
		// basic product page view
		public Product getProduct();
		
		// product comparison
		public List<Product> getProductsByComparison();
		
		//advanced product search
		public List<Product> getProductsByAdvancedSearch();

}
