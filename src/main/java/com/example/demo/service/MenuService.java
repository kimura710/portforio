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
	
	// メニュー一覧を全件検索して取得
	public List<Menu> search(){
		return menuRepository.selectAll();
	}
	// メニュー取得
	public Menu search(int id) {
		return menuRepository.selectPK(id);
	}
	// メニュー登録
	@Transactional
	public void register(Menu menu) {
		menuRepository.insert(menu);
	}
	// メニュー更新
	@Transactional
	public void update(Menu menu) {
		menuRepository.update(menu);
	}
	// メニュー削除
	@Transactional
	public void delete(int id) {
		menuRepository.delete(id);
	}

}
