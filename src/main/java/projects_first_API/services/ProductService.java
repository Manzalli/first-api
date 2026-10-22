package projects_first_API.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects_first_API.dtos.ProductDTO;
import projects_first_API.dtos.ProductRequestDTO;
import projects_first_API.interfaces.ProductRepository;
import projects_first_API.entities.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService {

   @Autowired
    private ProductRepository dataBase;

   public List<ProductDTO> getAllProducts (){
       return dataBase.findAll()
               .stream()
               .map(product -> new ProductDTO(product))
               .collect(Collectors.toList());
   }

   public ProductDTO addProduct (ProductRequestDTO p){
       Product whateverProduct = new Product (p.name(), p.price());
       ProductDTO added = new ProductDTO (dataBase.save(whateverProduct));
       return added;
   }

   public Optional<ProductDTO> getProductById (Integer id){
        return dataBase.findById(id).map(product -> new ProductDTO(product));
   }

   public Optional<ProductDTO> removeProduct(Integer id){
       Optional<ProductDTO> search = dataBase.findById(id).map(product -> new ProductDTO(product));
        if(search.isPresent()){
            dataBase.deleteById(id);
        }
        return search;
   }

   public Optional<ProductDTO> updateProduct(ProductRequestDTO newProduct, Integer id){
       Optional<Product> search = dataBase.findById(id);
          Product selectedProduct = new Product (id, newProduct.name(), newProduct.price());
             ProductDTO updated = null;

                if(search.isPresent()){
                    updated = new ProductDTO (dataBase.save(selectedProduct));
                }

       return Optional.ofNullable(updated);
   }
}
