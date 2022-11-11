package kodlama.io.Kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.Kodlama.io.Devs.business.abstracts.ILanguageService;
import kodlama.io.Kodlama.io.Devs.business.requests.CreateLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.DeleteLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.UpdateLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.responses.GetAllLanguagesResponse;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {
	private ILanguageService iLanguageService;

	@Autowired
	public LanguagesController(ILanguageService iLanguageService) {
		super();
		this.iLanguageService = iLanguageService;
	}

	@GetMapping("/listall")
	public List<GetAllLanguagesResponse> getAllLanguage() {
		return iLanguageService.getAllLanguage();
	}
	
	@PostMapping("/add")
	public void addLanguage(@RequestBody CreateLanguageRequest createLanguageRequest) throws Exception {
		iLanguageService.addLanguage(createLanguageRequest);
	}
	
	@PutMapping("/update")
	public void updateLanguage(@RequestBody UpdateLanguageRequest updateLanguageRequest ) throws Exception{
		iLanguageService.updateLanguage(updateLanguageRequest);
	}
	
	@DeleteMapping("/delete")
	public void deleteLanguage(@RequestBody DeleteLanguageRequest deleteLanguageRequest) {
		iLanguageService.deleteLanguage(deleteLanguageRequest);
	}
	
	@GetMapping("/getbyid/{id}")
	public GetAllLanguagesResponse getLanguageById(@PathVariable("id") int id) {
		return iLanguageService.getLanguageById(id);
	}
	
	
}
