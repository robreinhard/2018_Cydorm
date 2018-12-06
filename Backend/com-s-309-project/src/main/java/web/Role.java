package web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;


/**
 * The Class Role.
 */
@Entity
@Table(name = "role")
public class Role {
    
    /** The role id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int role_id;
    
    /** The role. */
    @Column(name = "role")
    private String role;
}