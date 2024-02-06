package com.ssafy.api.coaching.mapper;

import com.ssafy.api.coach.dto.response.CoachesCoachingsResponseDto;
import com.ssafy.api.coaching.dto.response.CoachDetail;
import com.ssafy.api.coaching.dto.response.CoachingDetailResponseDto;
import com.ssafy.api.coaching.dto.response.CoameListResponseDto;
import com.ssafy.api.coaching.dto.response.GetOneCoachingResponseDto;
import com.ssafy.db.entity.Coaching;
import com.ssafy.db.entity.CoameCoaching;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CoachingMapper {

  CoachingMapper instance = Mappers.getMapper(CoachingMapper.class);

  /**
   * Mapper 사용방법
   * source = "변경 전 클래스", target = "변경 후 클래스"
   * ex1) DTO -> Coaching | source : DTO, target : Coaching
   * ex2) Coaching -> DTO | source : Coaching, target : DTO
   * cf) 같은 이름의 필드는 명시안해도 알아서 번역
   */

  @Mapping(source = "coame.stringId", target = "stringId")
  @Mapping(source = "coame.name", target = "name")
  @Mapping(source = "coame.profileImage.url", target = "url")
  CoameListResponseDto coachingToCoameListResponseDto(CoameCoaching coameCoaching);

  List<CoameListResponseDto> coachingToCoameListResponseDto(List<CoameCoaching> coameCoachings);

  // [coaching-2] 코칭 조회 요청 시 해당 코칭을 반환한다.
  @Mapping(source = "mainCategory.name", target = "main")
  @Mapping(source = "subCategory.name", target = "sub")
  GetOneCoachingResponseDto coachingToGetOneCoachingResponseDto(Coaching coaching);

  @Mapping(source = "mainCategory.name", target = "main")
  @Mapping(source = "subCategory.name", target = "sub")
  CoachesCoachingsResponseDto coachingToCoachesCoachingsResponseDto(Coaching coaching);

  @Mapping(source = "coach.name", target = "coachName")
  @Mapping(source = "coach.profileImage.url", target = "coachProfileImageUrl")
  @Mapping(source = "name", target = "coachingName")
  CoachingDetailResponseDto coachingToCoachingDetailResponseDto(Coaching coaching);

  // [coaching-2] 코칭 수정 요청 시 해당 DTO를 코칭으로 변경한다.
  List<CoachesCoachingsResponseDto> coachingToCoachesCoachingsResponseDto(List<Coaching> coachingList);

  @Mapping(source = "name", target = "coachingName")
  @Mapping(source = "summary", target = "coachingSummary")
  @Mapping(source = "mainCategory.name", target = "coachingMainCategory")
  @Mapping(source = "subCategory.name", target = "coachingSubCategory")
  CoachDetail coachingToCoachDetail(Coaching coaching);

  List<CoachDetail> coachingToCoachDetailList(List<Coaching> list);


}
