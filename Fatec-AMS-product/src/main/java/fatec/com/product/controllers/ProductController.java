package fatec.com.product.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.com.product.models.Product;
import fatec.com.product.models.SpecialProduct;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<SpecialProduct> specialProducts = new ArrayList<>();

    public ProductController() {
        // Produtos comuns iniciais
        products.add(new Product(1L, "Notebook", 3500.00, "Notebook bom"));
        products.add(new Product(2L, "Smartphone", 2000.00, "Smartphone Android 128GB"));
        products.add(new Product(3L, "Tablet", 1500.00, "Tablet Android 64GB"));

        // Produtos especiais iniciais
        specialProducts.add(new SpecialProduct(1L, "Notebook Pro", 5000.00, "Notebook gamer com desconto", 10.0));
        specialProducts.add(new SpecialProduct(2L, "Smart TV", 3000.00, "Smart TV 55' com desconto", 15.0));
    }

    // ─────────────────────────────────────────────
    // PRODUCT — listar
    // ─────────────────────────────────────────────

    @GetMapping
    public ArrayList<Product> getProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ─────────────────────────────────────────────
    // PRODUCT — cadastrar
    // ─────────────────────────────────────────────

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        boolean idExists = products.stream().anyMatch(p -> p.getId().equals(product.getId()));
        if (idExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // ─────────────────────────────────────────────
    // PRODUCT — alterar
    // ─────────────────────────────────────────────

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updated) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                updated.setId(id);
                products.set(i, updated);
                return ResponseEntity.ok(updated);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // ─────────────────────────────────────────────
    // PRODUCT — remover
    // ─────────────────────────────────────────────

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean removed = products.removeIf(p -> p.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // ─────────────────────────────────────────────
    // SPECIAL PRODUCT — listar
    // ─────────────────────────────────────────────

    @GetMapping("/special")
    public ArrayList<SpecialProduct> getSpecialProducts() {
        return specialProducts;
    }

    @GetMapping("/special/{id}")
    public ResponseEntity<SpecialProduct> getSpecialProductById(@PathVariable Long id) {
        return specialProducts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ─────────────────────────────────────────────
    // SPECIAL PRODUCT — cadastrar
    // ─────────────────────────────────────────────

    @PostMapping("/special")
    public ResponseEntity<SpecialProduct> createSpecialProduct(@RequestBody SpecialProduct product) {
        boolean idExists = specialProducts.stream().anyMatch(p -> p.getId().equals(product.getId()));
        if (idExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        specialProducts.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // ─────────────────────────────────────────────
    // SPECIAL PRODUCT — alterar
    // ─────────────────────────────────────────────

    @PutMapping("/special/{id}")
    public ResponseEntity<SpecialProduct> updateSpecialProduct(@PathVariable Long id,
            @RequestBody SpecialProduct updated) {
        for (int i = 0; i < specialProducts.size(); i++) {
            if (specialProducts.get(i).getId().equals(id)) {
                updated.setId(id);
                specialProducts.set(i, updated);
                return ResponseEntity.ok(updated);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // ─────────────────────────────────────────────
    // SPECIAL PRODUCT — remover
    // ─────────────────────────────────────────────

    @DeleteMapping("/special/{id}")
    public ResponseEntity<Void> deleteSpecialProduct(@PathVariable Long id) {
        boolean removed = specialProducts.removeIf(p -> p.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}