package vn.nvsoft.repository;
import org.springframework.data.repository.query.Param;
import vn.nvsoft.domain.ProIssue;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 * Spring Data  repository for the ProIssue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProIssueRepository extends JpaRepository<ProIssue, Long> {

    @Query(nativeQuery = true, value = "select * from pro_issue p where p.ngay_gio_ket_thuc is null or datediff(day, p.ngay_gio_bat_dau, :byDate)=0")
    List<ProIssue> findAllProIssueToDayAndInProgress(@Param("byDate") Date date);

}
