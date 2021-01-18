package org.courses.pages.adminpage.data;

import java.util.ArrayList;


public class MenuItemModel {
    private String itemName;
    private String pageHeader;
    private ArrayList<MenuItemModel> subMenu;

    public String getItemName() {
        return itemName;
    }

    public String getPageHeader() {
        return pageHeader;
    }

    public ArrayList<MenuItemModel> getSubMenu() {
        return subMenu;
    }
}
