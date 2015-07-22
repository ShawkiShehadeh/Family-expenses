/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamExp;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
//import FamExp.MD5;

/**
 *
 * @author user
 */
@Entity
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT m FROM User m"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT m FROM User m WHERE m.userId = :userId"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT m FROM User m WHERE m.userName = :userName"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT m FROM User m WHERE m.password = :password"),
    @NamedQuery(name = "User.findByUserType", query = "SELECT m FROM User m WHERE m.userType = :userType")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "userId")
    private Integer userId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "userName")
    private String userName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Password")
    private String password;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "userType")
    private int userType;

    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Userid")
    private Collection<Transactions> TransactionsCollection;
    
    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(Integer userId, String userName, String password, int userType) {
        this.userId = userId;
        this.userName = userName;
    //    this.password = MD5.getHashString(password);
        this.password =   MD5.getHashString(password);
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
    //    return  MD5.getHashString(password);
        return  password;
    }

    public void setPassword(String password) {
    //    this.password = password;
        if(password.length() > 1){this.password =   MD5.getHashString(password);}
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @XmlTransient
    public Collection<Transactions> getTransactionsCollection() {
        return TransactionsCollection;
    }

    public void setTransactionsCollection(Collection<Transactions> TransactionsCollection) {
        this.TransactionsCollection = TransactionsCollection;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FamExp.User[ userId=" + userId + " ]";
    }
    
}
