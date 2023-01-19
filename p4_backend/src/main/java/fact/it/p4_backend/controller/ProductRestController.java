package fact.it.p4_backend.controller;

import com.contentful.java.cda.*;
import fact.it.p4_backend.builders.ProductBuilder;
import fact.it.p4_backend.model.Product;
import fact.it.p4_backend.model.User;
import fact.it.p4_backend.repository.UserRepository;
import fact.it.p4_backend.services.ContentfulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    //    Contentful client

    @Autowired
    private ContentfulService contentfulService;


    //  Constructors:
    public ProductRestController() {
    }

    //  make a Product of a CDAEntry with use of the Product builder.
    public Product initProduct(CDAEntry entry) {
        ProductBuilder BuilderProduct = new ProductBuilder()
                .id(entry.id());
        if (entry.getField("title") != null) {
            BuilderProduct.title(entry.getField("title"));
        }
        if (entry.getField("description") != null) {
            BuilderProduct.description(entry.getField("description"));
        }
        if (entry.getField("price") != null) {
            BuilderProduct.price(entry.getField("price"));
        }
        if (entry.getField("rating") != null) {
            BuilderProduct.rating(entry.getField("rating"));
        }
        if (entry.getField("rates") != null) {
            BuilderProduct.rates(entry.getField("rates"));
        }
//      return the Product that is created with the builder, only values that are not null are added.
        return BuilderProduct.buildProduct();
    }

    @GetMapping("/product")
//    public ResponseEntity<CDAEntry> getAllUsersOrderedByNameAscending() {
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            final CDAArray all = contentfulService
                    .getClient()
                    .fetch(CDAEntry.class)
                    .where("content_type", "product")
                    .all();
//        check if it is an array that is not empty.
            if (all.items().size() > 0) {
//            add each item to the predefined array.
                for (CDAResource resource : all.items()) {
                    CDAEntry entry = (CDAEntry) resource;
                    products.add(initProduct(entry));
                }
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{stringId}")
    public ResponseEntity<Product> getAllProducts(@PathVariable("stringId") String id) {

        try {
            final CDAEntry one = contentfulService
                    .getClient()
                    .fetch(CDAEntry.class)
                    .where("content_type", "product")
                    .include(10)
                    .one(id);
//            use the product creator.
            Product product = initProduct(one);

            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //    debug settings and logs for keepsake.
    @GetMapping("/product/debug")
    public ResponseEntity<Boolean> debug() {
//        public CompletableFuture<List<Product>> getAllProducts() {
//        return CompletableFuture.completedFuture(products);

//    public ResponseEntity<CDAEntry> getAllUsersOrderedByNameAscending() {
//        final CDAEntry one = client.fetch(CDAEntry.class).one("7GvdIohWekPQP5JtqYmpBC");
//        final CDAArray entry = client.fetch(CDAEntry.class).where("content_type", "<product_content_type_id>").include(10).all();
//        CDAEntry.getfield("name")
        final CDAEntry one = contentfulService
                .getClient()
                .fetch(CDAEntry.class).include(10)
                .one("7GvdIohWekPQP5JtqYmpBC");
//        [Debug] get the response entity.
        System.out.println("(one field title) " + one.getField("title"));
        System.out.println("(one) " + one);

//        [Debug] get the response space.
        CDASpace space = contentfulService
                .getClient()
                .fetchSpace();
        System.out.println("(space) " + space);

        final CDAArray type = contentfulService
                .getClient()
                .fetch(CDAContentType.class)
                .all();
//        [Debug] get all the content types.
        System.out.println("(type) " + type);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}