package com.cg.edu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.edu.dao.JobRepository;
import com.cg.edu.dto.Job;
import com.cg.edu.service.JobService;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = JobPortalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobPortalApplicationTests {
//	
	@Autowired
JobRepository repository;
//	
	@Autowired
	JobService service;
//	
//	@LocalServerPort
//	private int port;
//
//	TestRestTemplate restTemplate = new TestRestTemplate();
//
//	
//	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + port;
//		result = prime * result + ((repository == null) ? 0 : repository.hashCode());
//		result = prime * result + ((restTemplate == null) ? 0 : restTemplate.hashCode());
//		result = prime * result + ((service == null) ? 0 : service.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		JobPortalApplicationTests other = (JobPortalApplicationTests) obj;
//		if (port != other.port)
//			return false;
//		if (repository == null) {
//			if (other.repository != null)
//				return false;
//		} else if (!repository.equals(other.repository))
//			return false;
//		if (restTemplate == null) {
//			if (other.restTemplate != null)
//				return false;
//		} else if (!restTemplate.equals(other.restTemplate))
//			return false;
//		if (service == null) {
//			if (other.service != null)
//				return false;
//		} else if (!service.equals(other.service))
//			return false;
//		return true;
//	}
//
//	@Test
//	public void testRetrieveJobList() {
//
//		ResponseEntity<List<Job>> response =   restTemplate.exchange(createURLWithPort("/getAllJob"), HttpMethod.GET, null, new ParameterizedTypeReference<List<Job>>() {
//			
//		});
//
//		List<Job> expected = service.findAllJobs();
//		List<Job> list = response.getBody();
//		assertArrayEquals(expected.toArray(), list.toArray());
//		
//	}
//	
//	private String createURLWithPort(String uri) {
//		return "http://localhost:" + port + uri;
//	}
//
	
//	@Autowired
//	JobProviderService providerService;
//	
//	JobProvider jobprovider1;
//	Job job;
//	@BeforeEach
//	public void init()
//	{
// 	jobprovider1=new JobProvider((long)244,"deekshanareddy","deekshana123@gmail.com","deekshanareddy","http://deek");
//		//Job job1 = new Job("IT" , "SoftwareFullStackDeveloper","Hyderabad",70000.00,5.00, "Fulltime",
//		//	jobprovider1);
//	}
//	@Test
//	void testloginProvider() {
//		JobProvider jobprovider=providerService.addJobProvider(jobprovider1);
//		assertEquals(true,jobprovider!=null);
//	}
	@Test
	void contextLoads() {
		
	}

	@Test
	public void getAllJob() {
		List<Job> jobList = repository.findAll();
//		Mockito.when(repo.findAll()).thenReturn(jobList);
		List<Job> resultList = service.findAllJobs();
//		assertThat(resultList).isEqualTo(jobList);
		assertEquals(jobList.size(), resultList.size()); 
	}

//    @Test
//    public void getAllCandidate() {
//    List<User> userlist = repo.();
//    List<Job> resultList = jobService.getAllUser(long);
//    assertEquals(userlist.size(), resultList.size()); 
    
    		
	
    }