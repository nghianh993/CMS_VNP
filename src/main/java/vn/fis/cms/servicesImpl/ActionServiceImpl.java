package vn.fis.cms.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.fis.cms.domain.Action;
import vn.fis.cms.repositories.ActionRepository;
import vn.fis.cms.services.IActionService;

@Service
public class ActionServiceImpl implements IActionService{

	@Autowired
	ActionRepository actionRepository;

	@Override
	public List<Action> GetListAction() {
		return actionRepository.findAll();
	}
}
