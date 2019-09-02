package free.tests.service;

import free.tests.entity.Country;

public interface CommonService {

    Country selectUsingCriteriaApi(String countryCode);

}
