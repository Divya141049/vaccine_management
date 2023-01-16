package com.me.hibernate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.HospitalDAO;
import com.me.dao.NurseDAO;
import com.me.dao.PharmacyDAO;
import com.me.dao.UserDAO;
import com.me.pojo.Hospital;
import com.me.pojo.Nurse;
import com.me.pojo.Pharmacy;
import com.me.pojo.User;

@Controller
public class AdminController {
	
	@RequestMapping(value = "/admin.htm", method = RequestMethod.POST)
	public ModelAndView adminPage(HttpServletRequest request, HttpServletResponse response, HospitalDAO hospitalDao, PharmacyDAO pharmacyDao) {
		
		String page = request.getParameter("sub");
		if(page.equals("MANAGE PATIENT")){
			return new ModelAndView("managePatient");
		}else if(page.equals("MANAGE HOSPITAL")){
			List<Hospital> hospitalList = hospitalDao.list();
			return new ModelAndView("manageHospital","hospitals",hospitalList);
		}else if(page.equals("MANAGE PHARMACY")){
			List<Pharmacy> pharmacyList = pharmacyDao.list();
			return new ModelAndView("managePharmacy","pharmacies",pharmacyList);
		}else {
			return new ModelAndView("manageNurse");
		}
	}
	
//	Hospital role
	
	@RequestMapping(value = "/hospital.htm", method = RequestMethod.POST)
	public ModelAndView manageHospital(HttpServletRequest request, HttpServletResponse response) {
		
		String action = request.getParameter("sub");
		System.out.println("action"+action);
		if(action.equals("ADD HOSPITAL")){
			return new ModelAndView("addHospital");
		}else {
			return new ModelAndView("editHospital");
		}
	}
	
	@RequestMapping(value = "/addHospital.htm", method = RequestMethod.POST)
	public ModelAndView addHospital(HttpServletRequest request, HttpServletResponse response, HospitalDAO hospitalDao, UserDAO userDao) {
		
		User existingUser = userDao.getByEmail(request.getParameter("email"));
		if(existingUser != null) {
			System.out.println("Email already exist.");
			return new ModelAndView("addHospital");
		}
		Hospital newHospital = new Hospital();
		newHospital.setName(request.getParameter("hospitalName"));
		newHospital.setAddress(request.getParameter("address"));
		newHospital.setContact(Integer.parseInt(request.getParameter("contact")));
		newHospital.setEmail(request.getParameter("email"));
		User user = new User();
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setFirstName(request.getParameter("hospitalName"));
		user.setLastName(request.getParameter("hospitalName"));
		user.setPhone(Integer.parseInt(request.getParameter("contact")));
		user.setRole("Hospital");
		userDao.save(user);
		hospitalDao.save(newHospital);
		System.out.println("Hospital Added successfully.");
		List<Hospital> hospitalList = hospitalDao.list();
		return new ModelAndView("manageHospital","hospitals",hospitalList);
	}
	
	@RequestMapping(value = "/editHospital.htm", method = RequestMethod.POST)
	public ModelAndView editDeleteHospital(HttpServletRequest request, HttpServletResponse response, UserDAO userDao, HospitalDAO hospitalDao) {
		
		User user = userDao.getByEmail(request.getParameter("email"));
		Hospital hospital = hospitalDao.getHospitalByEmail(request.getParameter("email"));
		if(user == null || !user.getRole().equals("Hospital")) {
			System.out.println("Email does not exist.");
			return new ModelAndView("editHospital");
		}else {
			return new ModelAndView("editDeleteHospital","hospital",hospital);
		}
	}
	
	@RequestMapping(value = "/editDeleteHospital.htm", method = RequestMethod.POST)
	public ModelAndView editHospital(HttpServletRequest request, HttpServletResponse response, UserDAO userDao, HospitalDAO hospitalDao) {
		
		String action = request.getParameter("sub");
		if(action.equals("EDIT")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Hospital hospital = hospitalDao.get(id);
			
			if(hospital != null) {
				hospital.setName(request.getParameter("hospitalName"));
				hospital.setAddress(request.getParameter("address"));
				hospital.setContact(Integer.parseInt(request.getParameter("contact")));
				hospitalDao.save(hospital);
				System.out.println("Hospital updated successfully.");
				List<Hospital> hospitalList = hospitalDao.list();
				return new ModelAndView("manageHospital","hospitals",hospitalList);
			}else {
				System.out.println("Hospital does not exist");
				return new ModelAndView("editHospital");
			}
		}else {
			int id = Integer.parseInt(request.getParameter("id"));
			Hospital hospital = hospitalDao.get(id);
			
			if(hospital != null) {
				hospitalDao.delete(hospital);
				userDao.deleteByEmail(request.getParameter("email"));
				System.out.println("Hospital deleted successfully.");
				List<Hospital> hospitalList = hospitalDao.list();
				return new ModelAndView("manageHospital","hospitals",hospitalList);
			}else {
				System.out.println("Hospital does not exist");
				return new ModelAndView("editHospital");
			}
		}
	}
	
//	Pharmacy role
	
	@RequestMapping(value = "/pharmacy.htm", method = RequestMethod.POST)
	public ModelAndView managePharmacy(HttpServletRequest request, HttpServletResponse response) {
		
		String action = request.getParameter("sub");
		if(action.equals("ADD PHARMACY")){
			return new ModelAndView("addPharmacy");
		}else {
			return new ModelAndView("editPharmacy");
		}
	}
	
	@RequestMapping(value = "/addPharmacy.htm", method = RequestMethod.POST)
	public ModelAndView addPharmacy(HttpServletRequest request, HttpServletResponse response, PharmacyDAO pharmacyDao, UserDAO userDao) {
		
		User existingUser = userDao.getByEmail(request.getParameter("email"));
		if(existingUser != null) {
			System.out.println("Email already exist.");
			return new ModelAndView("addPharmacy");
		}
		Pharmacy newPharmacy = new Pharmacy();
		newPharmacy.setName(request.getParameter("pharmacyName"));
		newPharmacy.setAddress(request.getParameter("address"));
		newPharmacy.setContact(Integer.parseInt(request.getParameter("contact")));
		newPharmacy.setEmail(request.getParameter("email"));
		User user = new User();
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setFirstName(request.getParameter("pharmacyName"));
		user.setLastName(request.getParameter("pharmacyName"));
		user.setPhone(Integer.parseInt(request.getParameter("contact")));
		user.setRole("Pharmacy");
		userDao.save(user);
		pharmacyDao.save(newPharmacy);
		System.out.println("Pharmacy Added successfully.");
		List<Pharmacy> pharmacyList = pharmacyDao.list();
		return new ModelAndView("managePharmacy","pharmacies",pharmacyList);
	}
	
	@RequestMapping(value = "/editPharmacy.htm", method = RequestMethod.POST)
	public ModelAndView editDeletePharmacy(HttpServletRequest request, HttpServletResponse response, UserDAO userDao, PharmacyDAO pharmacyDao) {
		
		User user = userDao.getByEmail(request.getParameter("email"));
		Pharmacy pharmacy = pharmacyDao.getPharmacyByEmail(request.getParameter("email"));
		if(user == null || !user.getRole().equals("Pharmacy")) {
			System.out.println("Email does not exist.");
			return new ModelAndView("editPharmacy");
		}else {
			return new ModelAndView("editDeletePharmacy","pharmacy",pharmacy);
		}
	}
	
	@RequestMapping(value = "/editDeletePharmacy.htm", method = RequestMethod.POST)
	public ModelAndView editPharmacy(HttpServletRequest request, HttpServletResponse response, UserDAO userDao, PharmacyDAO pharmacyDao) {
		
		String action = request.getParameter("sub");
		if(action.equals("EDIT")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Pharmacy pharmacy = pharmacyDao.get(id);
			
			if(pharmacy != null) {
				pharmacy.setName(request.getParameter("pharmacyName"));
				pharmacy.setAddress(request.getParameter("address"));
				pharmacy.setContact(Integer.parseInt(request.getParameter("contact")));
				pharmacyDao.save(pharmacy);
				System.out.println("Pharmacy updated successfully.");
				List<Pharmacy> pharmacyList = pharmacyDao.list();
				return new ModelAndView("managePharmacy","pharmacies",pharmacyList);
			}else {
				System.out.println("Phamacy does not exist");
				return new ModelAndView("editPharmacy");
			}
		}else {
			int id = Integer.parseInt(request.getParameter("id"));
			Pharmacy pharmacy = pharmacyDao.get(id);
			
			if(pharmacy != null) {
				pharmacyDao.delete(pharmacy);
				userDao.deleteByEmail(request.getParameter("email"));
				System.out.println("Pharmacy deleted successfully.");
				List<Pharmacy> pharmacyList = pharmacyDao.list();
				return new ModelAndView("managePharmacy","pharmacies",pharmacyList);
			}else {
				System.out.println("Pharmacy does not exist");
				return new ModelAndView("editPharmacy");
			}
		}
	}
	
//	Nurse role
	
	@RequestMapping(value = "/nurse.htm", method = RequestMethod.POST)
	public ModelAndView manageNurse(HttpServletRequest request, HttpServletResponse response) {
		
		String action = request.getParameter("sub");
		if(action.equals("ADD NURSE")){
			return new ModelAndView("addNurse");
		}else {
			return new ModelAndView("editNurse");
		}
	}
	
	@RequestMapping(value = "/addNurse.htm", method = RequestMethod.POST)
	public ModelAndView addNurse(HttpServletRequest request, HttpServletResponse response, NurseDAO nurseDao, UserDAO userDao) {
		
		User existingUser = userDao.getByEmail(request.getParameter("email"));
		if(existingUser != null) {
			System.out.println("Email already exist.");
			return new ModelAndView("addNurse");
		}
		Nurse newNurse = new Nurse();
		newNurse.setName(request.getParameter("name"));
		newNurse.setAddress(request.getParameter("address"));
		newNurse.setContact(Integer.parseInt(request.getParameter("contact")));
		newNurse.setEmail(request.getParameter("email"));
		User user = new User();
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setFirstName(request.getParameter("name"));
		user.setLastName(request.getParameter("name"));
		user.setPhone(Integer.parseInt(request.getParameter("contact")));
		user.setRole("Nurse");
		userDao.save(user);
		nurseDao.save(newNurse);
		System.out.println("Nurse Added successfully.");
		List<Nurse> nurseList = nurseDao.list();
		return new ModelAndView("manageNurse","nurses",nurseList);
	}
	
	@RequestMapping(value = "/editNurse.htm", method = RequestMethod.POST)
	public ModelAndView editDeleteNurse(HttpServletRequest request, HttpServletResponse response, UserDAO userDao, NurseDAO nurseDao) {
		
		User user = userDao.getByEmail(request.getParameter("email"));
		Nurse nurse = nurseDao.getNurseByEmail(request.getParameter("email"));
		if(user == null || !user.getRole().equals("Nurse")) {
			System.out.println("Email does not exist.");
			return new ModelAndView("editNurse");
		}else {
			return new ModelAndView("editDeleteNurse","nurse",nurse);
		}
	}
	
	@RequestMapping(value = "/editDeleteNurse.htm", method = RequestMethod.POST)
	public ModelAndView editNurse(HttpServletRequest request, HttpServletResponse response, UserDAO userDao, NurseDAO nurseDao) {
		
		String action = request.getParameter("sub");
		if(action.equals("EDIT")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Nurse nurse = nurseDao.get(id);
			
			if(nurse != null) {
				nurse.setName(request.getParameter("name"));
				nurse.setAddress(request.getParameter("address"));
				nurse.setContact(Integer.parseInt(request.getParameter("contact")));
				nurseDao.save(nurse);
				System.out.println("Nurse updated successfully.");
				List<Nurse> nurseList = nurseDao.list();
				return new ModelAndView("manageNurse","nurses",nurseList);
			}else {
				System.out.println("Nurse does not exist");
				return new ModelAndView("editNurse");
			}
		}else {
			int id = Integer.parseInt(request.getParameter("id"));
			Nurse nurse = nurseDao.get(id);
			
			if(nurse != null) {
				nurseDao.delete(nurse);
				userDao.deleteByEmail(request.getParameter("email"));
				System.out.println("Nurse deleted successfully.");
				List<Nurse> nurseList = nurseDao.list();
				return new ModelAndView("manageNurse","nurses",nurseList);
			}else {
				System.out.println("Nurse does not exist");
				return new ModelAndView("editNurse");
			}
		}
	}
	
}
