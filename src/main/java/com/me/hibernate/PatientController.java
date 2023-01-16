package com.me.hibernate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.me.dao.PharmacyDAO;
import com.me.dao.UserDAO;
import com.me.dao.VaccineDAO;
import com.me.dao.VaccineRequestDAO;
import com.me.pojo.Hospital;
import com.me.pojo.Nurse;
import com.me.pojo.Patient;
import com.me.pojo.Pharmacy;
import com.me.pojo.User;
import com.me.pojo.Vaccine;
import com.me.pojo.VaccineRequest;

@Controller
public class PatientController {
	
	@RequestMapping(value = "/patientPageSchedule.htm", method = RequestMethod.POST)
	public ModelAndView patientPageSchedule(HttpServletRequest request, HttpServletResponse response, PatientDAO patientDao, HospitalDAO hospitalDao, VaccineDAO vaccineDao) {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			Patient patient = patientDao.getPatientByEmail(user.getEmail());
//			String[] parts = request.getParameter("hospital").split(":");
//			String selectedHospital = parts[parts.length-1];
			String selectedHospital = request.getParameter("hospital");
			session.setAttribute("selectedHospital", selectedHospital);
			Hospital hospital = hospitalDao.getHospitalByEmail(selectedHospital);
			List<Vaccine> vaccineList = vaccineDao.getAvailableVaccines(hospital.getIdhospital());
			session.setAttribute("vaccineList", vaccineList);
			if(patient == null) {
				Patient newPatient = new Patient();
				newPatient.setFirstDoseStatus("Pending");
				newPatient.setSecondDoseStatus("Pending");
				newPatient.setBoosterStatus("Pending");
				return new ModelAndView("scheduleVaccination","patient",newPatient);
			}else {
				return new ModelAndView("scheduleVaccination","patient",patient);
			}
		}else {
			System.out.println("user null");
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value = "/patientPageUpdate.htm", method = RequestMethod.POST)
	public ModelAndView patientPageUpdate(HttpServletRequest request, HttpServletResponse response, PatientDAO patientDao) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			Patient patient = patientDao.getPatientByEmail(user.getEmail());
			if(patient == null) {
				Patient newPatient = new Patient();
				return new ModelAndView("updatePatientProfile","patient",newPatient);
			}else {
				return new ModelAndView("updatePatientProfile","patient",patient);
			}
		}else {
			System.out.println("user null");
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value = "/scheduleVaccination.htm", method = RequestMethod.POST)
	public ModelAndView scheduleVaccination(HttpServletRequest request, HttpServletResponse response, PatientDAO patientDao, VaccineRequestDAO vaccineRequestDao) {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			Patient patient = patientDao.getPatientByEmail(user.getEmail());
			if(patient == null) {
				Patient newPatient = new Patient();
				newPatient.setAddress("");
				newPatient.setDob(null);
				newPatient.setEmail(user.getEmail());
//				String[] parts = request.getParameter("hospital").split(":");
//				String selectedHospital = parts[parts.length-1];
				VaccineRequest vr = new VaccineRequest();
				vr.setHospitalEmail((String) session.getAttribute("selectedHospital"));
				vr.setPatientEmail(user.getEmail());
				vr.setPharmacyEmail("");
				vr.setVaccine(request.getParameter("vaccine"));
				try {
					if(request.getParameter("firstDoseDate") != null) {
						newPatient.setFirstDoseDate(new SimpleDateFormat("MM/dd/yyy").parse(request.getParameter("firstDoseDate")));
					}else {
						newPatient.setFirstDoseDate(null);
					}
					if(request.getParameter("secondDoseDate") != null) {
						newPatient.setFirstDoseDate(new SimpleDateFormat("MM/dd/yyy").parse(request.getParameter("firstDoseDate")));
					}else {
						newPatient.setSecondDoseDate(null);
					}
					if(request.getParameter("boosterDate") != null) {
						newPatient.setFirstDoseDate(new SimpleDateFormat("MM/dd/yyy").parse(request.getParameter("firstDoseDate")));
					}else {
						newPatient.setBoosterDate(null);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				newPatient.setFirstDoseStatus("Scheduled");
				newPatient.setFirstDoseVaccine(request.getParameter("vaccine"));
				newPatient.setSecondDoseVaccine(request.getParameter(""));
				newPatient.setBoosterVaccine(request.getParameter(""));
				vr.setDose("First");
				vr.setStatus(newPatient.getFirstDoseStatus());
				vr.setDate(newPatient.getFirstDoseDate());
				newPatient.setSecondDoseStatus("Pending");
				newPatient.setBoosterStatus("Pending");
				patientDao.save(newPatient);
				vaccineRequestDao.save(vr);
				System.out.println("First Dose scheduled");
				return new ModelAndView("patientPage","patient",newPatient);
			}else {
				patient.setEmail(user.getEmail());
//				String[] parts = request.getParameter("hospital").split(":");
//				String selectedHospital = parts[parts.length-1];
				VaccineRequest vr = new VaccineRequest();
				vr.setHospitalEmail((String) session.getAttribute("selectedHospital"));
				vr.setPatientEmail(user.getEmail());
				vr.setPharmacyEmail("");
				vr.setVaccine(request.getParameter("vaccine"));
				try {
					if(request.getParameter("firstDoseDate") != null) {
						patient.setFirstDoseDate(new SimpleDateFormat("MM/dd/yyy").parse(request.getParameter("firstDoseDate")));
					}else {
						patient.setFirstDoseDate(null);
					}
					if(request.getParameter("secondDoseDate") != null) {
						patient.setFirstDoseDate(new SimpleDateFormat("MM/dd/yyy").parse(request.getParameter("firstDoseDate")));
					}else {
						patient.setSecondDoseDate(null);
					}
					if(request.getParameter("boosterDate") != null) {
						patient.setFirstDoseDate(new SimpleDateFormat("MM/dd/yyy").parse(request.getParameter("firstDoseDate")));
					}else {
						patient.setBoosterDate(null);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(patient.getFirstDoseStatus().equals("Pending")) {
					patient.setFirstDoseStatus("Scheduled");
					System.out.println("First Dose scheduled");
					vr.setDose("First");
					vr.setDate(patient.getFirstDoseDate());
					vr.setStatus(patient.getFirstDoseStatus());
					patient.setFirstDoseVaccine(request.getParameter("vaccine"));
				}
				else if(patient.getSecondDoseStatus().equals("Pending")) {
					patient.setSecondDoseStatus("Scheduled");
					System.out.println("Second Dose scheduled");
					vr.setDose("Second");
					vr.setDate(patient.getSecondDoseDate());
					vr.setStatus(patient.getSecondDoseStatus());
					patient.setSecondDoseVaccine(request.getParameter("vaccine"));
				}else {
					patient.setBoosterStatus("Scheduled");
					System.out.println("Booster scheduled");
					vr.setDose("Booster");
					vr.setDate(patient.getBoosterDate());
					vr.setStatus(patient.getBoosterStatus());
					patient.setBoosterVaccine(request.getParameter("vaccine"));
				}
				patientDao.save(patient);
				vaccineRequestDao.save(vr);
				return new ModelAndView("patientPage","patient",patient);
			}
		}else {
			System.out.println("user null");
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value = "/updatePatientProfile.htm", method = RequestMethod.POST)
	public ModelAndView updatePatientProfile(HttpServletRequest request, HttpServletResponse response, PatientDAO patientDao, UserDAO userDao) {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			System.out.println("user in updaste= "+user.getEmail());
			Patient patient = patientDao.getPatientByEmail(user.getEmail());
			System.out.println("patient in update= "+patient +patient.getEmail());
			if(patient == null) {
				System.out.println("coming in patient  null");
				Patient newPatient = new Patient();
				newPatient.setAddress(request.getParameter("address"));
				newPatient.setEmail(user.getEmail());
				try {
					newPatient.setDob(new SimpleDateFormat("MM/dd/yyy").parse(request.getParameter("dob")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				newPatient.setFirstDoseDate(null);
				newPatient.setSecondDoseDate(null);
				newPatient.setBoosterDate(null);
				newPatient.setFirstDoseStatus("Pending");
				newPatient.setSecondDoseStatus("Pending");
				newPatient.setBoosterStatus("Pending");
				user.setFirstName(request.getParameter("firstName"));
				user.setLastName(request.getParameter("lastName"));
				user.setPhone(Integer.parseInt(request.getParameter("contact")));
				userDao.save(user);
				patientDao.save(newPatient);
				System.out.println("Patient profile updated");
				return new ModelAndView("patientPage","patient",newPatient);
			}else {
				System.out.println("coming in patient not null");
				patient.setAddress(request.getParameter("address"));
				patient.setEmail(user.getEmail());
				try {
					patient.setDob(new SimpleDateFormat("MM/dd/yyy").parse(request.getParameter("dob")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				user.setFirstName(request.getParameter("firstName"));
				user.setLastName(request.getParameter("lastName"));
				user.setPhone(Integer.parseInt(request.getParameter("contact")));
				userDao.save(user);
				patientDao.save(patient);
				System.out.println("Patient profile updated");
				return new ModelAndView("patientPage","patient",patient);
			}
		}else {
			System.out.println("user null");
			return new ModelAndView("login");
		}
	}
	
}
