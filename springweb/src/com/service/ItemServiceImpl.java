package com.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CustomerDAO;
import com.dao.ItemDAO;
import com.model.ArraytemDTO;
@Service("us3")
@Transactional
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemDAO itemdao;
	public ItemDAO getItemdao() {
		return itemdao;
	}

	public void setItemdao(ItemDAO itemdao) {
		this.itemdao = itemdao;
	}
	@Override
	public ArraytemDTO getItems() {
		ArraytemDTO ss=itemdao.getItems();
		System.out.println(ss.getItemImage());
		return ss;
	}

	
}
