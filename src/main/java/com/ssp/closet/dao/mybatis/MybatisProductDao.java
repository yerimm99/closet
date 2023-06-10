package com.ssp.closet.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dao.ProductDao;
import com.ssp.closet.dao.SequenceDao;
import com.ssp.closet.dao.mybatis.mapper.ProductMapper;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.Product;

@Repository
public class MybatisProductDao implements ProductDao {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private SequenceDao sequenceDao;


	
	public void insertGroupBuyProduct(Product product, Groupbuy groupBuy) throws DataAccessException {
		product.setProductId(sequenceDao.getNextId("productnum")); //productnum 은 추후 수정
		productMapper.insertGroupBuyProduct(product, groupBuy);
	}

	public void updateProduct(int productId) throws DataAccessException {
		productMapper.updateProduct(productId);
	}

	public void deleteProduct(int productId) throws DataAccessException {
		productMapper.deleteProduct(productId);
	}

	public List<Product> getProductList() throws DataAccessException {
		List<Product> list = productMapper.getProductList();
		return list;
	}

	public Product getProductDetail(int productId) throws DataAccessException {
		Product product = productMapper.getProductDetail(productId);
		return product;
	}
	
	
	//상품 검색
}
