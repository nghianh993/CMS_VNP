package vn.fis.cms.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import vn.fis.cms.model.AccountModel;

@Service
public interface AccountService {
	
	long GetCountUser();
	Page<AccountModel> GetListUser(int pageIndex, int pageSize);
}
