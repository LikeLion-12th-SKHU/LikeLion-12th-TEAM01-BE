//package org.skhuton.fitpete.community.board.domain.repository;
//
//import org.skhuton.fitpete.community.board.domain.Board;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface BoardPictureRepository extends JpaRepository<BoardPicture, Long> {
//    void deleteByBoardBoardId(Long boardId);
//
//    void deleteByBoardAndImageUrlIn(Board board, List<String> urlsToDelete);
//}