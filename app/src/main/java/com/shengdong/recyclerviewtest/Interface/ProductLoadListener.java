package com.shengdong.recyclerviewtest.Interface;

import com.shengdong.recyclerviewtest.Model.Products;
import com.shengdong.recyclerviewtest.utils.model.ShoeItem;

import java.util.List;

public interface ProductLoadListener {
    void onProductLoadSuccess(List<ShoeItem> shoeItemList);
    void onProductLoadFailed(String message);
}
