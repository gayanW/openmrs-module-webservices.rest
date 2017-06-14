package org.openmrs.module.webservices.docs.swagger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.converter.ModelConverterContextImpl;
import io.swagger.converter.ModelConverters;
import io.swagger.jackson.ModelResolver;
import io.swagger.models.*;
import io.swagger.models.Info;
import io.swagger.models.auth.BasicAuthDefinition;
import io.swagger.util.Json;
import org.junit.Test;
import org.openmrs.Patient;

import static junit.framework.TestCase.assertNotNull;

public class SwaggerSpecificationCreatorTest {
	@Test
	public void modelResolveTest() {
		final ModelResolver modelResolver = new ModelResolver(new ObjectMapper());
		final ModelConverterContextImpl context = new ModelConverterContextImpl(modelResolver);
		final Model model = context.resolve(Patient.class);
		assertNotNull(model);
	}

	@Test
	public void swaggerSerializeTest() throws JsonProcessingException {
		final io.swagger.models.Info info = new Info()
				.version("1.0.0")
				.title("Swagger WebServices REST");

		Swagger swagger = new Swagger()
				.info(info)
				.securityDefinition("basicAuth", new BasicAuthDefinition())
				.scheme(Scheme.HTTP)
				.consumes("application/json")
				.produces("application/json");

		final Model patientModel = ModelConverters.getInstance().read(Patient.class).get("Patient");
		swagger.addDefinition("Patient", patientModel);

		final String swaggerJson = Json.pretty(swagger);
		assertNotNull(swaggerJson);
	}
}
