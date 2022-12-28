package com.handson.chatbot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service

public class JokesEervice {
    public static final OkHttpClient client = new OkHttpClient().newBuilder()
            .build();

    @Autowired
    ObjectMapper om;
    public String getJokesValue(String keyword) throws IOException {

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.chucknorris.io/jokes/search?query=" + keyword)
                .method("GET", null)
                .addHeader("authority", "www.amazon.com")
                .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .addHeader("accept-language", "he-IL,he;q=0.9,en-US;q=0.8,en;q=0.7")
                .addHeader("cache-control", "max-age=0")
                .addHeader("cookie", "aws-ubid-main=512-4044335-0481831; aws-target-data=%7B%22support%22%3A%221%22%7D; regStatus=registered; session-id=138-0162980-7854026; session-id-time=2082787201l; i18n-prefs=USD; lc-main=he_IL; ubid-main=133-6247734-0240815; AMCV_7742037254C95E840A4C98A6%40AdobeOrg=1585540135%7CMCIDTS%7C19348%7CMCMID%7C67739888269032641830037019478844281460%7CMCAAMLH-1672236250%7C6%7CMCAAMB-1672236250%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1671638651s%7CNONE%7CMCAID%7CNONE%7CvVersion%7C4.4.0; aws-target-visitor-id=1671631450939-846148.37_0; aws-account-alias=995553441267; remember-account=true; awsc-color-theme=light; awsc-uh-opt-in=\"\"; noflush_awsccs_sid=5d99391e5e5992c92607b0df5f13e8f62ea0b791a0bdfb65ce193ce6549de260; aws-userInfo=%7B%22arn%22%3A%22arn%3Aaws%3Aiam%3A%3A995553441267%3Auser%2Fbracha%22%2C%22alias%22%3A%22995553441267%22%2C%22username%22%3A%22bracha%22%2C%22keybase%22%3A%22%22%2C%22issuer%22%3A%22http%3A%2F%2Fsignin.aws.amazon.com%2Fsignin%22%2C%22signinType%22%3A%22PUBLIC%22%7D; sp-cdn=\"L5Z9:DE\"; skin=noskin; session-token=\"cNiNyqOPB7GbkpcAyfp0ahPBogpqMonredB9zyBEven+oGMsPD9SAFW0DQFC+KmlQ+Dm9L24kC/9hO8iFb1zldPRxLo+7u9VtoI+qyK5voIpHsGUId3lYzco4UgxPWll9bcbVllVn8zuFHVHXUTXtqWAA2AGsiA81/OfyXlKCPuXCuqjsejhHtZ5+t7dPN4yDhx+i9+1VH5TzS5ENNT1zw0nrlXjIbHix4KxC5GM/2A=\"; csm-hit=tb:CFWNG0P4JETDVCPSFJ1Q+s-EQBY5YNAS43A91A9RSCM|1672063400085&t:1672063400085&adb:adblk_no")
                .addHeader("device-memory", "8")
                .addHeader("dnt", "1")
                .addHeader("downlink", "10")
                .addHeader("dpr", "1")
                .addHeader("ect", "4g")
                .addHeader("referer", "https://www.amazon.com/s?k=iphone&crid=1F8YTW4DVZ2W2&sprefix=iphone+%2Caps%2C139&ref=nb_sb_noss_2")
                .addHeader("rtt", "50")
                .addHeader("sec-ch-device-memory", "8")
                .addHeader("sec-ch-dpr", "1")
                .addHeader("sec-ch-ua", "\"Google Chrome\";v=\"107\", \"Chromium\";v=\"107\", \"Not=A?Brand\";v=\"24\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Linux\"")
                .addHeader("sec-ch-viewport-width", "634")
                .addHeader("sec-fetch-dest", "document")
                .addHeader("sec-fetch-mode", "navigate")
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("sec-fetch-user", "?1")
                .addHeader("upgrade-insecure-requests", "1")
                .addHeader("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .addHeader("viewport-width", "634")
                .build();
        Response response = client.newCall(request).execute();
        String res = response.body().string();
        JokesResponse jr = om.readValue(res,JokesResponse.class);
        if (jr.getResult() != null && jr.getResult().size() > 0) {
            return jr.getResult().get(0).getValue();
        }else {
            return "null";
        }


    }

    static class JokesResponse {
        List<JoketResult> result;

        public List<JoketResult> getResult() {
            return result;
        }
    }
    static class JoketResult {
        String value;

        public String getValue() {
            return value;
        }
    }
    }
