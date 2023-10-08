package com.dvicorp.mypassionauthserver.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class AuthDaoImpl implements  AuthDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthDaoImpl.class);

    public MyPassionUser getUserDetails(String username) {


        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();


        /*
        String userSQLQuery = "SELECT * FROM USERS WHERE USERNAME=?";
        List<MyPassionUser> list = jdbcTemplate.query(userSQLQuery, new String[] { username },
                (ResultSet rs, int rowNum) -> {

                    UserEntity user = new UserEntity();
                    user.setUsername(username);
                    user.setPassword(rs.getString("PASSWORD"));
                    return user;
                });
        if (list.size() > 0) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
            grantedAuthoritiesList.add(grantedAuthority);
            list.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
            return list.get(0);
        }
        return null;
        */
    }
}
