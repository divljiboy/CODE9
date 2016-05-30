package rs.code9.taster.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.code9.taster.model.Test;
import rs.code9.taster.repository.TestRepository;
import rs.code9.taster.service.TestService;

/**
 * Created by slobodan on 3/14/14.
 */
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	/**
	 * @see rs.code9.taster.service.TestService#findOne(Long)
	 */
	@Override
	public Test findOne(Long id) {
		return testRepository.findOne(id);
	}

	/**
	 * @see rs.code9.taster.service.TestService#findAll()
	 * @return
	 */
	@Override
	public List<Test> findAll() {
		return testRepository.findAll();
	}

	/**
	 * @see rs.code9.taster.service.TestService#save(rs.code9.taster.model.AbstractBaseEntity)
	 */
	@Override
	@Transactional
	public Test save(Test test) {
		return testRepository.save(test);
	}

	/**
	 * @see rs.code9.taster.service.TestService#remove(Long)
	 */
	@Override
	public void remove(Long id) throws IllegalArgumentException {
		Test test = testRepository.findOne(id);
		if (test == null) {
			throw new IllegalArgumentException(String.format(
							"Test with id=%d does not exist.",
							id));
		}
		testRepository.delete(id);
	}
}
