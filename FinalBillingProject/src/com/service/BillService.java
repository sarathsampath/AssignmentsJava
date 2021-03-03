package com.service;

import java.util.List;

import com.model.BillItemDTO;
import com.model.BillNoDTO;

public interface BillService {
	public void addItems(BillNoDTO bill,List<BillItemDTO> items);
	public int getBillno();
}
