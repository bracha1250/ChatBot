package com.handson.chatbot.service;

import okhttp3.*;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service

public class AppliancesconnectionService extends BaseSearchService {
        public static final Pattern PRODUCT_PATTERN = Pattern.compile(
                        "<meta itemprop=\\\"name\\\" content=([^>]+)[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^>]+>[^$]+([^><]+)");

        public String search(String keyword) throws IOException {
                return parseArtistHtml(getAppliancesconnection((keyword)));
        }

        private String parseArtistHtml(String html) {
                String res = "";
                html = html.replaceAll("\r\n", " ").replaceAll("\n", " ");
                Matcher matcher = PRODUCT_PATTERN.matcher(html);

                while (matcher.find()) {
                        res += matcher.group(1) + ", price:" + matcher.group(2) + "<br>\n";
                }
                return res;
        }

        private String getAppliancesconnection(String keyword) throws IOException {
                OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody body = RequestBody.create(mediaType, "");
                Request request = new Request.Builder()
                                .url("https://www.appliancesconnection.com/" + keyword + ".html")
                                .method("GET", null)
                                .addHeader("authority", "www.appliancesconnection.com")
                                .addHeader("accept",
                                                "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                                .addHeader("accept-language", "he-IL,he;q=0.9,en-US;q=0.8,en;q=0.7")
                                .addHeader("cache-control", "max-age=0")
                                .addHeader("cookie",
                                                "PHPSESSID=4b2a0f4621a42dd060f5ba027acaff78; _gid=GA1.2.62677121.1673385501; _gcl_au=1.1.872800885.1673385501; prism_223917933=c786dece-1385-435c-aafa-dc99de3e2cf9; _ju_dm=cookie; _ju_dn=1; _pin_unauth=dWlkPU5EbG1abVkzTWpBdE5qbG1NaTAwTnpVM0xXRmlaRGN0Tm1Sa1ltRXpNbVl4WlRSaw; __attentive_id=607dd0a8b8684db39b77641a6fc4717e; __attentive_cco=1673385502441; _ju_dc=501276c0-912c-11ed-8a2a-997ac1cd38a5; _attn_=eyJ1Ijoie1wiY29cIjoxNjczMzg1NTAyNzk4LFwidW9cIjoxNjczMzg1NTAyNzk4LFwibWFcIjoyMTkwMCxcImluXCI6ZmFsc2UsXCJ2YWxcIjpcIjYwN2RkMGE4Yjg2ODRkYjM5Yjc3NjQxYTZmYzQ3MTdlXCJ9In0=; __attentive_dv=1; _gac_UA-16863495-1=1.1673389933.Cj0KCQiAtvSdBhD0ARIsAPf8oNnw-24_bsOM50_czZywhkpggk3oyHpJfTEB_kobdcldNkCb9hMi0ewaAljxEALw_wcB; _gcl_aw=GCL.1673389933.Cj0KCQiAtvSdBhD0ARIsAPf8oNnw-24_bsOM50_czZywhkpggk3oyHpJfTEB_kobdcldNkCb9hMi0ewaAljxEALw_wcB; ken_gclid=Cj0KCQiAtvSdBhD0ARIsAPf8oNnw-24_bsOM50_czZywhkpggk3oyHpJfTEB_kobdcldNkCb9hMi0ewaAljxEALw_wcB; tpc_a=15465c9b1b7e4483938902c4347e9008.1673385502.vAU.1673389936; _ga_N7T0NSTBGV=GS1.1.1673389933.2.1.1673390807.0.0.0; _ga=GA1.1.785478461.1673385501; _uetsid=4f308020912c11ed9a378d236d98082e; _uetvid=4f30de60912c11edb732b97ff76893a7; cto_bundle=lsujiV84YXNScHhMdFhsVSUyRnQyYTJxbGNndFIwSyUyRmR5MmhuMjFEVWZoJTJCSk91Mm1FSFRla1dVbVhnbmslMkZ6VGhCWGpNR3glMkZKdWlDRUdsdlpnT1lGcVkzWEE0Z3ZLMXRGTFRLbHdCb0tFQkFORXVPYVRYbFdIcUsxS0xGQk9ScDdGJTJCczMyOGtuakZnSjQ4QjJqYkNSMHlqQXVHZGlOWlB2VjclMkJVa0IlMkJrMmxQZld2dVdrJTNE")
                                .addHeader("dnt", "1")
                                .addHeader("if-modified-since", "Tue, 10 Jan 2023 22:32:34 GMT")
                                .addHeader("if-none-match", "72ce022ea865822201d77191cf6d4aea")
                                .addHeader("referer",
                                                "https://www.appliancesconnection.com/?gclid=Cj0KCQiAtvSdBhD0ARIsAPf8oNnw-24_bsOM50_czZywhkpggk3oyHpJfTEB_kobdcldNkCb9hMi0ewaAljxEALw_wcB")
                                .addHeader("sec-ch-ua",
                                                "\"Google Chrome\";v=\"107\", \"Chromium\";v=\"107\", \"Not=A?Brand\";v=\"24\"")
                                .addHeader("sec-ch-ua-mobile", "?0")
                                .addHeader("sec-ch-ua-platform", "\"Linux\"")
                                .addHeader("sec-fetch-dest", "document")
                                .addHeader("sec-fetch-mode", "navigate")
                                .addHeader("sec-fetch-site", "same-origin")
                                .addHeader("sec-fetch-user", "?1")
                                .addHeader("upgrade-insecure-requests", "1")
                                .addHeader("user-agent",
                                                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                                .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
        }
}
