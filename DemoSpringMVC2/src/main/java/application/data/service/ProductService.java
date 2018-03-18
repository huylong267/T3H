package application.data.service;


import application.data.model.PagingableItemList;
import application.data.model.Product;
import application.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;



    public  void  addNewProduct(Product product){
        productRepository.save(product);
    }

    @Transactional
    public void addNewListProducts(List<Product> productList){

        productRepository.save(productList);
    }

    public long getTotalProducts(){

        return productRepository.getTotalProducts();
    }
    public PagingableItemList<Product> getListProducts(int pageSize, int pageNumber){
        PagingableItemList<Product>  pagingableItemList = new PagingableItemList<>();
        pagingableItemList.setPageSize(pageSize);
        pagingableItemList.setPageNumber(pageNumber);
        Page<Product> pages  = productRepository.findAll(new PageRequest(pageNumber,pageSize));
        pagingableItemList.setTotalProducts(pages.getTotalElements());
        pagingableItemList.setListData(pages.getContent());
        return  pagingableItemList;

    }
}
