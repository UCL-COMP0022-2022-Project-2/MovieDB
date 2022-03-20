package ucl.comp0022.team2.dao.interfaces;

import ucl.comp0022.team2.model.Report;

import java.util.List;

public interface SearchingReportDao {
    List<Report> getReport(int movieId);
}
