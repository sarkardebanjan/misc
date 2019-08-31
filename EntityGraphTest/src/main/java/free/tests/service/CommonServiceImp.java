package free.tests.service;

import free.tests.dao.CommonDao;
import free.tests.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImp implements CommonService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Country testEntityGraph(String countryCode) {
        return commonDao.testEntityGraph(countryCode);
    }

}
