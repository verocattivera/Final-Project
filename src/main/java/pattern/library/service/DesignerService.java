package pattern.library.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pattern.library.controller.model.DesignerData;
import pattern.library.controller.model.DesignerData.PatternData;
import pattern.library.controller.model.DesignerData.TypeData;
import pattern.library.dao.DesignerDao;
import pattern.library.dao.PatternDao;
import pattern.library.dao.TypeDao;
import pattern.library.entity.Designer;
import pattern.library.entity.Pattern;
import pattern.library.entity.Type;

@Service
public class DesignerService {

	@Autowired
	private DesignerDao designerDao;
	
	@Autowired
	private TypeDao typeDao;
	
	@Autowired
	private PatternDao patternDao;
	
	@Transactional(readOnly = false)
	public DesignerData SaveDesigner(DesignerData designerData) {
		//this will convert from designer data to designer entity
		Designer designer = designerData.toDesginer();
		Designer dbDesigner = designerDao.save(designer);
		
		return new DesignerData(dbDesigner);
	}

	@Transactional(readOnly = true)
	public DesignerData retrieveDesignerById(Long designerId) {
		Designer designer =  findDesignerById(designerId);
		return new DesignerData(designer);
	}

	private Designer findDesignerById(Long designerId) {
		return designerDao.findById(designerId)
				.orElseThrow(() -> new NoSuchElementException(
						"Designer with ID=" + designerId + " was not found"));
		
	}

	@Transactional(readOnly = true)
	public List<DesignerData> retrieveAllDesigners() {	
		return designerDao.findAll()
				.stream()
				.map(DesignerData::new)
				.toList();
	}

	@Transactional(readOnly = false)
	public void deleteDesigner(Long designerId) {
		Designer designer = findDesignerById(designerId);
		designerDao.delete(designer);
	}
	
	@Transactional(readOnly = true)
	public List<TypeData> retrieveAllTypes() {	
		return typeDao.findAll()
				.stream()
				.map(TypeData::new)
				.toList();
	}
	
	@Transactional(readOnly = false)
	public void addTypeToDesigner(Long designerId, Long typeId) {
		Designer designer = findDesignerById(designerId);
		Type type = findTypeById(typeId);
		type.getDesigners().add(designer);
		designer.getTypes().add(type);
	}

	private Type findTypeById(Long typeId) {
		return typeDao.findById(typeId)
				.orElseThrow(() -> new NoSuchElementException(
						"Type with ID=" + typeId + " was not found"));
	}

	@Transactional(readOnly = false)
	public PatternData savePattern(Long designerId, PatternData patternData) {
			Designer designer = findDesignerById(designerId);
		//this will convert from pattern data to pattern entity
			System.out.println(patternData);
			Pattern pattern = patternData.toPattern();
			System.out.println(pattern);
			pattern.setDesigner(designer);
			designer.getPatterns().add(pattern);
			Pattern dbPattern = patternDao.save(pattern);
			
			return new PatternData(dbPattern);	
	}

	@Transactional(readOnly = false)
	public PatternData updatePattern(PatternData patternData) {

		//this will convert from pattern data to pattern entity
			Pattern pattern = patternData.toPattern();
			Long designerId = pattern.getDesigner().getDesignerId();
			Designer designer = findDesignerById(designerId);
			pattern.setDesigner(designer);
			designer.getPatterns().add(pattern);
			Pattern dbPattern = patternDao.save(pattern);
			
			return new PatternData(dbPattern);	
	}
	
	@Transactional(readOnly = true)
	public List<PatternData> retrieveAllPatterns() {
		return patternDao.findAll()
				.stream()
				.map(PatternData::new)
				.toList();
	}
	
	@Transactional(readOnly = false)
	public void deletePattern(Long patternId) {
		Pattern pattern = findPatternById(patternId);
		patternDao.delete(pattern);
	}

	private Pattern findPatternById(Long patternId) {
		return patternDao.findById(patternId)
				.orElseThrow(() -> new NoSuchElementException(
						"Pattern with ID=" + patternId + " was not found"));
	}

	@Transactional(readOnly = true)
	public PatternData retrievePatternById(Long patternId) {
			Pattern pattern =  findPatternById(patternId);
			return new PatternData(pattern);
	}
	
	
	
	
}
