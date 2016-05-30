package rs.code9.taster.service.memory;

import org.springframework.stereotype.Service;

import rs.code9.taster.model.Category;
import rs.code9.taster.service.CategoryService;

/**
 * In memory backed {@link CategoryService} implementation.
 * 
 * @author d.gajic
 */
@Service
public class InMemoryCategoryService extends AbstractInMemoryService<Category>
		implements CategoryService {
}
