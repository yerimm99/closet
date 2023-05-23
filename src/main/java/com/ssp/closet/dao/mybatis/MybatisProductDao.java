package com.ssp.closet.dao.mybatis;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ssp.closet.dao.ProductDao;
import com.ssp.closet.dao.mybatis.mapper.ProductMapper;
import com.ssp.closet.dto.Product;

public class MybatisProductDao implements ProductDao {

	private ProductMapper productMapper;

	@Override
	public void insertProduct(Product product) throws DataAccessException {
		productMapper.insertProduct(product);
	}

	@Override
	public void updateProduct(Product product) throws DataAccessException {
		productMapper.updateProduct(product);
	}

	@Override
	public void deleteProduct(int productId) throws DataAccessException {
		productMapper.deleteProduct(productId);
	}

	@Override
	public List<Product> getProductList() throws DataAccessException {
		List<Product> list = productMapper.getProductList();
		return list;
	}

	@Override
	public Product getProductDetail(int productId) throws DataAccessException {
		Product product = productMapper.getProductDetail(productId);
		return product;
	}
}
