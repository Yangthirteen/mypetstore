package org.csu.mypetstore.service;


import org.csu.mypetstore.persistence.SearchDAO;
import org.csu.mypetstore.persistence.impl.SearchDAOImpl;

public class SearchService {
    public SearchDAO searchDAO;

    public SearchService(){
        searchDAO=new SearchDAOImpl();
    }

    public String searchMessage(String keyword){
        return searchDAO.searchMessage(keyword);
    }
}




