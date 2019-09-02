package free.tests.dao;

import free.tests.entity.Country;

public interface CommonDao {

    Country selectUsingCriteriaApi(String countryCode);
}
