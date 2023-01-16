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
import com.me.dao.PatientDAO;
import com.me.dao.UserDAO;
import com.me.pojo.Hospital;
import com.me.pojo.Patient;
import com.me.pojo.User;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, UserDAO userDao, PatientDAO patientDao, HospitalDAO hospitalDao) {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		if(email.equals("") || password.equals("")) {
			System.out.println("Enter email or password.");
			return new ModelAndView("login");
		}
		User user = userDao.getByEmail(email);
		if(user == null) {
			System.out.println("Email ID does not exist");
			return new ModelAndView("login");
		}else {
			if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
				System.out.println("Login Successfull.");
				session.setAttribute("user", user);
				if(user.getRole().equals("Admin")){
					return new ModelAndView("adminPage");
				}else if(user.getRole().equals("Patient")){
					Patient patient = patientDao.getPatientByEmail(user.getEmail());
					List<Hospital> list = hospitalDao.list();
//					for (Hospital hospital : list) {
//						System.out.println("hosp email "+hospital.getEmail());
//					}
					session.setAttribute("hospitals", list);
//					System.out.println(session.getAttribute("user"));
//					System.out.println(session.getAttribute("hospitals"));
//					List<Hospital> h = (List<Hospital>) session.getAttribute("list");
//					for (Hospital hospital1 : h) {
//						System.out.println("hospi "+hospital1.getName());
//					}
					if(patient == null) {
						Patient newPatient = new Patient();
						newPatient.setFirstDoseStatus("Pending");
						newPatient.setSecondDoseStatus("Pending");
						newPatient.setBoosterStatus("Pending");
//						session.setAttribute("patient", newPatient);
						return new ModelAndView("patientPage","patient",newPatient);
					}else {
//						session.setAttribute("patient", patient);
						return new ModelAndView("patientPage","patient",patient);
					}
//					return new ModelAndView("patientPage");
				}else if(user.getRole().equals("Hospital")){
					return new ModelAndView("hospitalPage");
				}else if(user.getRole().equals("Pharmacy")){
					return new ModelAndView("pharmacyPage");
				}else{
					return new ModelAndView("nursePage");
				}
			}else {
				System.out.println("Enter correct Email or Password.");
				return new ModelAndView("login");
			}
		}
	}
}
