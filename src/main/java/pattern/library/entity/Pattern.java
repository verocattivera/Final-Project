package pattern.library.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// Add annotations Entity from jakarta persistence and Data form lombok to 
// generate getters and setters, toString, equals, hashcode
@Entity
@Data
public class Pattern {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patternId;
	
	private String name;
	private String size;
	private String difficulty;
	private String notes;
	
	// To point back to designer foreign key
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	//the join column is the name of the column in the designer entity.
	@JoinColumn(name = "designer_id")
	private Designer designer;
	
	
	
}
