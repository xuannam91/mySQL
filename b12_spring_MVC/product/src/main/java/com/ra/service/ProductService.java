package com.ra.service;

import com.ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IGenericService<Product,Integer>{
    private List<Product> list = new ArrayList<>();

    public ProductService(){
        list.add(new Product(1,"pro1",5.0));
        list.add(new Product(2,"pro2",7.0));
        list.add(new Product(3,"pro3",9.0));
    }


    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Boolean save(Product product) {
        list.add(product);
        return true;
    }

    @Override
    public Product find(Integer integer) {
        for (Product p : list) {
            if(p.getProductCode() == integer){
                return p;
            }
        }
        return null;
    }

    @Override
    public Boolean update(Product product, Integer id) {
        int index = findIndex(id);
        if(index != -1){
            list.set(index,product);

        }

        return true;
    }

    @Override
    public void delete(Integer id) {
        Product p = find(id);
        list.remove(p);
    }


    public int findIndex(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProductCode() == id) {
                return i;
            }
        }
        return -1;
    }
}
