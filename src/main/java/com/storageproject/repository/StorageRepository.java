package com.storageproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.storageproject.model.Storage;

@Repository("storageRepository")
public interface StorageRepository extends JpaRepository<Storage, Long>,CrudRepository<Storage, Long> {
	Storage findByProductCode(int productCode);
	
	@Query(value="SELECT * FROM storage s WHERE s.product_name LIKE CONCAT('%',:productName,'%') AND s.product_category LIKE CONCAT('%',:category,'%')", nativeQuery = true)
	List<Storage> productName(@Param("productName")String productName,@Param("category")String category);
}