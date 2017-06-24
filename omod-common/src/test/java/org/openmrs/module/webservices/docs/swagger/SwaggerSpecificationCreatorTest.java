package org.openmrs.module.webservices.docs.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.converter.ModelConverterContextImpl;
import io.swagger.jackson.ModelResolver;
import io.swagger.models.Model;
import org.junit.Test;
import org.openmrs.Patient;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;

import static junit.framework.TestCase.assertNotNull;

public class SwaggerSpecificationCreatorTest extends BaseModuleWebContextSensitiveTest {
	@Test
	public void mainTest() {
		String str = new SwaggerSpecificationCreator("baseurl..").BuildJSON();
		assertNotNull(str);
	}

	@Test
	public void modelResolveTest() {
		final ModelResolver modelResolver = new ModelResolver(new ObjectMapper());
		final ModelConverterContextImpl context = new ModelConverterContextImpl(modelResolver);
		final Model model = context.resolve(Patient.class);
		assertNotNull(model);
	}
}
