/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamExp;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "Transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT m FROM Transactions m"),
    @NamedQuery(name = "Transactions.findByTransactID", query = "SELECT m FROM Transactions m WHERE m.transactID = :transactID"),
    @NamedQuery(name = "Transactions.findByUserID", query = "SELECT m FROM Transactions m WHERE m.userID = :userID"),
    @NamedQuery(name = "Transactions.findByIDcategory", query = "SELECT m FROM Transactions m WHERE m.IDcategory = :IDcategory"),
    @NamedQuery(name = "Transactions.findByVal", query = "SELECT m FROM Transactions m WHERE m.val = :val")})
public class Transactions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transact_ID")
    private Short transactID;
    
//    @Column(name = "IDcategory")
//    private Short IDcategory;
    
    @JoinColumn(name = "id_category", referencedColumnName = "categories_id")
    @ManyToOne(optional = false)
    private Categories IDCategory;
    
//    @Column(name = "user_ID")
//    private Short userID;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User userID;
    
    
    
    

    
    @Column(name = "val")
    private Integer val;

    
    
    
    
    public Transactions() {
    }

    public Transactions(Short transactID) {
        this.transactID = transactID;
    }

    public Transactions(Short transactID, Categories IDcategory) {
    this.transactID = transactID;
    this.IDCategory = IDCategory;
    }

//       public Depense() {
//    }

//    public Depense(Integer idDepense) {
//        this.idDepense = idDepense;
//    }

//    public Depense(Integer idDepense, Categories idCategory, Date dateDepense) {
//        this.idDepense = idDepense;
//        this.idCategory = idCategory;
//        this.dateDepense = dateDepense;
//    } 
    
    
    public Short getTransactID() {
        return transactID;
    }

    public void setTransactID(Short transactID) {
        this.transactID = transactID;
    }
    
//    public Integer getIdDepense() {
//        return idDepense;
//    }

//    public void setIdDepense(Integer idDepense) {
//        this.idDepense = idDepense;
//    }

    
        public Categories getIDcategory() {
        return IDCategory;
    }

    public void setIDcategory(Categories IDcategory) {
        this.IDCategory = IDcategory;
    }

//    public Categories getIdCategory() {
//        return idCategory;
//    }

//    public void setIdCategory(Categories idCategory) {
//        this.idCategory = idCategory;
//    }    
    
    


    @XmlTransient
    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

//    @XmlTransient

//    public int getDepense() {
//        return depense;
//    }

//    public void setDepense(int depense) {
//        this.depense = depense;
 //   }   
    
    
    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

//    public User getIdUser() {
//        return idUser;
//    }

//    public void setIdUser(User idUser) {
//        this.idUser = idUser;
//    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactID != null ? transactID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.transactID == null && other.transactID != null) || (this.transactID != null && !this.transactID.equals(other.transactID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FamExp.Transactions[ transactID=" + transactID + " ]";
    }
    
}
