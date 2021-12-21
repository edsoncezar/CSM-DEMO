package br.com.alcatellucent.csm.beans;

/**
 * @author
 * @since 1.1
 * @hibernate.class table="users" lazy = "false"
 */

public class Users {
    
    private long id;
    private String name;
    private String mail;
    private String password;
    private Long contextId;

    public Long getContextId() {
	return contextId;
    }

    public void setContextId(Long contextId) {
	this.contextId = contextId;
    }

    /**
     * @hibernate.id column="id" generator-class="increment"
     */
    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    /**
     * @hibernate.property column="name"
     */
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    /**
     * @hibernate.property column="mail"
     */
    public String getMail() {
	return mail;
    }

    public void setMail(String mail) {
	this.mail = mail;
    }

    /**
     * @hibernate.property column="password"
     */
    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
