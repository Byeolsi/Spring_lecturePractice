package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {

    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        /*
        String sql = "insert into member(name) values(?)";

        // 데이터베이스 커넥션(소켓)을 얻음.
        // Pseudo 코드
        Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, member.getName());
        ...
        pstmt.executeUpdate();
        // DB로 날아감.
        */

        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
