package com.srijan.springfundamentals.modules;

import com.srijan.springfundamentals.dto.Mail;
import com.srijan.springfundamentals.dto.Occassion;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Slf4j
@Service
public class EmailTemplateService {


    @Autowired
    private Configuration config;

    public String getTemplate(Mail mail , Occassion occassion) throws Exception {

        Template t = config.getTemplate(occassion.getTemplate());
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());
        log.debug("Mail Template: {}", html);
        return html;
    }

}
