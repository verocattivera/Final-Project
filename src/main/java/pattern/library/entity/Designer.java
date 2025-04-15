package pattern.library.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

//Annotations Entity from jakarta persistence and Data form lombok to 
//generate getters and setters, toString, equals, hashcode
@Entity
@Data
public class Designer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long designerId;
	
	private String name;
	private String website;
	private String email;
	
	
	//Cascade all to save and delete all the patterns in relation with the designer
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "designer", cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Set<Pattern> patterns = new HashSet<>();
	
	
	// to handle the many to many relation with Type
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "designer_type",
			joinColumns = @JoinColumn(name = "designer_id"),
			inverseJoinColumns = @JoinColumn( name = "type_id")
	)
	private Set<Type> types = new HashSet<>();
}
