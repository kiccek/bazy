import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryID;

    @NotNull
    String name;

    @OneToMany
    List<Product> products = new ArrayList<>();


    public Category(){
    }

    public Category(String name) {
        this.name = name;
    }

//    public void addProduct(Product p){
//        products.add(p);
//    }

}