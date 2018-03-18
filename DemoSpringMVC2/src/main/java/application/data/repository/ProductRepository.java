package application.data.repository;

import application.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product ,Integer> {
    @Query("select  count(p.id) from tbl_product p")
    long getTotalProducts();

}
