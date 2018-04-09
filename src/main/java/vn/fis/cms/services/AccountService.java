package vn.fis.cms.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import vn.fis.cms.domain.*;

@Service
public interface AccountService {
	
	long GetCountUser();
	Page<User> GetListUser(int pageIndex, int pageSize);
}
