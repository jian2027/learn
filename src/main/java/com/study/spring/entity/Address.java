package com.study.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_ADDRESS")
public class Address implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(generator = "userGenerator") 
    @GenericGenerator(name = "userGenerator", strategy = "uuid")
    @Column(name = "ADDRESS_ID", nullable = false, length = 11)
    private String id;
    
    @Column(name = "province")
    private String province;
    
    @Column(name = "city")
    private String city;
    
    
    public Address() {
    }
    
    public Address(String province, String city) {
          this.province = province;
          this.city = city;
    }
    
    
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProvince() {
          return province;
    }
    public void setProvince(String province) {
          this.province = province;
    }
    public String getCity() {
          return city;
    }
    public void setCity(String city) {
          this.city = city;
    }
    @Override
    public String toString() {
		return "Address [id=" + id + ", province=" + province + ", city=" + city + "]";
    }
}
