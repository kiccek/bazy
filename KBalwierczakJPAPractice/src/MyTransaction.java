import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MyTransaction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int transactionID;

    private int quantity=0;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    Set<Product> productsInTransaction = new HashSet<>();

    public MyTransaction() {
    }

    public void addProduct(Product product, int productQuantity) {
        productsInTransaction.add(product);
        quantity+=productQuantity;
        product.reduceQuantity(productQuantity);
    }

    public int getTransactionID() {
        return transactionID;
    }
}
