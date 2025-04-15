package pattern.library.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pattern.library.controller.model.DesignerData;
import pattern.library.controller.model.DesignerData.PatternData;
import pattern.library.controller.model.DesignerData.TypeData;
import pattern.library.service.DesignerService;

@RestController
@RequestMapping("/pattern_library")
@Slf4j
public class PatternLibraryController {
	@Autowired
	private DesignerService designerService; 
	
	//Creating a Designer
	@PostMapping("/designer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public DesignerData createDesigner(@RequestBody DesignerData designerData) {
		log.info("Creating Designer{}", designerData);
		return designerService.SaveDesigner(designerData);
	}
	
	//Updating a Designer
	@PutMapping("designer/{designerId}")
	public DesignerData updateDesigner(@PathVariable Long designerId, 
			@RequestBody DesignerData designerData) {
		designerData.setDesignerId(designerId);
		log.info("Updating Designer {}", designerData);
		return designerService.SaveDesigner(designerData);
	}
	
	//Retrieving a Designer by Id
	@GetMapping("/designer/{designerId}")
	public DesignerData retrieveDesigner(@PathVariable Long designerId) {
		log.info("Retrieving Designer with ID={}", designerId);
		return designerService.retrieveDesignerById(designerId);	
	}
	
	//Retrieving all Designers
	@GetMapping("/designer")
	public List<DesignerData> retrieveAllDesigners(){
		log.info("Retrieving all Designers");
		return designerService.retrieveAllDesigners();
	}

	//Deleting a Designer with Id
	@DeleteMapping("/designer/{designerId}")
	public Map<String, String> deleteDesigner(@PathVariable Long designerId){
		log.info("Deleting Designer with ID=" + designerId + ".");
		designerService.deleteDesigner(designerId);
		
		return Map.of("message", "Designer with ID=" + designerId 
				+ "was deleted succesfully.");
	}
	
	//Retrieving all Types
	@GetMapping("/type")
	public List<TypeData> retrieveAllTypes(){
		log.info("Retrieving all Types");
		return designerService.retrieveAllTypes();
	}
	
	//Adding Type and Designer to Designer-Type table
	@PostMapping("/designer/{designerId}/type/{typeId}")
	public Map<String, String> addTypeToDesigner(@PathVariable Long designerId,@PathVariable Long typeId){
		log.info("Add Type with ID=" + typeId + " to Designer with ID=" + designerId + ".");
		designerService.addTypeToDesigner(designerId, typeId);
		
		return Map.of("message", "Type with ID=" + typeId 
				+ " was added succesfully to Designer with ID=" 
				+ designerId);
	}

	//Adding a Pattern
	@PostMapping("/designer/{designerId}/pattern")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PatternData createPattern(@PathVariable Long designerId, @RequestBody PatternData patternData) {
		log.info("Creating Pattern{}", patternData);
		return designerService.savePattern(designerId, patternData);
	}
	
	
	//Retrieving a Pattern by Id
	@GetMapping("/pattern/{patternId}")
	public PatternData retrievepattern(@PathVariable Long patternId) {
		log.info("Retrieving Pattern with ID={}", patternId);
		return designerService.retrievePatternById(patternId);
		
	}
	
	//Retrieving all Patterns
	@GetMapping("/pattern")
	public List<PatternData> retrieveAllPatterns(){
		log.info("Retrieving all Patterns");
		return designerService.retrieveAllPatterns();
	}
	
	//Deleting a Pattern with Id
		@DeleteMapping("/pattern/{patternId}")
		public Map<String, String> deletePattern(@PathVariable Long patternId){
			log.info("Deleting Pattern with ID=" + patternId + ".");
			designerService.deletePattern(patternId);
			
			return Map.of("message", "Pattern with ID=" + patternId 
					+ " was deleted succesfully.");
		}

}



