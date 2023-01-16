package com.me.hibernate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.HospitalDAO;
import com.me.dao.PharmacyDAO;
import com.me.dao.UserDAO;
import com.me.dao.VaccineDAO;
import com.me.pojo.Hospital;
import com.me.pojo.Nurse;
import com.me.pojo.Pharmacy;
import com.me.pojo.User;
import com.me.pojo.Vaccine;

@Controller
public class HospitalController {
	
	@RequestMapping(value = "/hospitalPage.htm", method = RequestMethod.POST)
	public ModelAndView hospitalPage(HttpServletRequest request, HttpServletResponse response, VaccineDAO vaccineDao, HospitalDAO hospitalDao) {
		
		HttpSession session = request.getSession();
		String page = request.getParameter("sub");
		User user = (User) session.getAttribute("user");
		if(user != null) {
			if(page.equals("VIEW REQUESTS")){
				System.out.println(user.getEmail());
				return new ModelAndView("allVaccineRequestsPage");
			}else {
				System.out.println(user.getEmail());
				Hospital hospital = hospitalDao.getHospitalByEmail(user.getEmail());
				List<Vaccine> vaccineList = vaccineDao.list(hospital.getIdhospital());
				return new ModelAndView("addVaccinePage","vaccines",vaccineList);
//				return new ModelAndView("addVaccinePage");
			}
		}else {
			System.out.println("user null");
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value = "/addVaccine.htm", method = RequestMethod.POST)
	public ModelAndView addVaccine(HttpServletRequest request, HttpServletResponse response, VaccineDAO vaccineDao, HospitalDAO hospitalDao) {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
				Vaccine vaccine = new Vaccine();
				vaccine.setVaccineName(request.getParameter("name"));
				vaccine.setInStock(request.getParameter("inStock"));
				Hospital hospital = hospitalDao.getHospitalByEmail(user.getEmail());
				vaccine.setHospitalId(hospital.getIdhospital());
				vaccineDao.save(vaccine);
				System.out.println("Vaccine saved successfully");
				List<Vaccine> vaccineList = vaccineDao.list(hospital.getIdhospital());
				return new ModelAndView("addVaccinePage","vaccines",vaccineList);
		}else {
			System.out.println("user null");
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value = "/updateVaccine.htm", method = RequestMethod.POST)
	public ModelAndView updateVaccine(HttpServletRequest request, HttpServletResponse response, VaccineDAO vaccineDao, HospitalDAO hospitalDao) {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			String[] arr = request.getParameterValues("inStockArr");
			String[] idArr = request.getParameterValues("id");
			for (int i = 0; i < arr.length; i++) {
				System.out.println("value= "+arr[i]);
				System.out.println("id= "+idArr[i]);
				Vaccine vaccine = vaccineDao.getVaccine(Integer.parseInt(idArr[i]));
				if(vaccine != null) {
					vaccine.setInStock(arr[i]);
					vaccineDao.save(vaccine);
					System.out.println("Updated");
				}else {
					System.out.println("vaccine null, not updated");
				}
			}
			Hospital hospital = hospitalDao.getHospitalByEmail(user.getEmail());
			List<Vaccine> vaccineList = vaccineDao.list(hospital.getIdhospital());
			return new ModelAndView("addVaccinePage","vaccines",vaccineList);
		}else {
			System.out.println("user null");
			return new ModelAndView("login");
		}
	}
}
