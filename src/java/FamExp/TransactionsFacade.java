/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamExp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author user
 */


@Stateless
public class TransactionsFacade extends AbstractFacade<Transactions> {
    @PersistenceContext(unitName = "famexpPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransactionsFacade() {
        super(Transactions.class);
    }
     public List<Transactions> name(String name) {  
           List<User> Out;  
           Out = em.createNamedQuery("User.findByUserName").setParameter("userName", name).getResultList();  
          List<Transactions> outdepense = em.createNamedQuery("User.findByUserId").setParameter("userId", Out).getResultList();  
           
           return outdepense;  
      }  
       public List<Transactions> byiduser(int x) {
         
             return em.createQuery(
    "SELECT d FROM Transactions d WHERE d.userid IN (SELECT u FROM User u WHERE u.userId = :userId) ")
    .setParameter("userId", x).getResultList();
        

}
       
         public List<Object[]> byu(){
             
              //return (List<Object[]>) em.createQuery("SELECT SUM(d.depense), d.idUser  FROM Depense d GROUP BY d.idUser").getResultList();
return (List<Object[]>) em.createQuery("SELECT SUM(d.Transactions),  u.userName , d.userid  FROM Transactions d , User u WHERE d.userid = u GROUP BY d.userid").getResultList();

          //   String qlString = "SELECT SUM(d.depense),d.idUser  FROM Depense d GROUP BY d.idUser";
          //   System.out.println(qlString);
          //  return (List<Object[]>) em.createQuery(qlString).getResultList();

             }
         
           public List<Object[]> bycat(){
              // System.out.println("hi");
              //return (List<Object[]>) em.createQuery("SELECT SUM(d.depense),d.idCategory  FROM Depense d GROUP BY d.idCategory").getResultList();
return (List<Object[]>) em.createQuery("SELECT SUM(d.val), c.categoryName , c.categoryId  FROM Transactions d , Categories c WHERE d.idCategory = c GROUP BY c.categoryId").getResultList();

             }
           
             public Object byname(String name) {
return em.createQuery(
    "SELECT u FROM User u WHERE u.userName = :userName")
    .setParameter("userName", name).getSingleResult();

}

}



