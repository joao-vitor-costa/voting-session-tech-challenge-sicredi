package com.joao.dataprovider.strategy;

import com.joao.core.enumeration.VoteResultEnumeration;

public interface VoteResultStrategy {

    boolean toAccept(Long totalNo, Long totalYes);

    VoteResultEnumeration getResult();
}
