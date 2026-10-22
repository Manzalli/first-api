package projects_first_API.dtos;

import projects_first_API.entities.Product;

public record ProductDTO (Integer id, String name, double price){

        public ProductDTO (Product entity) {
            this(entity.getId(), entity.getName(), entity.getPrice());
        }

}