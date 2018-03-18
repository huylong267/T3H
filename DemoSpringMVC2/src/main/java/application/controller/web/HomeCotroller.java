package application.controller.web;

import application.data.model.PagingableItemList;
import application.data.model.Product;
import application.data.repository.ProductRepository;
import application.data.service.ProductService;
import application.viewmodel.common.LogoVM;
import application.viewmodel.common.ProductVM;
import application.viewmodel.homelanding.BannerVM;
import application.viewmodel.homelanding.HomeLandingVM;
import application.viewmodel.homelanding.MenuItemVM;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping(path = "/")
public class HomeCotroller {
    @Autowired
    private  ProductService productService;

    private String[] images={
            "http://demo80008.webthienthan.net/image/cache/catalog/san pham/hoaqua_4-500x500-241x241.jpg",
            "http://demo80008.webthienthan.net/image/cache/catalog/san pham/hoaqua_4-500x500-241x241.jpg",
            "http://demo80008.webthienthan.net/image/cache/catalog/san pham/hoaqua_4-500x500-241x241.jpg",
            "http://demo80008.webthienthan.net/image/cache/catalog/san pham/hoaqua_4-500x500-241x241.jpg",
            "http://demo80008.webthienthan.net/image/cache/catalog/san pham/hoaqua_4-500x500-241x241.jpg"
    };
    @RequestMapping(path = "admin", method = RequestMethod.GET)
    public  String admin(Model model){
        long totalProducts = productService.getTotalProducts();
        if (totalProducts <=0){
            ArrayList<Product> listProducts = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                Product p = new Product();
                p.setCreatedDate(new Date());
                p.setName("Product " +i);
                p.setshort_desc("Description for product "+i);
                p.setImage(images[random.nextInt(images.length)]);
                listProducts.add(p);
            }
            productService.addNewListProducts(listProducts);
            model.addAttribute("massage","Total added products"+listProducts.size());

        }
        else {
            model.addAttribute("massage", "Total existed products" +totalProducts);
        }
        return  "admin";
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(value = "pageSize",required = false)String ps,@RequestParam(value = "pageNumber",required = false)String pn){
        try {


        int pageSize = Integer.parseInt(ps);
        int pageNumber = Integer.parseInt(pn);
        if(pageSize > 0 && pageNumber >=0){

            model.addAttribute("pagingableItem",
                    productService.getListProducts(pageSize,pageNumber));


        }else {
            model.addAttribute("pagingableItem",
                    productService.getListProducts(10,0));
        }}
        catch (Exception e){
            model.addAttribute("pagingableItem",productService.getListProducts(10,0));
        }
        return "index";

    }

    @GetMapping("/homelanding")
    public String landing(Model model){
        HomeLandingVM vm = new HomeLandingVM();
        LogoVM logoVM = new LogoVM("SpringMVC","/img/logo.png","SpringMVC","SMVC");

        ArrayList<MenuItemVM> listHrMenuItems = new ArrayList<>();
        listHrMenuItems.add(new MenuItemVM("Menu 01","/homelanding"));
        listHrMenuItems.add(new MenuItemVM("Menu 02","/homelanding"));
        listHrMenuItems.add(new MenuItemVM("Menu 03","/homelanding"));
        listHrMenuItems.add(new MenuItemVM("Menu 04","/homelanding"));

        listHrMenuItems.get(0).getChildren().add(new MenuItemVM("Menu 01-01","/homelanding"));
        listHrMenuItems.get(0).getChildren().add(new MenuItemVM("Menu 01-02","/homelanding"));
        listHrMenuItems.get(0).getChildren().add(new MenuItemVM("Menu 01-03","/homelanding"));
        listHrMenuItems.get(0).getChildren().add(new MenuItemVM("Menu 01-04","/homelanding"));


        ArrayList<BannerVM> listBanners = new ArrayList<>();
        listBanners.add(new BannerVM("https://w3schools.com/bootstrap/la.jpg","Los Angeles"));
        listBanners.add(new BannerVM("https://w3schools.com/bootstrap/chicago.jpg","Chicago"));
        listBanners.add(new BannerVM("https://w3schools.com/bootstrap/ny.jpg","New York"));

        ArrayList<MenuItemVM> listVtMenuItems = new ArrayList<>();
        listVtMenuItems.add(new MenuItemVM("Menu aside 01","/homelanding"));
        listVtMenuItems.add(new MenuItemVM("Menu aside 02","/homelanding"));
        listVtMenuItems.add(new MenuItemVM("Menu aside 03","/homelanding"));
        listVtMenuItems.add(new MenuItemVM("Menu aside 04","/homelanding"));

        PagingableItemList<Product> pagingableItemListHot = productService.getListProducts(8,1);
        ArrayList<ProductVM> listHotProductVMs = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Product product:pagingableItemListHot.getListData() ) {
            ProductVM productVM  = modelMapper.map(product,ProductVM.class);
            listHotProductVMs.add(productVM);
        }

        PagingableItemList<Product> pagingableItemListTrend = productService.getListProducts(8,0);
        ArrayList<ProductVM> listTrendProductVMs = new ArrayList<>();
        for (Product product:pagingableItemListTrend.getListData()) {
            ProductVM productVM = modelMapper.map(product,ProductVM.class);
            listTrendProductVMs.add(productVM);
        }

        vm.setLogo(logoVM);
        vm.setListBanners(listBanners);
        vm.setListHotProducts(listHotProductVMs);
        vm.setListTrendProducts(listTrendProductVMs);
        vm.setListHrMenuItems(listHrMenuItems);
        vm.setListVtMenuItemsAside(listVtMenuItems);
        model.addAttribute("vm",vm);
        return "homelanding";

    }

}
