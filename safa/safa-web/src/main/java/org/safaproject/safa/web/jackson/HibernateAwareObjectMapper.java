package org.safaproject.safa.web.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
 
public class HibernateAwareObjectMapper extends ObjectMapper {
 
    public HibernateAwareObjectMapper() {
        Hibernate4Module hm = new Hibernate4Module();
        registerModule(hm);
    }
}
