package com.ssafy.api.coaching.controller;

import com.ssafy.api.coach.dto.request.CoachesRequestDto;
import com.ssafy.api.coaching.dto.response.CoachingDetailResponseDto;
import com.ssafy.api.coaching.dto.response.CoameListResponseDto;
import com.ssafy.api.coaching.service.CoachingService;
import com.ssafy.dto.ListDataDto;
import com.ssafy.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coachings")
@RequiredArgsConstructor
@Slf4j
public class CoachingController {
  private final CoachingService coachingService;

  /**
   * [coaching-6] 코미가 라이브 코칭을 수강 신청
   * privilege : 1
   *
   * @return [200] 정상 등록완료
   */
  @GetMapping("/live/{liveCoachingId}/{coameId}")
  public ResponseEntity<?> signUpClass(@PathVariable Long liveCoachingId, @PathVariable Long coameId) {

    coachingService.signUpClass(liveCoachingId, coameId);

    return new ResponseEntity<>(new MessageDto("sign up successfully !"), HttpStatus.OK);
  }

  /**
   * [coaching-7]
   * privilege : 2
   *
   * @return [200] 코미 id, 이름, 프로필사진 URL
   */
  @GetMapping("/live/{id}/coames")
  public ResponseEntity<?> getCoameList(@PathVariable Long id) {
    List<CoameListResponseDto> responseList = coachingService.getCoameList(id);

    return new ResponseEntity<>(new ListDataDto(responseList), HttpStatus.OK);
  }

  @GetMapping("/categories")
  public ResponseEntity<ListDataDto> CoachingRequestDto(CoachesRequestDto dto) {
    ListDataDto listDataDto = new ListDataDto(coachingService.getCoachingList(dto));
    return new ResponseEntity<>(listDataDto, HttpStatus.OK);
  }

  @GetMapping("/{coachingId}")
  public ResponseEntity<CoachingDetailResponseDto> getCoachingDetail(@PathVariable("coachingId") long coachingId) {
    CoachingDetailResponseDto responseDto = coachingService.getCoachingDetail(coachingId);

    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }
}
