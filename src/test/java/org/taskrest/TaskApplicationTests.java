package org.taskrest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskApplicationTests {

	@Test
	void contextLoads() {
		TaskApplication taskApplication= new TaskApplication();
		assertThat(taskApplication).isNotNull();
	}

}
