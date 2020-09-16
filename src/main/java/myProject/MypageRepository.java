package myProject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MypageRepository extends CrudRepository<Mypage, Long> {
	List<Mypage> findByStudentId(Long studentId);

}