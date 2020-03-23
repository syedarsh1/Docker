package com.ericsson.controller;

import com.ericsson.MdmsSettingsMockApplication;
import com.google.gson.Gson;
import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class Controller {
    @RequestMapping(value = "/service/components/{component}/regions/{region}/settings",method = GET)
    public HashMap<String, ConfigItem> getConfig() {
        HashMap<String, ConfigItem> configuration = new HashMap<>();
	System.out.println("Hello");
        configuration.put("pmon.directory", new ConfigItem("String","/opt/monaco/adapters/tvgcm_adapter/bin"));
        configuration.put("pmon.start", new ConfigItem("String","tvgcm_adapter start"));
        configuration.put("pmon.stop", new ConfigItem("String", "tvgcm_adapter stop"));
        configuration.put("pmon.check", new ConfigItem("String", "tvgcm_adapter status"));
        configuration.put("pmon.check_response", new ConfigItem("String", "Running"));
        configuration.put("pmon.run_as", new ConfigItem("String", "monaco"));
        configuration.put("cps.apis", new ConfigItem("String", "{\"apis\" : [{\"status_api\":\"http://@host@/catalog_player_service/api/status\"},{\"offering_api\":\"http://@host@/catalog_player_service/api/offering\"},{\"stream_api\":\"http://@host@/catalog_player_service/api/stream\"},{\"catalog_api\":\"http://@host@/catalog_player_service/api/catalog\"},{\"ingest_api\":\"http://@host@/catalog_player_service/api/ingest\"},{\"cleanup_api\":\"http://@host@/catalog_player_service/api/cleanup/start\"},{\"barker_api\":\"http://@host@/catalog_player_service/api/barker/update\"}]}"));
        configuration.put("settings.poll.interval.seconds",
                  new ConfigItem("Integer", "60"));
        configuration.put("ingest.mode", new ConfigItem("String", "VSPP"));
        configuration.put("vspp.ingest.timeout.minutes",
                  new ConfigItem("Integer", "20"));
        configuration.put("vspp.asset.afterlife.days", new ConfigItem("Integer", "14"));
        configuration.put("db.host", new ConfigItem("String", "localhost"));
        configuration.put("db.port", new ConfigItem("Integer", "27017"));
        configuration.put("cps.catalog_players",new ConfigItem("String","{\"catalog_players\":"+
                "[{\"name\":\"Docker_Primary_Service\","+
                "\"host\":\""+ MdmsSettingsMockApplication.primaryIpPort+"\",\"mode\":\"primary\","+
                "\"peer_name\":\"Docker_Standby_Service\"},"+
                "{\"name\":\"Docker_Standby_Service\","+
                "\"host\":\""+MdmsSettingsMockApplication.standbyPort+"\",\"mode\":\"standby\","+
                "\"peer_name\":\"Docker_Primary_Service\"}]}"));

        //configuration.put("cps.catalog_players", new ConfigItem("String", ""));
        Gson gson=new Gson();
        String string = gson.toJson(configuration);
        System.out.println(string);
        return configuration;
    }

    @Setter
    @Getter
    @Data
    private class ConfigItem {
        private String type;
        private String value;

        public ConfigItem(String type,String value){
            this.value = value;
            this.type = type;
        }
    }
}
