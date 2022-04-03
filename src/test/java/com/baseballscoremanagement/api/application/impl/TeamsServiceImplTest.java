package com.baseballscoremanagement.api.application.impl;

import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.repository.TeamsRepository;
import com.baseballscoremanagement.api.domain.sort.TeamSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TeamsServiceImplTest {

  @Mock
  private TeamsRepository teamsRepository;

  @InjectMocks
  private TeamsServiceImpl teamsServiceImpl;

  @Nested
  class getTeamList {
    @Test
    void 取得した結果を正常に返すことができること() {
      final var expectTeam = new Team(1, "テストチーム");
      Mockito.doReturn(Flux.just(expectTeam)).when(teamsRepository).getTeamList(TeamSort.DESC_GAMES, 1, 5);
      final var actualTeamList = teamsServiceImpl.getTeamList(TeamSort.DESC_GAMES, 1, 5);
      Assertions.assertEquals(List.of(expectTeam), actualTeamList);
      Mockito.verify(teamsRepository, Mockito.times(1)).getTeamList(TeamSort.DESC_GAMES, 1, 5);
    }
  }
}