package com.study.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "userGenerator") 
    @GenericGenerator(name = "userGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 11)
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PWD")
    private String pwd;

    /**
     * @ManyToOne 使用此标签建立多对一关联，此属性在“多”方使用注解在我们的“一”方属性上
     * @cascade 指定级联操作，以数组方式指定，如果只有一个，可以省略“{}”
     * @fetch 定义抓取策略
     * @optional 定义是否为必需属性，如果为必需（false），但在持久化时user = null,则会持久化失败
     * @targetEntity 目标关联对象，默认为被注解属性所在类
     * @ManyToOne(cascade ={CascadeType.REFRESH},fetch = FetchType.LAZY,optional = false,targetEntity = Address.class)
     */
    /**
	 * 注意：如果通过懒加载，
	 * @ManyToOne(fetch = FetchType.LAZY) 
	 * 需要配置：
	 * @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })，
	 * 否则报错：No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer......
	 */
    @ManyToOne()
    @JoinColumn(name="ADDRESS_ID")
    private Address address;
    
    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    public User(String id, String userName, String passWord,Address address) {
        this.id = id;
        this.name = userName;
        this.pwd = passWord;
        this.address = address;
    }

    public User(String id, String userName, String passWord) {
        this.id = id;
        this.name = userName;
        this.pwd = passWord;
    }
    
    public String getId() {
         return id;
    }

    public void setId(String id) {
         this.id = id;
    }

    public String getName() {
         return name;
    }

    public void setName(String name) {
         this.name = name;
    }

    public String getPwd() {
         return pwd;
    }

    public void setPwd(String pwd) {
         this.pwd = pwd;
    }
    
    public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
    public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + ", address=" + address + "]";
    }
}