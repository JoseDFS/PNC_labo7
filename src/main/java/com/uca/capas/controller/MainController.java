package com.uca.capas.controller;


import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.EstudiantesDao;
import com.uca.capas.domain.Estudiante;
import com.uca.capas.service.EstudianteService;

@Controller
public class MainController {

	@Autowired
	private EstudianteService estudianteService;
	
	@RequestMapping("/insertarEstudiante")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante =new Estudiante();
		mav.addObject("estudiante",estudiante);
		mav.setViewName("agregarEstudiante");
		return mav;
	}
	
	@RequestMapping("/estudiante")
	public ModelAndView mostrarLista() {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes =null;
		try {
			estudiantes = estudianteService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		mav.addObject("estudiantes",estudiantes);
		mav.setViewName("main");
		for (Estudiante estudiante : estudiantes) {
			System.out.println(estudiante.getNombre());
		}
		return mav;
	}
	
	@RequestMapping(value = "/mostrarEstudiante", method = RequestMethod.POST)
	public ModelAndView findOne(@RequestParam(value = "codigo") int id) {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = null;
		try {
			estudiante = estudianteService.findOne(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("estudiante", estudiante);
		mav.setViewName("estudiante");
		
		return mav;
	
	}
	@RequestMapping(value = "/editarEstudiante", method = RequestMethod.POST)
	public ModelAndView findOneEdit(@RequestParam(value = "codigo") int id) {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = null;
		try {
			estudiante = estudianteService.findOne(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("estudiante", estudiante);
		mav.setViewName("agregarEstudiante");
		
		return mav;
	
	}
	@RequestMapping(value = "/borrarEstudiante", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam(value = "codigo") int id) {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes =null;
		try {
			 estudianteService.delete(id);
			 estudiantes = estudianteService.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("main");
		
		return mav;
	
	}
	
	@RequestMapping("/save")
	public ModelAndView procesar(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if(result.hasErrors()) {
			mv.setViewName("agregarEstudiante");
		}else {
			estudianteService.save(estudiante);
			List<Estudiante> estudiantes = null;
			try {
				estudiantes = estudianteService.findAll();
			}catch(Exception e){
				e.printStackTrace();
			}
			mv.addObject("estudiantes", estudiantes);
			mv.setViewName("agregarEstudiante");
		}
		return mv;
	}
	
	@PostMapping(value = "/Filtrar")
	public ModelAndView filtro(@RequestParam(value = "nombre") String nombre) {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try {
			//estudiantes = estudianteService.empiezaCon(nombre);
			estudiantes = estudianteService.filtarPor(nombre);
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("main");
		return mav;
	}
}
