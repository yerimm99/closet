package com.ssp.closet.repository;

import com.ssp.closet.dto.LikeMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMarkRepository extends JpaRepository<LikeMark, Long> {
    // 추가적인 커스텀 메소드가 필요한 경우 여기에 선언할 수 있습니다.
}
