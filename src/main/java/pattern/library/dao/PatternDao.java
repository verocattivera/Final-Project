package pattern.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pattern.library.entity.Pattern;

public interface PatternDao extends JpaRepository<Pattern, Long> {

}
