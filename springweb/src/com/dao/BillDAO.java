package com.dao;

import java.util.List;

import com.model.BillItemDTO;
import com.model.BillNoDTO;

public abstract class BillDAO {
	public abstract void maxCustomer();
	public abstract void AddItemsInvoice(BillNoDTO bill,List<BillItemDTO> items);
	public abstract Long BillNo();
	public abstract void ItemNo();
}
