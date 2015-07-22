/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FamExp;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "UsersGroups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersGroups.findAll", query = "SELECT m FROM UsersGroups m"),
    @NamedQuery(name = "UsersGroups.findByUsergroup", query = "SELECT m FROM UsersGroups m WHERE m.UsersGroupsPK.usergroup = :usergroup"),
    @NamedQuery(name = "UsersGroups.findByUserID", query = "SELECT m FROM UsersGroups m WHERE m.UsersGroupsPK.userID = :userID")})
public class UsersGroups implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsersGroupsPK UsersGroupsPK;
     
    public UsersGroups() {
    }

    public UsersGroups(UsersGroupsPK UsersGroupsPK) {
        this.UsersGroupsPK = UsersGroupsPK;
    }

    public UsersGroups(String groupName, int userId) {
        this.UsersGroupsPK = new UsersGroupsPK(groupName, userId);
    }


    public UsersGroupsPK getUsersGroupsPK() {
        return UsersGroupsPK;
    }

    public void setUsersGroupsPK(UsersGroupsPK UsersGroupsPK) {
        this.UsersGroupsPK = UsersGroupsPK;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (UsersGroupsPK != null ? UsersGroupsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersGroups)) {
            return false;
        }
        UsersGroups other = (UsersGroups) object;
        if ((this.UsersGroupsPK == null && other.UsersGroupsPK != null) || (this.UsersGroupsPK != null && !this.UsersGroupsPK.equals(other.UsersGroupsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FamExp.UsersGroups[ UsersGroupsPK=" + UsersGroupsPK + " ]";
    }
    
}
