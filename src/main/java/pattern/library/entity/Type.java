package pattern.library.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

//Annotations Entity from jakarta persistence and Data form lombok to 
//generate getters and setters, toString, equals, hashcode
@Entity
@Data
public class Type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long typeId;
	
	private String name;
	
	//Many to many relation to designer
	//"types" is the set in designer
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "types")
	private Set<Designer> designers = new HashSet<>();
	
	
}
