package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.ArraytemDTO;
import com.model.BillItemDTO;
import com.model.BillNoDTO;
import com.model.Customer;
import com.model.Login;
import com.model.User;
import com.service.BillService;
import com.service.CustomerService;
import com.service.ItemService;
import com.service.LoginService;
import com.service.UserService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;




@Controller
public class BasicController {

	@Autowired
	UserService userService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	@Qualifier("us1")
	LoginService login;
	public LoginService getLogin() {
		return login;
	}
	public void setLogin(LoginService login) {
		this.login = login;
	}
	
	@Autowired
	@Qualifier("us2")
	CustomerService customer;
	
	public CustomerService getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerService customer) {
		this.customer = customer;
	}

	@Autowired
	@Qualifier("us3")
	ItemService itemservice;
	public ItemService getItemservice() {
		return itemservice;
	}
	public void setItemservice(ItemService itemservice) {
		this.itemservice = itemservice;
	}
	
	@Autowired
	@Qualifier("us4")
	BillService billobj;	
	public BillService getBillobj() {
		return billobj;
	}
	public void setBillobj(BillService billobj) {
		this.billobj = billobj;
	}
	@RequestMapping(value="/loadloginform", method=RequestMethod.GET)
	public ModelAndView loadLoginForm(ModelAndView mandv) {
		Login lfb=new Login();
		mandv.addObject("formBeanObject", lfb);
		mandv.setViewName("login1");
		return mandv;
	}
	@RequestMapping(value="/loginsubmit", method=RequestMethod.POST)
	public ModelAndView processLoginForm(Login logi,ModelAndView mandv,@ModelAttribute("formBeanObject") Login loginn,HttpServletRequest request) {
		System.out.println("check:"+logi.getName());	
		HttpSession session=request.getSession();
		session.setAttribute("Username",logi.getName());
		Customer lb=new Customer();
		try {
					
					if(login.checkUser(logi.getName(),logi.getPassword())){	
						if(login.checkStatus(logi.getName()))
							{ 	
								if(login.updateStatus(logi.getName(),1)) {
									System.out.println("hai registered");
									mandv.addObject("CustomerBean",lb);
									mandv.setViewName("welcome");
									return mandv;	
								}
							}
								
						else {
							System.out.println("No update status");
							mandv.addObject("CustomerBean", lb);
							mandv.setViewName("welcome");
							return mandv;
							
							}
					}
					else {
						System.out.println("register status");
							logi.setStatus(0);
							if(login.registerUser(logi)) {
								System.out.println("user registered");
								System.out.println("1 registered");
								mandv.addObject("CustomerBean",lb);
								mandv.setViewName("welcome");
								return mandv;
							}
							
					}
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}

		return mandv;
	}
	@RequestMapping(value="/customersubmit", method=RequestMethod.POST)
	public ModelAndView processcustomer(Customer customerobj,ModelAndView mandv,HttpServletRequest request,@ModelAttribute("CustomerBean") Customer c) {
		System.out.println(customerobj.getCustomerMobile()+":"+customerobj.getCustomerName());
		HttpSession session=request.getSession();
		System.out.println("check2="+session.getAttribute("Username"));
		String Name=customer.checkCustomer(customerobj);
		session.setAttribute("CustomerName", Name);
		session.setAttribute("CustomerMobile",customerobj.getCustomerMobile());
		c.setCustomerName(Name);
		mandv.setViewName("displayWelcome");
		return mandv;
	}
	
	
	
	@RequestMapping(value="/processItems", method=RequestMethod.POST)
	public ModelAndView processItems(HttpServletRequest request,ModelAndView mandv) {
		HttpSession session=request.getSession();
		ArraytemDTO obj=itemservice.getItems();
		session.setAttribute("Items", obj);
		mandv.setViewName("shop");
		return mandv;
	}
	
	@RequestMapping(value="/itemsubmit", method=RequestMethod.POST)
	public ModelAndView itemsubmit(ModelAndView mandv,HttpServletRequest request) {
		HttpSession session=request.getSession();
		BillNoDTO billnodto=new BillNoDTO();
		ArraytemDTO Idto=(ArraytemDTO)session.getAttribute("Items");
		int id[]=Idto.getItemId();
		
		int billno=billobj.getBillno();
		System.out.println("billno"+billno);
		billnodto.setBillNo(billno);
		billnodto.setCustomerMobile(session.getAttribute("CustomerMobile").toString());
		billnodto.setDate(new Date());
		
		
		
		int amounttopaid=0;
		String itemsq[]=Idto.getItemDescription();
		int price[]=Idto.getPrice();
		
		
	Document document = new Document();
		
		try {

			PdfWriter.getInstance(document, new FileOutputStream(billno+".pdf"));
			document.open();
			System.out.println("pdf started");

			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			PdfPTable table = new PdfPTable(3);
			
			Paragraph preface = new Paragraph();
			preface.add("Invoice of The Purchase");
		    document.add( Chunk.NEWLINE );
		    preface.setAlignment(Element.ALIGN_CENTER);
		    document.add(preface);
			
		    Paragraph preface1 = new Paragraph();
	        preface1.add("Billno:");
	        preface1.add(billno+"");
		    document.add(preface1);
		    document.add( Chunk.NEWLINE );

		    Paragraph preface2 = new Paragraph();
	        preface2.add("CustomerName:");
	        preface2.add(session.getAttribute("CustomerName").toString());
		    document.add(preface2);
		    document.add( Chunk.NEWLINE );

		    Paragraph preface3 = new Paragraph();
	        preface3.add("CustomerMobile:");
	        preface3.add(session.getAttribute("CustomerMobile").toString());
		    document.add(preface3);
		    document.add( Chunk.NEWLINE );
		}
		    catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		WritableWorkbook workbook;
        try {
            workbook = Workbook.createWorkbook(new File("/Users/sarath/eclipse/jee-2020-12/Eclipse.app/Contents/MacOS/"+billno+".xls"));
            WritableSheet sheet = workbook.createSheet("Tony", 0);
		    Label label = new Label(0, 0, "Bill No");
	          sheet.addCell(label);
	          Number number = new Number(1, 0, billno);
	          sheet.addCell(number);
            label = new Label(0, 1, "Customer Name");
          sheet.addCell(label);
          label = new Label(1, 1, session.getAttribute("CustomerName").toString());
          sheet.addCell(label);
          label = new Label(0, 2, "Customer Mobile");
        sheet.addCell(label);
        label = new Label(1, 2, session.getAttribute("CustomerMobile").toString());
        sheet.addCell(label);
		   
            System.out.println("created Excel...");
            workbook.write();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
		
		
		
		List<BillItemDTO> lis=new ArrayList<>();
		Enumeration<String> en=request.getParameterNames();
		int i=0;
			while(en.hasMoreElements()) {
				BillItemDTO billitem1=new BillItemDTO();
				String name=en.nextElement();
				System.out.println(name);
				for(int is=0;is<id.length;is++) {
					if(request.getParameter(id[is]+"")!=null && name.equals(id[is]+"") ) {
						PdfPTable table = new PdfPTable(3);
						PdfPCell header = new PdfPCell();
						
						String item=request.getParameter(id[is]+"");
						int quantity=Integer.parseInt(request.getParameter(itemsq[is]));
						System.out.println(item+":"+(quantity*price[is]));
						int total=quantity*price[is];
						amounttopaid+=total;
						
						billitem1.ItemId=id[is];
						billitem1.Quantity=quantity;
						billitem1.Price=quantity*price[is];
						lis.add(billitem1);
						
						
						
						Phrase phr = new Phrase();
						phr.add(id[is]+"");
						header.setPhrase(phr);
						table.addCell(header);
						
						Phrase phr1 = new Phrase();
						phr1.add(item);
						header.setPhrase(phr1);
						table.addCell(header);
						
						Phrase phr2 = new Phrase();
						phr2.add(total+"");
						header.setPhrase(phr2);
						table.addCell(header);
						
						try {
							document.add(table);
							
						} catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						session.setAttribute(item+":",total+"");
						session.removeAttribute(id[is]+"");
						session.removeAttribute(itemsq[is]);
					}}
						
				
			}
		
		System.out.println(amounttopaid);
		
		Paragraph preface1 = new Paragraph();
        preface1.add("TotalAmount:");
        preface1.add(amounttopaid+"");
	    try {
			document.add(preface1);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	session.setAttribute("TotalAmount",amounttopaid);
    System.out.println("pdf closed");
    document.close();
    
		billobj.addItems(billnodto, lis);
		mandv.setViewName("checking");
		return mandv;
	}
	
	
	@RequestMapping(value="/processlogout", method=RequestMethod.POST)
	public ModelAndView processlogout(Customer customerobj,ModelAndView mandv,HttpServletRequest request) {
		HttpSession session=request.getSession();
		login.updateStatus(session.getAttribute("Username").toString(), 0);
		session.invalidate();
		Login lfb=new Login();
		mandv.addObject("formBeanObject", lfb);
		mandv.setViewName("login1");
		return mandv;
	}
}
