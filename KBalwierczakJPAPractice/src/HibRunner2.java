import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//import org.hibernate.query.Query;

import javax.persistence.*;
import java.util.List;

public class HibRunner2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("myDatabaseConfig");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();


        Category category = new Category("Clothes");
        Category category2 = new Category("Media");
//        Category category3 = new Category("Shoes");
        Supplier supplier = new Supplier("Supplier1", "Street 10", "NYC");
        Supplier supplier2 = new Supplier("Supplier2", "Street 153", "Cracow");
        Supplier supplier3 = new Supplier("Supplier3", "Street 4565", "Warsaw");
        Supplier supplier4 = new Supplier("Supplier4", "Street 12341", "Manchester");
        Product product = new Product("produkt1", 45);
        Product product2 = new Product("produkt2", 582);
        Product product3 = new Product("produkt3", 96);
        Product product4 = new Product("produkt4", 1232);
        Product product5 = new Product("produkt5", 741);
        Product product6 = new Product("produkt6", 452);
//        Product product7 = new Product("produkt7", 857);
//        Product product8 = new Product("produkt8", 9987);

        product.setCategory(category);
        product2.setCategory(category2);
        product3.setCategory(category);
        product4.setCategory(category2);
        product5.setCategory(category);
        product6.setCategory(category2);

        supplier.addProduct(product);
        supplier.addProduct(product5);
        supplier.addProduct(product6);
        supplier2.addProduct(product2);
        supplier3.addProduct(product3);
        supplier4.addProduct(product4);

        MyTransaction myTransaction = new MyTransaction();
        MyTransaction myTransaction2 = new MyTransaction();
        myTransaction.addProduct(product,10);
        myTransaction.addProduct(product2,10);
        myTransaction.addProduct(product3,10);
        myTransaction2.addProduct(product4,30);
        myTransaction2.addProduct(product5,30);
        myTransaction2.addProduct(product6,30);

        em.persist(supplier);
        em.persist(supplier2);
        em.persist(supplier3);
        em.persist(supplier4);
//        em.persist(product);
//        em.persist(product2);
//        em.persist(product3);
//        em.persist(product4);
//        em.persist(product5);
//        em.persist(product6);
        em.persist(category);
        em.persist(category2);
        em.persist(myTransaction);
        em.persist(myTransaction2);

        etx.commit();
        etx.begin();
        Supplier foundSupplier = em.find(Supplier.class, 1);
        String hql = "FROM Product P WHERE P.supplier = :supplier";
        Query query = em.createQuery(hql);
        query.setParameter("supplier",foundSupplier);
        List<Product> results = query.getResultList();
        System.out.println("Products supplied by "+foundSupplier.getCompanyName());
        for (Product prod : results) {
            System.out.println(prod.getName());
        }

        Product foundProduct = em.find(Product.class, 6);
        hql = "FROM Supplier S WHERE :product member of S.products";
        query = em.createQuery(hql);
        query.setParameter("product",foundProduct);
        Supplier supplierResult = (Supplier) query.getSingleResult();
        System.out.println("Product "+foundProduct.getName()+ " is supplied by "+supplierResult.getCompanyName());

        //MyTransaction foundTransaction = session.get(MyTransaction.class, 13);

//        hql = "FROM MyTransaction T join Product P where :product member of T.productsInTransaction";
//        query = session.createQuery(hql);
//        query.setParameter("product",foundProduct);
//        List<Product> transactionResults = query.list();
//        System.out.println("Transactions containing "+foundProduct.getName()+": ");
////        System.out.println("Products in transaction nr "+foundTransaction.getTransactionID()+": ");
//        for (Product prod : transactionResults) {
//            System.out.println(prod.getName());
//        }

        etx.commit();

        em.close();
    }
}