package com.joao.core.gateway;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.domain.VoteDomain;
import com.joao.core.domain.VoteResultDomain;

import java.util.List;

public interface VoteGateway {

    void save(VoteDomain voteDomain);

    List<VoteDomain> findByAgenda(AgendaDomain agendaDomain);

    VoteResultDomain getResult(AgendaDomain agendaDomain);
}
