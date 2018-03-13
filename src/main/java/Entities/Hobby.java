/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author AndersHC
 */
@Entity
@Table(name = "Hobby")
@NamedQueries({
    @NamedQuery(name = "Hobby.findAll", query = "SELECT h FROM Hobby h")
    , @NamedQuery(name = "Hobby.findByHobbyId", query = "SELECT h FROM Hobby h WHERE h.hobbyId = :hobbyId")
    , @NamedQuery(name = "Hobby.findByHobbyName", query = "SELECT h FROM Hobby h WHERE h.hobbyName = :hobbyName")
    , @NamedQuery(name = "Hobby.findByDescription", query = "SELECT h FROM Hobby h WHERE h.description = :description")})
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hobbyId")
    private Integer hobbyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "hobbyName")
    private String hobbyName;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @JoinTable(name = "Person_has_Hobby", joinColumns = {
        @JoinColumn(name = "Hobby_hobbyId", referencedColumnName = "hobbyId")}, inverseJoinColumns = {
        @JoinColumn(name = "Person_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Person> personCollection;

    public Hobby() {
    }

    public Hobby(Integer hobbyId) {
        this.hobbyId = hobbyId;
    }

    public Hobby(Integer hobbyId, String hobbyName) {
        this.hobbyId = hobbyId;
        this.hobbyName = hobbyName;
    }

    public Integer getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(Integer hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Person> getPersonCollection() {
        return personCollection;
    }

    public void setPersonCollection(Collection<Person> personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hobbyId != null ? hobbyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hobby)) {
            return false;
        }
        Hobby other = (Hobby) object;
        if ((this.hobbyId == null && other.hobbyId != null) || (this.hobbyId != null && !this.hobbyId.equals(other.hobbyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Hobby[ hobbyId=" + hobbyId + " ]";
    }
    
}
