package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Menu;
import com.example.demo.repository.MenuRepository;

@Service
public class MenuService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	// ���j���[�ꗗ��S���������Ď擾
	public List<Menu> search(){
		return menuRepository.selectAll();
	}
	// ���j���[�擾
	public Menu search(int id) {
		return menuRepository.selectPK(id);
	}
	// ���j���[�o�^
	@Transactional
	public void register(Menu menu) {
		menuRepository.insert(menu);
	}
	// ���j���[�X�V
	@Transactional
	public void update(Menu menu) {
		menuRepository.update(menu);
	}
	// ���j���[�폜
	@Transactional
	public void delete(int id) {
		menuRepository.delete(id);
	}

}
