package com.deloitte;
import com.DemoApplication;
import com.deloitte.broker.BigOpertaion;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.logging.Logger;

@RestController
public class MergeService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MergeService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/MergeNSplitService/merge", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public String mergeUsers(@RequestParam("fileIds") String fileIds,
                                    @RequestParam("accessToken") String accessToken,
                                    @RequestParam("instanceURL") String instanceURL,
                                    @RequestParam("useSoap")boolean useSoap) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        LOGGER.info("fileIds -> "+fileIds);
        LOGGER.info("accessToken -> "+accessToken);
        LOGGER.info("instanceURL -> "+instanceURL);
        LOGGER.info("useSoap -> "+useSoap);
        BigOpertaion bigOpertaion = new BigOpertaion();
        bigOpertaion.setFileIds(fileIds);
        bigOpertaion.setAccessToken(accessToken);
        bigOpertaion.setInstanceURL(instanceURL);
        bigOpertaion.setUseSoap(useSoap);
        rabbitTemplate.convertAndSend(DemoApplication.PDF_MERGE_QUEUE, bigOpertaion);
        MergeAndUploadPDF.mergeanduploadPDF(fileIds,accessToken,instanceURL,useSoap );
        return gson.toJson("Merge PDF SUCCESS");

    }

    @RequestMapping(value = "/MergeNSplitService/split", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public String splitPDFs(@RequestParam("file1Id") String file1Id,
                                   @RequestParam("parentId") String parentId,
                                   @RequestParam("accessToken") String accessToken,
                                   @RequestParam("instanceURL") String instanceURL,
                                   @RequestParam("useSoap")boolean useSoap) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        MergeAndUploadPDF.splitanduploadPDF(file1Id, parentId,accessToken,instanceURL,useSoap );
        return gson.toJson("SPLIT PDF SUCCESS");

    }
}
     
