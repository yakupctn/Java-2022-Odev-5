package kodlama.io.Kodlama.io.Devs.business.abstracts;

import java.util.List;

import kodlama.io.Kodlama.io.Devs.business.requests.CreateLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.DeleteLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.requests.UpdateLanguageRequest;
import kodlama.io.Kodlama.io.Devs.business.responses.GetAllLanguagesResponse;

public interface ILanguageService {
	List<GetAllLanguagesResponse> getAllLanguage();
	void addLanguage(CreateLanguageRequest createLanguageRequest) throws Exception;
	void updateLanguage(UpdateLanguageRequest updateLanguageRequest) throws Exception;
	void deleteLanguage(DeleteLanguageRequest deleteLanguageRequest);
	GetAllLanguagesResponse getLanguageById(int id);
}