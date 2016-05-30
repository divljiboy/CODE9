package com.levi9.taster.core.jpa;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;

import com.levi9.taster.commons.model.Page;
import com.levi9.taster.core.api.TestTemplateService;

/**
 * JPA implementation of {@link TestTemplateService}
 * 
 * @author r.horvat
 */
@Named("jpaTestTemplateService")
@Transactional
public class JpaTestTemplateService implements TestTemplateService<JpaTestTemplate> {

	@Inject
	private TestTemplateJpaRepository templateRepository;
		
	@Override
	public JpaTestTemplate save(JpaTestTemplate testTemplate) {
		return templateRepository.save((JpaTestTemplate) testTemplate);
	}

	@Override
	public void delete(Long id) {
		templateRepository.delete(id);
	}

	@Override
	public Page<? extends JpaTestTemplate> findAll(int page, int size) {
		return new JpaPage<JpaTestTemplate>(page * size, size, (int) templateRepository.count(), templateRepository.findAll(new PageRequest(page, size)).getContent());
	}

	@Override
	public JpaTestTemplate findOne(Long id) {
		return templateRepository.findOne(id);
	}

}
