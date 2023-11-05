package com.example.prm392project.presentation.store;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.prm392project.R;

import java.util.Arrays;
import java.util.List;

public class PagerViewModel extends ViewModel {

    private List<Tab> tabs;

    {
        tabs = Arrays.asList(
                new Tab(
                        Type.HOME,
                        R.string.home,
                        R.drawable.ic_home_selector,
                        null
                ),
                new Tab(
                        Type.PRODUCT,
                        R.string.product,
                        R.drawable.ic_product_selector,
                        null
                ),
                new Tab(
                        Type.SETTING,
                        R.string.setting,
                        R.drawable.ic_setting_selector,
                        null
                )
        );
    }

    /* **********************************************************************
     * Function
     ********************************************************************** */
    public List<Tab> getTabs() {
        return tabs;
    }

    /* **********************************************************************
     * Class
     ********************************************************************** */
    public enum Type {HOME, PRODUCT, FAVORITE, Cart,SETTING}

    public static class Tab {
        public final Type type;
        public final int title;
        public final int icon;
        public Fragment fragment;

        public Tab(Type type, int title, int icon, Fragment fragment) {
            this.type = type;
            this.title = title;
            this.icon = icon;
            this.fragment = fragment;
        }
    }
}
