package ucl.comp0022.team2.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucl.comp0022.team2.dao.interfaces.SearchingReportDao;
import ucl.comp0022.team2.model.Report;
import ucl.comp0022.team2.service.interfaces.Case2Service;

import java.util.List;

@Service
public class Case2ServiceImpl implements Case2Service {
    SearchingReportDao searchingReportDao;

    @Override
    public List<Report> getReports(String movieId) {
        return searchingReportDao.getReport(Integer.parseInt(movieId));
    }

    @Autowired
    public void setSearchingReportDao(SearchingReportDao searchingReportDao) {
        this.searchingReportDao = searchingReportDao;
    }

}
