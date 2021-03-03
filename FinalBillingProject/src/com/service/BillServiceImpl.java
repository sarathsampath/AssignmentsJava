package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BillDAO;
import com.dao.CustomerDAO;
import com.model.BillItemDTO;
import com.model.BillNoDTO;
@Service("us4")
@Transactional
public class BillServiceImpl implements BillService{
	@Autowired
	private BillDAO billobj;
	
@Override
public void addItems(BillNoDTO bill, List<BillItemDTO> items) {
	// TODO Auto-generated method stub
	billobj.AddItemsInvoice(bill, items);
	
}
@Override
public int getBillno() {
	// TODO Auto-generated method stub
	int billnum=(int) (billobj.BillNo()+1);
	return billnum;
}

public BillDAO getBillobj() {
	return billobj;
}

public void setBillobj(BillDAO billobj) {
	this.billobj = billobj;
}
}
