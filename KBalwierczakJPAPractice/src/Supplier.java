import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int supplier_ID;

    @NotNull
    private String companyName;

    private String street;
    private String city;

    @OneToMany
    private Set<Product> products = new HashSet<>();

    public Supplier(){
    }

    public Supplier(String companyName, String street, String city) {
        this.companyName = companyName;
        this.street = street;
        this.city = city;
    }

    public int getId() {
        return supplier_ID;
    }

    public void addProduct(Product product){
        products.add(product);
        product.setSupplier(this);
    }

    public String getCompanyName() {
        return companyName;
    }
}