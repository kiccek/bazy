import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int productID;

    @NotNull
    private String name;
    private int unitsInStock;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "productsInTransaction", cascade = {CascadeType.PERSIST})
    private Set<MyTransaction> transactionsWithProduct;

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {
    }

    public Product(String productName, int unitsInStock) {
        this.unitsInStock = unitsInStock;
        this.name = productName;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    public String getName(){
        return name;
    }

    public void reduceQuantity(int soldQuantity) {
        unitsInStock-=soldQuantity;
    }
}
