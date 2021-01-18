package library;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MypageRepository extends CrudRepository<Mypage, Long> {

    List<> findByMemberId(Long memberId);
    List<> findByRentalId(Long rentalId);
    List<> findByMemberId(Long memberId);
    List<> findByRentalId(Long rentalId);
    List<> findByMemberId(Long memberId);
    List<> findByRentalId(Long rentalId);

}