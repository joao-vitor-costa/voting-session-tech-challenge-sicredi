package com.joao.dataprovider.client;

import com.joao.dataprovider.config.ClientConfiguration;
import com.joao.dataprovider.dto.AssociateOutDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient ( value = "AssociateCliente", url = "${associate.url}", configuration = ClientConfiguration.class )
@Headers ( {"Content-Type: application/json; charset=utf-8", "Accept: application/json; charset=utf-8"} )
public interface AssociateClient {

    @GetMapping ( "/{id}" )
    AssociateOutDTO getAssociate(@PathVariable ( "id" ) String id);
}
