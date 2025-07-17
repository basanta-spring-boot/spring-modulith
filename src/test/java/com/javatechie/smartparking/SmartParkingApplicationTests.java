package com.javatechie.smartparking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
class SmartParkingApplicationTests {

	@Test
	void  writeDocumentation()  {
		var modules = ApplicationModules.of(SmartParkingApplication.class)
				.verify(); // optional: ensures module boundaries are respected
		new Documenter(modules)
				.writeModulesAsPlantUml()       // generates info for all modules
				.writeIndividualModulesAsPlantUml(); // docs per module
	}

}
