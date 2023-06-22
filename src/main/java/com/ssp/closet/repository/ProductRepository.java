package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dto.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	//List<Product> findByOrderByRank();

//		@Modifying
//		@Query("UPDATE Product p SET " +
//	            "p.description = :description, " +
//	            "p.picture1 = :picture1, " +
//	            "p.picture2 = :picture2, " +
//	            "p.picture3 = :picture3, " +
//	            "p.picture4 = :picture4 " +
//	            "WHERE p.productId = :productId")
//		void updateByProductId(
//	            @Param("description") String description,
//	            @Param("picture1") String picture1,
//	            @Param("picture2") String picture2,
//	            @Param("picture3") String picture3,
//	            @Param("picture4") String picture4,
//	            @Param("productId") int productId
//	    );
//		
//		@Modifying
//	    @Query("DELETE FROM Product p WHERE p.productId = :productId" + 
//	    		"AND EXISTS (SELECT g FROM GroupBuy g WHERE g.productId = p.productId AND g.peopleNum = 1)")
//		void deleteByProductIdIfGroupBuyPeopleNumIsOne( @Param("productId") int productId);
		
		List<Product> findByPtypeAndStatus(int ptype, int status);
		List<Product> findByNameIgnoreCaseContaining(String keywords);
		//List<Product> findAll();
		
		@Query("SELECT p FROM Product p ORDER BY p.rank DESC")
	    List<Product> findTopRankingProducts();
		
		Product findByProductId(int productId);
		
}