package com.learning.rest.webservices.restfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.rest.webservices.restfulwebservices.model.Name;
import com.learning.rest.webservices.restfulwebservices.model.PersonV1;
import com.learning.rest.webservices.restfulwebservices.model.PersonV2;

@RestController
public class PersonController {
	// URL versioning
	@GetMapping("/v1/person")
	public PersonV1 nameFirstVersion() {
		return new PersonV1("Tulsi Kant");
	}

	@GetMapping("/v2/person")
	public PersonV2 nameSecondVersion() {
		return new PersonV2(new Name("Tulsi", "Kant"));
	}

	// RequestParam Versioning
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 nameFirstVersionWithParam() {
		return new PersonV1("Tulsi Kant");
	}

	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 nameSecondVersionWithParam() {
		return new PersonV2(new Name("Tulsi", "Kant"));
	}

	// Header Versioning
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 nameFirstVersionWithHeader() {
		return new PersonV1("Tulsi Kant");
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 nameSecondVersionWithHeader() {
		return new PersonV2(new Name("Tulsi", "Kant"));
	}

	// Media Type Versioning
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 nameFirstVersionMediaType() {
		return new PersonV1("Tulsi Kant");
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 nameSecondVersionMediaType() {
		return new PersonV2(new Name("Tulsi", "Kant"));
	}
}
