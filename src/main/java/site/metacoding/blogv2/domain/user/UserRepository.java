package site.metacoding.blogv2.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    // 없는 것은 직접 만들기 (복잡한 것)
    @Query(value = "SELECT * FROM user WHERE username = :username AND password = :password", nativeQuery = true)
    User mLogin(@Param("username") String username, @Param("password") String password); // 영속성 컨텍스트에서 해줌
}
