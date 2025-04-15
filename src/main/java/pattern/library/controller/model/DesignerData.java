package pattern.library.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pattern.library.entity.Designer;
import pattern.library.entity.Pattern;
import pattern.library.entity.Type;

//DTO to convert Json to Java data and vice versa
///*****DesignerData******
@Data
@NoArgsConstructor
public class DesignerData {
	private Long designerId;
	private String name;
	private String website;
	private String email;
	private Set<PatternData> patterns = new HashSet<>();
	private Set<TypeData> types = new HashSet<>();
	
	//constructor for DesignerData
	public DesignerData(Designer designer) {
		this.designerId = designer.getDesignerId();
		this.name = designer.getName();
		this.website = designer.getWebsite();
		this.email = designer.getEmail();
		
		//set pattern data based on pattern objects
		for (Pattern pattern : designer.getPatterns()) {
			this.patterns.add(new PatternData(pattern));
		}
		
		//set type data based on type objects
		for (Type type : designer.getTypes()) {
			this.types.add(new TypeData(type));
		}
		
	}
	
	
	//Constructor that will take the designer variables
	public DesignerData(Long designerId, String name, String website,
			String email){
		this.designerId = designerId;
		this.name = name;
		this.website =  website;
		this.email = email;	
	}
	
	//convert from designer object back to designer data object
	public Designer toDesginer() {
		Designer designer = new Designer();
		
		designer.setDesignerId(designerId);
		designer.setName(name);
		designer.setWebsite(website);
		designer.setEmail(email);
		
		for(PatternData patternData : patterns) {
			designer.getPatterns().add(patternData.toPattern());
		}
		
		for (TypeData typeData : types) {
			designer.getTypes().add(typeData.toType());
		}
		
		return designer;
	}
	
	//******PatternData*******
	@Data
	@NoArgsConstructor
	public static class PatternData{
		private Long patternId;
		private String name;
		private String size;
		private String difficulty;
		private String notes;
		
		//PatternData constructor
		public PatternData(Pattern pattern) {
			this.patternId = pattern.getPatternId();
			this.name = pattern.getName();
			this.size = pattern.getSize();
			this.difficulty = pattern.getDifficulty();
			this.notes = pattern.getNotes();
			
		}
		
		public Pattern toPattern() {
			Pattern pattern = new Pattern();
			
			pattern.setPatternId(patternId);
			pattern.setName(name);
			pattern.setSize(size);
			pattern.setDifficulty(difficulty);
			pattern.setNotes(notes);
			
			return pattern;
		}
		
	}
	
	
	//*****TypeData*****
	
	@Data
	@NoArgsConstructor
	public static class TypeData{
		private Long typeId;
		private String name;
		
		//TypeData constructor
		public TypeData(Type type) {
			this.typeId = type.getTypeId();
			this.name = type.getName();
		}
		
		public Type toType() {
			Type type = new Type();
			
			type.setTypeId(typeId);
			type.setName(name);
			
			return type;
		}
	}
	
	
}

