package pattern.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import pattern.library.controller.model.DesignerData;
import pattern.library.entity.Designer;

public class PatternLibraryServiceTestSupport {
	
	private static final String DESIGNER_TABLE = "designer";
	//@formatter:off
	private DesignerData insertDesigner1 = new DesignerData(
			1L,
			"Vero Cattivera",
			"www.verocattivera.com",
			"vero@verocattivera.com"			
	);
	
	private DesignerData insertDesigner2 = new DesignerData(
			2L,
			"Veronica Reyes",
			"www.veronicarreyes.com",
			"vero@veronicareyes.com"			
	);
	
	private DesignerData updateDesigner1 = new DesignerData(
			1L,
			"Verito Cattivera",
			"www.veritocattivera.com",
			"verito@verocattivera.com"			
			);
	//@formatter:on
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PatternLibraryController designerController;
	
	protected DesignerData buildInsertDesigner(int which) {
	
		return which == 1 ? insertDesigner1 : insertDesigner2;
	}
	
	protected int rowsInDesignertable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, DESIGNER_TABLE);
	}

	protected DesignerData insertDesigner(DesignerData designerData) {
		Designer designer = designerData.toDesginer();
		DesignerData clone = new DesignerData(designer);
		
		clone.setDesignerId(null);
		return designerController.createDesigner(clone);
	}
	
	protected DesignerData retrieveDesigner(Long designerId) {
		return designerController.retrieveDesigner(designerId);
	}

	protected List<DesignerData> insertTwoDesigners() {
		DesignerData designer1 = insertDesigner(buildInsertDesigner(1));
		DesignerData designer2 = insertDesigner(buildInsertDesigner(2));
		
		return List.of(designer1,designer2);
	}
	
	protected List<DesignerData> retrieveAllDesigners() {
		return designerController.retrieveAllDesigners();
	}
	

	protected DesignerData updateDesigner(DesignerData designerData) {
		return designerController.updateDesigner(designerData.getDesignerId(),
				designerData);
	}

	protected DesignerData buildUpdateDesigner(int i) {
		return updateDesigner1;
	}
}
