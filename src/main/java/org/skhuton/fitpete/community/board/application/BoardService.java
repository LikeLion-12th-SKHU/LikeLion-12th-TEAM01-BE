package org.skhuton.fitpete.community.board.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.skhuton.fitpete.auth.global.util.GlobalUtil;
import org.skhuton.fitpete.community.board.api.dto.request.BoardSaveRequestDTO;
import org.skhuton.fitpete.community.board.api.dto.request.BoardUpdateRequestDTO;
import org.skhuton.fitpete.community.board.api.dto.response.BoardInfoResponseDTO;
import org.skhuton.fitpete.community.board.api.dto.response.BoardListResponseDTO;
import org.skhuton.fitpete.community.board.domain.Board;
import org.skhuton.fitpete.community.board.domain.BoardPicture;
import org.skhuton.fitpete.community.board.domain.BoardReport;
import org.skhuton.fitpete.community.board.domain.repository.BoardPictureRepository;
import org.skhuton.fitpete.community.board.domain.repository.BoardRecommendRepository;
import org.skhuton.fitpete.community.board.domain.repository.BoardReportRepository;
import org.skhuton.fitpete.community.board.domain.repository.BoardRepository;
import org.skhuton.fitpete.community.board.exception.NotAuthorizedToEditException;
import org.skhuton.fitpete.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final GlobalUtil globalUtil;
    private final BoardRepository boardRepository;
    private final BoardPictureRepository boardPictureRepository;
    private final BoardRecommendRepository boardRecommendRepository;
    private final BoardReportRepository boardReportRepository;

    @Transactional
    public Long boardSave(String email, BoardSaveRequestDTO boardSaveReqDto) {
        Member member = globalUtil.getMemberByEmail(email);
        Board board = boardSaveReqDto.toEntity(member);

        boardImageSave(board, boardSaveReqDto);
        Board saveBoard = boardRepository.save(board);

        return saveBoard.getBoardId();
    }

    private void boardImageSave(Board board, BoardSaveRequestDTO boardSaveRequestDTO) {
        for (String imageUrl : boardSaveRequestDTO.imageUrl()) {
            boardPictureRepository.save(BoardPicture.builder()
                    .board(board)
                    .imageUrl(imageUrl)
                    .build());
        }
    }

    // 커뮤니티 글 조회
    public BoardListResponseDTO categoryByBoardAll(String email, String category, Pageable pageable) {
        Member member = globalUtil.getMemberByEmail(email);

        Page<BoardInfoResponseDTO> byBoards;
        if (category.equals("ALL")) {
            byBoards = boardRepository.findByBoardAll(member, pageable);
        } else {
            byBoards = boardRepository.findByCategoryWithBoard(member, category, pageable);
        }

        return BoardListResponseDTO.from(byBoards);
    }

    // 커뮤니티 글 상세 조회
    public BoardInfoResponseDTO boardDetail(String email, Long boardId) {
        Member member = globalUtil.getMemberByEmail(email);
        Board board = globalUtil.getBoardById(boardId);

        boolean isLike = boardRecommendRepository.existsByBoardAndMember(board, member);

        Board commentAndBoardInfo = boardRepository.findByDetailBoard(board);

        return BoardInfoResponseDTO.detailOf(member, commentAndBoardInfo, isLike);
    }

    // 커뮤니티 글 삭제
    @Transactional
    public void boardDelete(String email, Long boardId) {
        Member member = globalUtil.getMemberByEmail(email);
        Board board = globalUtil.getBoardById(boardId);

        checkBoardOwnership(member, board);

        boardPictureRepository.deleteByBoardBoardId(boardId);
        boardRepository.delete(board);
    }

    // 커뮤니티 글 수정
    @Transactional
    public BoardInfoResponseDTO boardUpdate(String email, Long boardId, BoardUpdateRequestDTO boardUpdateRequestDTO) {
        Member member = globalUtil.getMemberByEmail(email);
        Board board = globalUtil.getBoardById(boardId);

        checkBoardOwnership(member, board);
        board.boardUpdate(boardUpdateRequestDTO);

        for (String url : boardUpdateRequestDTO.newImageUrl()) {
            boardPictureRepository.save(BoardPicture.builder()
                    .board(board)
                    .imageUrl(url)
                    .build());
        }

        boolean isRecommend = boardRecommendRepository.existsByBoardAndMember(board, member);

        return BoardInfoResponseDTO.detailOf(member, board, isRecommend);
    }

    private void checkBoardOwnership(Member member, Board board) {
        if (!member.getMemberId().equals(board.getWriter().getMemberId())) {
            throw new NotAuthorizedToEditException("커뮤니티 글 작성자만 수정 또는 삭제가 가능합니다.");
        }
    }

    // 커뮤니티 글 신고하기
    @Transactional
    public void boardReport(String email, Long boardId) {
        Member member = globalUtil.getMemberByEmail(email);
        Board board = globalUtil.getBoardById(boardId);


        BoardReport boardReport = BoardReport.builder()
                .member(member)
                .board(board)
                .build();

        board.updateBoardReportCount();
        boardReportRepository.save(boardReport);
    }

}