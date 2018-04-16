package vn.fis.cms.services;

import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;
import vn.fis.cms.model.AccountModel;

@Service
public interface IAccountService {
	
	long GetCountUser();
	Page<AccountModel> GetListUser(int pageIndex, int pageSize);
	
	List<String> GetListPermissonByUserEMail(String email);
}
