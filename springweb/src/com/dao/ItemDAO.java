package com.dao;

import com.model.ArraytemDTO;

public abstract class ItemDAO {
	public abstract ArraytemDTO getItems();
	public abstract void getItemByName(String Name);
	public abstract  void CountItem();
	public abstract void getItemById(int Id);
	public abstract void UpdatePrice(String Name,int price);
	public abstract void UpdateName(String Name,int ItemId);
	public abstract void DeleteItem(int ItemId);
	public abstract void DeleteItemByName(String ItemName);
}
 