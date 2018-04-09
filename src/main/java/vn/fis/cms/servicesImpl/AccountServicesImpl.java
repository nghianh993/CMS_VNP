package vn.fis.cms.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.fis.cms.model.AccountModel;
import vn.fis.cms.repositories.AccountRepository;
import vn.fis.cms.services.AccountService;

@Service
public class AccountServicesImpl implements AccountService{
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public long GetCountUser() {
		return accountRepository.count();
	}

	@Override
	public Page<AccountModel> GetListUser(int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		return accountRepository.findAllAccount(pageable);
	}

}
