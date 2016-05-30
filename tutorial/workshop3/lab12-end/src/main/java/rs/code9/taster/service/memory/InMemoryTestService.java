package rs.code9.taster.service.memory;

import org.springframework.stereotype.Service;

import rs.code9.taster.model.Test;
import rs.code9.taster.service.TestService;

/**
 * In memory backed {@link TestService} implementation.
 * 
 * @author p.stanic
 */
@Service
public class InMemoryTestService extends AbstractInMemoryService<Test>
		implements TestService {

}
