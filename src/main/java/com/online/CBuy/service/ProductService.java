package com.online.CBuy.service;

import com.online.CBuy.document.Product;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.pojo.Product.SetProduct;
import com.online.CBuy.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public AffectedRowsDto postProduct(SetProduct postProduct){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Optional<Product> product = Objects.nonNull(postProduct.getName()) ? productRepository.findByName(postProduct.getName()) : null;
        if(product.isEmpty()){
            Product product1 = new Product();
            if(Objects.nonNull(postProduct.getName())){
                product1.setName(postProduct.getName());
            }
            if(Objects.nonNull(postProduct.getDescription())){
                product1.setDescription(postProduct.getDescription());
            }
            if(Objects.nonNull(postProduct.getListKM())){
                product1.setListKM(postProduct.getListKM());
            }
            if(Objects.nonNull(postProduct.getPrice())){
                product1.setPrice(postProduct.getPrice());
            }
            if(Objects.nonNull(postProduct.getFileImage())){
                product1.setFileImage(postProduct.getFileImage());
            }
            if(Objects.nonNull(postProduct.getQuantity())){
                product1.setQuantity(postProduct.getQuantity());
            }
            if(Objects.nonNull(postProduct.getListOption())){
                product1.setListOption(postProduct.getListOption());
            }
            if(Objects.nonNull(postProduct.getSellerId())){
                product1.setSellerId(postProduct.getSellerId());
            }
            product1.setCreatedDate(new Date());
            product1.setUpdateDate(new Date());
            try{
                productRepository.save(product1);
                affectedRowsDto.setMessage("save success");
                affectedRowsDto.setAffectedRows(1);
            } catch (Exception e){
                affectedRowsDto.setMessage("save false");
            }
        } else {
            affectedRowsDto.setMessage("Already exists");
        }
        return affectedRowsDto;
    }

    public AffectedRowsDto putProduct(String id, SetProduct putProduct){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Product product = Objects.nonNull(id) ? productRepository.findOneById(new ObjectId(id)) : null;
        if(Objects.nonNull(product)){
            if(Objects.nonNull(putProduct.getName())){
                product.setName(putProduct.getName());
            }
            if(Objects.nonNull(putProduct.getDescription())){
                product.setDescription(putProduct.getDescription());
            }
            if(Objects.nonNull(putProduct.getListKM())){
                product.setListKM(putProduct.getListKM());
            }
            if(Objects.nonNull(putProduct.getPrice())){
                product.setPrice(putProduct.getPrice());
            }
            if(Objects.nonNull(putProduct.getFileImage())){
                product.setFileImage(putProduct.getFileImage());
            }
            if(Objects.nonNull(putProduct.getQuantity())){
                product.setQuantity(putProduct.getQuantity());
            }
            if(Objects.nonNull(putProduct.getListOption())){
                product.setListOption(putProduct.getListOption());
            }
            if(Objects.nonNull(putProduct.getSellerId())){
                product.setSellerId(putProduct.getSellerId());
            }
            product.setCreatedDate(new Date());
            product.setUpdateDate(new Date());
            try{
                productRepository.save(product);
                affectedRowsDto.setMessage("save success");
                affectedRowsDto.setAffectedRows(1);
            } catch (Exception e){
                affectedRowsDto.setMessage("save false");
            }
        } else {
            affectedRowsDto.setMessage("Already exists");
        }
        return affectedRowsDto;
    }
    public List<Product> getListAllProduct(){
        return productRepository.findAll();
    }

    public List<Product> getProductBySellerId(String userId){
        return productRepository.findOneBySellerId(new ObjectId(userId));
    }

    public Product getProduct(String id){
        return productRepository.findOneById(new ObjectId(id));
    }

    public AffectedRowsDto deleteProduct(String id){
        AffectedRowsDto affectedRowsDto = new AffectedRowsDto(0);
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            try {
                productRepository.deleteById(id);
                affectedRowsDto.setMessage("Product deleted successfully");
                affectedRowsDto.setAffectedRows(1);
                return affectedRowsDto;
            } catch (Exception e){
                affectedRowsDto.setMessage(e.getMessage());
                return affectedRowsDto;
            }
        } else {
            affectedRowsDto.setMessage("Product not found");
            return affectedRowsDto;
        }
    }
}
