package com.joao.dataprovider.strategy;

import com.joao.core.enumeration.VoteResultEnumeration;
import org.springframework.stereotype.Component;

@Component
public class VoteResultTiedStrategy implements VoteResultStrategy {
    @Override
    public boolean toAccept(final Long totalNo, final Long totalYes) {
        return totalNo.compareTo(totalYes) == 0;
    }

    @Override
    public VoteResultEnumeration getResult() {
        return VoteResultEnumeration.TIED;
    }
}