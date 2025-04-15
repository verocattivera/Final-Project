package pattern.library.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import pattern.library.PatternLibraryApplication;
import pattern.library.controller.model.DesignerData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE,
	classes = PatternLibraryApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@SqlConfig(encoding = "utf-8")

class PatternLibraryControllerTest extends PatternLibraryServiceTestSupport {

	@Test
	void testInsertDesigner() {
		//Given: A  Designer request.
		DesignerData request = buildInsertDesigner(1);
		DesignerData expected = buildInsertDesigner(1);
		
		//When: The Designer is added to the designer table.
		DesignerData actual = insertDesigner(request);
		
		//Then: The Designer returned is what is expected.
		assertThat(actual).isEqualTo(expected);
		
		//And: There is one row in the designer table.
		assertThat(rowsInDesignertable()).isOne();
	}

	@Test
	void testRetrieveDesigner() {
		//Given: A  Designer.   
		DesignerData designer = insertDesigner(buildInsertDesigner(1));
		DesignerData expected = buildInsertDesigner(1);
		
		//When: The Designer is retrieved by designerId.
		DesignerData actual = retrieveDesigner(designer.getDesignerId());
		
		//Then: The actual Designer is equal to the expected designer.
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void testRetrieveAllDesigners() {
		//Given: Two designers   
		List<DesignerData> expected = insertTwoDesigners();
		
		//When: All Designers are retrieved.
		List<DesignerData> actual = retrieveAllDesigners();
		
		//Then: The retrieved Designers are the same as expected.
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void testUpdateDesigner() {
		//Given: A  Designer and an update request.
		insertDesigner(buildInsertDesigner(1));
		DesignerData expected = buildUpdateDesigner(1);
		
		//When: The Designer is updated.
		DesignerData actual = updateDesigner(expected);
		
		//Then: The Designer is returned as expected.
		assertThat(actual).isEqualTo(expected);
		
		//And: There is one row in the designer table.
		assertThat(rowsInDesignertable()).isOne();
	}

}
