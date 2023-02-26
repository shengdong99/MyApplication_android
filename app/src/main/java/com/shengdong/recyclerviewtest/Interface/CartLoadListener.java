package com.shengdong.recyclerviewtest.Interface;

import com.shengdong.recyclerviewtest.utils.model.ShoeCart;

import java.util.List;

public interface CartLoadListener {
    void onCartLoadSuccess(List<ShoeCart> cartList);
    void onCartLoadFailed(String message);
}
