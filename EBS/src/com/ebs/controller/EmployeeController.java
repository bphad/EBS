package com.ebs.controller;

import java.util.ArrayList;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebs.beans.EHP;
import com.ebs.beans.EmpIncr;
import com.ebs.beans.Employee;
import com.ebs.beans.Project;

@Controller
public class EmployeeController {
	
	Employee employee;
	Project project;
	EHP ehp;
	
	private TreeSet<Employee> list_search = new TreeSet<>();
	private TreeSet<Project> list_searchp = new TreeSet<>();
	
	@Autowired
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Autowired
	public void setProject(Project project) {
		this.project = project;
	}
	
	@Autowired
	public void setEhp(EHP ehp) {
		this.ehp = ehp;
	}
	@RequestMapping(value="/AddEmployee",method=RequestMethod.POST)
	public String addEmployee(@ModelAttribute("emp") Employee e ,Model model, HttpServletRequest request){
		model.addAttribute("msg", "Employee " + e.getName()  +" is added to the System ");
		//Go to Account via Employee and insert record
		employee.addEmployee(e);
		ArrayList<Employee> emp_list = employee.getAllEmployees();
		ArrayList<Project> proj_list = project.getAllProjects();
		request.getSession().setAttribute("emp_list", emp_list);
		request.getSession().setAttribute("proj_list", proj_list);
		return "redirect:/employee";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/SearchEmployeeByName",method=RequestMethod.POST)
	public String searchEmployeeByName(Model model, HttpServletRequest request, @RequestParam(value="search") String search_name){
		//all the employees are already extracted in emp_list. 
				
		list_search.clear();
				ArrayList<Employee> elist = (ArrayList<Employee>)request.getSession().getAttribute("emp_list");
						for( Employee  e : elist){
							if(e.getName().contains(search_name)){
								list_search.add(e);
							}
						}
			model.addAttribute("search",search_name);	
			model.addAttribute("list_search",list_search);	
			return "employee_search";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="SearchEmployeeByEmail", method=RequestMethod.POST)
	public String searchEmployeeByEmail(Model model,HttpServletRequest request, @RequestParam(value="search") String search_email){
		//all the employees are already extracted in emp_list.
		list_search.clear();
			ArrayList<Employee> elist = (ArrayList<Employee>) request.getSession().getAttribute("emp_list");
			for(Employee e : elist) {
				if(e.getEmail().contains(search_email)) {
					list_search.add(e);
				}
			}
		model.addAttribute("search",search_email);
		model.addAttribute("list_search", list_search);
		return "employee_search";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="SearchEmployeeByProject", method=RequestMethod.POST)
	public String searchEmployeeByProject(Model model, @RequestParam(value="search") String search_by_proj, HttpServletRequest request){
		list_searchp.clear();
		ArrayList<EHP> list = new ArrayList<EHP>();
		ArrayList<Project> plist = (ArrayList<Project>) request.getSession().getAttribute("proj_list");
		for(Project p : plist ) {
			if(p.getName().contains(search_by_proj)) {
				list_searchp.add(p);
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("search", search_by_proj);
		model.addAttribute("list_searchp", list_searchp);
		return "employee_search";
	}
	
	@RequestMapping("show-emp-on-proj")
	public String showEmployeeOnProject(@RequestParam(value="pid") String pid,Model model){
		ArrayList<EHP> list = ehp.fetch(pid);
		model.addAttribute("list", list);
		return "employee_search_by_project";
	}
	
	@RequestMapping("AssignEmployeeToProject")
	public String assignEmployee(@RequestParam(value="eid") String eid,@RequestParam(value="pid") String pid,Model model){
		//assign
		employee.assignEmployee(eid,pid);
		model.addAttribute("emp",new Employee());
		model.addAttribute("msg","Employee Id :" + eid +" Assigned To Project Id :" + pid +" Successfully");
		return "employee";
	}
	
	@RequestMapping("UnAssignEmployeeToProject")
	public String unassignEmployee(@RequestParam(value="eid") String eid, @RequestParam("pid") String pid, Model model){
		//Unassign From Project
		employee.unassignEmployeeFromProject(eid, pid);
		model.addAttribute("emp", new Employee());
		model.addAttribute("msg", "Employee Id : " + eid + "Unassign From Project Id : " + pid);
		return "employee";
	}
	
	@RequestMapping("SendEmployeeMessage")
	public String sendMessageToEmployee(@RequestParam(value="eid") String eid, @RequestParam("msg") String msg, Model model){
		//Send Message
		//employee.sendMessage(eid, msg);
		model.addAttribute("emp", new Employee());
		return "employee";
	}
	
	@RequestMapping("showIncrementReport")
	public String showIncrementReport(@RequestParam(value="eid") String eid,Model model){
		//go to DB via Employee and get record of all increments of eid from salary_record table
		ArrayList<EmpIncr> list  = employee.getSalaryRecord(eid);
		model.addAttribute("list",list);
		return "increment_report";
	}
	
	@RequestMapping("IncrementSalary")
	public String incrementSalary(@RequestParam(value="eid") String eid,@RequestParam(value="increment_sal") String sal,Model model,HttpServletRequest request){
		//go to db via EMployee and update salary in employee and add record in salary_record using trigger
		employee.incrementSalary(eid,sal);
		model.addAttribute("msg", "Salary Updated");
		ArrayList<Employee> emp_list = employee.getAllEmployees();
		request.getSession().setAttribute("emp_list", emp_list);
		return "salary_record";
	}
}
