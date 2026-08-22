package com.realtime_monitoring.usermanag;

import com.realtime_monitoring.usermanag.config.DataInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest(properties = {"eureka.client.enabled=false", "grpc.server.enabled=false"})
@ActiveProfiles("test")
@MockitoBean(types = DataInitializer.class)
class UserManagApplicationTests {

	@Test
	void contextLoads() {
	}

}
