package com.jwt;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import com.jwt.config.SecurityConfig;
import com.jwt.controller.AuthController;
import com.jwt.controller.HomeController;
import com.jwt.service.TokenService;

@WebMvcTest({ HomeController.class, AuthController.class })
@Import({ SecurityConfig.class, TokenService.class })
public class HomeControllerTest {

	
	@Autowired
	MockMvc mvc;

	@Test
	void tokenWhenAnonymousThenStatusIsUnauthorized() throws Exception {
		this.mvc.perform(post("/token")).andExpect(status().isUnauthorized());
	}

	@Test
	void tokenWithBasicThenGetToken() throws Exception {
		MvcResult result = this.mvc.perform(post("/token")
				.with(httpBasic("hr", "sea4me")))
				.andExpect(status().isOk())
				.andReturn();
		
		String token = result.getResponse().getContentAsString();

		this.mvc.perform(get("/")
				.header("Authorization", "Bearer " + token))
				.andExpect((ResultMatcher) content().string("Hello, HR"));
		//assertThat(result.getResponse().getContentAsString()).isNotEmpty();
	}

	@Test
	void rootWhenUnauthenticatedThen401() throws Exception {
		this.mvc.perform(get("/")).andExpect(status().isUnauthorized());
	}

	@Test
	public void rootWithBasicStatusIsUnauthorized() throws Exception {
		this.mvc.perform(get("/").with(httpBasic("hr", "sea4me"))).andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser
	public void rootWithMockUserStatusIsOK() throws Exception {
		this.mvc.perform(get("/")).andExpect(status().isOk());
	}
	
}
