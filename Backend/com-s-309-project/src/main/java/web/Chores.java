package web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "chores")
public class Chores {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int chores_id;
	
	

}
