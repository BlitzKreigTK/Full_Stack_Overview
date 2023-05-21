package com.learning.rest.webservices.restfulwebservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.learning.rest.webservices.restfulwebservices.model.FilteringModel;

@RestController
public class FilteringController {

	// Static filtering @JsonIgnoreProperties and @JsonIgnore
//	@GetMapping("/filtering")
//	public FilteringModel staticFilter() {
//		return new FilteringModel("value1", "value2", "value3", "value4");
//	}
//
//	@GetMapping("/filtering-list")
//	public List<FilteringModel> staticFilterList() {
//		return Arrays.asList(new FilteringModel("value1", "value2", "value3", "value4"),
//				new FilteringModel("value1", "value2", "value3", "value4"));
//	}

	// Dynamic filtering @JsonFilter in model and MappingJacksonValue in controller
	@GetMapping("/filtering")
	public MappingJacksonValue dynamicFilter() {
		// Model class object
		FilteringModel filteringModel = new FilteringModel("value1", "value2", "value3", "value4");
		// MappingJacksonValue: A simple holder for the POJO to serialize model class.
		MappingJacksonValue mappingJacksonVal = new MappingJacksonValue(filteringModel);
		// SimpleBeanPropertyFilter: Implementation that only uses property name to
		// determine whether to serialize property as is, or to filter it out.
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field3", "field4");
		// BeanPropertyFilter that match given ids.
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilteringModelFilter", filter);
		mappingJacksonVal.setFilters(filters);
		return mappingJacksonVal;
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue dynamicFilterList() {
		List<FilteringModel> listFiltering = Arrays.asList(new FilteringModel("value1", "value2", "value3", "value4"),
				new FilteringModel("value1", "value2", "value3", "value4"));
		MappingJacksonValue mappingJacksonVal = new MappingJacksonValue(listFiltering);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilteringModelFilter", filter);
		mappingJacksonVal.setFilters(filters);
		return mappingJacksonVal;
	}
}
