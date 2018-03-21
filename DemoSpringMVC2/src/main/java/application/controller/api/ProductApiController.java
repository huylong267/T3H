package application.controller.api;


import application.data.model.Product;
import application.data.service.ProductService;
import application.model.BaseApiResult;
import application.model.DataApiResult;
import application.model.ProductDataModel;
import application.model.ProductDetailModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductApiController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create-product")
    public BaseApiResult createProduct(@RequestBody ProductDataModel product){
        DataApiResult result = new DataApiResult();
        try {
            if(!"".equals(product.getName())&&!"".equals(product.getShort_desc())&&!"".equals(product.getImage())){
                ModelMapper  modelMapper = new ModelMapper();
                Product productEntity = modelMapper.map(product,Product.class);
                productService.addNewProduct(productEntity);
                result.setData(productEntity.getId());
                result.setSuccess(true);
                result.setMessages("Successfully");
            }
            else {
                result.setMessages("FAIL");
                result.setSuccess(false);
            }

        }catch (Exception e){
            result.setMessages("FAIL");
            result.setSuccess(false);
        }
        return result;
    }

    @GetMapping("/detail/{productid}")
    public BaseApiResult detailProduct (@PathVariable int productid){
        DataApiResult result = new DataApiResult();

        try{
            Product productExist=productService.findOne(productid);
        if(productExist== null){
            result.setSuccess(false);
            result.setMessages("Fail");
        }
        else {
            ModelMapper modelMapper = new ModelMapper();
            ProductDetailModel productDetailModel = modelMapper.map(productExist,ProductDetailModel.class);
            result.setMessages("Successfully");
            result.setSuccess(true);
            result.setData(productDetailModel);
        }
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessages("Fail");
        }

    return  result;
    }
    @PostMapping("/update/{productid}")
    public BaseApiResult updateProduct(@PathVariable int productid,@RequestBody ProductDataModel dataModel){
        DataApiResult result = new DataApiResult();

        try {
            Product existProduct = productService.findOne(productid);
            if (!dataModel.getName().equals("")&&!dataModel.getShort_desc().equals("")&&!dataModel.getImage().equals("")){
                    existProduct.setName(dataModel.getName());
                    existProduct.setshort_desc(dataModel.getShort_desc());
                    existProduct.setImage(dataModel.getImage());
                    existProduct.setCreatedDate(dataModel.getCreatedDate());
                    result.setSuccess(true);
                    result.setMessages("Update successfully");
                    result.setData(dataModel);
                    productService.update(existProduct);
            }else {
                result.setSuccess(false);
                result.setMessages("Update fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessages("Update fail");
        }
        return  result;
    }

    @GetMapping("/search/{n}")
    public BaseApiResult searchByNameProduct(@PathVariable String n,@RequestBody ProductDataModel productDataModel){
        DataApiResult  result = new DataApiResult();

        try {
            List<Product> productExist = productService.searchName(n);
            if (productExist==null){
                result.setMessages("No product");
                result.setSuccess(false);
            }else {

                result.setSuccess(true);
                result.setMessages("Search successfully");
                result.setData(productExist);
            }
        } catch (Exception e) {
            result.setMessages("FAIL");
            result.setSuccess(false);
            e.printStackTrace();
        }
        return result;
    }
}
