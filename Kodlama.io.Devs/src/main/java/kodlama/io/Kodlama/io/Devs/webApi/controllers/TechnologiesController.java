package kodlama.io.Kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.Kodlama.io.Devs.business.abstracts.ITechnologyService;
import kodlama.io.Kodlama.io.Devs.business.requests.CreateTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.DeleteTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.UpdateLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.UpdateTechnologyRequest;
import kodlama.io.Kodlama.io.Devs.business.responses.GetAllTechnologiesResponse;

@RestController
@RequestMapping("/api/technologies")
public class TechnologiesController {
	private ITechnologyService iTechnologyService;

	@Autowired
	public TechnologiesController(ITechnologyService iTechnologyService) {
		super();
		this.iTechnologyService = iTechnologyService;
	}
	
	@GetMapping("/getallTechnology")
	public List<GetAllTechnologiesResponse> getAllTechnology(){
		return iTechnologyService.getAllTechnologies();
	}

	@PostMapping("/add")
	public void addTechnology(@RequestBody CreateTechnologyRequest createTechnologyRequest) throws Exception {
		iTechnologyService.addTechnology(createTechnologyRequest);
	}
	
	@PutMapping("/update")
	public void updateTechnology(@RequestBody UpdateTechnologyRequest updateTechnologyRequest) throws Exception {
		iTechnologyService.updateTechnology(updateTechnologyRequest);
	}
	
	@DeleteMapping("/delete")
	public void deleteTechnology(@RequestBody DeleteTechnologyRequest deleteTechnologyRequest) {
		iTechnologyService.deleteTechnology(deleteTechnologyRequest);
	}
	
}