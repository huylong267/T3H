package application.viewmodel.homelanding;

import application.viewmodel.common.LogoVM;
import application.viewmodel.common.ProductVM;

import java.util.ArrayList;

public class HomeLandingVM {
    private LogoVM logo;
    private ArrayList<MenuItemVM> listHrMenuItems;
    private  ArrayList<MenuItemVM> listVtMenuItemsAside;
    private ArrayList<ProductVM>  listHotProducts;
    private  ArrayList<ProductVM> listTrendProducts;
    private ArrayList<BannerVM>  listBanners;

    public LogoVM getLogo() {
        return logo;
    }

    public void setLogo(LogoVM logo) {
        this.logo = logo;
    }

    public ArrayList<MenuItemVM> getListHrMenuItems() {
        return listHrMenuItems;
    }

    public void setListHrMenuItems(ArrayList<MenuItemVM> listHrMenuItems) {
        this.listHrMenuItems = listHrMenuItems;
    }

    public ArrayList<MenuItemVM> getListVtMenuItemsAside() {
        return listVtMenuItemsAside;
    }

    public void setListVtMenuItemsAside(ArrayList<MenuItemVM> listVtMenuItemsAside) {
        this.listVtMenuItemsAside = listVtMenuItemsAside;
    }

    public ArrayList<ProductVM> getListHotProducts() {
        return listHotProducts;
    }

    public void setListHotProducts(ArrayList<ProductVM> listHotProducts) {
        this.listHotProducts = listHotProducts;
    }

    public ArrayList<ProductVM> getListTrendProducts() {
        return listTrendProducts;
    }

    public void setListTrendProducts(ArrayList<ProductVM> listTrendProducts) {
        this.listTrendProducts = listTrendProducts;
    }

    public ArrayList<BannerVM> getListBanners() {
        return listBanners;
    }

    public void setListBanners(ArrayList<BannerVM> listBanners) {
        this.listBanners = listBanners;
    }
}
