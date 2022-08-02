package com.joao.dataprovider.client;

import com.joao.dataprovider.config.ClientConfiguration;
import com.joao.dataprovider.dto.AssociateValidOutDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient ( value = "UserInfoClient", url = "${user-info.url}", configuration = ClientConfiguration.class )
@Headers ( {"Content-Type: application/json; charset=utf-8", "Accept: application/json; charset=utf-8"} )
public interface AssociateInfoStatusClient {

    @GetMapping ( "/{cpf}" )
    AssociateValidOutDTO getInformationAssociateIsValid(@PathVariable ( "cpf" ) String cpf);
}
