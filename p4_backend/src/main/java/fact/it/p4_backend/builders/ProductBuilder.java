package fact.it.p4_backend.builders;

import fact.it.p4_backend.model.Product;

public class ProductBuilder {
    private String id;
    private String title = "no title";
    private String description = "no description";
    private double price ;
    private double rating;
    private double rates ;

    public ProductBuilder() {
    }
    public Product buildProduct(){
        return new Product(id, title, description, price, rating, rates);
    }

    public ProductBuilder id(String id){
        this.id = id;
        return this;
    }

    public ProductBuilder title(String title){
        this.title = title;
        return this;
    }

    public ProductBuilder description(String description){
        this.description = description;
        return this;
    }

    public ProductBuilder price(double price){
        this.price = price;
        return this;
    }

    public ProductBuilder rating(double rating){
        this.rating = rating;
        return this;
    }

    public ProductBuilder rates(double rates){
        this.rates = rates;
        return this;
    }


}
