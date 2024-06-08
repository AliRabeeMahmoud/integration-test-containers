package com.example.integration;


import com.example.integration.entity.Student;
import com.example.integration.repository.StudentRepository;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTestContainersApplicationTests extends AbstractContainerBaseTest{

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private MockMvc mockMvc;

	// given/when/then format - BDD style
	@Test
	public void givenStudents_whenGetAllStudents_thenListOfStudents() throws Exception {
		// given - setup or precondition
		List<Student> students =
				List.of(Student.builder().firstName("Ramesh").lastName("faadatare").email("ramesh@gmail.com").build(),
						Student.builder().firstName("tony").lastName("stark").email("tony@gmail.com").build());
		studentRepository.saveAll(students);

		// when - action
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/students"));

		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk());
		response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(students.size())));
	}



}
