package com.cloudNative._4.FinalProject.util;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



    public class WebUtils {

        /**
         * 将 JSON 字符串写入 HTTP 响应
         * @param response HTTP 响应
         * @param json JSON 字符串
         */
        public static void writeJson(HttpServletResponse response, String json) {
            response.setContentType("application/json; charset=utf-8");
            try (PrintWriter out = response.getWriter()) {
                out.print(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

