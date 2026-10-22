package projects_first_API.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import projects_first_API.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
